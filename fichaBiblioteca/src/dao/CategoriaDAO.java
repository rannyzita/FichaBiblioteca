package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Categoria;

public class CategoriaDAO {
	
	// create
	public void inserirCategoria (Categoria categoria) {
		String sql = "INSERT INTO CATEGORIA (nome) VALUES (?)";
	    
	    try (PreparedStatement stmt = ConnectionFactory.getConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	        stmt.setString(1, categoria.getNome());

	        // Executar a inserção
	        stmt.executeUpdate();
	        
	        // Recuperar o ID gerado
	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                categoria.setId(rs.getInt(1));  // Atualiza o objeto categoria com o ID gerado
	                System.out.println("Categoria inserida com ID: " + categoria.getId()); // Para depuração
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	// read
	public void lerCategoria (int id) {
		String sql = "SELECT id, nome FROM categoria WHERE id = ?";
		Categoria categoria = null;
		
		try (PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql)) {
    	    ps.setInt(1, id); // Define o parâmetro ID na consulta

    	    try (ResultSet rs = ps.executeQuery()) {
    	        if (rs.next()) {
    	            categoria = new Categoria();
    	            categoria.setId(rs.getInt("id"));
    	            categoria.setNome(rs.getString("nome"));
    	           
    	        }
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}
	}
	// update
	public void atualizarCategoria (Categoria categoria) {
		String sql = "UPDATE categoria SET nome = ? WHERE id = ?";
		
		try (PreparedStatement stmt = ConnectionFactory.getConexao().prepareStatement(sql);) {
   	        // Definindo os parâmetros da consulta
   	        stmt.setString(1, categoria.getNome());
   	        stmt.setInt(2, categoria.getId());  // ID do produto que será atualizado
   	        
   	        // Executando a atualização
   	        stmt.executeUpdate();
   	        
   	    } catch (SQLException e) {
   	        e.printStackTrace();
   	    }
	}
	// delete
	public void deletarCategoria(int id) {
		String sql = "DELETE FROM categoria WHERE id = ?";
		
		try (PreparedStatement stmt = ConnectionFactory.getConexao().prepareStatement(sql)) {
            stmt.setInt(1, id);  // Define o ID do produto a ser deletado
            
            // Executa a exclusão
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
}
