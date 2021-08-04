/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pax2cipher;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import static java.lang.Character.isLetter;
import static java.lang.Character.toLowerCase;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.lang.Character.isLetter;
import static java.lang.Character.toLowerCase;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import static java.lang.Character.isLetter;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.isLetter;
import static java.lang.Character.toLowerCase;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Xin
 *
 */
public class CipherDriver {

    private String fileName;
    private int option;
    private String encodeOrDecode;
    private String key;

    Scanner scan = new Scanner(System.in);
    Cipher myCipher = new Cipher();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public void fileEncodeCaesarCipher() {   // CaesorCipher driver

        Charset charset = Charset.forName("US-ASCII");
        fileName = JOptionPane.showInputDialog(null, "What is the Name of the file? ", "File Name?", JOptionPane.INFORMATION_MESSAGE);
        Path file = Paths.get(fileName);
        while (!isFileExist(file, charset)) {          //Check if the file name is valid and file do exist
            fileName = JOptionPane.showInputDialog(null, "What is the Name of the file? ", "File Name?", JOptionPane.INFORMATION_MESSAGE);
            file = Paths.get(fileName);

        }

        try {
            BufferedReader reader = Files.newBufferedReader(file, charset);  
            key = JOptionPane.showInputDialog(null, "Encoding/Decode key? Negative for Decode, Positive for Encode, press 3 or -3 for CaesorCipher", "INTEGER VALUE", JOptionPane.INFORMATION_MESSAGE);
            while (!isInt(key)) {
                key = JOptionPane.showInputDialog(null, "Encoding/Decode key? Negative for Decode, Positive for Encode, press 3 or -3 for CaesorCipher", "INTEGER VALUE", JOptionPane.INFORMATION_MESSAGE);
                                                                //error checking
            }
            int caesorkey = Integer.parseInt(key);              

            myCipher.setCaesorKey(caesorkey);
            String line = null;
            while ((line = reader.readLine()) != null) {        //read line by line

                for (int i = 0; i < line.length(); i++) {       // read char by char

                    System.out.print(myCipher.caesarCipherEncode(line.charAt(i)));
                }
                System.out.print("\n");
                //System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("File not found, Enter in valid File name!!!");
        }

    }

    public void fileEncodeSubstitutionCipher() {                // Encode for substitution Cipher
        Charset charset = Charset.forName("US-ASCII");
        fileName = JOptionPane.showInputDialog(null, "What is the Name of the file? ", "File Name?", JOptionPane.INFORMATION_MESSAGE);
        Path file = Paths.get(fileName);
        while (!isFileExist(file, charset)) {
            fileName = JOptionPane.showInputDialog(null, "What is the Name of the file? ", "File Name?", JOptionPane.INFORMATION_MESSAGE);
            file = Paths.get(fileName);

        }
        try {
            BufferedReader reader = Files.newBufferedReader(file, charset);
            key = JOptionPane.showInputDialog(null, "Enter in 26 differnt letter Key OR press -1 for default qwertyuiopasdfghjklzxcvbnm", "SubStitution KEY", JOptionPane.INFORMATION_MESSAGE); 
            //Enter in the key and do error checking making sure its proper 26 different characters or -1
            while (!stringValid()) {
                key = JOptionPane.showInputDialog(null, "Enter in 26 differnt letter Key OR press -1 for default qwertyuiopasdfghjklzxcvbnm", "SubStitution KEY", JOptionPane.INFORMATION_MESSAGE);

            }
            if (key.equals("-1")) {
                myCipher.setSubstitutionKey(myCipher.getSubstitutionKey());

            } else {
                myCipher.setSubstitutionKey(key);
            }
            encodeOrDecode = JOptionPane.showInputDialog(null, "Would you like to encode or decode? Enter in e/d ", "Encode or Decode?", JOptionPane.INFORMATION_MESSAGE);
            while (toLowerCase(encodeOrDecode.charAt(0)) != 'e' && toLowerCase(encodeOrDecode.charAt(0)) != 'd') {

                encodeOrDecode = JOptionPane.showInputDialog(null, "Would you like to encode or decode? Enter in e/d ", "Encode or Decode?", JOptionPane.INFORMATION_MESSAGE);
            }

            String line = null;
            while ((line = reader.readLine()) != null) {

                for (int i = 0; i < line.length(); i++) {
                    if (toLowerCase(encodeOrDecode.charAt(0)) == 'e') {

                        System.out.print(myCipher.substitutionCipher(line.charAt(i)));
                    } else {
                        System.out.print(myCipher.substitutionCipherDecode(line.charAt(i)));
                    }
                }
                System.out.print("\n");
                //System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("File not found, Enter in valid File name!!!");
            e.printStackTrace();
        }

    }

    public void fileEncodeRotationCipher() {                
        Charset charset = Charset.forName("US-ASCII");
        fileName = JOptionPane.showInputDialog(null, "What is the Name of the file? ", "File Name?", JOptionPane.INFORMATION_MESSAGE);
        Path file = Paths.get(fileName);
        while (!isFileExist(file, charset)) {
            fileName = JOptionPane.showInputDialog(null, "What is the Name of the file? ", "File Name?", JOptionPane.INFORMATION_MESSAGE);
            file = Paths.get(fileName);

        }

        try {
            BufferedReader reader = Files.newBufferedReader(file, charset);
            
            key = JOptionPane.showInputDialog(null, "Enter in 26 differnt letter Key OR press -1 for default qwertyuiopasdfghjklzxcvbnm", "Rotational KEY", JOptionPane.INFORMATION_MESSAGE);
            while (!stringValid()) {
                key = JOptionPane.showInputDialog(null, "Enter in 26 differnt letter Key OR press -1 for default qwertyuiopasdfghjklzxcvbnm", "Rotational KEY", JOptionPane.INFORMATION_MESSAGE);

            }
            if (key.equals("-1")) {
                myCipher.setRotationKey(myCipher.getRotationKey());

            } else {
                myCipher.setRotationKey(key);
            }
            encodeOrDecode = JOptionPane.showInputDialog(null, "Would you like to encode or decode? Enter in e/d ", "Encode or Decode?", JOptionPane.INFORMATION_MESSAGE);
            while (toLowerCase(encodeOrDecode.charAt(0)) != 'e' && toLowerCase(encodeOrDecode.charAt(0)) != 'd') {

                encodeOrDecode = JOptionPane.showInputDialog(null, "Would you like to encode or decode? Enter in e/d ", "Encode or Decode?", JOptionPane.INFORMATION_MESSAGE);
            }
//            if(toLowerCase(encodeOrDecode.charAt(0)) == 'd'){
//                for(int i = 0; i < charNum(file, charset); i ++){
//                myCipher.reverseRotate();
//                //System.out.println("key is " + " /" + myCipher.getRotationKey() + "/ " + " i value is " + i);
//        }
//            }

            String line = null;

            while ((line = reader.readLine()) != null) {

                for (int i = 0; i < line.length(); i++) {
                    if (toLowerCase(encodeOrDecode.charAt(0)) == 'e') {

                        System.out.print(myCipher.rotationalCipher(line.charAt(i)));
                    } else {
                        System.out.print(myCipher.rotationalCipherDecode(line.charAt(i)));
                        //System.out.print("the total number of character is " + charNum(file, charset));
                    }

                }
                System.out.print("\n");
                //System.out.println(line);
            }

        } catch (IOException e) {
            System.out.println("File not found, Enter in valid File name!!!");
        }

    }

    public void chooseOption() {                                  //Allows the person to choose which method to perform encoding decoding with
        String tempChoice = JOptionPane.showInputDialog(null, "1. CaesorCipher \n2. Substitution Cipher\n3. Rotation Cipher ", "Choose An Option", JOptionPane.INFORMATION_MESSAGE);
        while (true) {
            try {
                int choice = Integer.parseInt(tempChoice);

                while (choice < 1 || choice > 3) {
                    tempChoice = JOptionPane.showInputDialog(null, "1. CaesorCipher \n2. Substitution Cipher\n3. Rotation Cipher ", "Choose An Option ", JOptionPane.INFORMATION_MESSAGE);
                    choice = Integer.parseInt(tempChoice);

                }

                option = choice;
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Did you read the instructions, please enter in valid integer", "Idiot box", JOptionPane.OK_OPTION);
                tempChoice = JOptionPane.showInputDialog(null, "1. CaesorCipher \n2. Substitution Cipher\n3. Rotation Cipher ", "Choose An Option ", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public boolean stringValid() {                              //check if the key the person entered is valid or not. has to be -1, or 26 different letters of the alphebet
        if (key.equals("-1")) {
            return true;
        } else if (key.length() != 26) {
            return false;
        } else if (!isDifferent(key)) {
            return false;
        } else {
            return true;
        }

    }

    public boolean isDifferent(String key) {                    //checks if the person entered in different lettters
        boolean isdiffer = false;
        for (int i = 0; i < key.length(); i++) {
            isdiffer = true;
            for (int j = i + 1; j < key.length(); j++) {
                if (key.charAt(i) == key.charAt(j)) {
                    isdiffer = false;
                    break;
                }
            }
            if (isdiffer == false) {
                break;
            }

        }
        return isdiffer;

    }

    public boolean isFileExist(Path File, Charset charset) {  //error checking making sure the file exist
        try {
            BufferedReader reader = Files.newBufferedReader(File, charset);
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Please Enter in Valid File name", "Invalid File Name", JOptionPane.OK_OPTION);

            return false;
        }
    }

    public boolean isInt(String isOk) {                     //making sure the value is int before parsing it.
        try {
            Integer.parseInt(isOk);
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please Enter int Value", "Invalid Value", JOptionPane.OK_OPTION);
            return false;

        }
    }

    public void musicPlay() {                           //playing music get hyped. 
        File jam = new File("sound.WAV");
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(jam));
            clip.start();
            //Thread.sleep(clip.getMicrosecondLength()/1000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
