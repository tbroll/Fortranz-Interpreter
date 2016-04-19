public class Interpreter{
    private final String newJavaFile = "RNG.java";
    public static void main(String [] args){
        File file = new File("RNG.f");
        Interpreter interpreter = new Interpreter();
        interpreter.start(file);
    }
    private void start(File inputfile){
        readInput(inputfile);
        convertInputfileToJava();
        writeOutputFile();
    }

