/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysqlexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ryan Fadholi
 */
public class MysqlExample {
    private static String dbUri = "jdbc:mariadb://localhost:3306/user_demo?user=root";
    private static String getAllQuery = "SELECT * FROM user_details";    
    private static String searchUsernameQuery = "SELECT * FROM user_details "
            + "WHERE username LIKE ?";

    private static Connection contohDatabase = null;
    /**
     * @param args the command line arguments
     */
    private static void initConnection(){
        try {
            contohDatabase = DriverManager.getConnection(MysqlExample.dbUri);
            System.out.println("Connection to database has been established.");
        } catch (SQLException e) {
            System.out.println("Error in establishing connection to SQLite: " 
                    + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        
        MysqlExample.showAll();
        // TODO code application logic here
    }
    
    public static void showAll(){
        PreparedStatement stmt;       
        ResultSet rs;
        String currentUserId;
        String currentUsername;
        String currentFirstName;
        String currentLastName;
        String currentGender;
   
        if(MysqlExample.contohDatabase == null){
            MysqlExample.initConnection();
        }
        
        try{
            stmt = contohDatabase.prepareStatement(
                MysqlExample.getAllQuery);
            rs = stmt.executeQuery();
            while(rs.next()){
            //get the result
                currentUserId = rs.getString("user_id");
                currentUsername = rs.getString("username");
                currentFirstName = rs.getString("first_name");
                currentLastName = rs.getString("last_name");
                currentGender = rs.getString("gender");
    
                System.out.println("------------------");
                System.out.println(currentUserId + " - " + currentUsername);
                System.out.println(currentFirstName + " " + currentLastName 
                                   + ", " + currentGender);
            }
            //close connections
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error in matching word in database: " +
                    e.getMessage());
        }
    }
    
    public static void searchUsername(String username){
        PreparedStatement stmt;       
        ResultSet rs;
        String currentUserId;
        String currentUsername;
        String currentFirstName;
        String currentLastName;
        String currentGender;
        
        if(MysqlExample.contohDatabase == null){
            MysqlExample.initConnection();
        }
        
        try{
            stmt = contohDatabase.prepareStatement(
                MysqlExample.searchUsernameQuery);
            stmt.setString(1, "%" + username + "%");
            rs = stmt.executeQuery();
            System.out.println("Username that contains \"" + username + "\":");
            while(rs.next()){
            //get the result
                currentUserId = rs.getString("user_id");
                currentUsername = rs.getString("username");
                currentFirstName = rs.getString("first_name");
                currentLastName = rs.getString("last_name");
                currentGender = rs.getString("gender");
    
                System.out.println("------------------");
                System.out.println(currentUserId + " - " + currentUsername);
                System.out.println(currentFirstName + " " + currentLastName 
                                   + ", " + currentGender);
            }
            //close connections
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error in matching word in database: " +
                    e.getMessage());
        }
    }
    
}
