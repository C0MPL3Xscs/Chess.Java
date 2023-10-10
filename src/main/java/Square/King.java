/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Square;

import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.text.Position;

/**
 *
 * @author Samuel Sampaio
 */

public class King extends Piece{
    

    public King(boolean Black,String Position) {
        this.Black = Black;
        
        lastMoves.add(Position);
        
        if(Black){
            this.icon = "./src/main/java/Utils/BK.png";
        }else{
            this.icon = "./src/main/java/Utils/WK.png";
        }
        
    }
    
    //Verifies if the piece has already moved
    public boolean isFirstMove(){
        return this.lastMoves.size() == 1;
    }
    
}
