package controllers;

import app.Main;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * created by Islam
 */

public class MainViewController {
    private Stage mainStage;
    private Main main;

    @FXML
    public void handleClose(){
        mainStage.close();
    }

    @FXML
    public void showProviders() throws IOException {
        main.switchToProviderView();
    }

    @FXML
    public void showAbonents() throws IOException {
        main.switchToAbonentView();
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
