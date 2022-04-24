package snacktime;
import java.util.Random;
/**
 *
 * @author rocky
 */
public class Snack {
    private String name;
    private double price; //a
    
    public Snack(String name){
        this.name = name; // field: this.name , "this" is not necessary
        price = genRandomPrice(); //b
    
}

    public String getName(){
       return name;  
    }
    
    public double getPrice(){
       return price;
    }
    
    public void printMessage(){
        System.out.print(name + " is sold at $" + price); //e
    }
    
    public double genRandomPrice(){ // where is static?
        Random r = new Random();
        //price = ()(Math.random()*(15.0-5.0)) + 5.0;
        price = (r.nextInt(21)+10.0)/2;
        return price;
    }
    
}
