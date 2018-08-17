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
import java.util.Optional;

/**
 * created by Islam
 */

public class AbonentViewController {
    private Main main;
    private Stage mainStage;
    private Provider provider;
    private ProviderViewController providerViewController;

    @FXML
    public TableView<Phone> abonentsTable;

    @FXML
    public TableColumn<Phone, String> abonentNameColumn;

    @FXML
    public TableColumn<Phone, String> abonentPhoneColumn;

    @FXML
    public Label nameLabel;

    @FXML
    public Label numberLabel;

    @FXML
    public Label balanceLabel;

    @FXML
    public Label providerLabel;

    @FXML
    public Label amountReceivedLabel;

    @FXML
    public Label amountSendLabel;

    @FXML
    public void handleCreate() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/newAbonent_view.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Регистрация");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainStage);

        NewAbonentViewController controller = loader.getController();
        controller.setMain(main);
        controller.setStage(stage);
        controller.setParentController(this);
        stage.show();
    }

    @FXML
    public void handleEdit() throws IOException {
        if (abonentsTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/editAbonent_view.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Редактирование");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainStage);

            EditAbonentViewController controller = loader.getController();
            controller.setMain(main);
            controller.setStage(stage);
            controller.setParentController(this);
            controller.setPhone(getAbonentsTable().getSelectionModel().getSelectedItem());

            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Выберите абонента, которую хотите изменить");

            alert.show();
        }
    }

    @FXML
    public void handleDelete(){
        if (abonentsTable.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Удаление");
            alert.setHeaderText("Вы действительно хотите удалить абонента?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                main.getProviders().get(main.findProviderForAbonent(getAbonentsTable().getSelectionModel().getSelectedItem().getProvider())).unregister(abonentsTable.getSelectionModel().getSelectedItem());
                main.getProviders().get(main.findProviderForAbonent(getAbonentsTable().getSelectionModel().getSelectedItem().getProvider().getProvidersList().remove(main.findProviderForAbonent(getAbonentsTable().getSelectionModel().getSelectedItem().getProvider()))));
                main.getPhones().remove(getAbonentsTable().getSelectionModel().getSelectedItem());
                abonentsTable.refresh();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Выберите абонента, которую хотите удалить");

            alert.show();
        }
    }

    @FXML
    public void handleNewMessage() throws IOException {


    }

    @FXML
    public void handleMessage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/message_view.fxml"));
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainStage);

        stage.show();
    }

    @FXML
    public void handlePay() throws IOException {
        if (abonentsTable.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/pay_view.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Пополнение");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(mainStage);

            PayViewController controller = loader.getController();
            controller.setStage(stage);
            controller.setParentController(this);

            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Сначала выберите абонента");

            alert.show();
        }
    }

    @FXML
    public void initialize(){
        abonentNameColumn.setCellValueFactory(
                cellValue -> new SimpleStringProperty(cellValue.getValue().getName())
        );

        abonentPhoneColumn.setCellValueFactory(
                cellValue -> new SimpleStringProperty(cellValue.getValue().getNumber())
        );


        abonentsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showAbonentsInfo(newValue)
        );
    }

    @FXML
    public void showAbonentsInfo(Phone phone){
        if (phone != null){
            nameLabel.setText(phone.getName());
            numberLabel.setText(phone.getNumber());
            balanceLabel.setText(String.valueOf(phone.getBalance()));
            providerLabel.setText(phone.getProvider().getName());
            amountReceivedLabel.setText(String.valueOf(phone.getReceivedMessagesList().size()));
            amountSendLabel.setText(String.valueOf(phone.getSendMessagesList().size()));
        }
    }

    public void setMain(Main main) {
        this.main = main;
        abonentsTable.setItems(main.getPhones());
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public TableView<Phone> getAbonentsTable() {
        return abonentsTable;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }


}
