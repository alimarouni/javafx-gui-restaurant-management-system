package com.example.finalprojectjavafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setWidth(640);
        stage.setHeight(440);

        // Root pane with background style
        BorderPane b = new BorderPane();
        b.setStyle("-fx-background-color: grey; -fx-background-size: cover;");

        // Heading
        Text t = new Text("WELCOME TO OUR RESTAURANT");
        t.setStyle("-fx-font-size: 30px; -fx-fill: #d0e1e1; -fx-font-family: 'Arial', sans-serif;");
        VBox hBox = new VBox(t);
        hBox.setAlignment(Pos.CENTER);

        // Sub-heading and buttons
        Text t2 = new Text("CHOOSE");
        t2.setStyle("-fx-font-size: 24px; -fx-fill: #ecf0f1; -fx-font-family: 'Arial', sans-serif;");
        Button b1 = new Button("Customer");
        Button b2 = new Button("Worker");
        b1.setStyle("-fx-font-size: 18px; -fx-pref-width: 150px; -fx-pref-height: 40px; -fx-background-color: #d0e1e1; -fx-text-fill: #466d6d; -fx-border-radius: 5px;");
        b2.setStyle("-fx-font-size: 18px; -fx-pref-width: 150px; -fx-pref-height: 40px; -fx-background-color: #d0e1e1; -fx-text-fill: #466d6d; -fx-border-radius: 5px;");
        VBox vBox = new VBox(10, t2, b1, b2);
        vBox.setAlignment(Pos.CENTER);

        // Event handling
        Customer costumer = new Customer();
        b1.setOnAction(e -> {
            costumer.type();
        });
        Login login = new Login();
        b2.setOnAction(e -> {
            login.loginpage();
        });

        // Layout setup
        b.setTop(hBox);
        b.setCenter(vBox);

        // Scene setup
        Scene scene = new Scene(b, 640, 440);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}