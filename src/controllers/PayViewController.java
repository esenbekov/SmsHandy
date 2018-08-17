package controllers;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * created by Islam
 */

public class PayViewController {
    private Main main;
    private Stage stage;
    private AbonentViewController parentController;
    @FXML
    private TextField payTextField;

    @FXML
    public void handleOk(){
        if (!payTextField.getText().isEmpty()){
            if (payTextField.getText().matches("^[1-9][0-9]+$")) {
                parentController.getAbonentsTable().getSelectionModel().getSelectedItem().deposit(Integer.parseInt(payTextField.getText()));
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Укажите сумму в числах");
                alert.show();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Укажите желаемую сумму");
            alert.show();
        }
    }

    @FXML
    public void handleCancel(){
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setParentController(AbonentViewController parentController) {
        this.parentController = parentController;
    }
}
