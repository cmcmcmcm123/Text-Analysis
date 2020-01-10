import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class ByteRead {
    public int read(Stack<Integer> buffer, FileReader is) throws IOException {
        int c = 0;

        try {
            //if(!buffer.empty()) c = -2;// for now
            c = is.read();
        } catch(Exception e) {
            System.out.println(e);
        }

        return c;
    }

    public int push_back(Stack<Integer> buffer, FileReader is, int c) throws IOException {
        buffer.push(c);
        return read(buffer, is);
    }
    public int pop_back(Stack<Integer> buffer) {
        return buffer.pop();
    }
}
