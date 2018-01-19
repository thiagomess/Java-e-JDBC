package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TestaInsercaoDeProdutos {
	
	public static void main(String[] args) throws SQLException {
		
		Produto produto = new Produto("Geladeira", "Geladeira com 2 partas");
		
		try(Connection con  = new ConnectionPool().getConnection()){
			ProdutosDAO dao = new ProdutosDAO(con);
		}
				
		
		
		
	}

}
