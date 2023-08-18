package bVendas.domain.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import bVendas.domain.utils.FactoryDataBaseConnection;

public class GenericDao<T> {

	private EntityManager connection;
	private Class<T> entidade;

	
	public GenericDao(Class<T> entidade) {
		connection = FactoryDataBaseConnection.getEntityManager();
		this.entidade = entidade;
	}


	public GenericDao() {
		super();
		connection = FactoryDataBaseConnection.getEntityManager();
	}


	// métodos auxiliares para evitar repetição
	private GenericDao<T> beginTransaction() {
		connection.getTransaction().begin();
		return this;
	}

	private GenericDao<T> commitTransacion() {
		connection.getTransaction().commit();
		return this;
	}
	
	private GenericDao<T> remove(T entidade) {
		connection.remove(entidade);
		return this;
	}
	
	private GenericDao<T> merge(T entidade) {
		connection.merge(entidade);
		return this;
	}

	private GenericDao<T> persist(T entidade) {
		connection.persist(entidade);
		return this;
	}

	public void encerrarConexao() {
		connection.close();
	}

	// CRUD métodos
	public GenericDao<T> criarEntidade(T entidade) {
		return this.beginTransaction().persist(entidade).commitTransacion();
	}

	public T obterEntidadePorId(Class<T> classe, Long id) {
		return connection.find(classe, id);
	}

	public List<T> obterTodasAsEntidades() {
		return this.obterTodasAsEntidadesPaginado(20, 0);
	}

	public List<T> obterTodasAsEntidadesPaginado(int limit, int offSet) {
		if (entidade == null) {
			/*
			 * Não é possível consultar os objetos de uma classe se ela não for informada ao
			 * instanciar o DAO genário.
			 */
			throw new UnsupportedOperationException("Classe passada para o GenericDao nula.");
		}
		String jpql = "SELECT e FROM " + entidade.getName() + " e";
		TypedQuery<T> query = connection.createQuery(jpql, entidade);
		query.setMaxResults(limit);
		query.setFirstResult(offSet);

		return query.getResultList();
	}

	public GenericDao<T> atualizarEntidade(T entidade) {
		return this.beginTransaction().merge(entidade).commitTransacion();
	}

	public GenericDao<T> removerEntidade(T entidade) {
		return this.beginTransaction().remove(entidade).commitTransacion();
	}
}
