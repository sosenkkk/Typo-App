package com.example.typo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    static int c = 116;
    static final String DB_URL = "jdbc:mysql://localhost:3306/oops";
    static final String USER = "root";
    static final String PASS = "2022";

    public static void adduser(String username, String password) throws SQLException {
        String Query="insert into Users values(?,?)";
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt=conn.prepareStatement(Query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
    }
    public static void addusertorec(String username, String password) throws SQLException {

        String Query="insert into record values(?,?,?)";
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement stmt=conn.prepareStatement(Query);
        stmt.setInt(1,++c );
        stmt.setString(2, username);
        stmt.setString(3, password);
        stmt.executeUpdate();
    }
    public static ArrayList<String> display() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ArrayList<String> arr= new ArrayList<>();
        String Query="select * from users ;";
        ResultSet rs = stmt.executeQuery(Query);
        while (rs.next()){
            String u= rs.getString(1);
            arr.add(u);
            System.out.println(u);
        }
        return arr;
    }
    public static String displaybyID(int ID) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ArrayList<String> arr= new ArrayList<>();
        String Query="select * from record;";
        ResultSet rs = stmt.executeQuery(Query);
        while (rs.next()){
            int i = rs.getInt(1);
            String u= rs.getString(2);
            if(i==ID)
                return u;
        }
        return null;
    }
    public static boolean searchuser(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        String Query="select * from users where username='"+username+"';";
        ResultSet rs = stmt.executeQuery(Query);
        if(!rs.isBeforeFirst())
        {
            return false;
        }
        return  true;
    }
    public static boolean matchpass(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        String Query="select * from users where username='"+username+"'and password ='"+password+"';";
        ResultSet rs = stmt.executeQuery(Query);
        if(!rs.isBeforeFirst())
        {
            return false;
        }
        return  true;
    }
    public static void deleteuser(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        String Query="select * from users where username='"+username+"'and password ='"+password+"';";
        ResultSet rs = stmt.executeQuery(Query);
        if(rs.isBeforeFirst())
        {
            Query="delete from users where username='"+username+"'and password ='"+password+"';";
            stmt.executeUpdate(Query);
        }
    }
    public static void updateuser(String username, String password, String newpassword) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
        String Query="select * from users where username='"+username+"'and password ='"+password+"';";
        ResultSet rs = stmt.executeQuery(Query);
        if(rs.isBeforeFirst())
        {
            Query="UPDATE users set password ='"+newpassword+"' where username='"+username+"';";
            stmt.executeUpdate(Query);
        }
    }
    static public void addcsv(String filepath) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
            String lineText;
            lineReader.readLine();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values (?,?)");
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String user= data[0];
                String pass = data[1];
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, pass);
                preparedStatement.addBatch();
            }

            lineReader.close();
            preparedStatement.executeBatch();
            System.out.println("User data uploaded");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static public void updatecsv(String filepath) {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            BufferedReader lineReader = new BufferedReader(new FileReader(filepath));
            String lineText;
            lineReader.readLine();
            PreparedStatement preparedStatement = connection.prepareStatement("Update users set username=?, password=?");
            while ((lineText = lineReader.readLine()) != null) {
                String[] data = lineText.split(",");
                String user= data[0];
                String pass = data[1];
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, pass);
                preparedStatement.addBatch();
            }
            lineReader.close();
            preparedStatement.executeUpdate();
            System.out.println("User data updated");

        } catch (Exception e) {
            e.printStackTrace();
        }
        }

}


