package Model.Acquisto;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class AcquistoDAO {

	private static final String TABLE_NAME = "Acquisto";
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
	 
	 public synchronized Collection<AcquistoBean> doRetrieveByKey(int key) throws SQLException {
	        Collection<AcquistoBean> acquistoKey = new ArrayList<>();
	        Connection connection= null;
	        PreparedStatement preparedStatement = null;
	        String query = "SELECT * FROM " + TABLE_NAME + " WHERE IDAcquisto = ?";

	        try {
	        	connection = ds.getConnection(); 
	        	preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, key);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            AcquistoBean acquistoBean = new AcquistoBean();
	            while (resultSet.next()) {
	                acquistoBean.setIDAcquisto(resultSet.getInt("IDAcquisto"));
	                acquistoBean.setIDOrdine(resultSet.getInt("IDOrdine"));
	                acquistoBean.setIDGioco(resultSet.getInt("IDGioco"));
	                acquistoBean.setQuantita(resultSet.getInt("quantita"));
	                acquistoBean.setImmagine(resultSet.getString("immagine"));
	                acquistoBean.setPrezzo(resultSet.getFloat("prezzo"));
	                acquistoBean.setIva(resultSet.getInt("iva"));
	                acquistoKey.add(acquistoBean);
	            }

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }
	        return acquistoKey;
	    }
	 
	 public synchronized Collection<AcquistoBean> doRetriveAll() throws SQLException {
	        Connection connection = null;
	        PreparedStatement preparedStatement = null;
	        Collection<AcquistoBean> acquisti = new ArrayList<>();

	        String query = new String("SELECT * FROM " + TABLE_NAME );

	        try {
	            connection = ds.getConnection();

	            preparedStatement = connection.prepareStatement(query);

	            ResultSet resultSet = preparedStatement.executeQuery();
	            AcquistoBean acquistoBean = new AcquistoBean();
	            while (resultSet.next()) {
	            	acquistoBean.setIDAcquisto(resultSet.getInt("IDAcquisto"));
	                acquistoBean.setIDOrdine(resultSet.getInt("IDOrdine"));
	                acquistoBean.setIDGioco(resultSet.getInt("IDGioco"));
	                acquistoBean.setQuantita(resultSet.getInt("quantita"));
	                acquistoBean.setImmagine(resultSet.getString("immagine"));
	                acquistoBean.setPrezzo(resultSet.getFloat("prezzo"));
	                acquistoBean.setIva(resultSet.getInt("iva"));
	                acquisti.add(acquistoBean);
	            }

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }

	        return acquisti;
	    }
	 
	 public synchronized void doSave(AcquistoBean acquisto) throws SQLException{
		 
		 Connection connection = null;
	     PreparedStatement preparedStatement = null;
	     
	     String query = new String("INSERT INTO"+ TABLE_NAME + "(IDOrdine,IDGioco,quantita,immagine,prezzo,iva) VALUES(?,?,?,?,?,?,?) ");
	     try {
	            connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setInt(1, acquisto.getIDOrdine());
	            preparedStatement.setInt(2,acquisto.getIDGioco());
	            preparedStatement.setInt(3, acquisto.getQuantita());
	            preparedStatement.setString(4, acquisto.getImmagine());
	            preparedStatement.setFloat(5, acquisto.getPrezzo());
	            preparedStatement.setInt(6, acquisto.getIva());

	            preparedStatement.executeUpdate();
	            

	        } finally {
	            if (preparedStatement!= null)
	                preparedStatement.close();
	            if (connection != null)
	                connection.close();
	        }
	 }
	 
	 public synchronized void doUpdate(AcquistoBean acquisto) throws SQLException {
		 
		 	Connection connection= null;
		 	PreparedStatement preparedStatement = null;
	        String query = "UPDATE " + TABLE_NAME +
	                       " SET IDOrdine=?,IDGioco=?,quantita=?,immagine=?,prezzo=?,iva=?" +
	                       "WHERE IDAcquisto=?";

	        try {
	            connection = ds.getConnection();
	            preparedStatement = connection.prepareStatement(query);
	            
	            preparedStatement.setInt(1, acquisto.getIDOrdine());
	            preparedStatement.setInt(2,acquisto.getIDGioco());
	            preparedStatement.setInt(3, acquisto.getQuantita());
	            preparedStatement.setString(4, acquisto.getImmagine());
	            preparedStatement.setFloat(5, acquisto.getPrezzo());
	            preparedStatement.setInt(6, acquisto.getIva());
	            preparedStatement.setInt(7, acquisto.getIDAcquisto());

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
	        String query = "DELETE FROM " + TABLE_NAME + " WHERE IDAcquisto = ?";

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