package bVendas.domain.bean;

import javax.faces.bean.ManagedBean;

import bVendas.domain.entity.Empresa;
import bVendas.domain.persistence.GenericDao;

@ManagedBean(name = "empresaBean")
public class EmpresaBean {

	private Empresa empresa = new Empresa();
	
	private GenericDao<Empresa> dao = new GenericDao<Empresa>();
	
	public String salvarEmpresa() {
		dao.criarEntidade(empresa);
		return "";
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public GenericDao<Empresa> getDao() {
		return dao;
	}
	public void setDao(GenericDao<Empresa> dao) {
		this.dao = dao;
	}	
}
