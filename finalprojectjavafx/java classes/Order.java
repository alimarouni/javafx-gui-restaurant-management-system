package com.example.finalprojectjavafx;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Order {



    private String plate;
    private int plateqty;
    private int drinkqty;
    private String drink;
    private int nbCostumers;
    private Bill bill;
    private int nb;

    private String cname;

    private static ObservableList<Map<String, Object>> menuData = FXCollections.observableArrayList();

    public Order(){

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

    public void setNbCostumers(int nbCostumers) {
        this.nbCostumers = nbCostumers;
    }

    public int getNbCostumers() {
        return nbCostumers;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Bill getBill() {
        return bill;
    }


    void getOrder(){
        TableView<Map<String, Object>> tableView = new TableView<>();

        TableColumn<Map<String, Object>, Object> column1 = new TableColumn<>("plate");
        TableColumn<Map<String, Object>, Object> column2 = new TableColumn<>("drink");
        TableColumn<Map<String, Object>, Object> column3 = new TableColumn<>("plateprice");
        TableColumn<Map<String, Object>, Object> column4 = new TableColumn<>("drinkprice");

        tableView.getColumns().addAll(column1, column2, column3, column4);

        fetchMenuData(tableView);

        GridPane p = new GridPane();
        Text t1 = new Text("customer name:");
        TextField tx1 = new TextField();
        Text t2 = new Text("plate:");
        TextField tx2 = new TextField();
        Text t3 = new Text("drink:");
        TextField tx3 = new TextField();
        Text t4 = new Text("plate quantity:");
        TextField tx4 = new TextField();
        Text t5 = new Text("drink quantity:");
        TextField tx5 = new TextField();
        Button b = new Button("Order now");

        // Adding components to the grid pane
        p.add(t1, 0, 1);
        p.add(tx1, 1, 1);
        p.add(t2, 0, 2);
        p.add(tx2, 1, 2);
        p.add(t3, 0, 3);
        p.add(tx3, 1, 3);
        p.add(t4, 0, 4);
        p.add(tx4, 1, 4);
        p.add(t5, 0, 5);
        p.add(tx5, 1, 5);
        p.add(b, 1, 6);
        p.add(tableView, 0, 0, 2, 1);

        // Setting alignment and spacing
        p.setAlignment(Pos.CENTER);
        p.setVgap(10);
        p.setHgap(10);

        // Applying styles


        p.setStyle("-fx-background-color: grey; -fx-padding: 20px;");
        t1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        t2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        t3.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx3.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        t4.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx4.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        t5.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx5.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        b.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px; -fx-translate-x: 110;");
        p.setVgap(10);
        Bill bill=new Bill();
        b.setOnAction(e -> {
            try {

                addPlate(nb,tx1.getText(),tx2.getText(), tx3.getText(),Integer.parseInt(tx4.getText()),Integer.parseInt(tx5.getText()));
                System.out.println("Order is added!! ");

            } catch (NumberFormatException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                bill.printBill();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        Stage stage = new Stage();
        Scene s = new Scene(p, 600, 600);
        stage.setScene(s);
        stage.show();
    }



    @Override
    public String toString() {
        return "Order{" +
                "plate='" + plate + '\'' +
                ", drink='" + drink + '\'' +
                ", nbCostumers=" + nbCostumers +
                ", bill=" + bill +
                '}';
    }

    public void addPlate(int nb,String cname,String plate, String drink,int plateqty,int drinkqty) throws SQLException {
        Mysql.Connect();
        PreparedStatement st = Mysql.con.prepareStatement("INSERT INTO project.order (ordernb,cname,plate,drink,plateqty,drinkqty) VALUES (?,?,?,?,?,?)");
        st.setInt(1,nb);
        st.setString(2,cname);
        st.setString(3, plate);
        st.setString(4, drink);
        st.setInt(5,plateqty);
        st.setInt(6,drinkqty);
        st.executeUpdate();
        Mysql.Disconnect(); // Close the connection after use
    }
    private void fetchMenuData(TableView<Map<String, Object>> tableView) {
        String condition = "SELECT * FROM project.menu";
        ResultSet resultSet = Mysql.Fetch("project.menu", false, condition);

        try {
            // Create columns dynamically based on ResultSet metadata
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                final int index = i - 1;
                TableColumn<Map<String, Object>, Object> column = new TableColumn<>(resultSet.getMetaData().getColumnName(i));
                column.setCellValueFactory(cellData -> {
                    Map<String, Object> rowData = cellData.getValue();
                    return new SimpleObjectProperty<>(rowData.get(column.getText()));
                });
                tableView.getColumns().add(column);
            }

            // Populate data
            while (resultSet.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    rowData.put(resultSet.getMetaData().getColumnName(i), resultSet.getObject(i));
                }
                menuData.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableView.setItems(menuData);
    }

}



