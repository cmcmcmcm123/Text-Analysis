import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Tokenizer {
    /**
     *  Arraylist that will hold all of the tokens parsed from the document
     */
    private ArrayList<Token> tokens = new ArrayList<>();

    /**
     *  256 element array that will be used to check if a character is admissible or not
     */
    private boolean[] admissibleChars = new boolean[256];

    public static final int TOKENSIZE = 50;

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public boolean[] getAdmissibleChars() {
        return admissibleChars;
    }

    /**
     *  Tokenizer constructor
     *  Set all the numbers and letters (a-z and A-Z) to be true
     */
    public Tokenizer() {
        //set the numbers to true
        for(int i = 0; i < 10; i++) {
            admissibleChars[47 + i] = true;
        }
        //set the alphabets to true
        for(int i = 0; i < 26; i++) {
            admissibleChars[64 + i] = true;
            admissibleChars[96 + i] = true;

        }
    }

    /**
     * helper function for adding tokens in the list
     * @param tok
     */
    private void addToken(String tok) {
        Token token = new Token();
        token.setToken(tok);
        tokens.add(token);

        Stemmer stem = new Stemmer();

        if(token.getToken().length() > 0)
            System.out.println(stem.runStem(token.getToken()));
    }

    /**
     * Reads the file
     * @param fileName
     * @throws IOException
     */
    public void readFile(String fileName) throws IOException {
        FileReader file = null;
        Stack<Integer> buffer = new Stack<>();
        String output = "output.txt";
        //FileOutputStream fos = null;
        //DataOutputStream dos = null;

        try {
            String token = ""; //place holder for the token being parsed

            file = new FileReader(fileName);
           //fos = new FileOutputStream(output);
            //dos = new DataOutputStream(fos);

            ByteRead br = new ByteRead();

            int c = br.read(buffer, file);

            while(c != -1) {
                //read byte by byte, then output to a text file
               // dos.writeByte((char) c);

                //if admissible, then put in the token place holder
                if(admissibleChars[c - 1]) token += Character.toString((char) c);

                //special case if c is @ or .
                else if(c == 46 || c == 64) {
                    c = br.push_back(buffer, file, c);
                    if(c == -1) {
                        if(token.length() != 0) {
                            addToken(token);
                            token = "";
                        }

                        addToken(String.valueOf((char) br.pop_back(buffer)));
                    }
                    else if(admissibleChars[c - 1]) {
                        token += Character.toString((char) br.pop_back(buffer));
                        token += Character.toString((char) c);
                    }
                    else {
                        if (token.length() != 0) {
                            addToken(token);
                            token = "";
                        }

                        addToken(String.valueOf((char) br.pop_back(buffer)));
                        addToken(String.valueOf((char) c));
                    }
                }

                //if c is inadmissible
                else {
                    if(token.length() != 0) {
                        addToken(token);
                        token = "";
                    }

                    addToken(String.valueOf((char) c));
                }

                c = br.read(buffer, file);
            }
            if(token.length() != 0) addToken(token);

        } catch(Exception e) {
            System.out.println(e);
        } finally {
            file.close();
        }
    }
}
