package logic;

import model.Produto;

public class ProdutoLogic {
	// SEI DE NADA NÃO 
	//  criar a lógica de negócios aqui - validações de dados
	
	// validação do nome  
	public void validarNome(Produto produto) {
		if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo!");
        }

		// verifica se o nome do produto é nulo, vazio ou só espaços e lança uma exceção se for inválido
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser nulo ou vazio!");
        }
        
        // verifica se o nome do produto contém apenas números
        if (produto.getNome().matches("[0-9]+")) {
            throw new IllegalArgumentException("O nome do produto não pode conter apenas números!");
        }
	}
	
	// validação do preço
	public void validarPreco(Produto produto) {
		if (produto == null) {
            throw new IllegalArgumentException("O produto não pode ser nulo!");
        }

        if (produto.getPreco() == 0.0) {
            throw new IllegalArgumentException("O preço do produto não pode ser zero!");
        }

        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo!");
        }
	}
	
}
