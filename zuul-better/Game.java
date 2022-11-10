import java.util.HashMap;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 * 
 * modified by Charlie Madigan (K19019003)
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom, previousRoom;
    private Inventory playerInventory;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser =  new Parser();
        playerInventory = new Inventory (20);
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     * This method also generates the items that  can be found inside that room and associates them  to the room.
     */
    private void createRooms()
    {
        Room lobby, secondFloorHallway, library, masterBedroom, masterBedroomBathroom, loft, firstFloorHallway, livingRoom, 
        porch, office, officeBathroom, diningRoom, basementHallway, kitchen;

        // create the rooms
        lobby = new Room("Lobby");
        secondFloorHallway = new Room("Second Floor Hallway");
        library = new Room("Library");
        masterBedroom = new Room("Master Bedroom");
        masterBedroomBathroom = new Room("Master Bedroom Bathroom");
        loft = new Room("Loft");
        firstFloorHallway = new Room("First Floor Hallway");
        livingRoom = new Room ("Living Room");
        porch = new Room("Proch");
        office = new Room("Office");
        officeBathroom = new Room("Office Bathroom");
        diningRoom = new Room ("Dining Room");
        basementHallway =  new Room("Basement Hallway");
        kitchen = new Room ("Kitchen");

        lobby.setExit("east", diningRoom);
        lobby.setLockedDoors("east", 1);
        lobby.setExit("west", livingRoom);
        lobby.setLockedDoors("west", 1);
        lobby.setExit("upstairs", secondFloorHallway);
        lobby.setLockedDoors("upstairs", 1);
        Item lobbyCabinate =  new Item ("cabinate", "Decoartion", false, true, 30);
        Item lobbyCabinateGoldCoins = new Item("coin", "Score", true, false, 0);
        Item lobbyEastDoorKey  = new Item("eastkeylobby", "Key", true, false, 5);
        lobbyCabinate.addItemToInventory(lobbyCabinateGoldCoins);
        lobbyCabinate.addItemToInventory(lobbyEastDoorKey);
        Item lobbySmallTable = new Item("smalltable", "Decoration", false, true, 20);
        Item lobbyGoldBar =  new Item("goldbard", "Score", true, false, 0);
        Item lobbyFood = new Item("food", "nurishment", true, false, 2);
        lobbySmallTable.addItemToInventory(lobbyGoldBar);
        lobbySmallTable.addItemToInventory(lobbyCabinateGoldCoins);
        lobbySmallTable.addItemToInventory(lobbyFood);
        lobby.addItemToRoom(lobbyCabinate);

        livingRoom.setExit("north", porch);
        livingRoom.setLockedDoors("north", 0);
        livingRoom.setExit("east", lobby);
        livingRoom.setLockedDoors("east", 0);
        Item livingRoomTable = new Item("table", "Decoration", false, true, 50);
        Item mainExitKey = new Item ("mainexitkey", "key", true, false, 0);
        livingRoomTable.addItemToInventory(mainExitKey);
        livingRoom.addItemToRoom(livingRoomTable);

        porch.setExit("east", office);
        porch.setLockedDoors("east", 0);
        porch.setExit("south", livingRoom);
        porch.setLockedDoors("south", 0);

        office.setExit("south", officeBathroom);
        office.setLockedDoors("south", 0);
        office.setExit("west", porch);
        office.setLockedDoors("south", 0);

        officeBathroom.setExit("north", office);
        officeBathroom.setLockedDoors("north", 0);

        diningRoom.setExit("north", kitchen);
        diningRoom.setLockedDoors("north", 0);
        diningRoom.setExit("west", lobby);
        diningRoom.setLockedDoors("west", 0);
        Item diningRoomTable = new Item("table", "Decoration", false, true, 30);
        Item diningRoomPlate = new Item("plate", "Decoration", true, false, 4);
        Item diningRoomKnife = new Item("knife", "Decoration", true, false, 4);
        Item diningRoomFork = new Item("fork", "Decoration", true, false, 4);
        Item diningRoomGlass = new Item("glass", "Decoration", true, false, 4);
        Item diningRoomCoin = new Item("coin", "Score", true, false, 0);
        Item diningRoomGoldBar = new Item("goldbar", "Score", true, false, 0);
        Item diningRoomFood = new Item("food", "Nurishment", true, false, 2);
        diningRoomTable.addItemToInventory(diningRoomPlate);
        diningRoomTable.addItemToInventory(diningRoomKnife);
        diningRoomTable.addItemToInventory(diningRoomFork);
        diningRoomTable.addItemToInventory(diningRoomGlass);
        diningRoomTable.addItemToInventory(diningRoomCoin);
        diningRoomTable.addItemToInventory(diningRoomGoldBar);
        diningRoomTable.addItemToInventory(diningRoomFood);
        diningRoom.addItemToRoom(diningRoomTable);

        kitchen.setExit("south", diningRoom);
        kitchen.setLockedDoors("south", 0);
        Item kitchenOven = new Item("oven", "Decoration", false , true, 50);
        Item kitchenSink = new Item("sink", "Decoration", false , true, 50);
        Item kitchenWashingMachine = new Item("washingmachine", "Decoration", false , true, 50);
        Item kitchenTable = new Item("table", "Decoration", false , true, 50);
        Item kitchenPlate = new Item("plate", "Decoration", true, false, 4);
        Item kitchenKnife = new Item("knife", "Decoration", true, false, 4);
        Item kitchenFork = new Item("fork", "Decoration", true, false, 4);
        Item kitchenGlass = new Item("glass", "Decoration", true, false, 4);
        Item kitchenCoin = new Item("coin", "Score", true, false, 0);
        Item kitchenGoldBar = new Item("goldbar", "Score", true, false, 0);
        Item kitchenFood = new Item("food", "Nurishment", true, false, 2);
        Item livingRoomKey = new Item("westkeylobby", "key", true, false, 0);
        Item kitchenPot = new Item ("pot","Decoration", true, false, 0);
        kitchenTable.addItemToInventory(diningRoomPlate);
        kitchenTable.addItemToInventory(diningRoomKnife);
        kitchenTable.addItemToInventory(diningRoomFork);
        kitchenTable.addItemToInventory(diningRoomGlass);
        kitchenTable.addItemToInventory(diningRoomCoin);
        kitchenTable.addItemToInventory(diningRoomGoldBar);
        kitchenTable.addItemToInventory(diningRoomFood);
        kitchenTable.addItemToInventory(livingRoomKey);
        kitchenWashingMachine.addItemToInventory(diningRoomCoin);
        kitchenWashingMachine.addItemToInventory(diningRoomGoldBar);
        kitchenWashingMachine.addItemToInventory(livingRoomKey);
        kitchenSink.addItemToInventory(diningRoomPlate);
        kitchenSink.addItemToInventory(diningRoomKnife);
        kitchenSink.addItemToInventory(diningRoomGlass);
        kitchenSink.addItemToInventory(diningRoomFork);
        kitchen.addItemToRoom(kitchenOven);
        kitchen.addItemToRoom(kitchenTable);
        kitchen.addItemToRoom(kitchenWashingMachine);
        kitchen.addItemToRoom(kitchenSink);

        previousRoom = currentRoom = lobby;  // Sets the starting room of the  game
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getName());
        System.out.println(currentRoom.getExitString());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("back")) {
            backCMD();
        } else if (commandWord.equals("inventory")) {
            showInventory();
        } else  if (commandWord.equals("take")) {
            takeItem(command);
        } else if (commandWord.equals("inspect")) {
            inspect(command);
        } else if (commandWord.equals("describe")) {
            describe();
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("the house in a panic.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        if (currentRoom.isDoorLocked(command.getSecondWord()) == true) {
            System.out.println("This door is locked, maybe theres a key to open it somewhere around here?");
            String itemName = command.getSecondWord() + "key" + currentRoom.getName().toLowerCase();
            if (playerInventory.findInventoryItem(itemName)) {
                System.out.println("Oh, you;ve found the key! You can now enter this room.");
                currentRoom.unlockDoor(command.getSecondWord());
            } else {
                return;
            }
        }
        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            System.out.println(currentRoom.getName());
            System.out.println(currentRoom.getExitString());
            if (currentRoom.getName().equals("Lobby")) {
                checkEndCondition();
            }
        }
    }

    /**
     * This method checks to see if the  end  condition for the game has been met
     * if it has, then the game will move to the game over text.
     */

    private void checkEndCondition () {
        if (playerInventory.findInventoryItem("mainexitkey")) {
            endGame();
        }
    }

    /**
     * this method tells the player they have finished the game but instead of quitting, the player has the option to continue exploring.
     */

    private void  endGame () {
        System.out.println("Congratulations!\nYou found the key and was able to  escape from the house!\nThank you for playing!");
        System.out.println("You can continue to explore if you like but when your finish type 'quit' to leave.");
    }

    /**
     * This command allows the player to go backwards to the previous room
     */

    private void backCMD () {
        currentRoom = previousRoom;
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * this method calls a method from the player  inventory which outputs the current player inventory to the screen.
     */

    private void showInventory () {
        playerInventory.displayInventory();
    }

    /**
     * This method takes an item from one inventory and places it into  another
     * It first  checks to see if the command has the item [second token], if not ask the player what item they are looking for
     * Then it checks to see if the command has the location of the item [third  token] if not ask the player where they think the item is
     * Then it checks to see if the current room has the location of the item, if the location  isnt in this room, tell the player
     * Then it checks if the item exists in that object. if the item doesnt exist in that location tell the player the item isnt there
     * then it moves the object from one inventory to the other.
     */
    private void  takeItem (Command command) {
        if (!command.hasSecondWord()) {                             //Check if we have the second word in the command
            System.out.println("What item do you want to take?");
        } else {
            if (command.hasThirdWord() == false) {                  //Check if we have the third word in the command
                System.out.println("Where do you want to take the item from?");
            } else {
                if (currentRoom.getItemList().isEmpty()) {
                    System.out.println(command.getThirdWord() + " doesn't exist within this room.");
                } else {
                    for (int i = 0; i < currentRoom.getItemList().size(); i++){            //This block checks to see if the item is in the room
                        Item tmp = currentRoom.getInventoryItem(i);
                        if (tmp.getItemName().equals(command.getThirdWord())) {        //If the item is in this room, get the item inventory to find the item we want 
                            Item itemMoved = tmp.removeItem(command.getSecondWord());
                            if (itemMoved == null) {
                                System.out.println("Sorry, I couldn't find that item in there");
                            } else {
                                playerInventory.addItemToInventory(itemMoved);
                            }
                            return;
                        }
                    }
                    System.out.println("Sorry I couldnt find that item.");
                }
            }
        }
    }

    /**
     * This method allows the player to see the items in an objects inventory
     * It first checks to see if the location is specified [second token], if not ask the player what they would like to  inspect
     * Then it prints the full list of items out.
     */

    private void inspect(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("What would you like to inspect?");
        } else {
            for(int i = 0; i < currentRoom.getItemList().size(); i++){
                Item tmp = currentRoom.getInventoryItem(i);
                if (tmp.getItemName().equals(command.getSecondWord())) {
                    tmp.displayInventory();
                }
            }
        }
    }

    /**
     * this method allows the player to see what items currently exist within the room.
     */
    private void describe () {
        System.out.println("The room has the following objects: \n");
        currentRoom.printInventoryList();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
