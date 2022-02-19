package dbbroker;

import constants.MyServerConstants;
import java.sql.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import dbbroker.BrokerBazePodataka;
import domain.GeneralDObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BrokerBazePodataka_impl extends BrokerBazePodataka {

   Connection conn = null;
   String url="";
   String username="";
   String password="";
     
   @Override
    public boolean makeConnection() 
    {   readConfigProperties();
        try {
             //Class.forName(driver);
             conn = DriverManager.getConnection(url,username,password);
             conn.setAutoCommit(false); // Ako se ovo ne uradi nece moci da se radi roolback.
            } catch (SQLException ex) // | ClassNotFoundException ex) 
            {
            Logger.getLogger(BrokerBazePodataka_impl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
  
    
    void readConfigProperties()
    { Properties prop = new Properties();
      InputStream input = null;
      try { input = new FileInputStream("config/config.properties");
  	    prop.load(input);
            url = prop.getProperty(MyServerConstants.DB_CONFIG_URL);
            username = prop.getProperty(MyServerConstants.DB_CONFIG_USERNAME);
            password = prop.getProperty(MyServerConstants.DB_CONFIG_PASSWORD);
	  } catch (IOException ex){} 
            finally 
              { if (input != null) 
                 {  try { input.close();} catch (IOException e) {}
	         }
	      }
    }
    
   /* Connection conn = null;

    @Override
    public boolean makeConnection() {
        try {

            String url = "jdbc:mysql://localhost:3306/medicalis";
            String user = "root";
            String password = "";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Uspesna konekcija");
            conn.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka_impl.class.getName()).log(Level.SEVERE, null, ex);

            return false;
        }
        return true;
    }
*/
    @Override
    public boolean insertRecord(GeneralDObject odo) {
        String upit = "INSERT INTO " + odo.getClassName() + " VALUES (" + odo.getAtrValue() + ")";
        System.out.println(upit);
        return executeUpdate(upit);
    }

    @Override
    public boolean getCounter(GeneralDObject odo, AtomicInteger counter) {
        String sql = "SELECT Counter FROM Counter WHERE TableName = '" + odo.getClassName() + "'";

        ResultSet rs = null;
        Statement st = null;

        boolean signal = false; // record doesn't exist
        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery(sql);
            signal = rs.next();
            counter.set(rs.getInt("Counter") + 1);
        } catch (SQLException ex) {
            System.out.println("Greska u GetCounter");
            signal = false;
        } finally {
            close(null, st, rs);
        }
        return signal;

    }

    @Override
    public boolean increaseCounter(GeneralDObject odo, AtomicInteger counter) {
        String upit = "UPDATE Counter SET Counter =" + counter.get() + " WHERE TableName = '" + odo.getClassName() + "'";
        return executeUpdate(upit);
    }

    @Override
    public boolean deleteRecord(GeneralDObject odo) {
        String upit = "DELETE FROM " + odo.getClassName() + " WHERE " + odo.getWhereCondition();
        return executeUpdate(upit);
    }

    @Override
    public boolean updateRecord(GeneralDObject odo, GeneralDObject odoold) {
        String upit = "UPDATE " + odo.getClassName() + " SET " + odo.setAtrValue() + " WHERE " + odoold.getWhereCondition();
        return executeUpdate(upit);
    }

    @Override
    public boolean updateRecord(GeneralDObject odo) {
        String upit = "UPDATE " + odo.getClassName() + " SET " + odo.setAtrValue() + " WHERE " + odo.getWhereCondition();
        System.out.println("ATR VALUE: " + odo.setAtrValue());
        return executeUpdate(upit);
    }

    public boolean executeUpdate(String upit) {
        Statement st = null;
        boolean signal = false;
        try {
            st = conn.prepareStatement(upit);
            int rowcount = st.executeUpdate(upit);
            if (rowcount > 0) {
                signal = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka_impl.class.getName()).log(Level.SEVERE, null, ex);
            signal = false;
        } finally {
            close(null, st, null);
        }
        return signal;
    }

    @Override
    public GeneralDObject findRecord(GeneralDObject odo) {
        ResultSet rs = null;
        Statement st = null;
        String upit = "SELECT * FROM " + odo.getClassName() + " WHERE " + odo.getWhereCondition();
        boolean signal;
        try {
            st = conn.prepareStatement(upit);
            rs = st.executeQuery(upit);
            signal = rs.next();
            if (signal == true) {
                odo = odo.getNewRecord(rs);
            } else {
                odo = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka_impl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return odo;
    }

    @Override
    public GeneralDObject findRecord1(GeneralDObject odo) {
        ResultSet rs = null;
        Statement st = null;
        String upit = "SELECT * FROM " + odo.getClassName() + " WHERE " + odo.getWhereCondition1();
        boolean signal;
        try {
            st = conn.prepareStatement(upit);
            rs = st.executeQuery(upit);
            signal = rs.next();
            if (signal == true) {
                odo = odo.getNewRecord(rs);
                System.out.println("Odo: " + odo);
            } else {
                odo = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka_impl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return odo;
    }

    @Override
    public Long findMaxRecord(GeneralDObject odo) {
        ResultSet rs = null;
        Statement st = null;
        Long max = 0l;
        String upit = "SELECT MAX(" + odo.getFields() + ") FROM " + odo.getClassName();// + " WHERE " + odo.getWhereCondition();
        boolean signal;
        try {
            st = conn.prepareStatement(upit);
            rs = st.executeQuery(upit);
            signal = rs.next();

            if (signal == true) {
                max = rs.getLong("MAX(" + odo.getFields() + ")");

            } else {
                max = 0l;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka_impl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return max;
    }

    @Override
    public List<GeneralDObject> findAllRecords(GeneralDObject odo) {
        List<GeneralDObject> list = new ArrayList<>();
        ResultSet rs = null;
        Statement st = null;
        String upit = "SELECT * FROM " + odo.getClassName() + " WHERE " + odo.getWhereCondition();
        System.out.println("Upit: " + upit);
        boolean signal;
        try {
            st = conn.prepareStatement(upit);
            rs = st.executeQuery(upit);
            signal = rs.next(); // rs.next() vraca true ako ima postoji rezultat upita.
            while (signal == true) {
                odo = odo.getNewRecord(rs);
                list.add(odo);
                signal = rs.next();
                // } else {
                //   odo = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka_impl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return list;
    }

    public List<GeneralDObject> findAllRecords_NoCondition(GeneralDObject odo) {
        List<GeneralDObject> list = new ArrayList<>();
        ResultSet rs = null;
        Statement st = null;
        String upit = "SELECT * FROM " + odo.getClassName() + " ORDER BY " + odo.getOrderBy();
        boolean signal;
        try {
            st = conn.prepareStatement(upit);
            rs = st.executeQuery(upit);
            signal = rs.next(); // rs.next() vraca true ako ima postoji rezultat upita.
            while (signal == true) {
                odo = odo.getNewRecord(rs);
                list.add(odo);
                signal = rs.next();
                // } else {
                //   odo = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrokerBazePodataka_impl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(null, st, rs);
        }
        return list;
    }

    @Override
    public boolean commitTransation() {
        try {
            conn.commit();
        } catch (SQLException esql) {
            return false;
        }
        return true;
    }

    @Override
    public boolean rollbackTransation() {
        try {
            conn.rollback();
        } catch (SQLException esql) {
            return false;
        }

        return true;
    }

    @Override
    public void closeConnection() {
        close(conn, null, null);
    }

    public void close(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(BrokerBazePodataka_impl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(BrokerBazePodataka_impl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BrokerBazePodataka_impl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
