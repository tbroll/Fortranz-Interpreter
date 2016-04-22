import java.io.*;
import java.lang.*;
import java.util.*;
public class Interpreter{
    private BufferedReader br;
    private String[] operators = {"+", "-", "/", "*","="};
    private String[] nativeWords = {"IF", "THEN", "ELSE",
        "CALL", "END IF", "END","SUBROUTINE", "DO","CONTINUE", "GOTO"}; 
    private String[] variables = {"I","J","K","L","M","N"};
    private String[] inputOutput = {"WRITE", "READ"};
    private String[] Logical = {".LT.", ".GT."};
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in); 
        //        System.out.println("Which fortranz program do you want me to convert to Java?");
        //       String file = scanner.next();
        Interpreter interpreter = new Interpreter();
        String file = "RNG.f"; //this is temporary
        interpreter.readInput(file);
    }
    private void readInput(String input){
        initialize(input);
        String thisLine = null;
        try{
            br = new BufferedReader(new FileReader(input));
            while((thisLine = br.readLine()) != null){
                convertInputfileToJava(thisLine);
            }
        }
        catch(IOException e){
            System.out.println("error: " + e.getMessage());
        }
    }
    private void convertInputfileToJava(String currentLine){
        //  System.out.println(currentLine);
        String[] line = currentLine.split("\\s+");
        //            for(int i = 0; i<line.length; i++){
        //                System.out.println(line[i]);
        //            }
        //            System.out.println("Num Of Strings "+ line.length);
        //            System.out.println(line[1].length());

        //System.out.println(line.length);
        //for(int i = 0; i < line.length; i++)
        //    if(i == line.length-1){
        //        System.out.println(line[i]);
        //    }
        //    else{
        //        System.out.print(line[i]);
        //    }
        if(line[1].equals("CALL")){
            System.out.println(line[2]+"()");
        } 
        else if(line[1].equals("END")){
            System.out.println("}");
        }
        else if(line[1].equals("SUBROUTINE")){
            System.out.println("private void "+line[2] + "{");
        }
        else if(line[1].equals("DO")){
            System.out.println("for(int " +
                    line[3].substring(0,line[3].length()-1) + "; " +
                    line[3].substring(0,1)+" < " + line[4] + "; " +
                    line[3].substring(0,1) + "++){"); 
        }
        
        else if(line[0].matches("1") || line[0].matches("2") ||
                line[0].matches("3") || line[0].matches("4") ||
                line[0].matches("5") || line[0].matches("6")  
                ){
                if(line[1].matches("CONTINUE")){
                    System.out.println("}");
                }
                else{};
                }

        else if(line[0].matches("1") || line[0].matches("2") ||
                line[0].matches("3") || line[0].matches("4") ||
                line[0].matches("5") || line[0].matches("6")){
                line[7] = "Math.random()";
            System.out.print("do{ int "); 
            for(int j = 3; j < line.length; j++)
            {System.out.print(line[j]);}
                System.out.println("");
                }
        else if(line[1].equals("I") || line[1].equals("J")){
                line[5] = "Math.random()";
            for(int j = 1; j < line.length; j++)
            {System.out.print(line[j]);}
            System.out.println("");
        }
        else if(line[1].equals("WRITE")){
            System.out.println("System.out.println(" + line[2] + " " +
                    (line[3]) +");");
        }
        else{
            System.out.println("exited");
            System.exit(1);
        }
    }
    private void writeOutputFile(){
    }
    private void initialize(String input){
        String[] filename = input.split("\\.");
        input = filename[0];
        System.out.println("public class "+input+" {\npublic static void main(String[] args){");
    }
}

