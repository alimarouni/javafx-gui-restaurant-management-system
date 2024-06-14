package com.example.finalprojectjavafx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Worker {
    public Worker(){

    }
    public void manager() {
        VBox v=new VBox(10);
        v.setAlignment(Pos.CENTER);
        HBox h1=new HBox(5);
        h1.setAlignment(Pos.CENTER);
        Employee employee=new Employee();
        Button b1 = new Button("Add Employee");
        b1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        Button b2 = new Button("Remove Employee");
        b2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        Button b3 = new Button("Search Employee");
        b3.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        Button b4 = new Button("Modify Employee");
        b4.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        h1.getChildren().addAll(b1,b2,b3,b4);
        b1.setOnAction(e->{
            employee.addEmp();
        });
        b2.setOnAction(e->{
            employee.removeEmp();
        });
        b3.setOnAction(e->{
            employee.Searchemp();
        });
        b4.setOnAction(e->{
            employee.modifyEmp();
        });

        HBox h2=new HBox(5);
        h2.setAlignment(Pos.CENTER);
        Menu menu=new Menu();
        Button b5 = new Button("Add Plate");
        b5.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        Button b6 = new Button("Remove Plate");
        b6.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        Button b7 = new Button("Add drink");
        b7.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        Button b8 = new Button("Remove Drink");
        b8.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        h2.getChildren().addAll(b5,b6,b7,b8);
        b5.setOnAction(e->{
            menu.addPlate();
        });
        b6.setOnAction(e->{
            menu.removePlate();
        });
        b7.setOnAction(e->{
            menu.addDrink();
        });
        b8.setOnAction(e->{
            menu.removeDrink();
        });

        HBox h3=new HBox(5);
        h3.setAlignment(Pos.CENTER);
        Inventory inventory=new Inventory();
        Button b9 = new Button("Add Stock");
        b9.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        Button b10 = new Button("Remove Stock");
        b10.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        h3.getChildren().addAll(b9,b10);
        b9.setOnAction(e->{
            inventory.AddStock();
        });
        b10.setOnAction(e->{
            inventory.RemoveStock();
        });
        v.setStyle("-fx-background-color: grey; -fx-padding: 20px;");

        v.getChildren().addAll(h1,h2,h3);
        Scene s=new Scene(v,500,500);
        Stage stage =new Stage();
        stage.setScene(s);
        stage.show();
    }
    public void waiter(){
        VBox v=new VBox(10);
        Stage stage=new Stage();
        Inventory Inv=new Inventory();
        v.setStyle("-fx-background-color: grey; -fx-padding: 20px;");
        Button b1=new Button("Get Order");
        b1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        Button b2=new Button("Add to Stock");
        b2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 150px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");
        Button b3=new Button("Delete to Stock");
        b3.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 180px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px;");

        v.getChildren().addAll(b1,b2,b3);
        v.setAlignment(Pos.CENTER);
        b1.setOnAction(e->{
            Order o=new Order();
            o.getOrder();
        });
        b2.setOnAction(e->{
            Inv.AddStock();
        });
        b3.setOnAction(e->{
            Inv.RemoveStock();
        });
        Scene s=new Scene(v,400,400);
        stage.setScene(s);
        stage.show();
    }
}
