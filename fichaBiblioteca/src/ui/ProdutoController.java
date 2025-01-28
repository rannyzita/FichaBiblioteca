package ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class ProdutoController implements Initializable {

	@FXML private TextField txtNome;
	@FXML private TextField txtPreco;
	@FXML private TextField txtCategoria;
	@FXML private Button btnUpload;
	@FXML private Button btnSalvar;
	@FXML private Button btnLimpar;
	
	@FXML 
	private void handleSalvar() {
        // Lógica de salvar produto
    }
	
	@FXML
	private void handleUpload() {
        // Lógica para selecionar imagem
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
