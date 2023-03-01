package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    public Label lblRound;
    @FXML
    private Label lblAnnounce;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button btnClose;
    @FXML
    private ImageView imgPlayer;
    @FXML
    private Label lblPlayer;
    @FXML
    private ImageView imgBot;
    @FXML
    private Label lblBot;
    @FXML
    private Label aICounter;
    @FXML
    private Label tieCounter;
    @FXML
    private Label playerCounter;
    @FXML
    private ImageView imgRock;
    @FXML
    private ImageView imgPaper;
    @FXML
    private ImageView imgScissor;
    @FXML
    private Button btnScissor;
    @FXML
    private Button btnPaper;
    @FXML
    private Button btnRock;

    private String playerName;
    private IPlayer human;
    private IPlayer bot;
    private GameManager ge;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttonImage();
        playerName = getNameFromUser();


        human = new Player(playerName, PlayerType.Human);
        bot = new Player("SkyNet", PlayerType.AI);

        ge = new GameManager(human, bot);

        setNames(bot.getPlayerName(), human.getPlayerName().toString());
        dragScreen();
    }

    private void setNames(String botName, String humanName){
        lblPlayer.setText(humanName);
        lblBot.setText(botName);
    }

    @FXML
    private void handleRock(ActionEvent actionEvent) {
        doMove("Rock");
    }

    @FXML
    private void handlePaper(ActionEvent actionEvent) {
        doMove("Paper");
    }

    @FXML
    private void handleScissor(ActionEvent actionEvent) {
        doMove("Scissor");
    }

    private String getNameFromUser(){
        String defaultName = "Dwayne";

        TextInputDialog dialog = new TextInputDialog("" + defaultName);
        dialog.setTitle("Change Name");
        dialog.setHeaderText("What is your name");
        dialog.setContentText("Please write you name");

        // Traditional way to get the response value.
        Optional<String> playerName = dialog.showAndWait();

        if (playerName.isPresent()){
            return dialog.getEditor().getText();
        }
        return null;
    }

    private void doMove(String playerMove) {
        changeImg("Human", playerMove);
        ge.playRound(Move.valueOf(playerMove));

        ArrayList<Result> results = (ArrayList<Result>) ge.getGameState().getHistoricResults();
        Result result = results.get(results.size()-1);
            changeImg("Bot", getBotMove(result));
            addPoints(result);

    }

    public String getBotMove(Result result){
        if (result.getLoserPlayer().getPlayerType() == PlayerType.AI) {
            return result.getLoserMove().toString();
        }
        else {
            return result.getWinnerMove().name();
        }
    }

    public void handleExit(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void HandleHistory(ActionEvent event) {

    }

    private void addPoints(Result result){
        if (result.getType() == ResultType.Win){
            if (result.getWinnerPlayer().getPlayerType() == PlayerType.AI) {
                aICounter.setText((Integer.parseInt(aICounter.getText()) + 1) + "");
                lblAnnounce.setText(bot.getPlayerName() + " WINS!");
            }

            else {
                playerCounter.setText((Integer.parseInt(playerCounter.getText()) + 1) + "");
                lblAnnounce.setText(human.getPlayerName() + " WINS!");
            }
        }
        else {
            tieCounter.setText((Integer.parseInt(tieCounter.getText()) + 1) + "");
            lblAnnounce.setText("TIE!");
        }
        lblRound.setText(result.getRoundNumber()+"");
    }

    private void changeImg(String playerType, String move){

        if (playerType.equals("Human")){
            if (move.equals("Rock")){
                callRock(playerType);
            }
            else if (move.equals("Paper")){
                callPaper(playerType);
            }
            else if (move.equals("Scissor")){
                callScissor(playerType);
            }
        }
        else if (playerType.equals("Bot")){
            if (move.equals("Rock")){
                callRock(playerType);
            }
            else if (move.equals("Paper")){
                callPaper(playerType);
            }
            else if (move.equals("Scissor")){
                callScissor(playerType);
            }
        }

    }

    private void callRock(String playerType){
        File file = new File("data/The_Rock_Icon.png");
        Image image = new Image(file.toURI().toString());
        if (playerType.equals("Human"))
            imgPlayer.setImage(image);
        if (playerType.equals("Bot"))
            imgBot.setImage(image);
    }
    private void callPaper(String playerType){
        File file = new File("data/paper-removebg-preview.png");
        Image image = new Image(file.toURI().toString());
        if (playerType.equals("Human"))
            imgPlayer.setImage(image);
        if (playerType.equals("Bot"))
            imgBot.setImage(image);
    }
    private void callScissor(String playerType){
        File file = new File("data/gZiXk0l-removebg-preview.png");
        Image image = new Image(file.toURI().toString());
        if (playerType.equals("Human"))
            imgPlayer.setImage(image);
        if (playerType.equals("Bot"))
            imgBot.setImage(image);
    }
    private void buttonImage(){
        File fileR = new File("data/The_Rock_Icon.png");
        Image imageR = new Image(fileR.toURI().toString());
        imgRock.setImage(imageR);

        File fileP = new File("data/paper-removebg-preview.png");
        Image imageP = new Image(fileP.toURI().toString());
        imgPaper.setImage(imageP);

        File fileS = new File("data/gZiXk0l-removebg-preview.png");
        Image imageS = new Image(fileS.toURI().toString());
        imgScissor.setImage(imageS);
    }

    private void dragScreen(){
        borderPane.setOnMousePressed(pressEvent -> {
            borderPane.setOnMouseDragged(dragEvent -> {
                borderPane.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                borderPane.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
            });
        });
    }
}
