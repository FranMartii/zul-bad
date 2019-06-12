import java.util.*;
public class Player
{

    private Room currentRoom;
    private Stack<Room> stackSalas;
    private HashMap<String,Item> mochila;
    private int pesoMochila;
    private int pesoMax;

    public Player(Room inicio){
        stackSalas = new Stack();
        currentRoom = inicio;
        mochila = new HashMap<>();
        pesoMochila = 0;
        pesoMax = 10;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command){
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
            look();
        }
    }

    public void goBack() 
    {
        if(!stackSalas.empty()){             
            currentRoom = stackSalas.pop();
        }
        else{
            System.out.println("No hay salas antes de esta"); 
        }
        look();
    }

    public void look() {   
        System.out.println(currentRoom.getLongDescription());
    }

    public void eat(){
        System.out.println("You have eaten now and you are not hungry any more");
    }

    public void take(Command command) {
        String item = command.getSecondWord();
        if(currentRoom.getItem(item) != null){
            if(pesoMochila < pesoMax){            
                mochila.put(item,currentRoom.getItem(item));
                currentRoom.eliminarItem(item);
                pesoMochila += mochila.get(item).getPeso();
                System.out.println("Peso: (" + pesoMochila + "/" + pesoMax + ")\n");
            }
            else{
                System.out.println("Peso excedido, no puedes cargar mas objetos. Tira alguno primero.\n");
            }
        }
        else{
            System.out.println("No se encontraron items con ese nombre");
        }
    }

    public void drop(Command command) {  
        String item = command.getSecondWord();
        if(mochila.get(item) != null){
            pesoMochila -= mochila.get(item).getPeso();
            currentRoom.addItem(item,mochila.get(item));
            mochila.remove(item); 
            System.out.println("Peso: (" + pesoMochila + "/" + pesoMax + ")\n");
        }
    }

    public void getItems(){
        System.out.println(currentRoom.getItemString());
    }
}
