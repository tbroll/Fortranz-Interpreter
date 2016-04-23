#/bin/bash
#Do not modify

#creates the translated java file created the Fortranz file
javac Interpreter.java
java Interpreter > RNG.java

#executes the translated file
javac RNG.java
java RNG

#remove all binary files
rm *.class 


