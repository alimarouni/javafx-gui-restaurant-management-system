package com.example.finalprojectjavafx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.sql.*;

public class Employee extends GridPane {
    private String id;
    private String fname;
    private String lname;
    private double sallary;
    private String speciality;
    Random rand = new Random();
    private Worker worker;

    public Employee(String fname, String lname, double sallary, String speciality) {

        if (fname.length() > 2) {
            this.fname = fname;
        }
        if (lname.length() > 2) {
            this.lname = lname;

        }
        int r = rand.nextInt(9000) + 1000;
    }

    public Employee() {
    }


    public String getID() {

        return id;
    }

    public void setID(String id) {
        int r = rand.nextInt(9000) + 1000;

        this.id = fname.substring(0, 2) + lname.substring(0, 2) + "@" + r;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public double getSallary() {
        return sallary;
    }

    public void setSallary(double sallary) {
        this.sallary = sallary;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void addEmp() {
        GridPane p = new GridPane();
        Text t1 = new Text("fname:");
        TextField tx1 = new TextField();
        Text t2 = new Text("lname:");
        TextField tx2 = new TextField();
        Text t3 = new Text("Salary:");
        TextField tx3 = new TextField();
        Text t4 = new Text("Speciality:");
        TextField tx4 = new TextField();
        Button b = new Button("Add Employee");
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
        b.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px; -fx-translate-x: 38;");
        p.setVgap(10);

        b.setOnAction(e -> {
            try {
                insertPerson(tx1.getText(), tx2.getText(), Double.parseDouble(tx3.getText()), tx4.getText());
                System.out.println("employee is added!! ");

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

    public void removeEmp() {
        GridPane p = new GridPane();
        Text t1 = new Text("fname:");
        TextField tx1 = new TextField();
        Text t2 = new Text("lname:");
        TextField tx2 = new TextField();
        Button b = new Button("Delete");
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
        });
        Text txt = new Text();
        b.setOnAction(e -> {
            try {
                deleteEmployee(tx1.getText(), tx2.getText());
                System.out.println("employee is deleted!! ");
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

    public void Searchemp() {
        GridPane p = new GridPane();
        Text t1 = new Text("fname:");
        TextField tx1 = new TextField();
        Text t2 = new Text("lname:");
        TextField tx2 = new TextField();
        Button b = new Button("Search");
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
        });
        Text txt = new Text();
        b.setOnAction(e -> {
            try {
                searchEmployee(tx1.getText(), tx2.getText());

            } catch (NumberFormatException e1) {
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




    public void modifyEmp() {
        Stage stage = new Stage();
        BorderPane p = new BorderPane();
        ScrollPane scrollPane = new ScrollPane(p);

        Text t0 = new Text("Enter employee details to modify:");
        Text t12 = new Text("fname:");
        TextField tx1 = new TextField();
        Text t22 = new Text("lname:");
        TextField tx2 = new TextField();

        CheckBox c1 = new CheckBox("fname");
        CheckBox c2 = new CheckBox("lname");
        CheckBox c3 = new CheckBox("Salary");
        CheckBox c4 = new CheckBox("Speciality");

        Text t = new Text("Choose what you want to modify!!");
        VBox v = new VBox(10);
        Button b = new Button("modify");
        v.getChildren().addAll(t0, t12, tx1, t22, tx2, t, c1, c2, c3, c4, b);
        p.setTop(v);

        TextField t1 = new TextField();
        t1.setVisible(false);
        TextField t2 = new TextField();
        t2.setVisible(false);
        TextField t3 = new TextField();
        t3.setVisible(false);
        TextField t4 = new TextField();
        t4.setVisible(false);
        Text f1 = new Text();
        Text f2 = new Text();
        Text f3 = new Text();
        Text f4 = new Text();

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        p.setStyle("-fx-background-color: grey; -fx-padding: 20px;");
        t0.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: white;");
        t.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: white;");
        b.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 120px; -fx-pref-height: 40px; -fx-background-color: #27ae60; -fx-text-fill: #fff; -fx-border-radius: 5px; -fx-translate-x: 120;");
        c1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        c2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        c3.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        c4.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 16px; -fx-fill: #2c3e50;");
        tx1.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");
        tx2.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 8px; -fx-border-color: #bdc3c7;");


        c1.setOnAction(e -> {
            if (c1.isSelected()) {
                f1.setText("Fname:");
                t1.setVisible(true);
                v.getChildren().addAll(f1, t1);
            } else {
                v.getChildren().removeAll(f1, t1);
                t1.setVisible(false);
            }
        });

        c2.setOnAction(e -> {
            if (c2.isSelected()) {
                f2.setText("Lname:");
                t2.setVisible(true);
                v.getChildren().addAll(f2, t2);
            } else {
                v.getChildren().removeAll(f2, t2);
                t2.setVisible(false);
            }
        });

        c3.setOnAction(e -> {
            if (c3.isSelected()) {
                f3.setText("Salary:");
                t3.setVisible(true);
                v.getChildren().addAll(f3, t3);
            } else {
                v.getChildren().removeAll(f3, t3);
                t3.setVisible(false);
            }
        });

        c4.setOnAction(e -> {
            if (c4.isSelected()) {
                f4.setText("Speciality:");
                t4.setVisible(true);
                v.getChildren().addAll(f4, t4);
            } else {
                v.getChildren().removeAll(f4, t4);
                t4.setVisible(false);
            }
        });

        b.setOnAction(e -> {
            try {
                String firstName = tx1.getText();
                String lastName = tx2.getText();

                if (!firstName.isEmpty() && !lastName.isEmpty()) {
                    if (c1.isSelected()) {
                        try {
                            modifyfname(t1.getText(), firstName, lastName);
                            System.out.println("Employee's first name modified!");
                        } catch (NumberFormatException e1) {
                            e1.printStackTrace();
                        }
                    }
                    if (c2.isSelected()) {
                        try {
                            modifylname(t2.getText(), firstName, lastName);
                            System.out.println("Employee's last name modified!");
                        } catch (NumberFormatException e1) {
                            e1.printStackTrace();
                        }
                    }

                    if (c3.isSelected()) {
                        try {
                            modifySallary(firstName, lastName, Double.parseDouble(t3.getText()));
                            System.out.println("Employee's salary modified!");
                        } catch (NumberFormatException e1) {
                            e1.printStackTrace();
                        }
                    }

                    if (c4.isSelected()) {
                        try {
                            modifyspeciality(firstName, lastName, t4.getText());
                            System.out.println("Employee's speciality modified!");
                        } catch (NumberFormatException e1) {
                            e1.printStackTrace();
                        }
                    }
                    System.out.println("Modifications applied");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Please enter both fname and lname.");
                    alert.show();
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid number for salary.");
                alert.show();
            }
        });
        Scene s = new Scene(scrollPane, 400, 400);
        stage.setScene(s);
        stage.show();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", sallary=" + sallary +
                ", speciality='" + speciality + '\'' +
                '}';
    }

    public void insertPerson(String fname, String lname, Double sallary, String speciality) throws SQLException {
        Mysql.Connect();
        int r = rand.nextInt(9000) + 1000;
        String generatedId = fname.substring(0, 2) + lname.substring(0, 2) + "@" + r;
        PreparedStatement st = Mysql.con.prepareStatement("INSERT INTO project.employees (id,fname, lname, sallary, speciality) VALUES (?,?, ?, ?, ?)");
        st.setString(1, generatedId);
        st.setString(2, fname);
        st.setString(3, lname);
        st.setDouble(4, sallary);
        st.setString(5, speciality);
        st.executeUpdate();
        Mysql.Disconnect(); // Close the connection after use
    }

    public void deleteEmployee(String fname, String lname) throws SQLException {
        Mysql.Connect();
        String sql = "DELETE FROM project.employees WHERE fname = ? AND lname = ?";
        try (PreparedStatement st = Mysql.con.prepareStatement(sql)) {
            st.setString(1, fname);
            st.setString(2, lname);
            st.executeUpdate();
            Mysql.Disconnect();
        }
    }

    public void searchEmployee(String fname, String lname) {
        boolean found = false;

        Mysql.Connect();
        String sql = "SELECT fname, lname, sallary, speciality FROM project.employees WHERE fname=? AND lname=?";

        try (PreparedStatement st = Mysql.con.prepareStatement(sql)) {
            st.setString(1, fname);
            st.setString(2, lname);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    String retrievedFirstName = rs.getString("fname");
                    String retrievedLastName = rs.getString("lname");
                    String retrievedSallary = rs.getString("sallary");  // Corrected column name
                    String retrievedSpeciality = rs.getString("speciality");

                    // Do something with the retrieved data, e.g., print or store it
                    System.out.println("First Name: " + retrievedFirstName);
                    System.out.println("Last Name: " + retrievedLastName);
                    System.out.println("Sallary: " + retrievedSallary);
                    System.out.println("Speciality: " + retrievedSpeciality);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mysql.Disconnect();
        }
    }

    public void modifyfname(String newFname, String originalFname, String originalLname) {
        Mysql.Connect();
        String sql = "UPDATE project.employees SET fname=? WHERE fname=? AND lname=?";
        try (PreparedStatement st = Mysql.con.prepareStatement(sql)) {
            st.setString(1, newFname);  // Set the new first name
            st.setString(2, originalFname);  // Set the original first name
            st.setString(3, originalLname);  // Set the last name
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mysql.Disconnect();
        }
    }

    public void modifylname(String newLname, String originalFname, String originalLname) {
        Mysql.Connect();
        String sql = "UPDATE project.employees SET lname=? WHERE fname=? AND lname=?";
        try (PreparedStatement st = Mysql.con.prepareStatement(sql)) {
            st.setString(1, newLname);
            st.setString(2, originalFname);
            st.setString(3, originalLname);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mysql.Disconnect();
        }
    }




    public void modifySallary(String fname, String lname, double newSalary) {
        Mysql.Connect();
        String sql = "UPDATE project.employees SET sallary=? WHERE fname=? AND lname=?";
        try (PreparedStatement st = Mysql.con.prepareStatement(sql)) {
            st.setDouble(1, newSalary);      // Set the new salary
            st.setString(2, fname);          // Set the first name
            st.setString(3, lname);          // Set the last name
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mysql.Disconnect();
        }
    }


    public void modifyspeciality(String fname, String lname, String newSpeciality) {
        Mysql.Connect();
        String sql = "UPDATE project.employees SET speciality=? WHERE fname=? AND lname=?";
        try (PreparedStatement st = Mysql.con.prepareStatement(sql)) {
            st.setString(1, newSpeciality);  // Set the new speciality
            st.setString(2, fname);          // Set the first name
            st.setString(3, lname);          // Set the last name
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mysql.Disconnect();
        }
    }

}