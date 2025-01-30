package model;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private byte[] imagem;
    private Categoria categoria;
<<<<<<< HEAD
   
=======
	
>>>>>>> refs/heads/feature-yarlla
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void setImagem(Blob blob) {
        try {
            if (blob != null) {
                InputStream inputStream = blob.getBinaryStream();
                this.imagem = convertInputStreamToByteArray(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private byte[] convertInputStreamToByteArray(InputStream inputStream) {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}