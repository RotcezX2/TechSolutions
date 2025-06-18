package com.abraham.ts.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    // Botones de la barra lateral
    @FXML private Button settingsBtn;
    @FXML private Button logoutBtn;
    @FXML private Button helpBtn;
    @FXML private Button listBtn;
    @FXML private Button chartBtn;

    // Botones de período
    @FXML private ToggleButton mensualBtn;
    @FXML private ToggleButton semestralBtn;
    @FXML private ToggleButton anualBtn;

    // Gráficos
    @FXML private PieChart pieChart;
    @FXML private LineChart<String, Number> lineChart;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis yAxis;

    // Botones de navegación
    @FXML private Button prevBtn;
    @FXML private Button nextBtn;

    // Categorías
    @FXML private Label category1;
    @FXML private Label category2;
    @FXML private Label category3;
    @FXML private Label category4;
    @FXML private Label category5;
    @FXML private Label category6;
    @FXML private Label category7;
    @FXML private Label category8;
    @FXML private Label category9;

    // Botón descargar
    @FXML private Button downloadBtn;

    // Grupo de botones para período
    private ToggleGroup periodGroup;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupPeriodButtons();
        setupCharts();
        loadInitialData();
    }

    private void setupPeriodButtons() {
        periodGroup = new ToggleGroup();
        mensualBtn.setToggleGroup(periodGroup);
        semestralBtn.setToggleGroup(periodGroup);
        anualBtn.setToggleGroup(periodGroup);

        // Mensual seleccionado por defecto
        mensualBtn.setSelected(true);
    }

    private void setupCharts() {
        // Configurar gráfico circular
        pieChart.setTitle("");
        pieChart.setLegendVisible(false);
        pieChart.setLabelsVisible(false);

        // Configurar gráfico de líneas
        lineChart.setTitle("");
        lineChart.setLegendVisible(false);
        xAxis.setLabel("");
        yAxis.setLabel("");
    }

    private void loadInitialData() {
        loadPieChartData();
        loadLineChartData();
    }

    private void loadPieChartData() {
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Médico", 35),
                new PieChart.Data("Colegiaturas", 25),
                new PieChart.Data("Gastos Generales", 20),
                new PieChart.Data("Insumos", 15),
                new PieChart.Data("Otros", 5)
        );
        pieChart.setData(pieData);
    }

    private void loadLineChartData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Ene", 100));
        series.getData().add(new XYChart.Data<>("Feb", 120));
        series.getData().add(new XYChart.Data<>("Mar", 80));
        series.getData().add(new XYChart.Data<>("Abr", 150));
        series.getData().add(new XYChart.Data<>("May", 200));
        series.getData().add(new XYChart.Data<>("Jun", 180));

        lineChart.getData().clear();
        lineChart.getData().add(series);
    }

    // Manejadores de eventos para botones de la barra lateral
    @FXML
    private void handleSettings(ActionEvent event) {
        showAlert("Configuración", "Función de configuración no implementada");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        // Mostrar confirmación de cierre de sesión
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Cerrar Sesión");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("¿Estás seguro de que deseas cerrar sesión?");

        confirmAlert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                loadLogin();
            }
        });
    }

    @FXML
    private void handleHelp(ActionEvent event) {
        showAlert("Ayuda", "Función de ayuda no implementada");
    }

    @FXML
    private void handleList(ActionEvent event) {
        showAlert("Lista", "Cambiar a vista de lista");
    }

    @FXML
    private void handleChart(ActionEvent event) {
        showAlert("Gráficos", "Ya estás en la vista de gráficos");
    }

    // Manejadores para botones de período
    @FXML
    private void handleMensual(ActionEvent event) {
        if (mensualBtn.isSelected()) {
            updateDataForPeriod("mensual");
        }
    }

    @FXML
    private void handleSemestral(ActionEvent event) {
        if (semestralBtn.isSelected()) {
            updateDataForPeriod("semestral");
        }
    }

    @FXML
    private void handleAnual(ActionEvent event) {
        if (anualBtn.isSelected()) {
            updateDataForPeriod("anual");
        }
    }

    private void updateDataForPeriod(String period) {
        // Aquí puedes actualizar los datos según el período seleccionado
        System.out.println("Actualizando datos para período: " + period);

        // Ejemplo de actualización de datos
        switch (period) {
            case "mensual":
                loadPieChartData();
                loadLineChartData();
                break;
            case "semestral":
                loadSemestralData();
                break;
            case "anual":
                loadAnualData();
                break;
        }
    }

    private void loadSemestralData() {
        // Datos para vista semestral
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Médico", 40),
                new PieChart.Data("Colegiaturas", 30),
                new PieChart.Data("Gastos Generales", 20),
                new PieChart.Data("Insumos", 10)
        );
        pieChart.setData(pieData);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("S1", 600));
        series.getData().add(new XYChart.Data<>("S2", 750));
        lineChart.getData().clear();
        lineChart.getData().add(series);
    }

    private void loadAnualData() {
        // Datos para vista anual
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Médico", 45),
                new PieChart.Data("Colegiaturas", 25),
                new PieChart.Data("Gastos Generales", 20),
                new PieChart.Data("Insumos", 10)
        );
        pieChart.setData(pieData);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("2023", 1200));
        series.getData().add(new XYChart.Data<>("2024", 1500));
        lineChart.getData().clear();
        lineChart.getData().add(series);
    }

    // Manejadores para botones de navegación
    @FXML
    private void handlePrevious(ActionEvent event) {
        showAlert("Navegación", "Ir al período anterior");
    }

    @FXML
    private void handleNext(ActionEvent event) {
        showAlert("Navegación", "Ir al período siguiente");
    }

    // Manejador para clicks en categorías
    @FXML
    private void handleCategoryClick(MouseEvent event) {
        Label clickedLabel = (Label) event.getSource();
        String categoryText = clickedLabel.getText();
        showAlert("Categoría", "Seleccionaste: " + categoryText);
    }

    // Manejador para botón de descarga
    @FXML
    private void handleDownload(ActionEvent event) {
        showAlert("Descarga", "Descargando factura...");
        // Aquí implementarías la lógica de descarga
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Método para volver al login
    private void loadLogin() {
        try {
            // Cargar el FXML del login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/abraham/ts/views/LoginView.fxml"));
            Parent loginRoot = loader.load();

            // Crear nueva escena para el login
            Scene loginScene = new Scene(loginRoot, 800, 600);

            // Obtener el stage actual
            Stage currentStage = (Stage) logoutBtn.getScene().getWindow();

            // Configurar el stage con la escena de login
            currentStage.setTitle("Sistema de Login");
            currentStage.setScene(loginScene);
            currentStage.setMinWidth(800);
            currentStage.setMinHeight(600);
            currentStage.setResizable(false);
            currentStage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo cargar la pantalla de login: " + e.getMessage());
        }
    }
}