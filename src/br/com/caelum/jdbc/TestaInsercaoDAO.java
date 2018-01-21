package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class TestaInsercaoDAO {
	
	public static void main(String[] args) throws SQLException {
			
	Produto prod = new Produto("Geladeira Brastemp", "2 portas frosFree");
	
	try (Connection con = new ConnectionPool().getConnection()){
		ProdutoDAO dao = new ProdutoDAO(con);
		dao.salva(prod);
		
		List<Produto> produtos = dao.lista();
			for (Produto produto : produtos) {
				System.out.println("Existe" + produto);
				
			
		}
		
		}
	
			
			
}
}
