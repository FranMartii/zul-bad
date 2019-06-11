import java.util.*;
public class Room 
{
    private String description;
    private HashMap<String,Room> salidas;
    private HashMap<String,Item> coleccionItems;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description){
        this.description = description;
        salidas = new HashMap<>();
        coleccionItems = new HashMap<>();
    }
    
    public void addItem(String nombre,Item item){
        coleccionItems.put(nombre,item);
    }
    
    public Item getItem(String nombre){
        return coleccionItems.get(nombre);
    }

    public String getItemString(){
        String infoItems = "Items: \n";
        for(String i : coleccionItems.keySet()){
            infoItems += i + coleccionItems.get(i).getInfoItem() + "\n";
        }       
        return infoItems;
    }
    
    public void setExits(String direccion,Room sala){
        salidas.put(direccion, sala);
    }

    public Room getExit(String descripcion){
        return salidas.get(descripcion);
    }

    /**
     * Devuelve la información de las salidas existentes
     * Por ejemplo: "Exits: north east west"
     *
     * @return Una descripción de las salidas existentes.
     */
    public String getExitString(){
        String salidasExistentes = "Exits: ";
        for(String i : salidas.keySet()){
            salidasExistentes += i + " ";
        }       
        return salidasExistentes;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription(){
        return description;
    }

    /**
     * Devuelve un texto con la descripcion larga de la habitacion del tipo:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return Una descripcion de la habitacion incluyendo sus salidas
     */
    public String getLongDescription(){
        return "You are " + description + "\n\n"
        + getItemString()
        + getExitString();
    }
}
