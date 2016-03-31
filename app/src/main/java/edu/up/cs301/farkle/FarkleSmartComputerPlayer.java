package edu.up.cs301.farkle;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * represents a computer player with a smart AI, designed to detect and select high scoring
 * combinations
 *
 * @author Alexa Baldwin
 * @author Levi Banks
 * @author Sara Perkins
 * @author Briahna Santillana
 * @version 22 March 2016
 */
public class FarkleSmartComputerPlayer extends GameComputerPlayer implements FarklePlayer, Tickable {
    /* ---=== Instance Variables ===---*/
    private FarkleState state;
    private String highestCombo;
    private int highestScore;
    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public FarkleSmartComputerPlayer(String name) {
        super(name);
    }

    /**
     * recieve game info and make move if it is the computer's turn
     * @param info game info to be interpreted if it is a FarkleState
     */
    @Override
    protected void receiveInfo(GameInfo info) {

    }

    /**
     * save the highest combo and its score to be used in making a move
     * @return true if new combo is picked
     */
    public boolean chooseDice() {
        return true;
    }

    /**
     * callback method: the timer ticked
     */
    protected void timerTicked() {

    }

    /**
     * decide whether to bank or roll
     * @return true if bank is the best
     */
    public boolean bankCurSelected () {
        return true;
    }
}