package com.example.finalprojectjavafx;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Login {
    public Login() {

    }

    public void loginpage(){
        Stage stage=new Stage();
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        Text t = new Text("Email:");
        TextField txt = new TextField();
        Text t2 = new Text("Password:");
        TextField txt2 = new TextField();
        Text t4 = new Text("Enter if you are A (manager or employee):");
        TextField txt4 = new TextField();
        Button b = new Button("Login");
        Text t3 = new Text("Don't have an account?");
        Button b2 = new Button("Register");
        pane.add(t, 0, 1);
        pane.add(txt, 0, 2);
        pane.add(t2, 0, 3);
        pane.add(txt2, 0, 4);
        pane.add(t4, 0, 5);
        pane.add(txt4, 0, 6);
        pane.add(b, 0, 7);
        pane.add(t3, 0, 8);
        pane.add(b2, 0, 9);

        pane.setStyle("-fx-background-color: grey; -fx-padding: 20px;");

        t.setStyle("-fx-font-size: 18px; -fx-fill: #333333;");
        txt.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");

        t2.setStyle("-fx-font-size: 18px; -fx-fill: #333333;");
        txt2.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        t4.setStyle("-fx-font-size: 18px; -fx-fill: #333333;");
        txt4.setStyle("-fx-font-size: 14px; -fx-pref-width: 300px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        b.setStyle("-fx-background-color: #3498db; -fx-text-fill: #fff; -fx-font-size: 16px; -fx-padding: 10px 60px;-fx-translate-x: 90;");
        t3.setStyle("-fx-font-size: 16px; -fx-fill: #333333;-fx-translate-x: 90;");
        b2.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: #fff; -fx-font-size: 16px; -fx-padding: 10px 50px;-fx-translate-x: 90;");

        b.setOnAction(e->{
            String [] Email=txt.getText().split(" ");
            String UserId=Mysql.Login(Email[0],txt2.getText());
            if(UserId!=null) {
                System.out.println("log in succefull");

                String input = txt4.getText().toLowerCase();
                if (!input.equals("manager") && !input.equals("employee")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Please enter 'manager' or 'employee'.", ButtonType.OK);
                    alert.showAndWait();
                    txt4.clear();
                }
                Worker worker=new Worker();
                if (input.equals("manager")) {
                    worker.manager();
                }
                if (input.equals("employee")) {
                    worker.waiter();
                }
            }
        });

        b2.setOnAction(e->{
            BorderPane p=new BorderPane();
            Text text = new Text("Enter a new Email:");
            TextField txtf1 = new TextField();
            Text text2 = new Text("Enter a new Password:");
            PasswordField txtf2 = new PasswordField();
            Text text3 = new Text("Re-enter your Password:");
            PasswordField txtf3 = new PasswordField();
            Button but = new Button("Register");
            VBox v=new VBox(10);
            v.getChildren().addAll(text,txtf1,text2,txtf2,text3,txtf3,but);
            p.setCenter(v);

            p.setStyle("-fx-background-color: grey; -fx-padding: 20px;");
            text.setStyle("-fx-font-size: 18px; -fx-fill: #333333;");
            txtf1.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
            text2.setStyle("-fx-font-size: 18px; -fx-fill: #333333;");
            txtf2.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
            text3.setStyle("-fx-font-size: 18px; -fx-fill: #333333;");
            txtf3.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
            but.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: #fff; -fx-font-size: 16px; -fx-padding: 10px 50px;-fx-translate-x: 100;");

            but.setOnAction(d->{
                if (!txtf2.getText().equals(txtf3.getText())) {
                    txtf2.setText("");
                    txtf3.setText("");
                } else {
                    try {
                        if(txtf1.getText().isBlank() || txtf2.getText().isBlank() || txtf3.getText().isBlank()){
                            Mysql.ShowAlert("Error unable to add emptyfields");
                        }
                        Mysql.register(txtf1.getText(),txtf2.getText());
                        System.out.println("New User Is Added");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            Scene s = new Scene(p, 400, 400);
            stage.setScene(s);
            stage.setTitle("Registration");
            stage.show();
        });
        Scene s = new Scene(pane, 400, 400);
        stage.setTitle("Login");
        stage.setScene(s);
        stage.show();

    }
}
