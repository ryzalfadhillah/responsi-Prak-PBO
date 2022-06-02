/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.*;

/**
 *
 * @author Ryzal
 */

public class Koneksi {
    String DBurl = "jdbc:mysql://localhost/rentloyo";
    String DBusername = "root";
    String DBpassword = "";
    
    public Connection connection;
    public Statement statement;

    public Koneksi() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(DBurl,DBusername,DBpassword);
            System.out.println("Koneksi Berhasil");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Connection Failed\n" + ex.getMessage());
        }
    }       
}
