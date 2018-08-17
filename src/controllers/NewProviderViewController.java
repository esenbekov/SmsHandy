package controllers;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Provider;

/**
 * created by Islam
 */

public class NewProviderViewController {
    private Main main;
    private Stage stage;
    private ProviderViewController parentController;

    @FXML
    private TextField nameTextField;

    @FXML
    public void handleCreate(){
        if (nameTextField.getText().length() > 0) {
            Provider provider = new Provider(nameTextField.getText());
            if (!main.hasProvider(provider)) {
                main.getProviders().add(provider);
                parentController.getProvidersTable().refresh();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Провайдер с таким именем уже существует");
                alert.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Введите название провайдера");
            alert.show();
        }
    }

    @FXML
    public void handleCancel(){
        stage.close();
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setParentController(ProviderViewController parentController) {
        this.parentController = parentController;
    }
}
