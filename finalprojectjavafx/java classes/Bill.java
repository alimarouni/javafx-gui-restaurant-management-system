package com.example.finalprojectjavafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Bill {
    private int Date;
    private int qty;
    private String plate;
    private String drink;

    private Order order;
    public Bill(){

    }
    public Bill(int date,int qty,String plate,String drink) {
       this.Date = date;
       this.qty=qty;
       this.plate=plate;
       this.drink=drink;
    }

    public void setDate(int date) {
        Date = date;
    }
    public int getDate() {
        return Date;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public int getQty() {
        return qty;
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
    void printBill() throws SQLException {
        Stage stage = new Stage();
        VBox v = new VBox(10);
        v.setAlignment(Pos.CENTER);
        Order order = new Order();
        Label l1 = new Label("BILL");
        Label l2 = new Label();
        Label l3 = new Label();
        Label l4 = new Label();
        Label l5 = new Label();
        Label l6 = new Label();
        Label l7 = new Label();
        Label l8 = new Label();

        ResultSet rs = Mysql.Fetch("project.order", true, "ordernb = (SELECT MAX(ordernb) FROM project.order)");
        String cname;
        String plate1 = null;
        String drink1 = null;
        int plateqty = 0;
        int drinkqty = 0;
        double platetotal = 0.0;
        double drinktotal = 0.0;
        double plateprice = 0.0;
        double drinkprice = 0.0;
        double totalprice;

        if (rs.next()) {
            cname = rs.getString(2);
            l3.setText("Customer Name: " + cname);

            plateqty = rs.getInt(5);
            plate1 = rs.getString(3);
            l4.setText("Plate: " + plateqty + " " + plate1);

            drinkqty = rs.getInt(6);
            drink1 = rs.getString(4);
            l5.setText("Drink: " + drinkqty + " " + drink1);
        }

        if (plate1 != null) {
            ResultSet rs2 = Mysql.Fetch("project.menu", true, "plate = '" + plate1 + "'");
            if (rs2.next()) {
                plateprice = rs2.getDouble(2);
                platetotal = plateqty * plateprice;
            }
        }

        if (drink1 != null) {
            ResultSet rs3 = Mysql.Fetch("project.menu", true, "drink = '" + drink1 + "'");
            if (rs3.next()) {
                drinkprice = rs3.getDouble(4);
                drinktotal = drinkqty * drinkprice;
            }
        }

        totalprice = platetotal + drinktotal;
        l6.setText("Plate Price: $" + String.valueOf(plateprice));
        l7.setText("Drink Price: $" + String.valueOf(drinkprice));
        l8.setText("Total Price: $" + String.valueOf(totalprice));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(formatter);
        l2.setText("Order Time: " + formattedTime);

        v.setStyle("-fx-background-color: #f4f4f4; -fx-padding: 20px;");
        l1.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        l2.setStyle("-fx-font-size: 16px; -fx-text-fill: #666666;");
        l3.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        l4.setStyle("-fx-font-size: 16px; -fx-text-fill: #666666;");
        l5.setStyle("-fx-font-size: 16px; -fx-text-fill: #666666;");
        l6.setStyle("-fx-font-size: 16px; -fx-text-fill: #666666;");
        l7.setStyle("-fx-font-size: 16px; -fx-text-fill: #666666;");
        l8.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        v.getChildren().addAll(l1, l2, l3, l4, l5, l6, l7, l8);
        Scene scene = new Scene(v, 400, 300);
        stage.setScene(scene);
        stage.show();
    }






    @Override
    public String toString() {
        return "Bill{" +
                "Date=" + Date +
                ", qty=" + qty +
                ", plate='" + plate + '\'' +
                ", drink='" + drink + '\'' +
                '}';
    }
}
