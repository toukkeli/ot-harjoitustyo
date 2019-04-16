/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.character.behavior;

import fi.roguelike.character.Character;
import java.util.ArrayList;

/**
 *
 * @author toukk
 */
public abstract class Behavior {

    private Character character;

    public Behavior(Character c) {
        this.character = c;
    }

    public Character getCharacter() {
        return this.character;
    }

    abstract public String onActivate();

    abstract public String attack(Character other);

}
