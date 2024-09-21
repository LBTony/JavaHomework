import java.sql.*;
import java.io.*;
import java.util.*;
public class Project1{
 public static void main(String[] args)  {
 Console console = System.console();
 boolean isValid = false;
 String Users = "";
 String password = "";
 do {
 Users = console.readLine("%s", "Account: ");
 password = new String(console.readPassword("%s", "Password: "));
 isValid = validateLogin(Users, password);

            if (isValid) {
                System.out.println("Login Success");
            } else {
                System.out.println("Login Fail");
            }

        } while (!isValid);
    }
    public static boolean validateLogin(String Users, String password) {
        String url = "jdbc:mysql://127.0.0.1:3306/loginn";
	String dbUser = "root"; 
        String dbPassword = "ch998815";  

        boolean isValid = false;

        String query = "select * from login where Users = ? and password = ?"; 

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, Users);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                                if (rs.next()) {
                    isValid = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValid;
    }
}