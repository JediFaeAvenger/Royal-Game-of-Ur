//********************************************************************
//  GameLaunchText.java       Author: Lewis/Loftus
//
//  Displays numbers from the data structure multiplied by differnt #'s
//********************************************************************

import java.util.Scanner;

public class GameLaunchText
{
   //-----------------------------------------------------------------
   //  Instantiates a NumberData object and prints its contents 
   //-----------------------------------------------------------------
   public static void main (String[] args)
   {
      DataStructure myBoard = new DataStructure();
      Scanner scan = new Scanner (System.in);
      int turnCount = 1;
      boolean keepGoing = true;
      int movePiece = 0;

      // initializes the ai to the text version
      boardGameAI ai = new boardGameAI("t");

      //initializes the pieces of each color

      boardGamePiece white1 = new boardGamePiece(true, 2, 4);
      boardGamePiece white2 = new boardGamePiece(true, 2, 4);
      boardGamePiece white3 = new boardGamePiece(true, 2, 4);
      boardGamePiece white4 = new boardGamePiece(true, 2, 4);
      boardGamePiece white5 = new boardGamePiece(true, 2, 4);
      boardGamePiece white6 = new boardGamePiece(true, 2, 4);
      boardGamePiece white7 = new boardGamePiece(true, 2, 4);

      boardGamePiece black1 = new boardGamePiece(false, 0, 4);
      boardGamePiece black2 = new boardGamePiece(false, 0, 4);
      boardGamePiece black3 = new boardGamePiece(false, 0, 4);
      boardGamePiece black4 = new boardGamePiece(false, 0, 4);
      boardGamePiece black5 = new boardGamePiece(false, 0, 4);
      boardGamePiece black6 = new boardGamePiece(false, 0, 4);
      boardGamePiece black7 = new boardGamePiece(false, 0, 4);

      boardGameDie die = new boardGameDie();

      // prints the board
      System.out.println("Board: \n" + myBoard.boardToString());

      do
      {
         int rollNum = die.roll();

         if (turnCount % 2 != 0)
         {
            System.out.println("What piece would you like to move? 0 to quit");
            movePiece = scan.nextInt();
            System.out.println("roll: " + rollNum);
            if (movePiece == 0)
               keepGoing = false;

            // case switch to move the piece the user inputted
            switch (movePiece) {
               case 1:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(white1.getRow(), white1.getCol());
                     white1.playerMove();
                     if (i == rollNum-1) {
                        if (myBoard.isBlack(white1.getRow(), white1.getCol())) {
                           black1.kick();
                           black1.setTotalMoves(0);
                           myBoard.setNumBlack(0, 0);
                        }
                     }
                     myBoard.setNumWhite(white1.getRow(), white1.getCol());
                  }
                  break;
               case 2:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(white2.getRow(), white2.getCol());
                     white2.playerMove();
                     if (i == rollNum-1) {
                        if (myBoard.isBlack(white1.getRow(), white1.getCol())) {
                           black1.kick();
                           black1.setTotalMoves(0);
                           myBoard.setNumBlack(0, 0);
                        }
                     }
                     myBoard.setNumWhite(white2.getRow(), white2.getCol());
                  }
                  break;
               case 3:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(white3.getRow(), white3.getCol());
                     white3.playerMove();
                     if (i == rollNum-1) {
                        if (myBoard.isBlack(white1.getRow(), white1.getCol())) {
                           black1.kick();
                           black1.setTotalMoves(0);
                           myBoard.setNumBlack(0, 0);
                        }
                     }
                     myBoard.setNumWhite(white3.getRow(), white3.getCol());
                  }
                  break;
               case 4:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(white4.getRow(), white4.getCol());
                     white4.playerMove();
                     if (i == rollNum-1) {
                        if (myBoard.isBlack(white1.getRow(), white1.getCol())) {
                           black1.kick();
                           black1.setTotalMoves(0);
                           myBoard.setNumBlack(0, 0);
                        }
                     }
                     myBoard.setNumWhite(white4.getRow(), white4.getCol());
                  }
                  break;
               case 5:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(white5.getRow(), white5.getCol());
                     white5.playerMove();
                     if (i == rollNum-1) {
                        if (myBoard.isBlack(white1.getRow(), white1.getCol())) {
                           black1.kick();
                           black1.setTotalMoves(0);
                           myBoard.setNumBlack(0, 0);
                        }
                     }
                     myBoard.setNumWhite(white5.getRow(), white5.getCol());
                  }
                  break;
               case 6:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(white6.getRow(), white6.getCol());
                     white6.playerMove();
                     if (i == rollNum-1) {
                        if (myBoard.isBlack(white1.getRow(), white1.getCol())) {
                           black1.kick();
                           black1.setTotalMoves(0);
                           myBoard.setNumBlack(0, 0);
                        }
                     }
                     myBoard.setNumWhite(white6.getRow(), white6.getCol());
                  }
                  break;
               case 7:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(white7.getRow(), white7.getCol());
                     white7.playerMove();
                     if (i == rollNum-1) {
                        if (myBoard.isBlack(white1.getRow(), white1.getCol())) {
                           black1.kick();
                           black1.setTotalMoves(0);
                           myBoard.setNumBlack(0, 0);
                        }
                     }
                     myBoard.setNumWhite(white7.getRow(), white7.getCol());
                  }
                  break;
               default:
                  System.out.println("invalid choice");
                  break;
            }
         }

         else {
            System.out.println("roll: " + rollNum);
            movePiece = ai.doStuff();
            // case switch to move the piece the user inputted
            switch (movePiece) {
               case 1:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(black1.getRow(), black1.getCol());
                     black1.AIMove();
                     if (i == rollNum-1) {
                        if (myBoard.isWhite(black1.getRow(), black1.getCol())) {
                           white1.kick();
                           white1.setTotalMoves(0);
                           myBoard.setNumWhite(white1.getRow(), white1.getCol());
                        }
                     }
                     myBoard.setNumBlack(black1.getRow(), black1.getCol());
                  }
                  break;
               case 2:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(black2.getRow(), black2.getCol());
                     black2.AIMove();
                     if (i == rollNum-1) {
                        if (myBoard.isWhite(black1.getRow(), black1.getCol())) {
                           white1.kick();
                           white1.setTotalMoves(0);
                           myBoard.setNumWhite(white1.getRow(), white1.getCol());
                        }
                     }
                     myBoard.setNumBlack(black2.getRow(), black2.getCol());
                  }
                  break;
               case 3:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(black3.getRow(), black3.getCol());
                     black3.AIMove();
                     if (i == rollNum-1) {
                        if (myBoard.isWhite(black1.getRow(), black1.getCol())) {
                           white1.kick();
                           white1.setTotalMoves(0);
                           myBoard.setNumWhite(white1.getRow(), white1.getCol());
                        }
                     }
                     myBoard.setNumBlack(black3.getRow(), black3.getCol());
                  }
                  break;
               case 4:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(black4.getRow(), black4.getCol());
                     black4.AIMove();
                     if (i == rollNum-1) {
                        if (myBoard.isWhite(black1.getRow(), black1.getCol())) {
                           white1.kick();
                           white1.setTotalMoves(0);
                           myBoard.setNumWhite(white1.getRow(), white1.getCol());
                        }
                     }
                     myBoard.setNumBlack(black4.getRow(), black4.getCol());
                  }
                  break;
               case 5:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(black5.getRow(), black5.getCol());
                     black5.AIMove();
                     if (i == rollNum-1) {
                        if (myBoard.isWhite(black1.getRow(), black1.getCol())) {
                           white1.kick();
                           white1.setTotalMoves(0);
                           myBoard.setNumWhite(white1.getRow(), white1.getCol());
                        }
                     }
                     myBoard.setNumBlack(black5.getRow(), black5.getCol());
                  }
                  break;
               case 6:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(black6.getRow(), black6.getCol());
                     black6.AIMove();
                     if (i == rollNum-1) {
                        if (myBoard.isWhite(black1.getRow(), black1.getCol())) {
                           white1.kick();
                           white1.setTotalMoves(0);
                           myBoard.setNumWhite(white1.getRow(), white1.getCol());
                        }
                     }
                     myBoard.setNumBlack(black6.getRow(), black6.getCol());
                  }
                  break;
               case 7:
                  for (int i = 0; i < rollNum; i++) {
                     myBoard.reset(black7.getRow(), black7.getCol());
                     black7.AIMove();
                     if (i == rollNum-1) {
                        if (myBoard.isWhite(black1.getRow(), black1.getCol())) {
                           white1.kick();
                           white1.setTotalMoves(0);
                           myBoard.setNumWhite(white1.getRow(), white1.getCol());
                        }
                     }
                     myBoard.setNumBlack(black7.getRow(), black7.getCol());
                  }
                  break;
               default:
                  System.out.println("invalid choice");
                  break;
            }
         }

         // prints the updated board
         System.out.println(myBoard.displayToString());
         System.out.println("now it is player " + ((turnCount % 2)+1) + "'s turn\n");
         turnCount++;

      } while (keepGoing);
   }
}