package rps.bll.player;

//Project imports
import rps.bll.game.IGameState;
import rps.bll.game.Move;
import rps.bll.game.Result;

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
           return getRandomMove();
        }
    }
    private void checkOldPlayerMoves(IGameState state)
    {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();
        for (Result r: results)
        {
            if(r.getWinnerPlayer().getPlayerType().equals(PlayerType.Human) && !r.getWinnerPlayer().getPlayerType().equals(PlayerType.AI))
            {
                if(r.getWinnerMove().equals(Move.Rock) && !r.getLoserMove().equals(r.getWinnerMove()))
                {
                    rock++;
                } else if (r.getWinnerMove().equals(Move.Paper) && !r.getLoserMove().equals(r.getWinnerMove()))
                {
                    paper++;
                } else if (r.getWinnerMove().equals(Move.Scissor) && !r.getLoserMove().equals(r.getWinnerMove()))
                {
                    scissor++;
                }
            }
        }
    }

    private void getChancesForMove(IGameState state)
    {
        //Historic data to analyze and decide next move...
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();
        double p = (double) ((paper/results.size()*3)*100f);
        double r = (double) ((rock/results.size()*3)*100f);
        double s = (double) ((scissor/results.size()*3)*100f);
        System.out.println(paper+"p =" + p);
        System.out.println(rock+"r =" + r);
        System.out.println(scissor+"s = "+ s);
        System.out.println(p+r+s);
        chance.add(p);
        chance.add(r);
        chance.add(s);
        System.out.println(chance);
    }

    private Move getRandomMove()
    {
        int ran = RandomGenerator.getDefault().nextInt(3);
        if(ran == 0)
        {
            return Move.Rock;
        }
        else if (ran == 1)
        {
            return Move.Paper;
        }
        else
        {
            return Move.Scissor;
        }
    }
}
