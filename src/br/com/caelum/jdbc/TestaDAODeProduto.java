package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TestaDAODeProduto {
	
	public static void main(String[] args) throws SQLException {
		
		Produto prod = new Produto("Geladeira", "Geladeira com 2 partas");
		
		try(Connection con  = new ConnectionPool().getConnection()){
			ProdutosDAO dao = new ProdutosDAO(con);
			dao.salva(prod);
			
			List<Produto> produtos = dao.lista();
			for (Produto produto : produtos) {
				System.out.println("Existe o " + produto);
			}
		}
		
	}

}
