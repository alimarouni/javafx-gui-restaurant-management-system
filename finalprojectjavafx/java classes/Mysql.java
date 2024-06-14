package com.example.finalprojectjavafx;

import java.sql.*;

import javafx.scene.control.Alert;

public class Mysql {

    public static Connection con;

    public static void Connect() {
        String connectionUrl = "jdbc:mysql://localhost:3306/project?useSSL=true&trustCertificateKeyStoreUrl=path/to/my_truststore.jks";
        try {
            con = DriverManager.getConnection(connectionUrl, "root", "12102004");
            System.out.println("Connected Successfully !!");
        } catch (SQLException e) {
            handleSQLException("Error connecting to the database", e);
        }
    }

    public static void Disconnect() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Disconnected Successfully !!");
            }
        } catch (SQLException e) {
            handleSQLException("Error disconnecting from the database", e);
        }
    }

    private static void handleSQLException(String message, SQLException e) {
        // Log the exception or display an alert
        System.err.println(message);
        e.printStackTrace();

        // You might want to use an alert to inform the user in a GUI application
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText(message);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }

    public static ResultSet Fetch(String TableName, boolean conditionedQuery, String Condition) {
        Connect();
        ResultSet rs=null;
        try {
            Statement st=con.createStatement();
            if(conditionedQuery) {
                System.out.println(Condition);
                rs= st.executeQuery("Select * From "+TableName+" Where "+Condition);
            }
            else
                rs= st.executeQuery("Select * From "+TableName);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //CloseConnection();
        return rs;
    }

    public static String Login(String email,String password) {
        String UserId=null;
        ResultSet rs=Fetch("project.login",true,"email='"+email+"' and Password='"+password+"'");
        try {
            if(rs.next()) {
                UserId=rs.getString("email");
                ShowAlert(rs.getString("email")+" "+rs.getString("password")+"is logged in Successfully!");
            }
            else {
                ShowAlert("User is not registered");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return UserId;
    }

    public static void register(String email, String password) throws SQLException {
        Mysql.Connect();

        PreparedStatement st = Mysql.con.prepareStatement("INSERT INTO project.login (email,password) VALUES (?,?)");
        st.setString(1, email);
        st.setString(2, password);

        st.executeUpdate();
        Mysql.Disconnect(); // Close the connection after use
    }

    public static void ShowAlert(String message) {
        Alert a=new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(message);
        a.show();
    }

}
