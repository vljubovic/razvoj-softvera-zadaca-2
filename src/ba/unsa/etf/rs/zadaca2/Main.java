package ba.unsa.etf.rs.zadaca2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        OsobeModel model = new OsobeModel();
        model.napuni();
        OsobaController ctrl = new OsobaController(model);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adresar.fxml"));
        loader.setController(ctrl);
        Parent root = loader.load();

        //AnchorPane osobaOverview = (AnchorPane) loader.load();
        //Scene scene = new Scene(osobaOverview);
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.setTitle("Adresar");
        primaryStage.show();

    }


    /**
     * glavni prikaz
     */
   /* public void showOsobaOverview() {
        try {
            // Load osoba overview.
            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(Main.class.getResource("adresar.fxml"));
            AnchorPane osobaOverview = (AnchorPane) loader.load();

            Scene scene = new Scene(osobaOverview);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Give the controller access to the main app.
            OsobaController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * Returns the main stage.
     * @return
     */
    /*public Stage getPrimaryStage() {
        return primaryStage;
    }*/




    public static void main(String[] args) {
        launch(args);
    }
}
