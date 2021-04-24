import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Eric Deng
 */
public class MorseCodeConverter {

    public static MorseCodeTree tree=new MorseCodeTree();
    public MorseCodeConverter(){ }

    /**
     * Converts Morse code into English. Each letter is delimited by a space (‘ ‘).
     * Each word is delimited by a ‘/’.a file that contains ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
     * string returned = "Hello World"
     * @param codeFile name of the File that contains Morse Code
     * @return the English translation of the file
     * @throws FileNotFoundException
     */
    public static String convertToEnglish(File codeFile) throws FileNotFoundException{
        String code="";

            Scanner scan=new Scanner(codeFile);
            while(scan.hasNextLine()) {
                code += scan.nextLine();
            }
        //System.out.print(code);

        return convertToEnglish(code);
    }

    /**
     * Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
     * Example:
     * code = ".... . .-.. .-.. --- / .-- --- .-. .-.. -.."
     * string returned = "Hello World"
     * @param code the morse code
     * @return the English translation

     */
    public static String convertToEnglish(String code){
        String line="";
        String arr[]=code.split(" ");
        for(int i=0; i<arr.length;i++){
            if(arr[i].equals("/")) {
                line += " ";
            }
            line+=tree.fetch(arr[i]);
        }

        return line;
    }

    /**
     * returns a string with all the data in the tree in LNR order with an space in between them.
     * Uses the toArrayList method in MorseCodeTree It should return the data in this order:
     * "h s v i f u e l r a p w j b d x n c k y t z g q m o"
     * @return the data in the tree in LNR order separated by a space.
     */
    public static String printTree(){
        String printedTree="";
        for(String s: tree.toArrayList()){
            printedTree+=s+" ";
        }
        return printedTree;
    }
}
