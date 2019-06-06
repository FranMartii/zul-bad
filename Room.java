import java.util.*;
public class Room 
{
    private String description;
    private HashMap<String,Room> salidas;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(String direccion,Room neighbor) 
    {
        salidas.put(direccion, neighbor);
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

}
