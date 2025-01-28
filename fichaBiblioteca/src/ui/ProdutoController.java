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
	
	@FXML 
	private void handleSalvar() {
        // Lógica de salvar produto
    }
	
	@FXML
	private void handleUpload() {
        // Lógica para selecionar imagem
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
