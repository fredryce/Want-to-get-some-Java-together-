//Xin Wang 
//Pax 2 Cipher Encoding/Decoding
//09/21/2016
//Dr. Bushey
package pax2cipher;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Xin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CipherDriver CD = new CipherDriver();
        CD.musicPlay();
        CD.chooseOption(); //decide which type of Cipher to use
        if (CD.getOption() == 1) {
            CD.fileEncodeCaesarCipher();  // CaesarCipher
        } 
        else if (CD.getOption() == 2) {
           
            CD.fileEncodeSubstitutionCipher(); // SubstitutionCipher
            

        }
        else {
            
            CD.fileEncodeRotationCipher(); // Rotational Cipher
            

        }
        
    }
    
}
