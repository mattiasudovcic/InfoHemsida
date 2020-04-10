/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informatikhemsida;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Diana Jumaili
 */
public class HamtaData {
    
    JTextField textruta1;
    JTextField textruta2;
    ArrayList <String> mejladdresser = new ArrayList();
    boolean match;
    boolean godkant;
    String connectionURL = "jdbc:sqlserver://localhost:53158;databaseName=Informatik;user=admin;password=team15";
    Connection con;
    
    public boolean check(String inmatadText) throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        this.con = DriverManager.getConnection(connectionURL);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("Select * from konto where mejladress = " + "'" + inmatadText + "'" );
        String mejl;
        
        while (rs.next()){
        String Name = rs.getString(2);
        mejl = Name;
        mejladdresser.add(mejl);
        }
        
        for(String data : mejladdresser) {
            if (data.equalsIgnoreCase(inmatadText)){
                match = true;
        }
        else {
                System.out.println("Det finns ingen sådan mejladress.");
                match = false;
        }
    }
                    return match;
    }
    
    public void skapaKonto(String mejladress, String losenord) throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        this.con = DriverManager.getConnection(connectionURL);
        Statement st = con.createStatement();
        st.execute("INSERT INTO KONTO (Mejladress, Lösenord, Notis, AdminFunktionalitet) VALUES"
        + "('" + mejladress + "','" + losenord + "', 1,1)");
        
        
    }
    
}
    
   
