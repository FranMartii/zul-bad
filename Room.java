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
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west,Room surEste,Room norOeste) 
    {
        if(north != null)
            salidas.put("north", north);
        if(east != null)
            salidas.put("east", east);
        if(south != null)
            salidas.put("south", south);
        if(west != null)
            salidas.put("west", west);
        if(surEste != null)
            salidas.put("surEste", surEste);
        if(norOeste != null)
            salidas.put("norOeste", norOeste);
    }

    public Room getExit(String descripccion){
        Room salida = null;
        if(descripccion.equals("north")){
            salida = salidas.get("north");
        }
        if(descripccion.equals("south")){
            salida = salidas.get("south");
        }
        if(descripccion.equals("east")){
            salida = salidas.get("east");
        }
        if(descripccion.equals("west")){
            salida = salidas.get("west");
        }
        if(descripccion.equals("sureste")){
            salida = salidas.get("sureste");
        }
        if(descripccion.equals("noroeste")){
            salida = salidas.get("noroeste");
        }
        return salida;
    }

    /**
     * Devuelve la información de las salidas existentes
     * Por ejemplo: "Exits: north east west"
     *
     * @return Una descripción de las salidas existentes.
     */
    public String getExitString(){
        String salidasExistentes = "Exits: ";
        if(salidas.get("north") != null) {
            salidasExistentes += "north ";
        }
        if(salidas.get("east") != null) {
            salidasExistentes += "east ";
        }
        if(salidas.get("south") != null) {
            salidasExistentes += "south ";
        }
        if(salidas.get("west") != null) {
            salidasExistentes += "west ";
        }
        if(salidas.get("sureste") != null) {
            salidasExistentes += "sureste ";
        }
        if(salidas.get("noroeste") != null) {
            salidasExistentes += "noroeste ";
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
