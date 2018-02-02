package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.caelum.dao.ProdutosDAO;
import br.com.caelum.modelo.Produto;


public class TestaInsercaoDAO {
	
	public static void main(String[] args) throws SQLException {
			
	Produto prod = new Produto("Mesa azul", "modelo em madeira");
	
	try (Connection con = new ConnectionPool().getConnection()){
		ProdutosDAO dao = new ProdutosDAO(con);
		dao.salva(prod);
		
		List<Produto> produtos = dao.lista();
			for (Produto produto : produtos) {
				System.out.println("Existe" + produto);
				
			
		}
		
		}
	
			
			
}
}
