/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.roguelike.map;

/**
 * Describes the 3 types of tiles that can exist in the dungeon They don't
 * themselves have any functionality. That is handled in Map-class and
 * Game-class
 *
 * @author toukk
 */
public enum Tile {
    FLOOR,
    WALL,
    STAIRS;
}
