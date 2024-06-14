package com.example.finalprojectjavafx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Customer {
    private String Dinein;
    private int nb;

    private Order order;

    public Customer(String Dinein,int nb,Order order) {
        this.Dinein = Dinein;
        this.nb=nb;
        this.order=order;
    }
    public Customer() {

    }
    public void setDinein(String Dinein) {
        this.Dinein=Dinein;

    }
    public String getDinein() {
        return Dinein;
    }
    public void setNb(int nb) {
        this.nb = nb;
    }
    public int getNb() {
        return nb;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public Order getOrder() {
        return order;
    }
    public void type(){

        Text t=new Text("Choose One:");
        Button b=new Button("Delivery");
        Button b2=new Button("Dine in");


        VBox v=new VBox(10);
        v.getChildren().addAll(t,b,b2);
        v.setAlignment(Pos.CENTER);
        t.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-fill: #333333;");
        b.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");

        b2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        v.setStyle("-fx-background-color: grey; -fx-padding: 20px;");
        b.setOnAction(e->{
            Address c=new Address();
            c.createAdd();
        });
        b2.setOnAction(e->{
            Order o=new Order();
            o.getOrder();
        });
        Stage stage=new Stage();
        Scene s=new Scene(v,400,400);
        stage.setScene(s);
        stage.show();
    }
    @Override
    public String toString() {
        return "Customer{" +
                "Dinein=" + Dinein +
                ", nb=" + nb +
                ", order=" + order +
                '}';
    }

}