package itb.rpl.ppl.tgs2.znake.util;

/**
 *
 * @author 
 */
public class ZnakeConnection {
    
    private static final String fileName = "nama-filenya.txt";
    
    private static ZnakeConnection connection = new ZnakeConnection();
    
    private ZnakeConnection() {
        
    }
    
    public static ZnakeConnection getInstance() {
        return connection;
    }
}
