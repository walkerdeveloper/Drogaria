package br.com.drogaria.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.drogaria.util.HibernateUtil;

public class GenericDAO<Entidade> {

	// Classe que vai receber o tipo genérico que será listado... O hibernate tem
	// problemas em relação ao listar com tipos genéricos... ele precisa saber
	// o tipo que está sendo tratado(seu tipo class)
	// Para isso o uso da API reflect para ler tipo classe, métodos e atributos em
	// tempo de execução
	private Class<Entidade> classe;

	@SuppressWarnings("unchecked")
	public GenericDAO() {
		// Essa é a instrução que vei pegar o tipo classe do domain genérico que vai
		// estar sendo listado
		// O getActualTypeArguments()[0] pega justamento o argumento, ou seja, o tipo do
		// objeto que está sendo listado
		this.classe = this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			//Agora as instruções acima fazem sentido, como nao se pode usar o entidade.class com genericos, no momento que o tipo genérico que será
			//listado for instanciado, a variável classe vai receber seu tipo e lhe passar na consulta
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Entidade buscar(Long codigo) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			//Agora as instruções acima fazem sentido, como nao se pode usar o entidade.class com genericos, no momento que o tipo genérico que será
			//listado for instanciado, a variável classe vai receber seu tipo e lhe passar na consulta
			Criteria consulta = sessao.createCriteria(classe);
			consulta.add(Restrictions.idEq(codigo));
			Entidade resultado = (Entidade) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
	public void excluir(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(entidade);
			transacao.commit();
		} catch (RuntimeException erro) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
