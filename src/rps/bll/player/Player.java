package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;

//Java imports
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.random.RandomGenerator;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;

    private double rock;
    private double paper;
    private double scissor;
    private double p;
    private double r;
    private double s;
    private int tie;

    private ArrayList<Double> chance = new ArrayList();

    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;
    }


    @Override
    public String getPlayerName() {
        return name;
    }


    @Override
    public PlayerType getPlayerType() {
        return type;
    }


    /**
     * Decides the next move for the bot...
     * @param state Contains the current game state including historic moves/results
     * @return Next move
     */
    @Override
    public Move doMove(IGameState state) {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();
        if(results.isEmpty() || results.size() < 3)
        {
            return getRandomMove();
        }
        else
        {
            if (!chance.isEmpty())
            {
                chance.clear();
            }
            checkOldPlayerMoves(state);
            getChancesForMove(state);
           return getBotMove();
        }
    }
    private void checkOldPlayerMoves(IGameState state)
    {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();
        rock = 0;
        paper = 0;
        scissor = 0;
        tie = 0;
        for (Result r: results) {
            if(r.getType().equals(ResultType.Win))
                if (r.getWinnerPlayer().getPlayerType().equals(PlayerType.Human)) {
                    if (r.getWinnerMove().equals(Move.Rock) && !r.getLoserMove().equals(r.getWinnerMove())) {
                        rock++;
                    } else if (r.getWinnerMove().equals(Move.Paper) && !r.getLoserMove().equals(r.getWinnerMove())) {
                        paper++;
                    } else if (r.getWinnerMove().equals(Move.Scissor) && !r.getLoserMove().equals(r.getWinnerMove())) {
                        scissor++;
                    }
                }
            if(r.getWinnerPlayer().getPlayerType().equals(PlayerType.AI))
            {
                if (r.getLoserMove().equals(Move.Rock) && !r.getLoserMove().equals(r.getWinnerMove())) {
                    rock++;
                } else if (r.getLoserMove().equals(Move.Paper) && !r.getLoserMove().equals(r.getWinnerMove())) {
                    paper++;
                } else if (r.getLoserMove().equals(Move.Scissor) && !r.getLoserMove().equals(r.getWinnerMove())) {
                    scissor++;
                }
            }
            if(r.getType().equals(ResultType.Tie))
            {tie++;}
        }
    }

    private void getChancesForMove(IGameState state)
    {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();
        p = (double) ((paper/(results.size()-tie))*100f);
        r = (double) ((rock/(results.size()-tie))*100f);
        s = (double) ((scissor/(results.size()-tie))*100f);
        chance.add(p);
        chance.add(r);
        chance.add(s);
    }

    private Move getBotMove()
    {
      double ran = RandomGenerator.getDefault().nextDouble(100);
      if(ran <= chance.get(0))
      {
          return Move.Scissor;
      }
      else if(ran <= chance.get(0)+ chance.get(1))
      {
          return Move.Paper;
      }
      else
      {
          return Move.Rock;
      }
    }

    private Move getRandomMove(){
        int randomNumber = RandomGenerator.getDefault().nextInt(3);

        if (randomNumber == 0)
            return Move.Rock;
        else if (randomNumber == 1)
            return Move.Paper;
        else if (randomNumber == 2)
            return Move.Scissor;
        return null;
    }
}
