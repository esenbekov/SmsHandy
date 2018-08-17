package controllers;

import app.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Phone;
import models.PrepaidPhone;
import models.Provider;

/**
 * created by Islam
 */

public class EditAbonentViewController {
    private Main main;
    private Stage stage;
    private Phone phone;
    private AbonentViewController parentController;
    private Provider provider;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField providerTextField;

    @FXML
    public void handleOk(){
        if (nameTextField.getText().length() > 0 && phoneTextField.getText().length() > 0 && providerTextField.getText().length() > 0){
            Provider provider1 = new Provider(providerTextField.getText());
            //main.getProviders().get(main.findProviderForAbonent(provider1)).getProvidersList().remove(provider1);
            String oldNumber = main.getPhones().get(main.findPhoneForProvider(phone)).getNumber();
            Phone phone1 = new PrepaidPhone(nameTextField.getText(), phoneTextField.getText(), provider1);
            main.getProviders().get(main.findProviderForAbonent(provider1)).getAbonentsList().remove(oldNumber);
            if (main.hasProvider(provider1)) {
                if (phone1.getNumber().matches("^.+996[0-9]{9}")) {
                    phone.setName(nameTextField.getText());
                    phone.setNumber(phoneTextField.getText());
                    phone.setProvider(provider1);
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

    public void setPhone(Phone phone) {
        this.phone = phone;
        nameTextField.setText(phone.getName());
        phoneTextField.setText(phone.getNumber());
        providerTextField.setText(phone.getProvider().getName());
    }

    public void setParentController(AbonentViewController parentController) {
        this.parentController = parentController;
    }
}
