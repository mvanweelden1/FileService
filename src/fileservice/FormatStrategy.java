package fileservice;

/**
 *
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public interface FormatStrategy<T, E> {

    T decode(E dataFromSrc);

    String encode(T dataFromSrc);
}
