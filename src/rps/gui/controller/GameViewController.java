package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {

    @FXML
    private ImageView imgPlayer;
    @FXML
    private Label lblPlayer;
    @FXML
    private ImageView imgBot;
    @FXML
    private Label lblBot;
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

    private String playerMove;

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
    public GameViewController(){


    }

    public void setNames(String botName, String humanName){
        lblPlayer.setText(humanName);
        lblBot.setText(botName);
    }

    @FXML
    private void handleRock(ActionEvent actionEvent) {

    }

    @FXML
    private void handlePaper(ActionEvent actionEvent) {
    }

    @FXML
    private void handleScissor(ActionEvent actionEvent) {
    }

    private String getNameFromUser(){
        TextInputDialog dialog = new TextInputDialog("");
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

    public void startGame() {



    }

    private void setPlayerMove(String playerMove){
        this.playerMove = playerMove;
    }

    private String getPlayerMove() {
        return playerMove;
    }

    /**
     * Provides a custom formatted string representation of a Result
     *
     * @param result
     * @return
     */
    public String getResultAsString(Result result) {
        String statusText = result.getType() == ResultType.Win ? "wins over " : "ties ";

        return "Round #" + result.getRoundNumber() + ":" +
                result.getWinnerPlayer().getPlayerName() +
                " (" + result.getWinnerMove() + ") " +
                statusText + result.getLoserPlayer().getPlayerName() +
                " (" + result.getLoserMove() + ")!";
    }

}
