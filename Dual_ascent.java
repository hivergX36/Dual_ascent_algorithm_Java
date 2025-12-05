import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dual_ascent {
    String name;
    Dictionnary dict; 


    Dual_ascent(String name, int dictSize) {
        this.name = name;
        this.dict = new Dictionnary(dictSize);
    }

    void readData(String namefile){
        // function for parsing the file and filling dictionnary

        File file = new File(namefile);

        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                String key = parts[0];
                System.out.println("key: " + key);
                float value = Float.parseFloat(parts[1]);
                System.out.println("value: " + value);
                dict.insert(key, value);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }


    }

    void display_equations(){
        //display dictionnary content 
        System.out.println("equations content:");
        dict.display_dictionary();

    }
   


    
    
    
}
