package sql_islemleri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class sql_baglanti {
    
    final String KULLANICI_ADI="root";
    final String PASSWORD="";
    final String HOST="localhost";
    final String JDBC="jdbc:mysql://";
    final String DRIVER="com.mysql.jdbc.Driver"; 
    //final String DRIVER2="com.mysql.cj.jdbc.Driver"; 
    final Integer PORT=3306;
    
    String DBIsmi,baglantiURL;
    Connection baglanti;
    PreparedStatement komutTamamlayici;

    public sql_baglanti(String DBIsmi) {
        this.DBIsmi = DBIsmi;
        dbBaglantiKur();
    }
    
    public void dbBaglantiKur(){
        try {
            Class.forName(DRIVER);
            baglantiURL=JDBC+HOST+":"+PORT+"/"+DBIsmi;
            //baglantiURL:jdbc:mysql://localhost:3306/
            baglanti=(Connection) DriverManager.getConnection(baglantiURL,KULLANICI_ADI,PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(sql_baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
