package br.com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Fabricante;

public class FabricanteDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("Fabricante do Nordeste");
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		fabricanteDAO.salvar(fabricante);
	}
	
	@Test
	@Ignore
	public void listar() {
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		List<Fabricante> resultado = fabricanteDAO.listar();
		
		System.out.println("Total de fabricantes: " + resultado.size());
		
		/*for(Fabricante fabricante : resultado) {
			
		}*/	
	}
	
	@Test
	//@Ignore
	public void buscar(){
		Long codigo = 2L;
		
		FabricanteDAO fabricanteDAO = new FabricanteDAO();
		Fabricante fabricante = fabricanteDAO.buscar(codigo);
		
		if(fabricante == null){
			System.out.println("Nenhum registro encontrado");
		}else{
			System.out.println("Registro encontrado:");
			System.out.println(fabricante.getId() + " - " + fabricante.getDescricao());
		}
	}
}
