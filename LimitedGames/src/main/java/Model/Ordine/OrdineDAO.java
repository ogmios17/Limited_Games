package Model.Ordine;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class OrdineDAO {

	private static final String TABLE_NAME = "Ordine";
    private static final DataSource ds;
	
	 static {
	        try {
	            Context init = new InitialContext();
	            Context env = (Context) init.lookup("java:comp/env");

	            ds = (DataSource) env.lookup("jdbc/LimitedGames");
	        } catch (NamingException e) {
	            throw new RuntimeException();
	        }
	    }
	 
	 public synchronized Collection<OrdineBean> doRetrieveByKey(int key) throws SQLException {
	        Collection<OrdineBean> ordineKey = new ArrayList<>();
	        Connection connection= null;
	        PreparedStatement preparedStatement = null;
	        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";

	        try {
	        	connection = ds.getConnection(); 
	        	preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, key);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            OrdineBean ordineBean = new OrdineBean();
	            while (resultSet.next()) {
	                ordineBean.setID(resultSet.getInt("ID"));
	                ordineBean.setUsername(resultSet.getString("username"));
	                ordineBean.setPrezzo(resultSet.getFloat("prezzo"));
	                ordineBean.setDataConsegna(resultSet.getDate("dataConsegna"));
	                ordineBean.setDataOrdine(resultSet.getDate("dataOrdine"));
	                ordineBean.setNome(resultSet.getString("nome"));
	                ordineBean.setCognome(resultSet.getString("cognome"));
	                ordineBean.setVia(resultSet.getString("via"));
	                ordineBean.setCitta(resultSet.getString("citta"));
	                ordineBean.setCAP(resultSet.getString("CAP"));
	                ordineKey.add(ordineBean);
	            }

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }
	        return ordineKey;
	    }
	 
	 public synchronized Collection<OrdineBean> doRetriveAll() throws SQLException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        Collection<OrdineBean> ordini = new ArrayList<>();

	        String query = new String("SELECT * FROM " + TABLE_NAME );

	        try {
	            connection = ds.getConnection();

	            preparedStatement = connection.prepareStatement(query);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            OrdineBean ordineBean = new OrdineBean();
	            while (resultSet.next()) {
	            	ordineBean.setID(resultSet.getInt("ID"));
	                ordineBean.setUsername(resultSet.getString("username"));
	                ordineBean.setPrezzo(resultSet.getFloat("prezzo"));
	                ordineBean.setDataConsegna(resultSet.getDate("dataConsegna"));
	                ordineBean.setDataOrdine(resultSet.getDate("dataOrdine"));
	                ordineBean.setNome(resultSet.getString("nome"));
	                ordineBean.setCognome(resultSet.getString("cognome"));
	                ordineBean.setVia(resultSet.getString("via"));
	                ordineBean.setCitta(resultSet.getString("citta"));
	                ordineBean.setCAP(resultSet.getString("CAP"));
	                ordini.add(ordineBean);
	            }

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }

	        return ordini;
	    }
	 
	 public synchronized void doSave(OrdineBean ordine) throws SQLException{
		 
		 Connection connection = null;
	     PreparedStatement preparedStatement = null;
	     
	     String query = new String("INSERT INTO"+ TABLE_NAME + "(username,prezzo,dataConsegna,dataOrdine,nome,cognome,via,citta,CAP) VALUES(?,?,?,?,?,?,?,?,?) ");
	     try {
	            connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setString(1, ordine.getUsername());
	            preparedStatement.setFloat(2, ordine.getPrezzo());
	            preparedStatement.setDate(3, ordine.getDataConsegna());
	            preparedStatement.setDate(4, ordine.getDataOrdine());
	            preparedStatement.setString(5, ordine.getNome());
	            preparedStatement.setString(6, ordine.getCognome());
	            preparedStatement.setString(7, ordine.getVia());
	            preparedStatement.setString(8, ordine.getCitta());
	            preparedStatement.setString(9, ordine.getCAP());

	            preparedStatement.executeUpdate();
	            

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }
	 }
	 
	 public synchronized void doUpdate(OrdineBean ordine) throws SQLException {
		 
		 	Connection connection= null;
		 	PreparedStatement preparedStatement = null;
	        String query = "UPDATE " + TABLE_NAME +
	                       " SET username=?,prezzo=?,dataConsegna=?,dataOrdine=?,nome=?,cognome=?,via=?,citta=?,CAP=?" +
	                       "WHERE ID=?";

	        try {
	            connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setString(1, ordine.getUsername());
	            preparedStatement.setFloat(2, ordine.getPrezzo());
	            preparedStatement.setDate(3, ordine.getDataConsegna());
	            preparedStatement.setDate(4, ordine.getDataOrdine());
	            preparedStatement.setString(5, ordine.getNome());
	            preparedStatement.setString(6, ordine.getCognome());
	            preparedStatement.setString(7, ordine.getVia());
	            preparedStatement.setString(8, ordine.getCitta());
	            preparedStatement.setString(9, ordine.getCAP());
	            preparedStatement.setInt(10, ordine.getID());

	            preparedStatement.executeUpdate();
	            

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }
	    }
	 
	 public synchronized void doDelete(Integer id) throws SQLException {

		 	Connection connection= null;
		 	PreparedStatement preparedStatement = null;
	        String query = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";

	        try{
	        	connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, id);

	        preparedStatement.executeUpdate();
	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }

	    }
	 
	 
}