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
public class Player implements Character {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public Player() {
        this.name = "Ukko-pekka";
    }

    @Override
    public String getName() {
        return this.name;
    }
}
