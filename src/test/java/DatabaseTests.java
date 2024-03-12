import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.sql.*;

public class DatabaseTests extends BaseTest {
    @Test
    public void LoginDatabaseTest() throws SQLException {
        LoginPage loginPage = new LoginPage(getDriver());
        //Make sure you add mariadb dependency in build.gradle
        String host = "104.237.13.60";
        String port = "3306";
        String dbUrl = "jdbc:mariadb://" + host + ":" + port + "/dbkoel";
        String username = "dbuser20";
        String password = "pa$$20";
        //Establish connection with Database
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        //create a Statemnet object to send SQL queries
        Statement stmt = con.createStatement();
        //Execute SQL queries using executeQuery method
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM dbkoel.users u WHERE email ='test@testpro.io'");
        while (resultSet.next()) {
            loginPage.provideEmail(resultSet.getString("email"));
            loginPage.providePassword(resultSet.getString("password"));
            loginPage.clickLogin();
            System.out.println(resultSet.getString("name"));
        }
    }
}

