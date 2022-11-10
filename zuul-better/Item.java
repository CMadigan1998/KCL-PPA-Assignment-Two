import java.util.HashMap;
/**
 * This classe contains the values that will be sued to describe an item.
 * To create an item you  call the constructor and pass its name, type, wether the player can hold it or not,
 * welther the item contains its own seperate inventory and the items weight.
 *
 * @author Charlie Madigan (K19019003)
 * @version 201119
 */
public class Item
{
    private String name, type;
    private int value, stackAmount, itemWeight;
    private boolean playerCanHold, containsInventory;
    private HashMap<Integer,Item> inventory = new HashMap<>();
    public Item (String name, String type, boolean playerCanHold, boolean containsInventory, int  itemWeight) {
        this.name = name;
        this.type = type;
        this.playerCanHold = playerCanHold;
        this.containsInventory =  containsInventory;
        this.itemWeight  = itemWeight;
    }
    /**
     * This method simply returns the value stored in the varibale value.
     */
    public int getItemValue () {
        return value;
    }
    /**
     * this method simply returns the value store in the items weight varibale
     */
    public int getItemWeight () {
        return itemWeight;
    }
    /**
     * This method allows other classes to read the name of the item
     */
    public String getItemName () {
        return name;
    }
    /**
     * this method takes an Item object as a parameter
     * This method takes the parameter and adds it to the internal inventory
     */
    public void addItemToInventory (Item item) {
        inventory.put(inventory.size(), item);
    }
    /**
     * this method take a string as a parameter
     * This method takes the items name as a parameter, then looks for it within the inventory, if it exists
     * the method removes it from the hashmap
     */
    public Item removeItem (String name) {
        Item tmp;
        for (int i = 0; i < inventory.size(); i++) {
            tmp = inventory.get(i);
            if (tmp.getItemName().equals(name)) {
                inventory.remove(i);
                return tmp;
            }
        }
        return null;
    }
    /**
     * This item goes through the hashmap of the inventory and displayes the name of each of the items within
     * to allow the player to see whats inside the inventory.
     */
    public void displayInventory () {
        if (inventory.isEmpty()) {
            System.out.println("This item contains nothing.");
        } else {
            for (int i = 0; i < inventory.size(); i++) {
                Item tmp = inventory.get(i);
                System.out.println("Item : " + tmp.getItemName() + ": Weight :" + tmp.getItemWeight());
            }
        }
    }
}
