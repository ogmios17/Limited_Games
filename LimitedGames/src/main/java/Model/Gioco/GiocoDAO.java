package Model.Gioco;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class GiocoDAO {

	private static final String TABLE_NAME = "Gioco";
    private static final DataSource ds;
    private static final List<String> ORDERS = new ArrayList<>(Arrays.asList( "prezzo", "ReleaseDate"));
	
	 static {
	        try {
	            Context init = new InitialContext();
	            Context env = (Context) init.lookup("java:comp/env");

	            ds = (DataSource) env.lookup("jdbc/LimitedGames");
	        } catch (NamingException e) {
	            throw new RuntimeException();
	        }
	    }
	 
	 public synchronized Collection<GiocoBean> doRetrieveByEdizione(String edizione) throws SQLException {
	        Collection<GiocoBean> giocoEdizione = new ArrayList<>();
	        Connection connection= null;
	        PreparedStatement preparedStatement= null;
	        String query = "SELECT * FROM " + TABLE_NAME + " WHERE edizione = ?";

	        try {
	        	connection = ds.getConnection(); 
	        	preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, edizione);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            GiocoBean giocoBean = new GiocoBean();
	            while (resultSet.next()) {
	                giocoBean.setId(resultSet.getInt("ID"));
	                giocoBean.setDescrizione(resultSet.getString("descrizione"));
	                giocoBean.setEdizione(resultSet.getString("edizione"));
	                giocoBean.setIva(resultSet.getInt("IVA"));
	                giocoBean.setPrezzo(resultSet.getFloat("prezzo"));
	                giocoBean.setTitolo(resultSet.getString("titolo"));
	                giocoBean.setTitolo(resultSet.getString("immagine"));
	                giocoBean.setReleaseDate(resultSet.getDate("ReleaseDate"));
	                giocoEdizione.add(giocoBean);
	            }

	        }finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }
	        return giocoEdizione;
	    }
	 
	 public synchronized Collection<GiocoBean> doRetrieveByKey(int key) throws SQLException {
	        Collection<GiocoBean> giocoKey = new ArrayList<>();
	        Connection connection= null;
	        PreparedStatement preparedStatement = null;
	        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";

	        try {
	        	connection = ds.getConnection(); 
	        	preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, key);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            GiocoBean giocoBean = new GiocoBean();
	            while (resultSet.next()) {
	                giocoBean.setId(resultSet.getInt("ID"));
	                giocoBean.setDescrizione(resultSet.getString("descrizione"));
	                giocoBean.setEdizione(resultSet.getString("edizione"));
	                giocoBean.setIva(resultSet.getInt("IVA"));
	                giocoBean.setPrezzo(resultSet.getFloat("prezzo"));
	                giocoBean.setTitolo(resultSet.getString("titolo"));
	                giocoBean.setTitolo(resultSet.getString("immagine"));
	                giocoBean.setReleaseDate(resultSet.getDate("ReleaseDate"));
	                giocoKey.add(giocoBean);
	            }

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }
	        return giocoKey;
	    }
	 
	 public synchronized Collection<GiocoBean> doRetriveAll(String order) throws SQLException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        Collection<GiocoBean> giochi = new ArrayList<>();

	        String query = new String("SELECT * FROM " + TABLE_NAME );

	        try {
	            connection = ds.getConnection();

	            for (String s: ORDERS)
	                if (s.equals(order)) {
	                    query+=" ORDER BY ";
	            		query+=s;
	                }

	            preparedStatement = connection.prepareStatement(query);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            GiocoBean giocoBean = new GiocoBean();
	            while (resultSet.next()) {
	            	giocoBean.setId(resultSet.getInt("ID"));
	                giocoBean.setDescrizione(resultSet.getString("descrizione"));
	                giocoBean.setEdizione(resultSet.getString("edizione"));
	                giocoBean.setIva(resultSet.getInt("IVA"));
	                giocoBean.setPrezzo(resultSet.getFloat("prezzo"));
	                giocoBean.setTitolo(resultSet.getString("titolo"));
	                giocoBean.setTitolo(resultSet.getString("immagine"));
	                giocoBean.setReleaseDate(resultSet.getDate("ReleaseDate"));
	                giochi.add(giocoBean);
	            }

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }

	        return giochi;
	    }
	 
	 public synchronized void doSave(GiocoBean gioco) throws SQLException{
		 
		 Connection connection = null;
	     PreparedStatement preparedStatement = null;
	     
	     String query = new String("INSERT INTO"+ TABLE_NAME + "(titolo,immagine,prezzo,descrizione,IVA,edizione,ReleaseDate) VALUES(?,?,?,?,?,?,?) ");
	     try {
	            connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setString(1, gioco.getTitolo());
	            preparedStatement.setString(2, gioco.getImmagine());
	            preparedStatement.setFloat(3,gioco.getPrezzo());
	            preparedStatement.setString(4, gioco.getDescrizione());
	            preparedStatement.setInt(5, gioco.getIva());
	            preparedStatement.setString(6, gioco.getEdizione());
	            preparedStatement.setDate(7, gioco.getReleaseDate());

	            preparedStatement.executeUpdate();
	            

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }
	 }
	 
	 public synchronized void doUpdate(GiocoBean gioco) throws SQLException {
		 
		 	Connection connection= null;
		 	PreparedStatement preparedStatement = null;
	        String query = "UPDATE " + TABLE_NAME +
	                       " SET titolo=?,immagine=?,prezzo=?,descrizione=?,IVA=?,edizione=?,ReleaseDate=?" +
	                       "WHERE ID=?";

	        try {
	            connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setString(1, gioco.getTitolo());
	            preparedStatement.setString(2, gioco.getImmagine());
	            preparedStatement.setFloat(3,gioco.getPrezzo());
	            preparedStatement.setString(4, gioco.getDescrizione());
	            preparedStatement.setInt(5, gioco.getIva());
	            preparedStatement.setString(6, gioco.getEdizione());
	            preparedStatement.setDate(7, gioco.getReleaseDate());
	            preparedStatement.setInt(8, gioco.getId());

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
