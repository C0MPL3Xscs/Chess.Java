/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Square;

import java.util.ArrayList;

/**
 *
 * @author Samuel Sampaio
 */
public class Piece{
    public boolean Black;
    public String icon;
    public ArrayList<String> lastMoves = new ArrayList<String>();
    
    public String getIcon(){
        return icon;
    }
    
    //Registers a move
    public void addMove(String move){
        lastMoves.add(move);
    }
    
}
