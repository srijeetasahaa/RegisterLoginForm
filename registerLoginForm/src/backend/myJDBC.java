package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import constant.commonUse;

public class myJDBC {
    // we will first start with registering new users to our DB
    public static boolean register(String username, String password) {
        // to check if a username already exists in the DB a separate method is to be
        // created
        try {
            // the logic is that we will register only if the user is not found before
            if (!checkUserMethod(username)) {
                Connection con = DriverManager.getConnection(commonUse.DB_URL, commonUse.DB_USERNAME,
                        commonUse.DB_PASSWORD);

                // create insert query
                PreparedStatement insertUser = con.prepareStatement(
                        "insert into " + commonUse.DB_USER_TABLE + "(username,password)" + " values (?,?)");
                insertUser.setString(1, username);
                insertUser.setString(2, password);

                // update db with new user
                insertUser.executeUpdate();

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // check if username already exists
    // if false - user does not exist
    // if true - user already exists
    public static boolean checkUserMethod(String userName) {
        try {
            // JDBC server connection
            Connection con = DriverManager.getConnection(commonUse.DB_URL, commonUse.DB_USERNAME,
                    commonUse.DB_PASSWORD);

            PreparedStatement checkUserExist = con.prepareStatement(
                    "select * from " + commonUse.DB_USER_TABLE + " where username=?");

            // we will replace the "?" with values using the setString () method
            checkUserExist.setString(1, userName);

            // then we will store our result in a result set whoch we will be able to access
            ResultSet rs = checkUserExist.executeQuery();

            // check if the result set is empty
            // if it is empty then the username does not exist
            // we use isBeforeFirst () to point to the first row of the data that is
            // returned to our result set

            if (!rs.isBeforeFirst()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // if the user does not exits in the DB
        return true;
    }

    // validate user login
    // validate login credentials by checking to see if username/password pair
    // exists in the database
    public static boolean validateLogin(String username, String password) {
        try {
            Connection con = DriverManager.getConnection(commonUse.DB_URL, commonUse.DB_USERNAME,
                    commonUse.DB_PASSWORD);
            // create select query
            PreparedStatement validateUser = con
                    .prepareStatement("INSERT INTO " + commonUse.DB_USER_TABLE + " (username, password) VALUES (?, ?)");
            validateUser.setString(1, username);
            validateUser.setString(2, password);

            int rs = validateUser.executeUpdate ();

            if (rs>0) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // if it is true then there was a username of the same name
        return true;

    }

}
