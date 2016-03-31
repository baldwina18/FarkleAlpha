package edu.up.cs301.farkle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.R;

/**
 * represents a human player in the game, allowing the person to interact with the
 * gui in order to play
 *
 * @author Alexa Baldwin
 * @author Levi Banks
 * @author Sara Perkins
 * @author Briahna Santillana
 * @version 22 March 2016
 */
public class FarkleHumanPlayer extends GameHumanPlayer implements View.OnClickListener {
    /* ---=== Instance Variables ===--- */
    // text views
    protected TextView p0scoreText, p1scoreText, runningTotalText;

    // buttons
    protected Button rollDiceButton, bankPointsButton;
    protected ImageButton[] diceButtons = new ImageButton[6];

    // image res id's
    protected int[] diceWhiteResID = {R.drawable.white_one_die,R.drawable.white_two_die,
                                      R.drawable.white_three_die, R.drawable.white_four_die,
                                      R.drawable.white_five_die,R.drawable.white_six_die};
    protected int[] diceRedResID = {R.drawable.red_one_die,R.drawable.red_two_die,
                                    R.drawable.red_three_die, R.drawable.red_four_die,
                                    R.drawable.red_five_die,R.drawable.red_six_die};
    protected int[] imageButtonId = {R.id.dieOne, R.id.dieTwo, R.id.dieThree, R.id.dieFour,
                                     R.id.dieFive, R.id.dieSix};

    // game play variables
    private GameMainActivity myActivity;
    private FarkleState myState;

    /**
     * constructor for a human player
     *
     * @param name name of the player
     */
    public FarkleHumanPlayer(String name) {
        super(name);
    }

    /**
     * Returns the GUI's top view object
     *
     * @return the top object in the GUI's view hierarchy
     */
    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method when we get a message (e.g., from the game)
     *
     * @param info the message -- to be used if a game state
     */
    @Override
    public void receiveInfo(GameInfo info) {
        // ignore the message if it's not a FarkleState message
        if (!(info instanceof FarkleState)) return;

        // update our state; then update the display
        this.myState = (FarkleState)info;
        updateDisplay();

        // set the display
        /*
        if (info instanceof PigGameState) {
            if (this.playerNum == 0) {
                playerScoreTextView.setText("" +((PigGameState) info).getPlayer0Score());
                oppScoreTextView.setText("" +((PigGameState) info).getPlayer1Score());
            }
            else {
                playerScoreTextView.setText("" +((PigGameState) info).getPlayer1Score());
                oppScoreTextView.setText("" +((PigGameState) info).getPlayer0Score());
            }
            turnTotalTextView.setText("" + ((PigGameState) info).getCurTotal());
            int curVal = ((PigGameState) info).getCurDie();
            if (curVal == 1) {
                dieImageButton.setImageResource(R.drawable.face1);
            } else if (curVal == 2) {
                dieImageButton.setImageResource(R.drawable.face2);
            }else if (curVal == 3) {
                dieImageButton.setImageResource(R.drawable.face3);
            }else if (curVal == 4) {
                dieImageButton.setImageResource(R.drawable.face4);
            }else if (curVal == 5) {
                dieImageButton.setImageResource(R.drawable.face5);
            }else if (curVal == 6) {
                dieImageButton.setImageResource(R.drawable.face6);
            }


        }
        else {
            this.flash(0xFFFF80FF, 50);
            return;
        }*/

    }

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     * sets all of the view variables
     *
     * @param activity the activity under which we are running
     */
    public void setAsGui(GameMainActivity activity) {

        myActivity = activity;
        activity.setContentView(R.layout.farkle_human_player);

        // text views
        p0scoreText = (TextView)activity.findViewById(R.id.p0CurrentScore);
        p1scoreText = (TextView)activity.findViewById(R.id.p1CurrentScore);
        runningTotalText = (TextView)activity.findViewById(R.id.bankScore);

        // buttons
        rollDiceButton = (Button)activity.findViewById(R.id.rollDiceButton);
        bankPointsButton = (Button)activity.findViewById(R.id.bankPointsButton);
        ImageButton dieOne = (ImageButton)activity.findViewById(R.id.dieOne);
        ImageButton dieTwo = (ImageButton)activity.findViewById(R.id.dieTwo);
        ImageButton dieThree = (ImageButton)activity.findViewById(R.id.dieThree);
        ImageButton dieFour = (ImageButton)activity.findViewById(R.id.dieFour);
        ImageButton dieFive = (ImageButton)activity.findViewById(R.id.dieFive);
        ImageButton dieSix = (ImageButton)activity.findViewById(R.id.dieSix);
        diceButtons[0] = dieOne;  diceButtons[1] = dieTwo;  diceButtons[2] = dieThree;
        diceButtons[3] = dieFour; diceButtons[4] = dieFive; diceButtons[5] = dieSix;

        // listeners
        for(ImageButton ib : diceButtons) { ib.setOnClickListener(this); }
        rollDiceButton.setOnClickListener(this);
        bankPointsButton.setOnClickListener(this);

    }

    /**
     * handle all click events -- actions made by player
     * @param button view that was clicked
     */
    public void onClick(View button) {

        // if we are not yet connected to a game, ignore
        if (game == null) return;
        int id = button.getId();

        // Construct the action and send it to the game
        GameAction action = null;

        if (id == R.id.bankPointsButton) {
            action = new BankPointsAction(this);
        }
        else if (id == R.id.rollDiceButton) {
            action = new RollAction(this);
        }
        // Checks for all image buttons
        else {
            for(int i=0; i<imageButtonId.length; i++) {
                if(id == imageButtonId[i])
                    action = new SelectDieAction(this,i);
            }
        }
        if (action != null) {
            game.sendAction(action); // send action to the game
        }
    }

    /**
     * display the die using the image id's
     */
    public void displayDie() {

    }

    /**
     * set the display based on the current state
     */
    protected void updateDisplay() {

    }

}
