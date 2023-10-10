/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import Square.Square;
import java.util.ArrayList;

/**
 *
 * @author Samuel Sampaio
 */

public class Game {
    private Player player1;
    private Player player2;
    private Board board;

    //Default guest game mode
    public Game() {
        this.player1 = new Player("Player1");
        this.player2 = new Player("Player2");
        this.board = new Board();
    }
    
    //Returns the number of pieces in the board
    public int getNumberPieces(){
        return board.numberPieces();
    }
    
    //Moves a piece
    public void movePiece(String previous, String next){
        this.board.movePiece(previous, next);
    }
    
    //Returns player1 Object
    public Player getPlayer1() {
        return player1;
    }

    //Returns player2 Object
    public Player getPlayer2() {
        return player2;
    }
    
    //Returns the board
    public Square[][] getBoard(){
        return this.board.getBoard();
    }
    
    //Returns the square in position "Position"
    public Square getSquareAt(String Position){
        return board.getSquare(Position);
    }
    
    //returns a list of possible moves for the piece in the position "Position
    public ArrayList<String> possibleMoves(String Position){
        return this.board.possibleMoves(Position);
    }
    
    //Verifies if a position has a piece on it
    public boolean verifyPosition(String position){
        return board.getSquare(position).containsPiece();
    }
}
