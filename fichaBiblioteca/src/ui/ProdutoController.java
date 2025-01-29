package ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;
import java.util.ResourceBundle;

import dao.ProdutoDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Produto;
import model.Categoria;

public class ProdutoController implements Initializable {

    @FXML private TextField txtNome;
    @FXML private TextField txtPreco;
    @FXML private TextField txtCategoria;
    @FXML private Button btnUpload;
    @FXML private Button btnSalvar;
    @FXML private Button btnLimpar;
    @FXML private ImageView imgProduto;

    private byte[] imagemBytes;

    @FXML
    private void handleSave() throws IOException {
        String nome = txtNome.getText();
        String preco = txtPreco.getText();
        String categoria = txtCategoria.getText();
        
        double precoDouble = Double.parseDouble(preco);
        
        Produto produto = new Produto();
        Categoria categoriaTeste = new Categoria();
        

        if (nome.isEmpty() || preco.isEmpty() || categoria.isEmpty()) {
            System.out.println("Por favor, preencha todos os campos.");
            return;
        }
        
        produto.setNome(nome);
        produto.setPreco(precoDouble);
        categoriaTeste.setNome(categoria);
        
        File imagem = handleUpload();
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.inserirProduto(produto, imagem);
      
        System.out.println("Produto salvo:");
        System.out.println("Nome: " + nome);
        System.out.println("Preço: " + preco);
        System.out.println("Categoria: " + categoria);
        if (imagemBytes != null) {
            System.out.println("Imagem carregada com sucesso.");
        } else {
            System.out.println("Sem imagem carregada.");
        }

        handleClean(); 
    }

    @FXML
    private File handleUpload() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(null);
        
        
        if (file != null) {
            return file;
			// Path path = Paths.get(file.getAbsolutePath());
			// imagemBytes = Files.readAllBytes(path);
			// Image image = new Image(file.toURI().toString());
			// imgProduto.setImage(image);
			// System.out.println("Imagem carregada: " + file.getName());
        }
		return file;
    }

    @FXML
    private void handleClean() {
        txtNome.clear();
        txtPreco.clear();
        txtCategoria.clear();
        imgProduto.setImage(null);
        imagemBytes = null;
        btnLimpar.setDisable(true);
    }

    @FXML
    public void onKeyreleased() {
        boolean limpar = txtNome.getText().isEmpty() && 
                         txtPreco.getText().isEmpty() && 
                         txtCategoria.getText().isEmpty();
        btnLimpar.setDisable(limpar);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        btnLimpar.setDisable(true);
    }
}
