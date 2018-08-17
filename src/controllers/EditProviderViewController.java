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

public class EditProviderViewController {
    private Main main;
    private Stage stage;
    private ProviderViewController parentController;
    private Provider provider;

    @FXML
    private TextField nameTextField;

    @FXML
    public void handleEdit(){
        if (nameTextField.getText().length() > 0) {
            Provider provider1 = new Provider(nameTextField.getText());
            if (!main.hasProvider(provider1)) {
                provider.setName(nameTextField.getText());
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

    public void setProvider(Provider provider) {
        this.provider = provider;
        nameTextField.setText(provider.getName());
    }
}
