import java.io.*;
import java.lang.*;
import java.util.*;
public class Interpreter{
    private BufferedReader br;
    private int counter = 0;
    public static void main(String [] args){
        Interpreter interpreter = new Interpreter();
        String file = "RNG.fz";
        interpreter.readInput(file);
    }
    // The readInput method reads each line of the FortranZ program
    // and passes that String to a method that converts it to java.
    private void readInput(String input){
        initialize(input);
        String thisLine = null;
        try{
            br = new BufferedReader(new FileReader(input));
            while((thisLine = br.readLine()) != null){
                convertInputfileToJava(thisLine);
            }
        System.out.println("}");
        }
        catch(IOException e){
            System.out.println("error: " + e.getMessage());
        }
    }
    //this method translates all the fortranZ code to Java code.
    private void convertInputfileToJava(String currentLine){
        //this splits the current Line string into smaller strings. 
        //Each split is made on every blank space.
        String[] line = currentLine.split("\\s+");
        //this if statement converts methods from fortranZ to Java being called in the main class.
        if(line[1].equals("CALL")){
            System.out.println(line[2]+"();");
        } 
        //this makes the correct change to the end of each subroutine/method.
        else if(line[1].equals("END")){
            System.out.println("}");
        }
        //converts each subroutine into private methods and puts all 
        //the needed variables at the top of the method.
        else if(line[1].equals("SUBROUTINE")){
            System.out.println("private static void "+line[2] + "{");
           counter++;
          if (counter == 4){ 
            System.out.println("int I; \nint J; \nint L;");
          }
          else{
            System.out.println("int I; \nint J;");
          }
          System.out.println("System.out.println("+"\""+ "\""+");");
          System.out.println("System.out.println("+"\""+line[2]+"\""+");");
          System.out.println("System.out.println("+"\""+ "\""+");");
        }
        //converts Do loops into for loops.
        else if(line[1].equals("DO")){
            System.out.println("for(int " +
                    line[3].substring(0,line[3].length()-1) + "; " +
                    line[3].substring(0,1)+" < " + line[4] + "; " +
                    line[3].substring(0,1) + "++){"); 
        }
        //ends a Do loop/For loop
        else if(line[1].equals("CONTINUE")){
                    System.out.println("}");
                }
        //starts a Do-while loop
        else if(line[0].equals("10")){
                line[5] = "Math.random()";
                System.out.print("do{ "); 
                line[2] = line[2] + " (int)(";
                line[line.length-1] = line[line.length-1] + ")";
                printline(line, line.length);
                }
        //converts arithmetic operations
        else if(line[1].equals("I") || line[1].equals("J")){
                line[2] = "= (int)(";
                line[5] = "Math.random()";
                line[line.length-1] = line[line.length-1] +")";
                printline(line, line.length);
        }
        // converts the MOD function
        else if(line[1].equals("L")){
                line[4] = "%";
                printline(line, line.length);
        }
        //ends the do-while loop
        else if(line[1].equals("IF")){
            if(line[3].equals(".LT.")){
                line[3] = " < ";
            }
            else{
                line[3] = " > ";
            }
                line[1] = "}while ";
                printline(line, 5);
        }
        //handles Write commands -> converts them into System.out.println();
        else{
            String operand1 = line[2].substring(1,2);
                String operator = line[2].substring(2,3);
                String operand2 = line[2].substring(3,4);
            System.out.println("System.out.println(" + operand1 +"+" + "\""+
                    operator  + "\""+"+" + operand2+"+" + "\""+"= " + "\""+
                  "+"+ "(" + line[3]+ "));");
        }
    }
    //prints out each line
    private void printline(String[] line, int lastIndex){
            for(int j = 1; j < lastIndex; j++)
            {System.out.print(line[j]);}
            System.out.println(";");
    }
    // creates the head that every java class that has a main class 
    // has.
    private void initialize(String input){
        String[] filename = input.split("\\.");
        input = filename[0];
        System.out.println("public class "+input+" {\npublic static void main(String[] args){");
    }
}

