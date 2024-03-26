import java.util.*;

public class JSCHLogger implements com.jcraft.jsch.Logger {
    static Hashtable levels=new Hashtable();
    static{
        levels.put(new Integer(DEBUG), "DEBUG: ");
        levels.put(new Integer(INFO), "INFO: ");
        levels.put(new Integer(WARN), "WARN: ");
        levels.put(new Integer(ERROR), "ERROR: ");
        levels.put(new Integer(FATAL), "FATAL: ");
    }
    public boolean isEnabled(int level){
        return true;
    }
    public void log(int level, String message){
        System.err.print(levels.get(new Integer(level)));
        System.err.println(message);
    }
}