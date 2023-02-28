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
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

import static rps.bll.game.Move.Rock;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

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
        playerName = getNameFromUser();


        human = new Player(playerName, PlayerType.Human);
        bot = new Player("SkyNet", PlayerType.AI);

        ge = new GameManager(human, bot);

        setNames(bot.getPlayerName(), human.getPlayerName().toString());
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




        ge.getGameState().getHistoricResults().forEach((result) -> {
            System.out.println(getResultAsString(result));
            changeImg("Bot", getBotMove(result));
        });


    }

    public String getBotMove(Result result){
        if (result.getLoserPlayer().getPlayerName().equals(bot.getPlayerName())) {
            return result.getLoserMove().name();
        }
        else if (result.getWinnerPlayer().getPlayerName().equals(bot.getPlayerName())) {
            return result.getWinnerMove().name();
        }

        return null;
    }

    private String getResultAsString(Result result) {
        String statusText = result.getType() == ResultType.Win ? "wins over " : "ties ";

        return "Round #" + result.getRoundNumber() + ":" +
                result.getWinnerPlayer().getPlayerName() +
                " (" + result.getWinnerMove() + ") " +
                statusText + result.getLoserPlayer().getPlayerName() +
                " (" + result.getLoserMove() + ")!";
    }
    public void handleExit(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    public void HandleHistory(ActionEvent event) {

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
        File file = new File("data/the_Rock-removebg-preview.png");
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


}
