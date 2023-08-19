package bVendas.controler;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bVendas.domain.model.Empresa;
import bVendas.domain.persistence.GenericDao;

@ManagedBean(name = "empresaBean")
@ViewScoped
public class EmpresaBean {

	private Empresa empresa = new Empresa();
	
	private GenericDao<Empresa> dao = new GenericDao<Empresa>(Empresa.class);
	
	private List<Empresa> empresas;
	
	public String salvarEmpresa() {
		dao.criarEntidade(empresa);
		return "";
	}
	
	public List<Empresa> getEmpresas() {
		if(empresas == null) {
			empresas = dao.obterTodasAsEntidades();
		}
		return empresas;
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
