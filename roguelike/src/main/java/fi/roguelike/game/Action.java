/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.game;

/**
 * This enumerator contains descriptions of all the possible actions player can
 * instruct the character to make These actions are resolved/used in the
 * Game-class and are read from the player in UI
 *
 * @author toukk
 */
public enum Action {
    GO_NORTH,
    GO_NORTHEAST,
    GO_EAST,
    GO_SOUTHEAST,
    GO_SOUTH,
    GO_SOUTHWEST,
    GO_WEST,
    GO_NORTHWEST,
    GO_DOWN,
    OPEN_INVENTORY,
    QUIT_GAME;
}
