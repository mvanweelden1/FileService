package fileservice;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public interface FileWriterStrategy<T> {
    
    
    
    public abstract void writeToFile(String filePath, T data, boolean append)throws IOException;

}
