package br.com.agenda.aplicacao;

import java.sql.SQLException;
import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		ContatoDAO contatoDao = new ContatoDAO();
		
		Contato contato = new Contato();
		contato.setNome("Matheus Teixeira");
		contato.setIdade(25);
		contato.setDataCastro(new Date());
		
		//contatoDao.save(contato);
		
		// ATUALIZAR CONTATO
		
		Contato c1 = new Contato();
		
		c1.setNome("Leticia Teixeira Chaves Frazão");
		c1.setIdade(24);
		c1.setDataCastro(new Date());
		c1.setId(3);	// É O NÚMERO QUE ESTÁ NO BANCO DE DADOS
		
		contatoDao.update(c1);
		
		// Visualição dos registros do banco de dados TODOS
		
		for (Contato c : contatoDao.getContatos()) {
			System.out.println("Contato: " + c.getNome());
		}
	}

}
