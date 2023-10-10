/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

/**
 *
 * @author Samuel Sampaio
 */

public class Player {
    String name;
    

    public Player(String name) {
        this.name = name;
    }
    
    //Returns player name
    public String getName() {
        return name;
    }
    
    //Sets player name to "name"
    public void setName(String name){
        this.name = name;
    }
    
}
