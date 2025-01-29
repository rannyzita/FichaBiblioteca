package main;

import java.io.File;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import model.Categoria;
import model.Produto;

public class Main {

    public static void main(String[] args) {

        Categoria categoria02 = new Categoria();
        categoria02.setNome("sapato");

        // Instância do DAO para inserir a categoria
        CategoriaDAO categoriaBlusa = new CategoriaDAO();
        
        // Inserir a categoria no banco (isso pode gerar um ID para a categoria)
        categoriaBlusa.inserirCategoria(categoria02);
        
        //////////////////////////////////////////////////////////////////////////////
        // Agora a categoria01 já tem um ID válido
        Produto produto = new Produto();
        produto.setNome("tenis nike");
        produto.setPreco(1000);
        produto.setCategoria(categoria02);
        
        // Caminho correto da imagem
        File imagem = new File("C:\\Users\\Ranny\\Desktop\\AmykCakes\\img\\b 01.png");
        
        // Instância do DAO para inserir o produto
        ProdutoDAO produtoIF = new ProdutoDAO();
        
        // Inserir o produto no banco
        produtoIF.inserirProduto(produto, imagem);
    }
}
