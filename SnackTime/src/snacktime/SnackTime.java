package snacktime;
import javax.swing.JOptionPane;

/**
 *
 * @author rocky
 */



public class SnackTime {
    
 private static int[] coinsInCents; 
 private static String[] snackNames; 
 private static Snack[] snacks = new Snack[4]; // array create objects
 
 public static String showSnackMenu() {  
 
    int[] order = {1, 2, 3, 4};
    String menu = "";
        for(int i=0; i<snackNames.length;i++){
            menu = menu + order[i]+ ". "  +"[$"+snacks[i].getPrice()+"0] " + snackNames[i]+ "\t" +"\n";
 } 
 
 return JOptionPane.showInputDialog("Buy Snack: Input your choice\n" + menu +
         "5. Cancel", "<type [1-5] here");
 } 
 
    public static void main(String[] args) {

         
     
    snackNames = new String[]{"KitKat", "Oreo", "Marshmallow", "Cupcake"};
    
    double cash = 20;
    for (int i=0;i<=3;i++){
      snacks[i] = new Snack(snackNames[i]); 
      snacks[i].printMessage();
    }
     
     int snackMenuChoice = SnackTimeHelper.getChoiceFromSnackMenu();
        while (snackMenuChoice>5){
            JOptionPane.showMessageDialog(null,"Invalid input");
            snackMenuChoice=SnackTimeHelper.getChoiceFromSnackMenu();
        }
        
     if (snackMenuChoice == -1){
         System.out.println("User closed or cancelled dialog box");
         JOptionPane.showMessageDialog(null,"Hope to serve you again");
     }
    else if(snackMenuChoice == 1){
    System.out.println("User picked 1");
    JOptionPane.showConfirmDialog(null, "insert $20" 
    + " to buy " + snacks[0].getName() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
    cash = 20 - snacks[0].getPrice();
} else if(snackMenuChoice == 2){
    System.out.println("User picked 2");
    JOptionPane.showConfirmDialog(null, "insert $20" 
    + " to buy " + snacks[1].getName() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
    cash = 20 - snacks[1].getPrice();
} else if(snackMenuChoice == 3){
    System.out.println("User picked 3");
    JOptionPane.showConfirmDialog(null, "insert $20" 
    + " to buy " + snacks[2].getName() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
    cash = 20 - snacks[2].getPrice();
} else if(snackMenuChoice == 4){
    System.out.println("User picked 4");
    JOptionPane.showConfirmDialog(null, "insert $20" 
    + " to buy " + snacks[3].getName() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
    cash = 20 - snacks[3].getPrice();
} else if(snackMenuChoice == 5){
    JOptionPane.showMessageDialog(null, "Hope to serve you again");
}
     
  while (cash !=0 && snackMenuChoice<5 && snackMenuChoice>0){
      int tendollar = 0,fivedollar = 0,twodollar= 0 ,onedollar = 0;
      double fiftycent = 0;
            
            if(cash/10 != 0){
                tendollar = (int)cash/10;
                cash = cash - tendollar*10;}
            
            if(cash/5 != 0){
                fivedollar = (int)cash/5;
                cash = cash - fivedollar*5;}
               
            if(cash/2 != 0){
               twodollar = (int)cash/2;
               cash = cash - twodollar*2;}
            
            if(cash/1 != 0){
               onedollar =(int)cash/1;
               cash = cash - onedollar*1;}
            
            if(cash/0.5 != 0){
                fiftycent = cash/0.5;
                cash = cash - fiftycent*0.5;
            }
            
            double[] coins = {tendollar,fivedollar,twodollar,onedollar,(int)fiftycent};
            
            coins[0] = tendollar;
            coins[1] = fivedollar;
            coins[2] = twodollar;
            coins[3] = onedollar;
            coins[4] = fiftycent;
            
            double[] orders = {10,5,2,1,0.5};
            String return_price = "$" + snacks[snackMenuChoice-1].getPrice()
                    + "0 paid\nCoins returned:\n";
            
            for(int x=0;x<=4;x++){
                if(coins[x]!=0){
                 return_price = return_price + "$" + orders[x] + " x " + (int)coins[x] + "\n";
                }
            }
            
            JOptionPane.showMessageDialog(null,return_price);}
  
    }
}

