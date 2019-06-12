import java.util.*;
public class Item
{
    private String nombre;
    private int peso;
    private int vidaQueRecupera;

    public Item(String nombre,int peso, int vidaQueRecupera){
        this.nombre = nombre;
        this.peso = peso;
        this.vidaQueRecupera = vidaQueRecupera;       
    }
    
    public int getPeso(){
        return peso;
    }

    public String getInfoItem(){
        return "(Vida: " + vidaQueRecupera 
        + " Peso: " + peso + ")\n";
    }

}
