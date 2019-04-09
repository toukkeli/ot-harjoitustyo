/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike.ui;

import roguelike.domain.Game;

/**
 *
 * @author toukk
 */
public class App {

    
    public static void main(String [] args){
        Game game = new Game();
        Textapp app = new Textapp(game);
        
        game.getMap().setCharacter(3, 3, game.getPlayer());
        app.drawMap();
    }
}
