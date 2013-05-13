package fileservice;

import java.util.LinkedHashMap;
import java.util.List;

/**
 *This is an interface that dictates the the methods that all file writers 
 * must implement
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public interface FileWriterStrategy{
    
    
    /**
     * This method is used to write data to a file
     * @param filePath a String containing the filepath
     * @param data the data to be written
     * @param append a boolean true will append to the current file, false will
     * overwrite the current file
     * @throws FileServiceException 
     */
    public abstract void writeToFile(String filePath, 
            List<LinkedHashMap<String, String>> data,
            boolean append)throws FileServiceException;

}
