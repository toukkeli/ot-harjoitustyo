/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.domain;

/**
 *
 * @author toukk
 */
public class Game {
    private Map map;
    private Player player;
    
    public Game(){
        map = new Map(20,20);
    }
    
    public Map getMap(){
        return this.map;
    }
    
    public void setMap(Map map){
        this.map = map;
    }
    
    public Player getPlayer(){
        return this.player;
    }
    
    public void update(){
        this.getMap().update();
    }
    
    // In progress
    public void Moveplayer(){
        
    }
}
