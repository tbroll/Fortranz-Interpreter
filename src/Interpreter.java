import java.io.*;
import java.lang.*;
import java.util.*;
public class Interpreter{
    private BufferedReader br;
    private int counter = 0;
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in); 
        Interpreter interpreter = new Interpreter();
        String file = "RNG.f";
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
        System.out.println("}");
        }
        catch(IOException e){
            System.out.println("error: " + e.getMessage());
        }
    }
    private void convertInputfileToJava(String currentLine){
        String[] line = currentLine.split("\\s+");
        if(line[1].equals("CALL")){
            System.out.println(line[2]+"();");
        } 
        else if(line[1].equals("END")){
            System.out.println("}");
        }
        else if(line[1].equals("SUBROUTINE")){
            System.out.println("private static void "+line[2] + "{");
           counter++;
          if (counter == 4){ 
            System.out.println("int I; \nint J; \nint L;");
          }
          else{
            System.out.println("int I; \nint J;");
          }
        }
        else if(line[1].equals("DO")){
            System.out.println("for(int " +
                    line[3].substring(0,line[3].length()-1) + "; " +
                    line[3].substring(0,1)+" < " + line[4] + "; " +
                    line[3].substring(0,1) + "++){"); 
        }
        else if(line[1].equals("CONTINUE")){
                    System.out.println("}");
                }
        else if(line[0].equals("10")){
                line[5] = "Math.random()";
                System.out.print("do{ "); 
                line[2] = line[2] + " (int)(";
                line[line.length-1] = line[line.length-1] + ")";
                printline(line, line.length);
                }
        else if(line[1].equals("I") || line[1].equals("J")){
                line[2] = "= (int)(";
                line[5] = "Math.random()";
                line[line.length-1] = line[line.length-1] +")";
                printline(line, line.length);
        }
        else if(line[1].equals("L")){
                line[4] = "%";
                printline(line, line.length);
        }
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
        else{
            String operand1 = line[2].substring(1,2);
                String operator = line[2].substring(2,3);
                String operand2 = line[2].substring(3,4);
            System.out.println("System.out.println(" + operand1 +"+" + "\""+
                    operator  + "\""+"+" + operand2+"+" + "\""+"= " + "\""+
                  "+"+ "(" + line[3]+ "));");
        }
    }
    private void printline(String[] line, int lastIndex){
            for(int j = 1; j < lastIndex; j++)
            {System.out.print(line[j]);}
            System.out.println(";");
    }
    private void initialize(String input){
        String[] filename = input.split("\\.");
        input = filename[0];
        System.out.println("public class "+input+" {\npublic static void main(String[] args){");
    }
}

