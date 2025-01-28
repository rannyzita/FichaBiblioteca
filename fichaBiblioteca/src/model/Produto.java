package model;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private byte[] imagem;
    private Categoria categoria;
    
	public Produto(int id, String nome, double preco, byte[] imagem, Categoria categoria) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.imagem = imagem;
		this.categoria = categoria;
	}
	
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
}