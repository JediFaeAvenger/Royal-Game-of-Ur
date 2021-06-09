import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;

//************************************************************************
//  BoardGameProjectGUI.java       Author: Lewis/Loftus, adapted by D. Heath and Sophie von Coelln
//
//  Runs the GUI portion of the Royal Game of Ur.
//*************************************************************************************

public class BoardGameProjectGUI extends Application
{
    private Button rollButton, aiButton;
    private Text rules, aiText;
    private int turnCount = 1;
    private int dieRoll;
    private boolean useAI = true;

    private DataStructure myBoard = new DataStructure();

    boardGamePiece white1 = new boardGamePiece(true, 2, 4);
    boardGamePiece black1 = new boardGamePiece(false, 0, 4);

    boardGameDie mainDie = new boardGameDie();

    private Circle die1 = new Circle(35);
    private Circle die2 = new Circle(35);
    private Circle die3 = new Circle(35);
    private Circle die4 = new Circle(35);
    Circle[] diceArray = {die1, die2, die3, die4};

    private boardGameAI newAI = new boardGameAI("g");

    Group root = new Group();

    Scene scene = new Scene(root, 1050, 700);

    //--------------------------------------------------------------------
    //  Displays the scene, sets up the move method via
    //  scene.setOnMousePressed, and sets the starting positions for
    //  the two pieces.
    //--------------------------------------------------------------------
    public void start(Stage primaryStage)
    {
        myBoard.setNumBlack(0, 4);
        myBoard.setNumWhite(2, 4);

        updater(root);
        primaryStage.setTitle("Board Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.setOnMousePressed(this::mouseClickerMove);
        System.out.println(myBoard.displayToString());
    }

    //--------------------------------------------------------------------
    //  Clears and redraws the entire screen every time it is called,
    //  based on the display data structure updated via the move method
    //  of the boardGamePiece.java class.
    //--------------------------------------------------------------------
    public void updater(Group root)
    {
        root.getChildren().clear();

        int xsize = myBoard.getXlimit();
        int ysize = myBoard.getYlimit();
        Rectangle boardSquare;

        // draw the board using squares for each value of the board array and filling them based on that value, and circles where the display array is 4 or 6
        for (int r = 0; r < xsize; r++)
        {
            for (int c = 0; c < ysize; c++)
            {
                Circle wCircle, bCircle;
                boardSquare = new Rectangle(100*c+175, 100*r+250, 100, 100);
                if (myBoard.isNull(r, c)) {
                    boardSquare.setFill(null);
                    root.getChildren().add(boardSquare);

                    if (myBoard.isWhite(r, c))
                    {
                        wCircle = new Circle(100 * c + 225, 100 * r + 300, 30);
                        wCircle.setFill(Color.WHITE);
                        wCircle.setStroke(Color.BLACK);
                        wCircle.setStrokeWidth(3.0);
                        root.getChildren().add(wCircle);
                    }
                    if (myBoard.isBlack(r, c))
                    {
                        bCircle = new Circle(100*c+225, 100*r+300, 30);
                        bCircle.setStroke(Color.BLACK);
                        bCircle.setStrokeWidth(3.0);
                        root.getChildren().add(bCircle);
                    }
                }
                else if (myBoard.isRosetta(r, c))
                {
                    boardSquare.setFill(Color.rgb(207, 226, 243));
                    boardSquare.setStrokeWidth(5);
                    boardSquare.setStroke(Color.BLACK);
                    root.getChildren().add(boardSquare);

                    if (myBoard.isWhite(r, c)) {
                        wCircle = new Circle(100 * c + 225, 100 * r + 300, 30);
                        wCircle.setFill(Color.WHITE);
                        wCircle.setStroke(Color.BLACK);
                        wCircle.setStrokeWidth(3.0);
                        root.getChildren().add(wCircle);
                    }
                    if (myBoard.isBlack(r, c))
                    {
                        bCircle = new Circle(100*c+225, 100*r+300, 30);
                        bCircle.setStroke(Color.BLACK);
                        bCircle.setStrokeWidth(3.0);
                        root.getChildren().add(bCircle);
                    }
                }
                else
                {
                    boardSquare.setFill(Color.WHITE);
                    boardSquare.setStrokeWidth(5);
                    boardSquare.setStroke(Color.BLACK);
                    root.getChildren().add(boardSquare);

                    if (myBoard.isWhite(r, c))
                    {
                        wCircle = new Circle(100*c+225, 100*r+300, 30);
                        wCircle.setFill(Color.WHITE);
                        wCircle.setStroke(Color.BLACK);
                        wCircle.setStrokeWidth(3.0);
                        root.getChildren().add(wCircle);
                    }
                    if (myBoard.isBlack(r, c))
                    {
                        bCircle = new Circle(100*c+225, 100*r+300, 30);
                        bCircle.setStroke(Color.BLACK);
                        bCircle.setStrokeWidth(3.0);
                        root.getChildren().add(bCircle);
                    }
                }
            }
        }

        // displays the rules of the game
        rules = new Text("Welcome to the royal game of Ur! The rules are simple: Each team has a separate path to take across the board. " +
                "Roll to \ndetermine how far you can move. Move your piece that number of spaces. " +
                "If you hit another player they are kicked off the \nboard and have to start over, " +
                "unless they are on a rosetta, in which case you can't move there, because they are " +
                "unable \nto be kicked off. Rosettas are invincible spaces, and grant you a second roll. " +
                "To win, get your piece off the board \nbefore the other player. Move your white piece by clicking on it, " +
                "and clicking on the black piece will cause the AI to \nmove it. The number of white circles in the dice is the number of spaces that can be moved.");
        rules.setFill(Color.WHITE);
        rules.setFont(Font.font("Calibri", 20));

        // displays whether or not the AI is currently being used
        aiText = new Text("");
        aiText.setFill(Color.WHITE);
        aiText.setFont(Font.font("Calibri", 20));

        // creates a button to toggle the use of the AI
        aiButton = new Button("Use AI?");
        aiButton.setOnAction(this::processAIButtonPress);

        // used to display the ai button and text more neatly
        HBox aiBox = new HBox(50);
        aiBox.getChildren().addAll(aiButton, aiText);
        aiBox.setAlignment(Pos.CENTER);

        // causes all the dice to start out as negative black circles
        for (Circle d : diceArray) {
            d.setStroke(Color.BLACK);
            d.setStrokeWidth(5);
        }

        // creates a button to roll the dice
        rollButton = new Button("roll");
        rollButton.setOnAction(this::processRollButtonPress);

        // used to display the dice more neatly
        VBox dice = new VBox(30);
        dice.setAlignment(Pos.CENTER);
        dice.getChildren().addAll(die1, die2, die3, die4, rollButton);

        // used to display the rules and ai box more neatly
        VBox toolbar = new VBox(10);
        toolbar.setStyle("-fx-background-color: #1e4852");
        toolbar.setAlignment(Pos.CENTER);
        toolbar.setPrefWidth(1050);
        toolbar.setPadding(new Insets(15, 10, 10, 10));
        toolbar.getChildren().addAll(rules, aiBox);

        // used to display all the hboxes and vboxes more neatly
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(toolbar);
        borderPane.setLeft(dice);
        borderPane.setMargin(dice, new Insets(20, 0, 0, 25));
        root.getChildren().add(borderPane);

        // adjusts the ai text to display whether or not it is currently in use
        if (useAI)
            aiText.setText("Now using the AI");
        else if (!useAI)
            aiText.setText("No longer using the AI");

        scene.setFill(Color.rgb(118, 165, 175));
    }

    //--------------------------------------------------------------------
    //  Mouse Listener to process when the roll button is pressed.
    //  Rolls all four dice, and updates the  circles that
    //  represent them.
    //--------------------------------------------------------------------
    public void processRollButtonPress(ActionEvent event)
    {
        dieRoll = mainDie.roll();

        for (Circle d : diceArray)
            d.setFill(Color.BLACK);

        for (int i = 0; i < dieRoll; i++)
            diceArray[i].setFill(Color.WHITE);
    }

    //--------------------------------------------------------------------
    //  Mouse Listener to process when the AI button is pressed.
    //  Toggles the AI from on to off, and updates the corresponding
    //  text.
    //--------------------------------------------------------------------
    public void processAIButtonPress(ActionEvent event)
    {
        if (useAI)
            useAI = false;
        else if (!useAI)
            useAI = true;

        if (useAI)
            aiText.setText("Now using the AI");
        else if (!useAI)
            aiText.setText("No longer using the AI");
    }

    //--------------------------------------------------------------------
    //  Mouse Listener to process when the scene is clicked, in the area
    //  of one of the pieces. Moves the piece the appropriate amount of
    //  of spaces based on the die roll.
    //--------------------------------------------------------------------
    public void mouseClickerMove(MouseEvent event)
    {
        int pieceRow, pieceCol;

        pieceCol = (int)(event.getX()-175) / 100;
        pieceRow = (int)(event.getY()-250) / 100;

        // moves the pieces as if two individual players are playing
        if (!useAI)
        {
            // checks if the clicked area corresponds to a piece
            if (white1.getRow() == pieceRow && white1.getCol() == pieceCol)
            {
                // moves the piece based on the dice roll
                for (int i = 0; i < dieRoll; i++)
                {
                    // makes sure to only reset the space if it is a previous move and not an opponent's piece
                    if (myBoard.isWhite(white1.getRow(), white1.getCol()))
                        myBoard.reset(white1.getRow(), white1.getCol());
                    white1.playerMove();

                    // kicks an opponent's piece on the space the piece lands on, as long as it is not a rosetta
                    if (i == dieRoll-1) {
                        if (myBoard.isBlack(white1.getRow(), white1.getCol()) && !myBoard.isRosetta(white1.getRow(), white1.getCol()))
                        {
                            black1.kick();
                            black1.setTotalMoves(0);
                            myBoard.setNumBlack(0, 0);
                        }
                    }

                    // sets the space to the variable that represents a white piece, as long as the piece does not go over the board
                    if (white1.getTotalMoves() > 14)
                        myBoard.reset(white1.getRow(), white1.getCol());
                    else
                        myBoard.setNumWhite(white1.getRow(), white1.getCol());
                }
            }

            // checks if the clicked area corresponds to a piece
            if (black1.getRow() == pieceRow && black1.getCol() == pieceCol)
            {
                // makes sure to only reset the space if it is a previous move and not an opponent's piece
                for (int i = 0; i < dieRoll; i++) {
                    if (myBoard.isBlack(black1.getRow(), black1.getCol()))
                        myBoard.reset(black1.getRow(), black1.getCol());
                    black1.AIMove();

                    // kicks an opponent's piece on the space the piece lands on, as long as it is not a rosetta
                    if (i == dieRoll-1) {
                        if (myBoard.isWhite(black1.getRow(), black1.getCol()))
                        {
                            white1.kick();
                            white1.setTotalMoves(0);
                            myBoard.setNumWhite(white1.getRow(), white1.getCol());
                        }
                    }

                    // sets the space to the variable that represents a white piece, as long as the piece does not go over the board
                    if (black1.getTotalMoves() > 14)
                        myBoard.reset(black1.getRow(), black1.getCol());
                    else
                        myBoard.setNumBlack(black1.getRow(), black1.getCol());
                }
            }
        }

        // moves the pieces as if one player is playing
        else if (useAI)
        {
            // checks if the clicked area corresponds to a piece
            if (white1.getRow() == pieceRow && white1.getCol() == pieceCol)
            {
                // makes sure to only reset the space if it is a previous move and not an opponent's piece
                for (int i = 0; i < dieRoll; i++)
                {
                    if (myBoard.isWhite(white1.getRow(), white1.getCol()))
                        myBoard.reset(white1.getRow(), white1.getCol());
                    white1.playerMove();

                    // kicks an opponent's piece on the space the piece lands on, as long as it is not a rosetta
                    if (i == dieRoll-1) {
                        if (myBoard.isBlack(white1.getRow(), white1.getCol())) {
                            black1.kick();
                            black1.setTotalMoves(0);
                            myBoard.setNumBlack(0, 0);
                        }
                    }

                    // sets the space to the variable that represents a white piece, as long as the piece does not go over the board
                    if (white1.getTotalMoves() > 14)
                        myBoard.reset(white1.getRow(), white1.getCol());
                    else
                        myBoard.setNumWhite(white1.getRow(), white1.getCol());
                }
            }

            // rolls again for the ai and sets the dice to the result of this
            dieRoll = mainDie.roll();
            for (int i = 0; i < dieRoll; i++)
                diceArray[i].setFill(Color.WHITE);

            // makes sure to only reset the space if it is a previous move and not an opponent's piece
            for (int i = 0; i < dieRoll; i++)
            {
                if (myBoard.isBlack(black1.getRow(), black1.getCol()))
                    myBoard.reset(black1.getRow(), black1.getCol());
                black1.AIMove();

                // kicks an opponent's piece on the space the piece lands on, as long as it is not a rosetta
                if (i == dieRoll - 1) {
                    if (myBoard.isWhite(black1.getRow(), black1.getCol())) {
                        white1.kick();
                        white1.setTotalMoves(0);
                        myBoard.setNumWhite(0, 0);
                    }
                }

                // sets the space to the variable that represents a white piece, as long as the piece does not go over the board
                if (black1.getTotalMoves() > 14)
                    myBoard.reset(black1.getRow(), black1.getCol());
                else
                    myBoard.setNumBlack(black1.getRow(), black1.getCol());
            }
        }

        // calls updater to display the updated array
        updater(root);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}