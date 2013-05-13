package fileservice;

/**
 *This is a custom exception for the file service program
 * 
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public class FileServiceException extends Exception{
    
    public static final String MSG = "File Service cound not process request";
    /**
     * Constructs an exception with the default error message
     */
    public FileServiceException() {
        super(MSG);
    }
    /**
     * Constructs an exception with a custom message
     * 
     * @param message a String 
     */
    public FileServiceException(String message) {
        super(message);
    }
    /**
     * Constructs an exception with a custom message and the cause
     * @param string a String
     * @param cause the cause
     */
    public FileServiceException(String string, Throwable cause) {
        super(string, cause);
    }
    /**
     * Constructs an exception with the cause
     * 
     */
    public FileServiceException(Throwable cause) {
        super(cause);
    }
    
    

}
