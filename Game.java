import java.util.*;
public class Game 
{
    private Parser parser;
    private Player player;
   
    /**
     * Create the game and initialise its internal map.
     */
    public Game(){
        player = new Player(createRooms());
        parser = new Parser(); 
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms(){
        Room inicio, pasilloCeldas, celdaVacia1,celdaVacia2, pasilloExterior,vestuarioGuardias, 
        comedorReclusos,enfermeria,ventanaAbierta,salidaEnfermeria,patioReclusos,tunelPatio,salidaPatio;

        Item mochila,medicamentos,comida,itemInutil,itemPesado;
        mochila = new Item("Mochila",1,50,true,true);
        medicamentos = new Item("Medicamentos",5,10,true,false);
        comida = new Item("Comida",2,5,true,false);
        itemInutil = new Item("Inutil",10,0,false,false);
        itemPesado = new Item("Pesas",50,0,false,false);

        // create the rooms
        inicio = new Room("Tu celda de la prision");
        pasilloCeldas = new Room("Pasillo donde se encuentan las celdas");
        celdaVacia1 = new Room("Celda vacia enfrente de la tuya");
        celdaVacia2 = new Room("Celda de tu compañero, esta vacia");
        pasilloExterior = new Room("Pasillo exterior separado de las celdas");
        vestuarioGuardias = new Room("Vestuario de los guardias de la prision");
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
        vestuarioGuardias.addItem("Mochila",mochila);

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
        pasilloExterior.setExits("east",vestuarioGuardias);
        comedorReclusos.setExits("south", pasilloExterior);
        enfermeria.setExits("east",pasilloExterior);
        enfermeria.setExits("south", ventanaAbierta);
        ventanaAbierta.setExits("north",enfermeria);
        ventanaAbierta.setExits("south", salidaEnfermeria);
        patioReclusos.setExits("north", pasilloCeldas);
        patioReclusos.setExits("east", tunelPatio);
        tunelPatio.setExits("east",salidaPatio);
        tunelPatio.setExits("west", patioReclusos);
        // casilla de salida

        return inicio;
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
        player.look();
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
            player.goRoom(command);            
        }
        else if (commandWord.equals("look")) {  
            player.look();
        }
        else if (commandWord.equals("eat")) {
            player.eat();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("back")) {
            player.goBack();            
        }
        else if (commandWord.equals("take")) {
            player.take(command);
        }
        else if (commandWord.equals("drop")) {
            player.drop(command);
        }
        else if (commandWord.equals("items")) {
            player.getItems();
        }
        else if (commandWord.equals("equipar")) {
            player.equipar(command);
        }
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
        System.out.println("around at the prision.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
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
