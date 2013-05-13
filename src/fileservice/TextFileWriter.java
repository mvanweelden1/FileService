package fileservice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * This class is an implementation of the filewriterstrategy that is used for
 * writing data to a text file.
 *
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public class TextFileWriter implements FileWriterStrategy {

    private FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> formatter;
    public static final String NULL_FORMATTER_MSG = "Formatter is not specified ";
    private static final String WRITE_ERR_MSG = "Cannot write file ";
    private static final String CLOSE_ERR_MSG = "Error closing file ";

    /**
     * constructor for the text file writer
     *
     * @param formatter a format strategy object
     * @throws FileServiceException
     */
    public TextFileWriter(FormatStrategy formatter) throws FileServiceException {
        try {
            setFormatter(formatter);
        } catch (FileServiceException e) {
            throw new FileServiceException(e.getMessage());
        }

    }

    /**
     * This method is used for writing data to a text file
     *
     * @param filePath A String containing the filepath the data is to be
     * written to
     * @param data A list of linked hash maps containing the data to be written
     * @param append a boolean. True means the data will be appended to the file
     * False means the file will be overwritten
     *
     * @throws FileServiceException
     */
    @Override
    public final void writeToFile(String filePath,
            List<LinkedHashMap<String, String>> data,
            boolean append) throws FileServiceException {

        File file = new File(filePath);
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new BufferedWriter(
                    new FileWriter(file, append)));

            String encodedData = formatter.encode(data);
            writer.print(encodedData);
        } catch (IOException ioe) {
            throw new FileServiceException(WRITE_ERR_MSG + ioe.getMessage());
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                throw new FileServiceException(CLOSE_ERR_MSG);

            }
        }



    }
    /**
     * This method is used to get the value of the formatter variable
     * 
     * @return the formatter
     */
    public final FormatStrategy<List<LinkedHashMap<String, String>>, 
            List<String>> getFormatter() {
        return formatter;
    }
    /**
     * This method is used to set the format strategy variable
     * @param formatter a formatstrategy object
     * @throws FileServiceException 
     */
    public final void setFormatter(
            FormatStrategy<List<LinkedHashMap<String, String>>,
                    List<String>> formatter) throws FileServiceException {
        if (formatter == null) {
            throw new FileServiceException(NULL_FORMATTER_MSG);
        }
        this.formatter = formatter;
    }
    /**
     * This method is used to retrieve the current state of the textfilewriter 
     * object
     * 
     * @return a string
     */
    @Override
    public final String toString() {
        return "TextFileWriter{" + "formatter=" + formatter + '}';
    }
    /**
     * Hash code method
     * @return int hash
     */
    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.formatter);
        return hash;
    }
    /**
     * Equals method
     * @param obj
     * @return  a boolean
     */
    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TextFileWriter other = (TextFileWriter) obj;
        if (!Objects.equals(this.formatter, other.formatter)) {
            return false;
        }
        return true;
    }
    
    
}
