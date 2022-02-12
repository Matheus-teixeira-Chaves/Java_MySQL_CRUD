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
		
		contatoDao.save(contato);
	}

}
