/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//javax.sound.sampled.AudioInputStream;
//javax.sound.sampled.AudioSystem;
package pax2cipher;

import java.io.BufferedReader;
import static java.lang.Character.isLetter;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.isLetter;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.isLetter;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.isLetter;
import static java.lang.Character.toLowerCase;

/**
 *
 * @author Xin
 */
public class Cipher {
    private String rotationKey;
    private String substitutionKey;
    private int caesorKey;
    private boolean isCap;
    final String lowerStringKey = "abcdefghijklmnopqrstuvwxyz";
    final String upperStringKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Cipher() {
        rotationKey = "qwertyuiopasdfghjklzxcvbnm";
        substitutionKey = "qwertyuiopasdfghjklzxcvbnm";
        
        
        caesorKey = 0;
    }

    public int getCaesorKey() {
        return caesorKey;
    }

    public String getRotationKey() {
        return rotationKey;
    }

    public String getSubstitutionKey() {
        return substitutionKey;
    }

    public void setCaesorKey(int caesorKey) {
        this.caesorKey = caesorKey;
    }

    public void setRotationKey(String rotationKey) {
        this.rotationKey = rotationKey;
    }

    public void setSubstitutionKey(String substitutionKey) {
        this.substitutionKey = substitutionKey;
    }

    
    
    
    
    
    public char caesarCipherEncode(char Char) {       //CaesorCipher encode, takes in 1 char at a time and perform the encoding then return the char
        if (isLetter(Char)) {
            isCap(Char);                              //check if the character is upper case or not, set the variable isChar to true if its uppercase
            char lowerChar = toLowerCase(Char);       //convert it to lower case
            int letterAscii = (int) lowerChar - 97;   //convert it to ascii number then subtract 97 so a = 0, z = 25
            if(caesorKey >= 0){                       // if positive key entered perform encoding
                
            
            letterAscii = ((letterAscii + caesorKey) % 26) + 97;
            
            }
            else{                                    //if negative key then perform the decoding process
                if((letterAscii + caesorKey) < 0){     
                    letterAscii = ((26 + (letterAscii + caesorKey)) % 26) + 97;
                    //System.out.print("ola?");
                }
                else{
                    letterAscii = ((letterAscii + caesorKey) % 26) + 97;
                }
                  
            }
            if(isCap == true){
                return Character.toUpperCase((char)letterAscii); // if the captial letter is true, then return the char after converting to captial
                                                                    
            }
            else{
            return (char)letterAscii; 
            }

        } else {
            return Char;
        }

    }
    public char substitutionCipher(char Char){ //Substitution Cipher encode, takes in 1 char at a time and perform the encoding then return the char
        if(isLetter(Char)){
            isCap(Char);
            char lowerChar = toLowerCase(Char);
            int letterAscii = (int)lowerChar - 97;
            if(isCap == true){
                return Character.toUpperCase(substitutionKey.charAt(letterAscii));
            }
            else{
            return substitutionKey.charAt(letterAscii);
            }
        }
        else {
            return Char;
        }
    }
    public char substitutionCipherDecode(char Char){            //Substitution Cipher decode, takes in 1 char at a time and perform the encoding then return the char
        int index = 0;
        if(isLetter(Char)){
            isCap(Char);
            char lowerChar = toLowerCase(Char);
            for(int i = 0; i < substitutionKey.length(); i++){
                if(substitutionKey.charAt(i) == lowerChar){
                index = i;
                break;
                }
            }
            if(isCap == true){
                return Character.toUpperCase(lowerStringKey.charAt(index));
            }
            else{
            return lowerStringKey.charAt(index);
            }
           
        }
        else{
            return Char;
            
        }
    }
    public char rotationalCipher(char Char){            //Rotation Cipher encode, takes in 1 char at a time and perform the encoding then return the char
        if(isLetter(Char)){
            isCap(Char);
            char lowerChar = toLowerCase(Char);
            int letterAscii = (int)lowerChar - 97;
            lowerChar = rotationKey.charAt(letterAscii);
            rotateKey();
            //System.out.println(rotationKey);
            if(isCap == true){
                return Character.toUpperCase(lowerChar);
            }
            else{
            return lowerChar;
            }
        }
        else {
            
            return Char;
        }
        //qwertyuiopasdfghjklzxcvbnm
        //abcdefghijklmnopqrstuvwxyz
     
    }
     public char rotationalCipherDecode(char Char){      //Rotation Cipher decode, takes in 1 char at a time and perform the encoding then return the char
        
         
        int index = 0;
        if(isLetter(Char)){
            isCap(Char);
            char lowerChar = toLowerCase(Char);
            for(int i = 0; i < rotationKey.length(); i++){
                if(rotationKey.charAt(i) == lowerChar){
                index = i;
                break;
                }
            }
            
            rotateKey();
            if(isCap == true){
                return Character.toUpperCase(lowerStringKey.charAt(index));
            }
            else{
            return lowerStringKey.charAt(index);
            }
           
        }
        else{
            return Char;
            
        }
    }
    public void rotateKey(){                            // Rotate the key after each of the character is being encoded.

           char keyZero = rotationKey.charAt(0);
           rotationKey = rotationKey.substring(1);
           rotationKey += keyZero;
           //System.out.println(rotationKey);
        
    }

    public void isCap(char Char){                       //check if the character is capital or not
        isCap = false;
        if(Character.isUpperCase(Char)){
            isCap = true;
        }
        
   
        
    }
    
   
   
}
