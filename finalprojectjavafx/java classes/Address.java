package com.example.finalprojectjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Address {
    private int nb;
    private Customer customer;
    private String street;
    private String CName;
    private String city;
    private String building;
    private int CphoneNumber;
    ComboBox<String> c = new ComboBox<>();
    private String[] cities = {"beirut", "dahye", "baabda", "chayeh", "hadath", "santarez"};

    public Address() {

    }

    public Address(Customer customer, String street, String city, String building) {
        this.customer = customer;
        this.street = street;
        this.city = city;
        this.building = building;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public void createAdd() {
        GridPane p=new GridPane();
        p.setAlignment(Pos.CENTER);
        p.setVgap(10);
        p.setStyle("-fx-background-color: grey; -fx-padding: 20px;");
        Label l = new Label("City: ");
        ObservableList<String> items = FXCollections.observableArrayList(cities);
        c.getItems().addAll(items);
        Label l2 = new Label("Street");
        TextField t = new TextField();
        Label l3 = new Label("Building");
        TextField t2 = new TextField();
        Label l4=new Label("phone number");
        TextField t3 = new TextField();
        Button b=new Button("Go To Order");
        c.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        b.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px; -fx-translate-x: 40;");
        l.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        t.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        l2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        t2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        l3.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        t3.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        l4.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        p.add(l,0,0);
        p.add(c,0,1);
        p.add(l2,0,2);
        p.add(t,0,3);
        p.add(l3,0,4);
        p.add(t2,0,5);
        p.add(l4,0,6);
        p.add(t3,0,7);
        p.add(b,0,8);

        Order order=new Order();
        b.setOnAction(e -> {
            try {
                insertAddress( nb,t.getText(), t2.getText(),Integer.parseInt(t3.getText()));
                System.out.println("Address created!! ");

            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            }
            order.getOrder();
        });

        Stage stage = new Stage();
        Scene s = new Scene(p, 400, 400);
        stage.setScene(s);
        stage.show();
    }


    @Override
    public String toString() {
        return "Address{" +
                "customer=" + customer +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", building='" + building + '\'' +
                '}';
    }

    public void insertAddress( int nb,String street, String building,int cphoneNumber) {
        try {
            Mysql.Connect();

            try (PreparedStatement st = Mysql.con.prepareStatement(
                    "INSERT INTO project.address (nb,city, street, building,phonenb) VALUES ( ? ,?, ?, ?,?)")) {
                st.setInt(1,nb);
                st.setString(2, c.getValue());
                st.setString(3, street);
                st.setString(4, building);
                st.setInt(5,cphoneNumber);
                st.executeUpdate();
            }
            System.out.println("Address created!!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Mysql.Disconnect();

        }
    }
}
