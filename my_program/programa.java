import java.io.*;  
import java.util.Scanner;

public class programa {
    public static void main (String[] args){
        lerEntrada();
    }
    
    private static void lerEntrada(){
        try {
            FileInputStream fis = new FileInputStream("my_program/entrada.txt");
            Scanner sc = new Scanner(fis);
            while(sc.hasNextLine()) {  
                sc.nextLine();
                sc.nextLine();
                String[] entrada = sc.nextLine().split("|");
            } 
            sc.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}