package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        Label nameLabel = new Label("Customer Name:");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter customer name");

        Label provinceLabel = new Label("Province:");

        ComboBox<String> provinceBox = new ComboBox<>();
        provinceBox.getItems().addAll(
                "Central",
                "Copperbelt",
                "Eastern",
                "Luapula",
                "Lusaka",
                "Muchinga",
                "Northern",
                "North-Western",
                "Southern",
                "Western"
        );

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                nameLabel,
                nameField,
                provinceLabel,
                provinceBox
        );

        Scene scene = new Scene(layout, 400, 300);

        stage.setTitle("Customer Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}