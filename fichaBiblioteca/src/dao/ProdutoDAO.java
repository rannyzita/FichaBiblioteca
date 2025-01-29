package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Produto;

public class ProdutoDAO {
	
	// Create 
    public void inserirProduto(Produto produto, File imagem) {
    	String sql = "INSERT INTO PRODUTO (nome, preco, imagem, categoria) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement stmt = ConnectionFactory.getConexao().prepareStatement(sql); 
             FileInputStream fis = new FileInputStream(imagem)) {
            
            // Definindo os parâmetros para o produto
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setBinaryStream(3, fis);
            
            // Verifique se o ID da categoria foi atribuído corretamente antes de inserir
            if (produto.getCategoria() != null && produto.getCategoria().getId() != 0) {
                stmt.setInt(4, produto.getCategoria().getId());
            } else {
                throw new SQLException("Categoria não tem um ID válido.");
            }
            
            // Executa a inserção
            stmt.executeUpdate();
            
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    // Read
    public void lerProduto(int id) {
    	String sql = "SELECT id, nome, preco, imagem, categoria FROM produto WHERE id = ?";
    	Produto produto = null;
    	
    	try (PreparedStatement ps = ConnectionFactory.getConexao().prepareStatement(sql)) {
    	    ps.setInt(1, id); // Define o parâmetro ID na consulta

    	    try (ResultSet rs = ps.executeQuery()) {
    	        if (rs.next()) {
    	            produto = new Produto();
    	            produto.setId(rs.getInt("id"));
    	            produto.setNome(rs.getString("nome"));
    	            produto.setPreco(rs.getDouble("preco"));

    	            // Lendo o dado MEDIUMBLOB como Blob
    	            byte[] imagem = rs.getBytes("imagem");
    	            // MySQL converte MEDIUMBLOB para Blob
    	            if (imagem != null) {
    	                produto.setImagem(imagem); // Armazena como InputStream no Produto
    	            }
    	        }
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}
    }
    
    // Update
    public void atualizarProduto(Produto produto, File imagem) {
    	String sql = "UPDATE produto SET nome = ?, preco = ?, imagem = ?, categoria = ? WHERE id = ?";
    	
    	try (PreparedStatement stmt = ConnectionFactory.getConexao().prepareStatement(sql); 
    	         FileInputStream fis = new FileInputStream(imagem)) {
    	        
    	        // Definindo os parâmetros da consulta
    	        stmt.setString(1, produto.getNome());
    	        stmt.setDouble(2, produto.getPreco());
    	        stmt.setBinaryStream(3, fis);  // Para a imagem
    	        stmt.setInt(4, produto.getCategoria().getId());
    	        stmt.setInt(5, produto.getId());  // ID do produto que será atualizado
    	        
    	        // Executando a atualização
    	        stmt.executeUpdate();
    	        
    	    } catch (SQLException | IOException e) {
    	        e.printStackTrace();
    	    }
    }
    
    // Delete
    public void deletarProduto(int id) {
    	String sql = "DELETE FROM produto WHERE id = ?";
        
        try (PreparedStatement stmt = ConnectionFactory.getConexao().prepareStatement(sql)) {
            stmt.setInt(1, id);  // Define o ID do produto a ser deletado
            
            // Executa a exclusão
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
