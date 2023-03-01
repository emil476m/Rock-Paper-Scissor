package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import static rps.bll.game.Move.Rock;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    public ImageView imgPlayer;
    public Label lblPlayer;
    public ImageView imgBot;
    public Label lblBot;
    public Label aICounter;
    public Label tieCounter;
    public Label playerCounter;
    public ImageView imgRock;
    public ImageView imgPaper;
    public ImageView imgScissor;
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
        CallRock();
    }

    public void handlePaper(ActionEvent actionEvent) {
        CallPaper();
    }

    public void handleScissor(ActionEvent actionEvent) {
        CallScissor();
    }

    public void HandleExit(ActionEvent event) {
    }

    public void HandleHistory(ActionEvent event) {
    }

    public void CallRock(){
        File file = new File("data/the_Rock-removebg-preview.png");
        Image image = new Image(file.toURI().toString());
        imgPlayer.setImage(image);
    }
    public void CallPaper(){
        File file = new File("data/paper-removebg-preview.png");
        Image image = new Image(file.toURI().toString());
        imgPlayer.setImage(image);
    }
    public void CallScissor(){
        File file = new File("data/gZiXk0l-removebg-preview.png");
        Image image = new Image(file.toURI().toString());
        imgPlayer.setImage(image);
    }
}
