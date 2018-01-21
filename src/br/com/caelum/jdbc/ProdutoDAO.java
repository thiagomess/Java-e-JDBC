package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

	private Connection con;
	

	public ProdutoDAO(Connection con) {
		this.con = con;
		
	}
	
	public void salva(Produto produto) throws SQLException {
		String sql = "insert into Produto(nome, descricao) values(?,?)";
				
		try(PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, produto.nome);
			stmt.setString(2, produto.descricao);
			stmt.execute();
			
		try(ResultSet rs = stmt.getGeneratedKeys()){
				
			while(rs.next()) {
				int id = rs.getInt("id");
				produto.setId(id);
				}				
			}			
		}		
	}
	
	public List<Produto> lista() throws SQLException{
		List<Produto> produtos = new ArrayList<>();
		
		String sql = "select * from Produto";
		try(PreparedStatement stmt = con.prepareStatement(sql)){
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){
				while(rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					String descricao = rs.getString("descricao");
					Produto produto = new Produto(nome, descricao);
					produto.setId(id);
					produtos.add(produto);
		
		
			}
		}
			 	
				
		}			
		return produtos;
		
	}
}