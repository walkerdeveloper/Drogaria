package br.com.drogaria.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.domain.Estado;

public class EstadoDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio de Janeiro");
		estado.setSigla("RJ");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);

	}

	@Test
	@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		System.out.println("Total de registros encontrados: " + resultado.size());

		for (Estado estado : resultado) {
			System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 2L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		// Caso seja passado um código que não exista, gerará nullpointerexception, pois,
		// o resultado dessa consulta não retornará nada
		// e o objeto estará nulo

		if (estado == null) {
			System.out.println("Nenhum registro encontrado.");
		} else {
			
			System.out.println("Registro encontrado: ");
			System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
	
	@Test
	public void excluir() {
		
		Long codigo = 2L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		
		if (estado == null) {
			System.out.println("Nenhum registro encontrado.");
		} else {
			estadoDAO.excluir(estado);
			System.out.println("Registro removido!");
			System.out.println(estado.getId() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
		
		
	}
}
