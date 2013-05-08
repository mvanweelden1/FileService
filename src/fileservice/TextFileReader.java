package fileservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
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

    public TextFileReader(FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> formatter) throws FileServiceException {
        try {
            setFormatter(formatter);
        } catch (IllegalArgumentException e) {
            throw new FileServiceException(e.getMessage());
        }



    }

    @Override
    public List<LinkedHashMap<String, String>> readFile(String filepath) throws FileServiceException {

        if (filepath == null || filepath.length() == ZERO) {
            throw new FileServiceException(NO_FILEPATH_MSG);
        }
        File file = new File(filepath);
        if(!(file.exists())){
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

    public FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> getFormatter() {
        return formatter;
    }

    public final void setFormatter(FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> formatter) {
        if (formatter == null) {
            throw new IllegalArgumentException(NULL_FORMATTER_MSG);
        }
        this.formatter = formatter;
    }
    

    public static void main(String[] args) throws FileServiceException, IOException {
        try {
            //        TextFileReader reader = new TextFileReader(new CsvCommaFormat(false));
            //        
            //        List<LinkedHashMap<String, String>> data = reader.readFile
            //                (File.separatorChar + "temp" + File.separatorChar 
            //                + "contactlist.txt");

            TextFileReader reader = new TextFileReader(new CsvCommaFormat(true));

            List<LinkedHashMap<String, String>> data = reader.readFile(File.separatorChar + "temp" + File.separatorChar
                    + "contactlistWithHeaders.txt");

            for (LinkedHashMap<String, String> linkedHashMap : data) {
                System.out.println(linkedHashMap);
            }
        } catch (FileServiceException ex) {
            throw new FileSystemException(ex.getMessage());
        }




    }
}
