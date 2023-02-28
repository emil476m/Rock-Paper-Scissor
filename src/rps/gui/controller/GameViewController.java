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
        doMove("Rock");
        CallRock();
    }

    @FXML
    private void handlePaper(ActionEvent actionEvent) {
        doMove("Paper");
        CallPaper();
    }

    @FXML
    private void handleScissor(ActionEvent actionEvent) {
        doMove("Scissor");
        CallScissor();
    }

    private String getNameFromUser(){
        String defaultName = "Dave";

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

    public void doMove(String playerMove) {
        ge.playRound(Move.valueOf(playerMove));

        ge.getGameState().getHistoricResults().forEach((result) -> {
            System.out.println(getResultAsString(result));
        });

    }

    private void setPlayerMove(String playerMove){
        this.playerMove = playerMove;
    }

    private String getPlayerMove() {
        return playerMove;
    }

    public String getResultAsString(Result result) {
        String statusText = result.getType() == ResultType.Win ? "wins over " : "ties ";

        return "Round #" + result.getRoundNumber() + ":" +
                result.getWinnerPlayer().getPlayerName() +
                " (" + result.getWinnerMove() + ") " +
                statusText + result.getLoserPlayer().getPlayerName() +
                " (" + result.getLoserMove() + ")!";
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
