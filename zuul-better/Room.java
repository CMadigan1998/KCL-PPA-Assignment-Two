import java.util.Set;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * modified by Charlie Madigan (K19019003)
 */

public class Room 
{
    private String name;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private HashMap<Integer, Item> roomItems;
    private HashMap<String, Integer> lockedDoors;

    /**
     * Create a room described "description" and named "name". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".   
     * @param description The room's description.
     */
    public Room(String name) 
    {
        this.name = name;
        exits = new HashMap<>();
        lockedDoors = new HashMap<>();
        roomItems = new HashMap<>();
        
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    /**
     * this method takes two parameters, String and Integer
     * This method allows the creation of a hashmap that uses a string represting the door
     * and an integer as locked or not to represent a locked door
     * 0 = Unlocked door
     * 1 = Locked door
     */
    public void setLockedDoors (String direction, Integer locked) {
        if (locked < 0 || locked  > 1) {
            System.out.println("VALUE OUT OF RANGE (0-1)");
        } else {
            lockedDoors.put(direction, locked);
    }
    }
    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getName()
    {
        return name;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are "  + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    /**
     * this method simply returns  the list of all the items within the room
     */
    public HashMap getItemList (){
        return roomItems;
    }
    /**
     * this takes an int value as a parameter and returns the Item object associated with it.
     * When  this is called an Item object is returned that is associated with the index past to it in the form
     * of a parameter. 
     */
    public Item getInventoryItem (int itemIndex) {
        return roomItems.get(itemIndex);
    }
    /**
     * when this method is called, the names of all the items within the inventory list are printed to the consle.
     */
    public void printInventoryList () {
        for(int i = 0; i < roomItems.size();  i++){
            System.out.println(roomItems.get(i).getItemName()  + "\n");
        }
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    /**
     * this method takes one string as a paramter and returns on boolean
     * The purpose of this method is to check if the exit identified by the direction variable is locked
     * true =  the door is locked
     * false = the door is unlocked
     */
    public boolean isDoorLocked (String direction) {
        if (lockedDoors.get(direction) == 1) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * This method takes an Item object as the parameter
     * The method adds the item specified by the parameter to the inventory.
     */
    public void addItemToRoom (Item item) {
        roomItems.put(roomItems.size(),item);
    }
    /**
     * thie method takes one string variable 
     * The purpose of this method  is to change the value associated with a locked exit to zero indicating that  it is unlocked
     */
    public void unlockDoor (String direction) {
        lockedDoors.replace(direction, 0);
    }
}

