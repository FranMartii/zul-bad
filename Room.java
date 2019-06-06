/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room surEsteExit;
    private Room norOesteExit;
    
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
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(surEste != null)
            surEsteExit = surEste;
        if(norOeste != null)
            norOesteExit = norOeste;
    }

    public Room getExit(String descripccion){
        Room salida = null;
        if(descripccion.equals("north")){
            salida = northExit;
        }
        if(descripccion.equals("south")){
            salida = southExit;
        }
        if(descripccion.equals("east")){
            salida = eastExit;
        }
        if(descripccion.equals("west")){
            salida = westExit;
        }
        if(descripccion.equals("sureste")){
            salida = surEsteExit;
        }
        if(descripccion.equals("noroeste")){
            salida = norOesteExit;
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
        if(northExit != null) {
            salidasExistentes += "north ";
        }
        if(eastExit != null) {
            salidasExistentes += "east ";
        }
        if(southExit != null) {
            salidasExistentes += "south ";
        }
        if(westExit != null) {
            salidasExistentes += "west ";
        }
        if(surEsteExit != null) {
            salidasExistentes += "sureste ";
        }
        if(surEsteExit != null) {
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
