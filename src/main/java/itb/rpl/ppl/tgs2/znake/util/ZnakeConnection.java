package itb.rpl.ppl.tgs2.znake.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author 
 */
public class ZnakeConnection {
    
    private static ZnakeConnection connection = new ZnakeConnection();
    
    private ZnakeConnection() {
        Connection connection = null;
//        try {
//            Class.forName("com.mysql.Driver").newInstance();
//            connection = DriverManager.getConnection();
//        } catch () {
        
    }
    
    public static ZnakeConnection getInstance() {
        return connection;
    }
}
