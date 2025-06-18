package com.abraham.ts.controllers;

import com.abraham.ts.models.user.User;
import com.abraham.ts.models.user.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink registerLink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupEventHandlers();
    }

    private void setupEventHandlers() {
        userField.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                handleLogin(null);
            }
        });

        passwordField.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                handleLogin(null);
            }
        });
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String usuario = userField.getText().trim();
        String contraseña = passwordField.getText().trim();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error de validación",
                    "Por favor complete todos los campos.");
            return;
        }

        if (authenticateUser(usuario, contraseña) != null) {
            loadDashboard();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error de autenticación",
                    "Usuario o contraseña incorrectos.");
            passwordField.clear();
        }
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        showAlert(Alert.AlertType.INFORMATION, "Registro",
                "Función de registro no implementada aún.");
    }

    private User authenticateUser(String usuario, String contraseña) {
        UserDAO userdao = new UserDAO();
        List<User> users = userdao.listAll();
        User userReal = null;
        for (User u : users) {
            if (u.getUserName().equals(usuario) && u.getPassword().equals(contraseña)) {
                userReal = u;
            }
        }
        return userReal;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/abraham/ts/views/DashboardView.fxml"));
            Parent dashboardRoot = loader.load();

            Scene dashboardScene = new Scene(dashboardRoot, 1000, 700);

            Stage currentStage = (Stage) loginButton.getScene().getWindow();

            currentStage.setTitle("Dashboard - Sistema de Gráficos");
            currentStage.setScene(dashboardScene);
            currentStage.setMinWidth(900);
            currentStage.setMinHeight(600);
            currentStage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error",
                    "No se pudo cargar el dashboard: " + e.getMessage());
        }
    }

    /*
    private void loadRegisterView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) registerLink.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}