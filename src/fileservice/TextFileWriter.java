package fileservice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public class TextFileWriter implements FileWriterStrategy<List<LinkedHashMap<String, String>>> {

    private FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> formatter;
    public static final String NULL_FORMATTER_MSG = "Formatter is not specified ";
    private static final String WRITE_ERR_MSG = "Cannot write file ";
    private static final String CLOSE_ERR_MSG = "Error closing file ";

    public TextFileWriter(FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> formatter) throws FileServiceException {
        try {
            setFormatter(formatter);
        } catch (FileServiceException e) {
            throw new FileServiceException(e.getMessage());
        }

    }

    @Override
    public void writeToFile(String filePath, List<LinkedHashMap<String, String>> data, boolean append) throws FileServiceException {

        File file = new File(filePath);
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
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

    public FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> getFormatter() {
        return formatter;
    }

    public final void setFormatter(FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> formatter) throws FileServiceException {
        if (formatter == null) {
            throw new FileServiceException(NULL_FORMATTER_MSG);
        }
        this.formatter = formatter;
    }

//    public static void main(String[] args) throws IOException {
//        LinkedHashMap<String, String> map1 = new LinkedHashMap<>();
//        map1.put("Hours Parked: ", "22.0");
//        map1.put("Fees Collected", "$10.00");
//
//        LinkedHashMap<String, String> map2 = new LinkedHashMap<>();
//        map2.put("Hours Parked: ", "3.25");
//        map2.put("Fees Collected", "$2.50");
//
//        List<LinkedHashMap<String, String>> data = new ArrayList<>();
//
//        data.add(map1);
//        data.add(map2);
//
//        TextFileWriter writer = new TextFileWriter(new CsvCommaFormat(true));
//        String filePath = File.separatorChar + "temp" + File.separatorChar
//                + "parkedHours.txt";
//
//        writer.writeToFile(filePath, data, true);
//
//    }
}
