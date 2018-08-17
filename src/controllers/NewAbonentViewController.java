package controllers;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Phone;
import models.PrepaidPhone;
import models.Provider;


/**
 * created by Islam
 */

public class NewAbonentViewController {
    private Main main;
    private Stage stage;
    private AbonentViewController parentController;


    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField providerTextField;

    @FXML
    private TableView<Provider> providersTable;

    @FXML
    public void handleOk(){
        if (nameTextField.getText().length() > 0 && phoneTextField.getText().length() > 0 && providerTextField.getText().length() > 0){
            Provider provider1 = new Provider(providerTextField.getText());
            Phone phone1 = new PrepaidPhone(nameTextField.getText(), phoneTextField.getText(), provider1);
            if (main.hasProvider(provider1)){
                if (!main.hasPhone(phone1)) {
                    if (phone1.getNumber().matches("^.+996[0-9]{9}")) {
                        main.getPhones().add(phone1);
                        main.getProviders().get(main.findProviderForAbonent(provider1)).register(phone1);
                        parentController.getAbonentsTable().refresh();
                        stage.close();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка");
                        alert.setHeaderText("Не правильно ввели номер");
                        alert.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Абонент с таким номером уже зарегистирован");
                    alert.show();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Провайдер с таким именем не существует");
                alert.show();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Все поля должны быть заполнены");
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

    public void setParentController(AbonentViewController parentController) {
        this.parentController = parentController;
    }

}
