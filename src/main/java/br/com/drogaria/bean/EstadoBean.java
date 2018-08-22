package br.com.drogaria.bean;

import javax.faces.bean.ManagedBean;

import org.omnifaces.util.Messages;

@SuppressWarnings("deprecation")
@ManagedBean
public class EstadoBean {

	public void salvar(){
		
		Messages.addGlobalError("Programação web com Java");
	}
}
