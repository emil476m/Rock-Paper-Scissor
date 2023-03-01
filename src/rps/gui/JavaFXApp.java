package rps.gui;

// Java imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX implementation of the RPS game
 *
 * @author smsj
 */
public class JavaFXApp extends Application {

    public static void launch() {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/rps/gui/view/Rock_Paper_Scissors.fxml"));

        Parent root = loader.load();

        stage.setTitle("Welcome to the not-implemented Rock-Paper-Scissor game!");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
