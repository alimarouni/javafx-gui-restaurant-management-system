package com.example.finalprojectjavafx;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Menu {
    private String plate;
    private String drink;
    private double plateprice;
    private double drinkprice;
    private Inventory inventory;

    public Menu(){

    }
    public Menu(String plate, String drink, double plateprice, double drinkprice) {
        this.plate = plate;
        this.drink = drink;
        this.plateprice = plateprice;
        this.drinkprice=drinkprice;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
    public String getPlate() {
        return plate;
    }
    public void setDrink(String drink) {
        this.drink = drink;
    }
    public String getDrink() {
        return drink;
    }
    public void setplplatePrice(double plateprice) {
        this.plateprice = plateprice;
    }
    public double getplatePrice() {
        return plateprice;
    }

    public void setpldrinkPrice(double drinkprice) {
        this.drinkprice = drinkprice;
    }
    public double getdrinkPrice() {
        return drinkprice;
    }
    public String toString() {
        return "Menu{" +
                "plate='" + plate + '\'' +
                ", drink='" + drink + '\'' +
                ", price=" + plateprice +
                '}';
    }
    public void addPlate(){
        GridPane p = new GridPane();
        Text t1 = new Text("plate name:");
        TextField tx1 = new TextField();
        Text t2 = new Text("plate price:");
        TextField tx2 = new TextField();
        Button b = new Button("Add plate");
        p.add(t1, 0, 0);
        p.add(tx1, 0, 1);
        p.add(t2, 0, 2);
        p.add(tx2, 0, 3);
        p.add(b, 0, 4);
        p.setAlignment(Pos.CENTER);

        // Apply styles directly in Java code
        p.setStyle("-fx-background-color: grey; -fx-padding: 20px;");
        t1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        t2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        b.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px; -fx-translate-x: 38;");
        p.setVgap(10);

        b.setOnAction(e -> {
            try {
                insertPlate(tx1.getText(), Double.parseDouble(tx2.getText()));
                System.out.println("plate is added!! ");

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
    public void removePlate (){
        GridPane p = new GridPane();
        Text t1 = new Text("plate name: ");
        TextField tx1 = new TextField();
        Button b = new Button("Delete");
        p.add(t1, 0, 0);
        p.add(tx1, 0, 1);
        p.add(b, 0, 3);
        p.setAlignment(Pos.CENTER);

        p.setStyle("-fx-background-color: grey; -fx-padding: 20px;");
        t1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        b.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px; -fx-translate-x: 38;");
        p.setVgap(10);

        b.setOnAction(e -> {
        });
        Text txt = new Text();
        b.setOnAction(e -> {
            try {
                deletePlate(tx1.getText());
                System.out.println("plate is deleted!! ");
            } catch (NumberFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        p.add(txt, 0, 5);
        Stage stage = new Stage();
        Scene s = new Scene(p, 400, 400);
        stage.setScene(s);
        stage.show();
    }
    public void addDrink(){
        GridPane p = new GridPane();
        Text t1 = new Text("drink name: ");
        TextField tx1 = new TextField();
        Text t2 = new Text("drink price:");
        TextField tx2 = new TextField();
        Button b = new Button("Add drink");
        p.add(t1, 0, 0);
        p.add(tx1, 0, 1);
        p.add(t2, 0, 2);
        p.add(tx2, 0, 3);
        p.add(b, 0, 4);
        p.setAlignment(Pos.CENTER);

        p.setStyle("-fx-background-color: grey; -fx-padding: 20px;");
        t1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        t2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        b.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px; -fx-translate-x: 38;");
        p.setVgap(10);

        b.setOnAction(e -> {
            try {
                insertDrink(tx1.getText(), Double.parseDouble(tx2.getText()));
                System.out.println("drink is added!! ");

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
    public void removeDrink (){
        GridPane p = new GridPane();
        Text t1 = new Text("drink name: ");
        TextField tx1 = new TextField();
        Button b = new Button("Delete");
        p.add(t1, 0, 0);
        p.add(tx1, 0, 1);
        p.add(b, 0, 3);
        p.setAlignment(Pos.CENTER);

        p.setStyle("-fx-background-color: grey; -fx-padding: 20px;");
        t1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        b.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px; -fx-translate-x: 38;");
        p.setVgap(10);

        b.setOnAction(e -> {
        });
        Text txt = new Text();
        b.setOnAction(e -> {
            try {
                deleteDrink(tx1.getText());
                System.out.println("drink is deleted!! ");
            } catch (NumberFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        p.add(txt, 0, 5);
        Stage stage = new Stage();
        Scene s = new Scene(p, 400, 400);
        stage.setScene(s);
        stage.show();
    }


    public void insertPlate(String plate, double plateprice) throws SQLException {
        Mysql.Connect();
        PreparedStatement st = Mysql.con.prepareStatement("INSERT INTO project.menu (plate,plateprice) VALUES (?, ?)");
        st.setString(1, plate);
        st.setDouble(2, plateprice);
        st.executeUpdate();
        Mysql.Disconnect(); // Close the connection after use
    }

    public void deletePlate(String plate) throws SQLException {
        Mysql.Connect();
        String sql = "DELETE FROM project.menu WHERE plate = ?";
        try (PreparedStatement st = Mysql.con.prepareStatement(sql)) {
            st.setString(1, plate);
            st.executeUpdate();
            Mysql.Disconnect();
        }
    }

    public void insertDrink(String drink, double drinkprice) throws SQLException {
        Mysql.Connect();
        PreparedStatement st = Mysql.con.prepareStatement("INSERT INTO project.menu (drink,drinkprice) VALUES (?, ?)");
        st.setString(1, drink);
        st.setDouble(2, drinkprice);
        st.executeUpdate();
        Mysql.Disconnect(); // Close the connection after use
    }

    public void deleteDrink(String drink) throws SQLException {
        Mysql.Connect();
        String sql = "DELETE FROM project.menu WHERE drink = ?";
        try (PreparedStatement st = Mysql.con.prepareStatement(sql)) {
            st.setString(1, drink);
            st.executeUpdate();
            Mysql.Disconnect();
        }
    }
}
