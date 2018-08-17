package controllers;

import app.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Phone;
import models.Provider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

/**
 * created by Islam
 */

public class ProviderViewController {
    private Main main;
    private Stage mainStage;

    @FXML
    public TableView<Provider> providersTable;
    @FXML
    public TableColumn<Provider, String> providerNameColumn;
    @FXML
    public Label nameLabel;
    @FXML
    public Label abonAmountLabel;



    @FXML
    public void handleNewProvider() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/newProvider_view.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Новый провайдер");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainStage);

        NewProviderViewController controller = loader.getController();
        controller.setMain(main);
        controller.setStage(stage);
        controller.setParentController(this);

        stage.show();
    }

    @FXML
    public void handleEditProvider() throws IOException {
        if (providersTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/editProvider_view.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Редактирование");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainStage);

            EditProviderViewController controller = loader.getController();
            controller.setMain(main);
            controller.setStage(stage);
            controller.setParentController(this);
            controller.setProvider(getProvidersTable().getSelectionModel().getSelectedItem());

            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Выберите провайдера, которую хотите изменить");

            alert.show();
        }
    }

    @FXML
    public void handleDeleteProvider(){
        if (providersTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление");
            alert.setHeaderText("Вы действительно хотите удалить провайдера?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                main.getPhones().removeAll(main.removingPhones(getProvidersTable().getSelectionModel().getSelectedItem()));
                providersTable.getSelectionModel().getSelectedItem().getProvidersList().remove(getProvidersTable().getSelectionModel().getSelectedItem());
                main.getProviders().remove(getProvidersTable().getSelectionModel().getSelectedItem());
                providersTable.refresh();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Выберите провайдера, которую хотите удалить");

            alert.show();
        }
    }

    @FXML
    public void initialize(){
        providerNameColumn.setCellValueFactory(
                cellValue -> new SimpleStringProperty(cellValue.getValue().getName())
        );

        providersTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showProvidersInfo(newValue)
        );
    }

    @FXML
    public void showProvidersInfo(Provider provider){
        if (provider != null){
            nameLabel.setText(provider.getName());
            abonAmountLabel.setText(String.valueOf(provider.getAbonentsList().size()));
            providersTable.refresh();
            System.out.println("providers  "+main.getProviders().size());
            System.out.println("abonentsList  "+provider.getAbonentsList().size());
            System.out.println("phones  "+main.getPhones().size());
            System.out.println("providersList  "+provider.getProvidersList().size());
            System.out.println(provider.getAbonentsList().isEmpty());

        }else {
            nameLabel.setText("Не выбран");
            abonAmountLabel.setText("Не выбран");
        }
    }

    public void setMain(Main main) {
        this.main = main;
        providersTable.setItems(main.getProviders());
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public TableView<Provider> getProvidersTable() {
        return providersTable;
    }

    public TableColumn<Provider, String> getProviderNameColumn() {
        return providerNameColumn;
    }
}
