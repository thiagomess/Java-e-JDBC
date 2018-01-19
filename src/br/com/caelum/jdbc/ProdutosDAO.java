package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProdutosDAO {

	private final Connection con;

	public ProdutosDAO(Connection con) {
		this.con = con;
	}
	
	public void salva(Produto produto) throws SQLException {
		String sql = "insert into Produto(nome, descricao) values(?, ?)";
			try(PreparedStatement stmt = con.prepareStatement(sql, 
					Statement.RETURN_GENERATED_KEYS)){
				
				stmt.setString(1, produto.getNome());
				stmt.setString(2, produto.getDescricao());
				stmt.execute();
		
			try(ResultSet rs = stmt.getGeneratedKeys()){
				if(rs.next()) {
					int id = rs.getInt("id");
					produto.setId(id);
				}
			}
		}
		
	}

	
}
