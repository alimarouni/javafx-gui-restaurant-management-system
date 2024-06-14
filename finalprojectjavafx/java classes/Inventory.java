package com.example.finalprojectjavafx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Inventory {

    private int stock;
    public Inventory(int qty,int stock){

        this.stock=stock;
    }
    public Inventory(){

    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void AddStock(){
        GridPane p = new GridPane();
        Text t1 = new Text(" Stock Name:");
        TextField tx1 = new TextField();
        Text t2 = new Text("Stock Quantity:");
        TextField tx2 = new TextField();
        Text t3 = new Text("Stock Type:");
        TextField tx3 = new TextField();
        Text t4 = new Text("Expiry date:");
        TextField tx4 = new TextField();
        Button b = new Button("Add Stock");
        p.add(t1, 0, 0);
        p.add(tx1, 0, 1);
        p.add(t2, 0, 2);
        p.add(tx2, 0, 3);
        p.add(t3, 0, 4);
        p.add(tx3, 0, 5);
        p.add(t4, 0, 6);
        p.add(tx4, 0, 7);
        p.add(b, 0, 8);
        p.setAlignment(Pos.CENTER);

        p.setStyle("-fx-background-color: grey; -fx-padding: 20px;");
        t1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        t2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        t3.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx3.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        t4.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx4.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        b.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px; -fx-translate-x: 40;");
        p.setVgap(10);

        b.setOnAction(e -> {
            try {
                insertPerson(tx1.getText(), Integer.parseInt(tx2.getText()), tx3.getText(), tx4.getText());
                System.out.println("Stock  added!! ");

            } catch (NumberFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        Stage stage = new Stage();
        Scene s = new Scene(p, 400, 400);
        stage.setScene(s);
        stage.show();
    }
    public void RemoveStock(){
        Stage stage=new Stage();
        GridPane p = new GridPane();
        Text t1 = new Text("Stock Name:");
        TextField tx1 = new TextField();
        p.add(t1, 0, 0);
        p.add(tx1, 0, 1);
        Button b=new Button("Delete Stock");
        p.add(b,0,2);
        p.setAlignment(Pos.CENTER);

        p.setStyle("-fx-background-color: grey; -fx-padding: 20px;");
        t1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        b.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px; -fx-translate-x: 40;");
        p.setVgap(10);

        b.setOnAction(e->{
            try {
                deleteStock(tx1.getText());
                System.out.println("Stock  deleted!! ");

            } catch (NumberFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        Scene s=new Scene(p,400,400);
        stage.setScene(s);
        stage.show();
    }

    @Override
    public String toString() {
        return "Inventory{" +

                ", stock=" + stock +
                '}';
    }
    public void insertPerson(String name, int quantity, String type, String expirydate) throws SQLException {
        Mysql.Connect();
        PreparedStatement st = Mysql.con.prepareStatement("INSERT INTO project.inventory (name, quantity, type, expirydate) VALUES (?, ?, ?, ?)");
        st.setString(1, name);
        st.setInt(2, quantity);
        st.setString(3, type);
        st.setString(4, expirydate);
        st.executeUpdate();
        Mysql.Disconnect(); // Close the connection after use
    }
    public void deleteStock(String name) throws SQLException {
        Mysql.Connect();
        String sql = "DELETE FROM project.inventory WHERE name = ? ";
        try (PreparedStatement st = Mysql.con.prepareStatement(sql)) {
            st.setString(1, name);
            st.executeUpdate();
            Mysql.Disconnect();
        }
    }
}