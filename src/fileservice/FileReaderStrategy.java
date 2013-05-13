package fileservice;


/**
 *This is an interface that dictates what methods all file readers must contain
 * It uses generics for the data return type
 * 
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public interface FileReaderStrategy<T> {
    /**
     * This method is used to read from a file
     * @param filepath a String containing the filepath
     * @return this is a generic decided at implementation
     * @throws FileServiceException 
     */
    public abstract T readFile(String filepath) throws FileServiceException;
}
