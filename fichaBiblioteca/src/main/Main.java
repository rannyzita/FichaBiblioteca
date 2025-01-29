package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import model.Categoria;
import model.Produto;

public class Main extends Application {
	
	@Override
    public void start(Stage primaryStage) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/ui/Produto.fxml"));
            Scene cena = new Scene(p);
            primaryStage.setScene(cena);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	
    	launch(args);

        //Categoria categoria02 = new Categoria();
        //categoria02.setNome("sapato");

        // Instância do DAO para inserir a categoria
        //CategoriaDAO categoriaBlusa = new CategoriaDAO();
        
        // Inserir a categoria no banco (isso pode gerar um ID para a categoria)
        //categoriaBlusa.inserirCategoria(categoria02);
        
        //////////////////////////////////////////////////////////////////////////////
        // Agora a categoria01 já tem um ID válido
        //Produto produto = new Produto();
        //produto.setNome(nome);
        //produto.setPreco(preco);
        //produto.setCategoria(categoria);
        
        // Caminho correto da imagem
        //File imagem = new File("C:\\Users\\Ranny\\Desktop\\AmykCakes\\img\\b 01.png");
        
        // Instância do DAO para inserir o produto
        //ProdutoDAO produtoIF = new ProdutoDAO();
        
        // Inserir o produto no banco
        //produtoIF.inserirProduto(produto, imagem);
    }
}
