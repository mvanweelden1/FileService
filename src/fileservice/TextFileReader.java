package fileservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 *This class is an implementation of the file reader strategy interface.
 * It is used for reading the data from a text file
 * 
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public class TextFileReader implements FileReaderStrategy {

    private FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> formatter;
    public static final String NULL_FORMATTER_MSG = "Formatter is not specified ";
    public static final String NO_FILEPATH_MSG = "File path not specified ";
    private static final String READ_ERR_MSG = "Unable to read file ";
    private static final String CLOSE_ERR_MSG = "Error closing file ";
    private static final String FILE_DOESNT_EXIST_ERR_MSG = "File doesnt exist ";
    private static final int ZERO = 0;
    /**
     * Constructor
     * 
     * @param formatter a format strategy object
     * @throws FileServiceException 
     */
    public TextFileReader(FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> formatter) throws FileServiceException {
        try {
            setFormatter(formatter);
        } catch (IllegalArgumentException e) {
            throw new FileServiceException(e.getMessage());
        }
    }
    /**
     * This method is used for reading data from a text file
     * @param filepath a String containing the filepath the data is to be read
     * from
     * @return a list of linked hash maps containing the data read from the file
     * @throws FileServiceException 
     */
    @Override
    public final List<LinkedHashMap<String, String>> readFile(String filepath) throws FileServiceException {

        if (filepath == null || filepath.length() == ZERO) {
            throw new FileServiceException(NO_FILEPATH_MSG);
        }
        File file = new File(filepath);
        if (!(file.exists())) {
            throw new FileServiceException(FILE_DOESNT_EXIST_ERR_MSG);
        }

        BufferedReader in = null;
        List<String> rawData = null;
        try {

            in = new BufferedReader(new FileReader(file));

            String line = in.readLine();
            rawData = new ArrayList<String>();

            while (line != null) {
                rawData.add(line);
                line = in.readLine();
            }

        } catch (IOException ioe) {
            throw new FileServiceException(READ_ERR_MSG + ioe.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException ioe) {
                throw new FileServiceException(CLOSE_ERR_MSG);
            }
        }
        return formatter.decode(rawData);

    }
    /**
     * This method is used to get the value of the format strategy variable
     * @return the value of the format strategy object
     */
    public final FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> getFormatter() {
        return formatter;
    }
    /**
     * This method is used to set the formatter variable
     * @param formatter  a format strategy object
     */
    public final void setFormatter(FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> formatter) {
        if (formatter == null) {
            throw new IllegalArgumentException(NULL_FORMATTER_MSG);
        }
        this.formatter = formatter;
    }
    /**
     * This method is used to retrieve the current state of the object
     * @return a string
     */
    @Override
    public final String toString() {
        return "TextFileReader{" + "formatter=" + formatter + '}';
    }
    /**
     * The hash code method
     * @return hash
     */
    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.formatter);
        return hash;
    }
    /**
     * Equals method
     * @param obj the object to be compared
     * @return a boolean
     */
    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TextFileReader other = (TextFileReader) obj;
        if (!Objects.equals(this.formatter, other.formatter)) {
            return false;
        }
        return true;
    }
    

  
}
