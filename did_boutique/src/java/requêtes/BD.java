/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requêtes;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author USER
 */
public class BD {
    
     public static Connection maConnexion() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attestation?useSSL=false","root","");  
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/did_boutique", "root", "");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
