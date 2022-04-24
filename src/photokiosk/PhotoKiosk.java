package photokiosk;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * I declare that the assignment here submitted is original except for source
 * material explicitly acknowledged, and that the same or closely related
 * material has not been previously submitted for another course. I also
 * acknowledge that I am aware of University policy and regulations on honesty
 * in academic work, and of the disciplinary guidelines and procedures
 * applicable to breaches of such policy and regulations, as contained in the
 * website.
 *
 * University Guideline on Academic Honesty:
 * http://www.cuhk.edu.hk/policy/academichonesty/
 *
 * Student Name : Tam Rocky Lok Ki
 * Student ID   : 1155158247    
 * Class/Section: CSCI1130A
 * Date         : 8/11/2021
 */


class PPM {
  // instance fields
  private String imageName;
  private int width, height;
  private int maxValue;
  private Color[][] image;

  // Default constructor for creating an blank PPM image of 2 x 3
  // provided for reference, NEED NOT be modified
  public PPM() {
    imageName = "Simple";
    width = 2;
    height = 3;
    maxValue = 255;
    image = new Color[height][width];
    image[0][0] = new Color(0, 128, 255);

    int r = image[0][0].getRed();
    int g = image[0][0].getGreen();
    int b = image[0][0].getBlue();

    image[0][1] = new Color(r, g + 127, b - 128);
    image[1][0] = new Color(128, g, 128);
    image[1][1] = new Color(255, 0, 255);
    image[2][0] = new Color(255, 255, 255);
    image[2][1] = new Color(0, 0, 0);
  }

  // Constructor for creating an "orange" PPM image of width x height
  // All pixels shall carry Color(255, 165, 0) in RGB
  public PPM(String name, int w, int h) {
       imageName = name;
       width = w;
       height = h;
       image = new Color[30][40];
       for(h=0;h<30;h++){
            for (w=0;w<40;w++){
                image[h][w] = new Color(255, 165, 0);               
       }
       }    
  }

  // Constructor for reading a PPM image file
  public PPM(String filename) {
    this.imageName = filename;
    read(filename);  // boolean part     
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Color[][] getImage() {
    return image;
  }

  // Show image on screen
  // given and NEED NOT be modified
  public void showImage() {
    if (getHeight() <= 0 || getWidth() <= 0 || image == null) {
      JOptionPane.showConfirmDialog(null, "Width x Height = " + getWidth() + "x" + getHeight(), imageName + " corrupted!", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE, null);
      return;
    }

    BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

    for (int row = 0; row < getHeight(); row++) {
      for (int col = 0; col < getWidth(); col++) {
        img.setRGB(col, row, image[row][col].getRGB());
      }
    }

    JOptionPane.showConfirmDialog(null, "Width x Height = " + getWidth() + "x" + getHeight(), imageName, JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(img));
  }

  /* student's work here to define extra methods to handle color space conversion, 
     grayscale and saturate */
 
  
  public PPM grayscale() {

    PPM obj1 = new PPM("peacock_256.ppm");
    
    
    for(int h=0;h<256;h++){
      for(int w=0;w<256;w++){
    int r = image[h][w].getRed();
    int g = image[h][w].getGreen();
    int b = image[h][w].getBlue();
    int Y = (int)((0.257*r) + (0.504*g) + (0.098*b) + 16);   // convert RGB to YUV
    r = Y;    // replace RGB with Y
    g = Y;
    b = Y;
          obj1.image[h][w] = new Color(r,g,b);
    }
    }
    obj1.imageName = "Grayscale"; // change name
    return obj1;
  }
  
  public PPM saturate() {
  
  PPM obj2 = new PPM("peacock_256.ppm");
  
   for(int h=0;h<256;h++){ 
     for(int w=0;w<256;w++){ 
         
   int R = image[h][w].getRed();
   int G = image[h][w].getGreen();
   int B = image[h][w].getBlue(); 
   
       
   int Y = (int)((0.257*R) + (0.504*G) + (0.098*B) + 16); // Convert RGB to YUV
   int U = (int)-((0.148*R) - (0.291*G) + (0.439*B) + 128);
   int V = (int)((0.439*R) - (0.368*G) - (0.071*B) + 128);

   int Unew = (U-128)*2+128;   // Scale U&V
   int Vnew = (V-128)*2+128;
   
   R = (int)(1.164*(Y - 16) + 1.596*(Vnew - 128));   // Convert new YUV to RGB
   G = (int)(1.164*(Y - 16) - 0.813*(Vnew - 128) - 0.391*(Unew - 128));
   B = (int)(1.164*(Y - 16) + 2.018*(Unew - 128));

   if ((R>=0&&R<=maxValue)&&(G>=0&&G<=maxValue)&&(B>=0&&B<=maxValue)){ // RGB restrict
       
       obj2.image[h][w] = new Color(R,G,B);
     }
   }
   }
   obj2.imageName = "Saturate"; // change name
   return obj2;
  }

  public boolean read(String filename) {
    try {
      File f = new File(filename);
      Scanner reader = new Scanner(f); //read file
      String header = reader.nextLine(); // p3
      if (header == null || header.length() < 2 || header.charAt(0) != 'P' || header.charAt(1) != '3') {
        throw new Exception("Wrong PPM header!");
      }
      
      do { // skip lines (if any) start with '#'
        header = reader.nextLine();
      } while (header.charAt(0) == '#');

      Scanner readStr = new Scanner(header);  // get w, h from last line scanned instead of file
      width = readStr.nextInt();
      height = readStr.nextInt();
//      width = reader.nextInt();
//      height = reader.nextInt();
      maxValue = reader.nextInt();  // get the rest from file      

      System.out.println("Reading PPM image of size " + width + "x" + height);
 
      
      image = new Color[height][width];
      
      for(int H=0;H<height;H++){
       for(int R=0;R<width;R++){
        int r = reader.nextInt();
        int g = reader.nextInt();
        int b = reader.nextInt();
        image[H][R] = new Color (r,g,b);
        
        }
      }

      
    } catch (Exception e) {
      System.err.println(e);
      image = null;
      width = -1;
      height = -1;
      return false;
    }
    return true;
  }

  public void write(String filename) { 
    PrintStream ps;
    try {
      ps = new PrintStream(filename);
      
    } catch (Exception e) {
      System.err.println(e);
    }
  }

}

public class PhotoKiosk {


  public static void main(String[] args) {
    String filename;

    PPM imgDefault;
    imgDefault = new PPM();
    imgDefault.showImage();

    PPM imgBlank;
    imgBlank = new PPM("Orange", 40, 30);
    imgBlank.showImage();

    filename = "WRONG_FILENAME.ppm";
    PPM imgFileCorrupted;
    imgFileCorrupted = new PPM(filename);
    imgFileCorrupted.showImage();

    filename = "rgb_30x25.ppm";
    PPM imgFileSmall;
    imgFileSmall = new PPM(filename);   
    imgFileSmall.showImage();

    filename = "peacock_256.ppm";
    PPM imgFile2;
    imgFile2 = new PPM(filename);
    imgFile2.showImage();

    PPM result = imgFile2.grayscale();
    result.showImage();
    result.write("grayscale.ppm");

    PPM result2 = imgFile2.saturate();
    result2.showImage();
    result2.write("saturate.ppm");
  }
}