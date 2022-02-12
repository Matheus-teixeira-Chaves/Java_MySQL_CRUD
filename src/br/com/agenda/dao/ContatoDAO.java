package br.com.agenda.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

//import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	/*
	 * CRUD
	 * 
	 * C: CREATE
	 * R: READ
	 * U: UPDATE
	 * D: DELETE
	 * */
	
	public void save(Contato contato) throws SQLException {
		
		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// Criar uma conexão com o banco de dados
			
			conn = ConnectionFactory.createConnectionToMySQL();
			
			// Criamos uma PreparedStatement, para executar uma query
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			// Adicionar os valores que são esperados pela query
			
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCastro().getTime()));
			
			// Executar a query
			
			pstm.execute();
			
			System.out.println("Contato Salvo Com Sucesso!");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			// Fechar as conexões
			
			if (pstm != null) {
				pstm.close();
			}
			
			if (conn != null) {
				conn.close();
			}
		} //catch (Exception e) {
			//e.printStackTrace();
		//}
	}
	
}
