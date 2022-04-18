import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnect {
    Connection conn = null;
    public static Connection ConnectDB(){

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/sanctuary","root","");
            JOptionPane.showMessageDialog(null, "connection Establised");
            return conn;
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }
}
