/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fileservice;

/**
 *
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public class FileServiceException extends Exception{
    
    public static final String MSG = "File Service cound not process request";

    public FileServiceException() {
        super(MSG);
    }

    public FileServiceException(String message) {
        super(message);
    }

    public FileServiceException(String string, Throwable cause) {
        super(string, cause);
    }

    public FileServiceException(Throwable cause) {
        super(cause);
    }

    public FileServiceException(String string, Throwable cause, boolean bln, boolean bln1) {
        super(string, cause, bln, bln1);
    }
    
    

}
