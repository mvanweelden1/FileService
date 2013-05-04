package fileservice;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public interface FileWriterStrategy {
    
    public abstract void writeLineToFile(String data, boolean append)throws IOException;
    
    public abstract void writeAllToFile(List<String> data, boolean append)throws IOException;

}
