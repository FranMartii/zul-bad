import java.util.*;
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> stackSalas;
    private HashMap<String,Item> mochila;
    private int pesoMochila;
    private static int PESOMAX = 10;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        stackSalas = new Stack();
        mochila = new HashMap<>();
        pesoMochila = 0;
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room inicio, pasilloCeldas, celdaVacia1,celdaVacia2, pasilloExterior, 
        comedorReclusos,enfermeria,ventanaAbierta,salidaEnfermeria,
        patioReclusos,tunelPatio,salidaPatio;

        Item medicamentos,comida,itemInutil,itemPesado;
        medicamentos = new Item("Medicamentos",5,50,true);
        comida = new Item("Comida",2,25,true);
        itemInutil = new Item("Inutil",10,0,false);
        itemPesado = new Item("Pesas",50,0,false);

        // create the rooms
        inicio = new Room("Tu celda de la prision");
        pasilloCeldas = new Room("Pasillo donde se encuentan las celdas");
        celdaVacia1 = new Room("Celda vacia enfrente de la tuya");
        celdaVacia2 = new Room("Celda de tu compa�ero, esta vacia");
        pasilloExterior = new Room("Pasillo exterior separado de las celdas");
        comedorReclusos = new Room("Comedor de reclusos");
        enfermeria = new Room("Enfermeria de la prision");
        ventanaAbierta = new Room("Saliente de ventana de la enfermeria");
        salidaEnfermeria = new Room("Salida de la prision por la enfermeria");
        patioReclusos = new Room("Patio exterior de los reclusos");
        tunelPatio = new Room("Tunel escondido para escapar de la prision");
        salidaPatio = new Room("Salida de la prision a traves del tunel del patio");

        // initialise room items

        comedorReclusos.addItem("Comida",comida);
        enfermeria.addItem("Medicamentos",medicamentos);
        pasilloCeldas.addItem("Inutil",itemInutil);
        patioReclusos.addItem("Pesas",itemPesado);

        // initialise room exits

        inicio.setExits("east", pasilloCeldas);
        pasilloCeldas.setExits("north",pasilloExterior);
        pasilloCeldas.setExits("east",celdaVacia1);
        pasilloCeldas.setExits("south",patioReclusos);
        pasilloCeldas.setExits("west",inicio);
        pasilloCeldas.setExits("suroeste",celdaVacia2);
        celdaVacia1.setExits("west", pasilloCeldas);
        pasilloExterior.setExits("north",comedorReclusos);
        pasilloExterior.setExits("west",enfermeria);
        comedorReclusos.setExits("south", pasilloExterior);
        enfermeria.setExits("east",pasilloExterior);
        enfermeria.setExits("south", ventanaAbierta);
        ventanaAbierta.setExits("north",enfermeria);
        ventanaAbierta.setExits("south", salidaEnfermeria);
        patioReclusos.setExits("north", pasilloCeldas);
        patioReclusos.setExits("east", tunelPatio);
        tunelPatio.setExits("east",salidaPatio);
        tunelPatio.setExits("west", patioReclusos);

        currentRoom = inicio;  // casilla de salida
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
        printLocationInfo();
    }

    private void printLocationInfo() 
    {
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
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
        else if (commandWord.equals("look")) {  
            look();
        }
        else if (commandWord.equals("eat")) {
            System.out.println("You have eaten now and you are not hungry any more");
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("back")) {
            goBack();
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("items")) {
            getItems();
        }
        return wantToQuit;
    }

    //comandos

    private void look() {   
        System.out.println(currentRoom.getLongDescription());
    }

    private void take(Command command) {
        String item = command.getSecondWord();
        if(currentRoom.getItem(item) != null){
            if(pesoMochila < PESOMAX){            
                mochila.put(item,currentRoom.getItem(item));
                currentRoom.eliminarItem(item);
                pesoMochila += mochila.get(item).getPeso();
                System.out.println("Peso: (" + pesoMochila + "/" + PESOMAX + ")\n");
            }
            else{
                System.out.println("Peso excedido, no puedes cargar mas objetos. Tira alguno primero.\n");
            }
        }
        else{
            System.out.println("No se encontraron items con ese nombre");
        }
    }

    private void drop(Command command) {  
        String item = command.getSecondWord();
        pesoMochila -= mochila.get(item).getPeso();
        currentRoom.addItem(item,mochila.get(item));
        mochila.remove(item); 
        System.out.println("Peso: (" + pesoMochila + "/" + PESOMAX + ")\n");
    }

    private void getItems(){
        System.out.println(currentRoom.getItemString());
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
        System.out.println("around at the prision.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            stackSalas.addElement(currentRoom);
            currentRoom = nextRoom;   
            printLocationInfo();
        }
    }

    private void goBack() 
    {
        if(!stackSalas.empty()){             
            currentRoom = stackSalas.pop();
            printLocationInfo();
        }
        else{
            System.out.println("No hay salas antes de esta"); 
            printLocationInfo();
        }
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
