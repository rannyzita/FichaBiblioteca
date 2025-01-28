package ui;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Categoria;
import model.Produto;


public class ProdutoController implements Initializable {

	@FXML private TextField txtNome;
	@FXML private TextField txtPreco;
	@FXML private TextField txtCategoria;
	@FXML private Button btnUpload;
	@FXML private Button btnSalvar;
	@FXML private Button btnLimpar;
	@FXML private ImageView imgProduto;
	private byte[] imagemSelecionada;

	
	@FXML
    private void handleSave() {
        String nome = txtNome.getText();
        String precoTexto = txtPreco.getText();
        String categoriaTexto  = txtCategoria.getText();

        if (nome.isEmpty() || precoTexto.isEmpty() || categoriaTexto.isEmpty() || imagemSelecionada == null) {
            mostrarAlerta("Preencha todos os campos!", Alert.AlertType.WARNING);
            return;
        }

        try {
            double preco = Double.parseDouble(precoTexto);
            Categoria categoria = converterStringParaCategoria(categoriaTexto);
            Produto produto = new Produto(0, nome, preco, imagemSelecionada, categoria);
            produto.setNome(nome);
            produto.setPreco(preco);
            produto.setCategoria(categoria);
            produto.setImagem(imagemSelecionada);

            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.salvar(produto);

            mostrarAlerta("Produto salvo com sucesso!", Alert.AlertType.INFORMATION);
            handleClean();

        } catch (NumberFormatException e) {
            mostrarAlerta("Preço inválido!", Alert.AlertType.ERROR);
        }
    }
	
	private Categoria converterStringParaCategoria(String categoriaTexto) {
		// TODO Auto-generated method stub
		return null;
	}

	@FXML
    private void handleUpload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg")
        );
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                Path path = Paths.get(file.getAbsolutePath());
                imagemSelecionada = Files.readAllBytes(path);
                Image imagem = new Image(file.toURI().toString());
                imgProduto.setImage(imagem);
            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlerta("Erro ao carregar imagem", Alert.AlertType.ERROR);
            }
        }
    }

	@FXML
	private void handleClean() {
		txtNome.clear();
        txtPreco.clear();
        txtCategoria.clear();
        btnLimpar.setDisable(true);
	}
	
	public void onKeyreleased() {
        boolean limpar = txtNome.getText().isEmpty() && txtPreco.getText().isEmpty() && txtCategoria.getText().isEmpty();
        btnLimpar.setDisable(limpar);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		btnLimpar.setDisable(true);
	}

}
