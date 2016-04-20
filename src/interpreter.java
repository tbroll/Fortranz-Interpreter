import java.io.*;
import java.lang.*;
import java.util.*;
public class Interpreter{
    private String[] operators = {"+", "-", "/", "*"};
    private String[] nativeWords = {"WRITE", "READ", "IF", "THEN", "ELSE",
        "CALL", "END IF", "END","SUBROUTINE", "DO","CONTINUE", "GOTO"}; 
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Which fortranz program do you want me to convert to Java?");
        String file = scanner.next();
        Interpreter interpreter = new Interpreter();
        interpreter.readInput(file);
    }
    private void readInput(String input){
        initialize(input);
        String thisLine = null;
        try{
        BufferedReader br = new BufferedReader(new FileReader(input));
        while((thisLine = br.readLine()) != null){
        convertInputfileToJava(thisLine);
    }
        }
        catch(IOException e){
            System.out.println("error: " + e.getMessage());
        }
    }
    private void convertInputfileToJava(String currentLine){
    }
    private void writeOutputFile(){
    }
    private void initialize(String input){
        String[] filename = input.split("\\.");
        System.out.println(filename.length);
        input = filename[0];
        System.out.println("import java.util.*; \npublic class "+input+" {");
        System.out.println("public static void main(String[] args){");
    }
}

