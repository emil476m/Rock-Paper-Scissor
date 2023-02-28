package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    public ImageView imgPlayer;
    public Label lblPlayer;
    public ImageView imgBot;
    public Label lblBot;
    @FXML
    private Button btnScissor;
    @FXML
    private Button btnPaper;
    @FXML
    private Button btnRock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void handleRock(ActionEvent actionEvent) {
    }

    public void handlePaper(ActionEvent actionEvent) {
    }

    public void handleScissor(ActionEvent actionEvent) {
    }
}
