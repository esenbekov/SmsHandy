package app;

import controllers.AbonentViewController;
import controllers.MainViewController;
import controllers.ProviderViewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Phone;
import models.PrepaidPhone;
import models.Provider;
import models.TariffPlanPhone;

import java.io.IOException;
import java.util.ArrayList;

/**
 * created by Islam
 */

public class Main extends Application{
    private Stage mainStage;
    private BorderPane mainPane;
    private ObservableList<Provider> providers = FXCollections.observableArrayList();
    private ObservableList<Phone> phones = FXCollections.observableArrayList();

    public Main() {
        providers.add(new Provider("O!"));
        providers.add(new Provider("MegaCom"));
        providers.add(new Provider("BeelineKG"));
        //phones.add(new PrepaidPhone("Ислам", "+996555247208", providers.get(1)));
        //phones.add(new PrepaidPhone("Дастан", "+996552270898", providers.get(1)));
        //phones.add(new TariffPlanPhone("Эркебек", "+996550123456", providers.get(1)));
        //phones.add(new TariffPlanPhone("Бека", "+996702556644", providers.get(0)));
        //phones.add(new TariffPlanPhone("Ади", "+996707998877", providers.get(0)));
    }

    public void start(Stage stage) throws IOException {
        mainStage = stage;
        mainStage.setTitle("Мобильная сеть");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/main_view.fxml"));
        mainPane = loader.load();
        Scene scene = new Scene(mainPane);
        mainStage.setScene(scene);

        MainViewController controller = loader.getController();
        controller.setMainStage(mainStage);
        controller.setMain(this);


        switchToProviderView();

        mainStage.show();
    }

    public void switchToProviderView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/provider_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        ProviderViewController controller = loader.getController();
        controller.setMain(this);
        controller.setMainStage(mainStage);
    }

    public void switchToAbonentView() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../views/abonent_view.fxml"));
        AnchorPane pane = loader.load();
        mainPane.setCenter(pane);

        AbonentViewController controller = loader.getController();
        controller.setMain(this);
        controller.setMainStage(mainStage);
    }

    public boolean hasProvider(Provider provider){
        for (Provider p: providers){
            if (p.equals(provider)){
                return true;
            }
        }
        return false;
    }

    public boolean hasPhone(Phone phone){
        for (Phone p: phones){
            if (p.equals(phone)){
                return true;
            }
        }
        return false;
    }

    public int findProviderForAbonent(Provider provider){
        for (int i=0; i<providers.size(); i++){
            if (providers.get(i).getName().equals(provider.getName())){
                return i;
            }
        }
        return 0;
    }

    public int findPhoneForProvider(Phone phone){
        for (int i=0; i<phones.size(); i++){
            if (phones.get(i).getNumber().equals(phone.getNumber())){
                return i;
            }
        }
        return 0;
    }

    public ArrayList<Phone> removingPhones(Provider provider){
        ArrayList<Phone> removePhones = new ArrayList<>();
        for (Phone p: phones){
            if (p.getProvider().getName().equals(provider.getName())){
                removePhones.add(p);
            }
        }
        return removePhones;
    }

    public ObservableList<Provider> getProviders() {
        return providers;
    }

    public ObservableList<Phone> getPhones() {
        return phones;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
