package Model.Edizione;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class EdizioneDAO {

	private static final String TABLE_NAME = "Edizione";
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
	 
	 public synchronized Collection<EdizioneBean> doRetrieveByKey(int gioco, String piattaforma) throws SQLException {
	        Collection<EdizioneBean> edizioneKey = new ArrayList<>();
	        Connection connection= null;
	        PreparedStatement preparedStatement = null;
	        String query = "SELECT * FROM " + TABLE_NAME + " WHERE IDGioco = ? AND piattaforma = ?";

	        try {
	        	connection = ds.getConnection(); 
	        	preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, gioco);
	            preparedStatement.setString(2, piattaforma);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            EdizioneBean edizioneBean = new EdizioneBean();
	            while (resultSet.next()) {
	                edizioneBean.setIDGioco(resultSet.getInt("IDGioco"));
	                edizioneBean.setPiattaforma(resultSet.getString("piattaforma"));
	                edizioneBean.setQuantita(resultSet.getInt("quantita"));
	                edizioneKey.add(edizioneBean);
	            }

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }
	        return edizioneKey;
	    }
	 
	 public synchronized Collection<EdizioneBean> doRetriveAll() throws SQLException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        Collection<EdizioneBean> edizioni = new ArrayList<>();

	        String query = new String("SELECT * FROM " + TABLE_NAME );

	        try {
	            connection = ds.getConnection();

	            preparedStatement = connection.prepareStatement(query);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            EdizioneBean edizioneBean = new EdizioneBean();
	            while (resultSet.next()) {
	            	edizioneBean.setIDGioco(resultSet.getInt("IDGioco"));
	                edizioneBean.setPiattaforma(resultSet.getString("piattaforma"));
	                edizioneBean.setQuantita(resultSet.getInt("quantita"));
	                edizioni.add(edizioneBean);
	            }

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }

	        return edizioni;
	    }
	 
	 public synchronized void doSave(EdizioneBean edizione) throws SQLException{
		 
		 Connection connection = null;
	     PreparedStatement preparedStatement = null;
	     
	     String query = new String("INSERT INTO"+ TABLE_NAME + "(IDGioco,piattaforma,quantita) VALUES(?,?,?) ");
	     try {
	            connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setInt(1, edizione.getIDGioco());
	            preparedStatement.setString(2,edizione.getPiattaforma());
	            preparedStatement.setInt(3, edizione.getQuantita());
	            

	            preparedStatement.executeUpdate();
	            

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }
	 }
	 
	 public synchronized void doUpdate(EdizioneBean edizione) throws SQLException {
		 
		 	Connection connection= null;
		 	PreparedStatement preparedStatement = null;
	        String query = "UPDATE " + TABLE_NAME +
	                       " SET quantita=?" +
	                       "WHERE IDGioco=? AND piattaforma =?";

	        try {
	            connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setInt(1, edizione.getQuantita());
	            preparedStatement.setInt(2, edizione.getIDGioco());
	            preparedStatement.setString(3,edizione.getPiattaforma());

	            preparedStatement.executeUpdate();
	            

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }
	    }
	 
	 public synchronized void doDelete(Integer id, String piattaforma) throws SQLException {

		 	Connection connection= null;
		 	PreparedStatement preparedStatement = null;
	        String query = "DELETE FROM " + TABLE_NAME + " WHERE IDGioco = ? AND piattaforma = ?";

	        try{
	        	connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, id);
	            preparedStatement.setString(2, piattaforma);

	        preparedStatement.executeUpdate();
	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }

	    }
	 
	 
}