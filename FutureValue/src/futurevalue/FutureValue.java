package futurevalue;
import java.util.Scanner;
/**
 *
 * @author rocky
 */
class FutureValue {
 
    
    public static void main(String[] args) {
    Scanner myObj = new Scanner(System.in);  
    
    System.out.println("Input Principal [$10000.00 - $109700.00]: ");
    double p = myObj.nextDouble();
    while (p<10000.00 || p>109700.00){
        System.out.println("Invalid Principal, please enter again.");
        System.out.println("Input Principal [$10000.00 - $109700.00]: ");
        p = myObj.nextDouble();
    }
    
    System.out.println("Input Annual Interest Rate [1.0% - 10.0%]: ");
    double r = myObj.nextDouble();
    while (r<1.0 || r>10.0){
        System.out.println("Invalid Annual Interest Rate, please enter again.");
        System.out.println("Input Annual Interest Rate [1.0% - 10.0%]: ");
        r = myObj.nextDouble();
    }
    
    System.out.println("Input Timespan [2 - 10 years]: "); 
    int t = myObj.nextInt();
    while (t<2 || t>10){
        System.out.println("Invalid Timespan, please enter again.");
        System.out.println("Input Timespan [2 - 10 years]: ");
        t = myObj.nextInt();   
    }
    
    System.out.println("Input Compounding Period [2, 3 or 6 months]: ");
    int m = myObj.nextInt();
    while (m!=2 && m!=3 && m!=6){
        System.out.println("Invalid Compounding Period, please enter again.");
        System.out.println("Input Compounding Period [2, 3 or 6 months]: ");
        m = myObj.nextInt();
    }
    
    double FV1 = p*Math.pow((1+r/(100*(12/m))),(12/m));
    System.out.format("Future Value after 1 year: $%.2f\n", FV1);
    
    for (int i=2; i<=t; i++){ 
        double FVi = p*Math.pow((1+r/(100*(12/m))),i*(12/m));
        if(i<=2 || i==t){
          System.out.format("Future Value after " + i + " years: $%.2f\n", FVi);
          }else if (i==3){
            System.out.println("...");
          }
    }
    
    double E = Math.exp(1);
    double time_double_asset = 
    
    (Math.log(2)/Math.log(E))/((12/m)*(Math.log(1+r/(100*(12/m)))/Math.log(E)));

    double round_time = Math.round(time_double_asset);
      
    if (time_double_asset>round_time){
      System.out.format("Time to double asset: %d years and %d months", (int)round_time, m);
    }else{
      System.out.format("Time to double asset: %d years", (int)round_time);
    }
      
    }
    
}