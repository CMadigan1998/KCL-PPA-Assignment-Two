import java.util.HashMap;
/**
 * This  inventory class keeps track of the Item objects being stored within the hashmap
 * It also keeps track of the number of items in the  invetory as well as their combined weight and  the maximum weight of the 
 * invetory as set out by the constructor.
 *
 * @author Charlie Madigan (K19019003)
 * @version 201119
 */
public class Inventory
{
    private HashMap<Integer, Item> playerInventory;
    private int maximumWeight, currentInventoryWeight, numberOfItems;
    public  Inventory (int maximumWeight) {
        playerInventory = new HashMap<>();
        this.maximumWeight = maximumWeight;
        numberOfItems = 0;
    }
    /**
     * this method takes on parameter of type Item
     * The purpose of this method is to add the Item passed to it in the parameters to the inventorys items
     * The method first checks to see if this items weight combined with the inventorys total weight, is  larger than the maximum weight
     * if so the item isnt added and the player is notified.
     * if its not than the item is added to the inventory and the game tells the player  its been added.
     */
    public void addItemToInventory (Item item) {
        if ((item.getItemWeight() + currentInventoryWeight) > maximumWeight) {
            System.out.println("Cannot add item to inventory, too many items");
        } else {
            playerInventory.put (numberOfItems, item);
            numberOfItems++;
            System.out.println(item.getItemName() + " added to the inventory,");
        }
    }
    /**
     * this method take no parameters and goes through  the list of items  and output the name of each item to the console along
     * with its weight.
     */
    public void displayInventory () {
        if (playerInventory.isEmpty()) {
            System.out.println("Your inventory is  empty");
        } else {
            for(int i = 0; i < numberOfItems; i++) {
                Item tmp = playerInventory.get(i);
                System.out.println("Item : " + tmp.getItemName() + ": Weight :" + tmp.getItemWeight());
            }
        }
    }
    /**
     * this method takes one string as the parameter.
     * It goes through the list of items and checks to see if an object of type Item exists in  the list with a name the same
     * as the parameter, if not a false boolean is returned, if it is in there, than a true boolean is returned.
     */
    public boolean findInventoryItem (String name) {
        for (int i = 0;  i < playerInventory.size(); i++) {
            Item tmp = playerInventory.get(i);
            if (tmp.getItemName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
