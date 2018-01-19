package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {
	
/*	Isto é, a cada novo statement do tipo insert (update, delete e similares), o comando é comitado automaticamente para o servidor,
	não dá para voltar atrás. Este é o comportamento padrão de uma Connection segundo a especificação do JDBC. Mas e se eu quiser executar
	os dois inserts ou nada? Isto é, tudo ou nada. Ou executa os dois com sucesso ou nenhum. Nesse caso precisamos desativar o auto commit.
			Para isso fazemos logo após receber a conexão:*/
	
	public static void main(String[] args) throws SQLException {
		
		String sql = "insert into Produto(nome, descricao) values(?, ?)";		
		
		try(Connection connection = Database.getConnection()){
		connection.setAutoCommit(false);
		try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){		
		adiciona("Tv 55 polegadas", "full hd", statement);
		adiciona("Bicicleta aro 16'", "banco em couro", statement);
		adiciona("bluray", "bluray com 3 hdmi", statement);
		connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
			connection.rollback();
			System.out.println("rollback efetuado");
		}
	}
}

	private static void adiciona(String nome, String descricao, PreparedStatement statement) throws SQLException {
		if(nome.equals("bluray"))
			throw new IllegalArgumentException("Dado nao permitido");
		
		statement.setString(1, nome);
		statement.setString(2, descricao);
		
		boolean resultado = statement.execute();
		System.out.println(resultado);
		try(ResultSet resultSet = statement.getGeneratedKeys()){
		 
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id");
			System.out.println(id + " gerado");
			
			}
		}
	}
}
