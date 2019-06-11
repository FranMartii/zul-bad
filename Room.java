import java.util.*;
public class Room 
{
    private String description;
    private Item itemActual;
    private HashMap<String,Room> salidas;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description,Item item) 
    {
        this.description = description;
        this.itemActual = item;
        salidas = new HashMap<>();
    }

    private Item getItem(){
        return itemActual;
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
        String infoItem = "";
        if(itemActual != null){
            infoItem = itemActual.getInfoItem();
        }
        return "You are " + description + "\n\n"
        + "Items: \n" + infoItem
        +  getExitString();
    }
}
