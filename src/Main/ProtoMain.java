package Main;

import Control.CommandInterpreter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Test class for sequences
 * @author Zsófi
 */
public class ProtoMain {
    /**
     * Will be filled with interpreters - each of them corresponds to a test, running a distinct game instance
     */
    static CommandInterpreter interpreter;
    static final String inputFileDir = "./clitestfiles/input/";
    static final String outputFileDir = "./clitestfiles/output/";

    /**
     * The user has to choose the mode of operation (see the println strings below)
     * Creates the list of the necessary command interpreters based on that,
     * then starts them, one after the other
     * The Command interpreter will wait for commands and forward them to the game instance
     * The loop, which enables the continous sampling of commands, is implemented in Command
     * Interpeter, not in main.
     * @author Zsófi
     */
    public static void main(String[] args) {
        System.out.println("What mode do you want the prototype to run in?");
        System.out.println("(1) Start the game with the system input and output");
        System.out.println("(2) Run a chosen testfile and generate output to system output");
        System.out.println("(3) Run a chosen testfile and generate output to its corresponding output file");

        Scanner mainScanner = new Scanner(System.in); // scanning the mode and inputfile choices of the user

        File inputfile;
        // based on the choice of the user, fills the interpreter list
        switch(mainScanner.nextInt()) {
            case 1:
                // Adds a system in/out interpreter
                interpreter = new CommandInterpreter(System.in, System.out);
                break;
            case 2:
                // Adds a chosen test with the system output as the output
                try {
                    ArrayList<File> testfiles = parseTestInputDirectory();
                    File testfile = chooseFile(mainScanner, testfiles);
                    addTest(testfile);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                break;
            case 3:
                System.out.println("This will override the output files generated earlier, do you want to continue? (y/N)");
                String dummy2 = mainScanner.nextLine();
                if(mainScanner.nextLine().toLowerCase().equals("y")) {
                    // Adds a chosen test with the output as its output file
                    try {
                        ArrayList<File> testfiles = parseTestInputDirectory();
                        File testfile = chooseFile(mainScanner, testfiles);
                        addTest(testfile, createOutputFile(testfile));
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                else return;
                // ha nem igent választ, befejezzük a futást
                break;
            default:
                System.out.println("No such choice");
                return;
        }

        interpreter.waitingCommands();
    }

    /**
     * Parses the testfile's directory and adds an interpreter for every testfile and
     * sets the output to the corresponding test output files
     * @author Zsófi
     */
    static void addAllTests() {
        ArrayList<File> testfiles;
        try {
            testfiles = parseTestInputDirectory();
        } catch (IOException e) {
            System.out.println("No testfiles found");
            return;
        }
        for(File testfile : testfiles) {
            try(InputStream inputStream = new FileInputStream(testfile);
                PrintStream outputStream = new PrintStream(new FileOutputStream(createOutputFile(testfile)))) {
                interpreter = new CommandInterpreter(inputStream, outputStream);
            }
            catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

    }

    /**
     * Creates an interpreter based on the given input and output file
     * @param testfile Input file of the to be started test
     * @param outputfile Output file of the to be started test
     * @throws IOException If there's a problem with creating the input/output streams
     * @author Zsófi
     */
    static void addTest(File testfile, File outputfile) throws IOException {
        InputStream inputStream = new FileInputStream(testfile);
        PrintStream outputStream = new PrintStream(new FileOutputStream(outputfile));
        interpreter = new CommandInterpreter(inputStream, outputStream);
    }

    /**
     * Creates an interpreter based on the given input with System.out as the output
     * @param testfile Input file of the to be started test
     * @throws IOException If there is a problem with creating the input stream
     * @author Zsófi
     */
    static void addTest(File testfile) throws IOException {
        InputStream inputStream = new FileInputStream(testfile);
        interpreter = new CommandInterpreter(inputStream, System.out);
    }


    /**
     * Parses the file input directory and creates a list of the test input files
     * (the input files are in the form: test_<TESTID>IN.txt)
     * @return list of files in the test input file directory
     * @author Zsófi
     */
    private static ArrayList<File> parseTestInputDirectory() throws IOException {
        Stream<Path> walk = Files.walk(Paths.get(inputFileDir));
        ArrayList<File> files = walk.filter(Files::isRegularFile).filter(name -> name.toFile().getName().matches("test_[0-9]*IN.txt"))
                .map(Path::toFile).collect(Collectors.toCollection(ArrayList::new));
        files.sort(Comparator.comparing(File::getName));
        return files;
    }

    /**
     * Creates an output testfile based on the input test file
     * @param inputFile The test's input file, in the form "test_<TESTID>IN.txt"
     * @return The test's corresponding output file in the output file directory, in the form "test_<TESTID>OUT.txt
     * @author Zsófi
     */
    private static File createOutputFile(File inputFile) {
        String inputfileName = inputFile.getName();
        File outputfile = new File(String.format("%s%sOUT.txt", outputFileDir, inputfileName.substring(0, inputfileName.length() - 6)));
        System.out.println(outputfile.getAbsolutePath());
        return outputfile;
    }

    /**
     * Parses the given file list and makes the use choose one (and returns it)
     * @param input The method asks the user to choose a file through this scanner (the output is the system output)
     * @param files List of files in test input directory
     * @return Returns the chosen input file
     * @author Zsófi
     */
    private static File chooseFile(Scanner input, List<File> files) {
        System.out.println("Choose a testfile from the following:");
        int i = 1;
        for(File file : files) {
            System.out.printf("(%d) %s%n", i, file.getName()); i++;
        }
        return files.get(input.nextInt()-1);
    }
}

