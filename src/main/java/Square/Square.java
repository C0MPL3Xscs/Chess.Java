/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Square;

/**
 *
 * @author Samuel Sampaio
 */
public class Square {
    
    private String positon;
    private Piece piece;

    public Square(String positon, Piece piece) {
        this.positon = positon;
        this.piece = piece;
    }
    
    //Verifies if the square contains a piece
    public boolean containsPiece(){
        return this.piece != null;
    }
    
    //Changes position to "Position"
    public void setPosition(String Position){
        this.positon = Position;
        if(piece!=null){
            this.piece.addMove(positon);
        }
    }
    
    //Returns position
    public String getPosition(){
        return this.positon;
    }

    //Returns the piece on the square
    public Piece getPiece() {
        return piece;
    }
    
    
}
