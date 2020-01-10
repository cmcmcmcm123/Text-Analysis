import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DriverFile {
    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        Tokenizer t = new Tokenizer();
        File folder = new File(System.getProperty("user.dir") + "/src/InputData");
        String[] files = folder.list();
        int i = 1;

        if(files == null) System.out.println("Invalid Directory");
        else {
            for(String file : files) {
                t.readFile("src/InputData/" + file);

                ArrayList<Token> test =  t.getTokens();
                //System.out.println(i);
                i++;
//                for(Token e : test) {
//                    if(e.getToken().length() == 1) System.out.println((int) e.getToken().charAt(0));
//                    else System.out.println(e.getToken());
//                }
//                System.out.println("***********************************");
//                System.out.println("Total number of tokens: " + test.size());
            }
        }
        long endTime = System.nanoTime();
        double seconds = ((double) endTime - startTime) / 1_000_000_000.0;

        System.out.println("Total number of tokens: " + t.getTokens().size());
        System.out.println("Took " + seconds+ " seconds");
    }
}