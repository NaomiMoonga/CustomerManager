package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final ObservableList<Customer> customers =
            FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        Label title = new Label("Customer Manager");

        // Customer name
        TextField nameField = new TextField();
        nameField.setPromptText("Customer name");

        // Province selection
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

        provinceBox.setPromptText("Select province");

        // Add button
        Button addButton = new Button("Add Customer");

        // Delete button
        Button deleteButton = new Button("Delete Customer");

        // Table
        TableView<Customer> table = new TableView<>();

        TableColumn<Customer, String> nameColumn =
                new TableColumn<>("Name");

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );

        TableColumn<Customer, String> provinceColumn =
                new TableColumn<>("Province");

        provinceColumn.setCellValueFactory(
                new PropertyValueFactory<>("province")
        );

        table.getColumns().addAll(nameColumn, provinceColumn);

        table.setItems(customers);

        // Add customer
        addButton.setOnAction(event -> {

            String name = nameField.getText().trim();
            String province = provinceBox.getValue();

            if (name.isEmpty()) {
                showAlert(
                        "Missing Name",
                        "Please enter the customer's name."
                );
                return;
            }

            if (province == null) {
                showAlert(
                        "Missing Province",
                        "Please select a province."
                );
                return;
            }

            Customer customer = new Customer(name, province);

            customers.add(customer);

            nameField.clear();
            provinceBox.setValue(null);
        });

        // Delete customer
        deleteButton.setOnAction(event -> {

            Customer selectedCustomer =
                    table.getSelectionModel().getSelectedItem();

            if (selectedCustomer == null) {
                showAlert(
                        "No Customer Selected",
                        "Please select a customer to delete."
                );
                return;
            }

            Alert confirmation =
                    new Alert(Alert.AlertType.CONFIRMATION);

            confirmation.setTitle("Delete Customer");
            confirmation.setHeaderText(
                    "Delete " + selectedCustomer.getName() + "?"
            );
            confirmation.setContentText(
                    "Are you sure you want to delete this customer?"
            );

            confirmation.showAndWait().ifPresent(response -> {

                if (response ==
                        javafx.scene.control.ButtonType.OK) {

                    customers.remove(selectedCustomer);
                }
            });
        });

        // Form layout
        HBox form = new HBox(
                10,
                nameField,
                provinceBox,
                addButton,
                deleteButton
        );

        // Main layout
        VBox layout = new VBox(
                15,
                title,
                form,
                table
        );

        layout.setPadding(new Insets(20));

        // Scene
        Scene scene = new Scene(
                layout,
                700,
                500
        );

        stage.setTitle("Customer Manager");
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String message) {

        Alert alert =
                new Alert(Alert.AlertType.WARNING);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}