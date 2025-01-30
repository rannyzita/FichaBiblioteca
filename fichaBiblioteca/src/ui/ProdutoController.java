package ui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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

    private File imagemFile;

    @FXML
    private void handleSave() {
        try {
            String nome = txtNome.getText();
            String preco = txtPreco.getText();
            String categoriaNome = txtCategoria.getText();

            if (nome.isEmpty() || preco.isEmpty() || categoriaNome.isEmpty()) {
                System.out.println("Por favor, preencha todos os campos.");
                return;
            }

            double precoDouble = Double.parseDouble(preco);

            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setPreco(precoDouble);

            Categoria categoria = new Categoria();
            categoria.setNome(categoriaNome);
            produto.setCategoria(categoria);

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.inserirProduto(produto, imagemFile);

            System.out.println("Produto salvo com sucesso.");
            handleClean();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg")
        );
        imagemFile = fileChooser.showOpenDialog(null);

        if (imagemFile != null) {
            imgProduto.setImage(new Image(imagemFile.toURI().toString()));
            System.out.println("Imagem carregada: " + imagemFile.getName());
        }
    }

    @FXML
    private void handleClean() {
        txtNome.clear();
        txtPreco.clear();
        txtCategoria.clear();
        imgProduto.setImage(null);
        imagemFile = null;
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
    public void initialize(URL url, ResourceBundle rb) {
        btnLimpar.setDisable(true);
    }
}
