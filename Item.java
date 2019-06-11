import java.util.*;
public class Item
{
    private String nombre;
    private int peso;
    private int vida;

    public Item(String nombre,int peso, int vida){
        this.nombre = nombre;
        this.peso = peso;
        this.vida = vida;       
    }

    public String getInfoItem(){
        return "(Vida: " + vida 
        + " Peso: " + peso + ")\n";
    }

}
