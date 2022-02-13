package br.com.agenda.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
	
	public List<Contato> getContatos() {
		
		String sql = "SELECT * FROM contatos";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		// Classe que vai recuperar os dados do banco. *** SELECT ***
		
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			//pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Contato contato = new Contato();
				
				// Recuperar o id
				
				contato.setId(rset.getInt("id"));
				
				// Recuperar o nome
				
				contato.setNome(rset.getString("nome"));
				
				// Recuperar a idade
				
				contato.setIdade(rset.getInt("idade"));
				
				// Recuperar a data de cadastro
				
				contato.setDataCastro(rset.getDate("dataCadastro"));
				
				contatos.add(contato);	
			}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rset != null) {
						rset.close();
					}
					
					if (pstm != null) {
						pstm.close();
					}
					
					if (conn != null) {
						conn.close();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
					}
				
			}
		
		return contatos;
		
	}

	public void update(Contato contato) {
		String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? " + 
		"WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// Criar conexão com o banco
			
			conn = ConnectionFactory.createConnectionToMySQL();
			
			// Criar a classe para executar a query
			
			pstm = conn.prepareStatement(sql);
			
			// Adicionar os valores para atualizar
			
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCastro().getTime()));
			
			// Qual o id do registro que deseja atualizar?
			
			pstm.setInt(4, contato.getId());
			
			// Executar a query
			
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			} finally {
				try {
					if (pstm != null) {
						pstm.close();
					}
					
					if (conn != null) {
						conn.close();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
	
}
