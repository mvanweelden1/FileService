package fileservice;

/**
 *This interface dictates the methods that all formats must have
 * Generics are used for the return types and the parameters
 * 
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public interface FormatStrategy<T, E> {
    /**
     * Method used for decoding data from the file.
     * @param dataFromSrc
     * @return 
     */
    public abstract T decode(E dataFromSrc);
    /**
     * method used to encode data to be written to the file
     * @param dataFromSrc
     * @return 
     */
    public abstract String encode(T dataFromSrc);
}
