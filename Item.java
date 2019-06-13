import java.util.*;
public class Item
{
    private String nombre;
    private int peso;
    private int aumentoCarga;
    private boolean canBePicked;
    private boolean equipable;

    public Item(String nombre,int peso, int aumentoCarga,boolean canBePicked,boolean equipable){
        this.nombre = nombre;
        this.peso = peso;
        this.aumentoCarga = aumentoCarga; 
        this.canBePicked = canBePicked;
        this.equipable = equipable;
    }
    
    public int getPeso(){
        return peso;
    }

    public int getAumentoCarga(){
        return aumentoCarga;
    }
    
    public boolean getRecogible(){
        return canBePicked;
    }
    
    public boolean getEquipable(){
        return equipable;
    }
    
    public String getInfoItem(){
        return "(Carga Maxima: +" + aumentoCarga 
        + " Peso: " + peso + ")\n";
    }

}
