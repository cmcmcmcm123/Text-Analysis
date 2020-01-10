public class Token {
    //the actual token parsed
    private String token;

    //this one is for later
    private String stem;

    //this one is for later
    private int tokenID;

    //position of the token in the file
    //this one is for later
    private int tokenPos;


    public Token() {

    }

    /**
     *  Getters
     *
     */
    public int getTokenID() {
        return tokenID;
    }

    public int getTokenPos() {
        return tokenPos;
    }

    public String getStem() {
        return stem;
    }

    public String getToken() {
        return token;
    }

    /**
     *  Setters
     *
     */
    public void setStem(String stem) {
        this.stem = stem;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTokenID(int tokenID) {
        this.tokenID = tokenID;
    }

    public void setTokenPos(int tokenPos) {
        this.tokenPos = tokenPos;
    }
}
