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

    public TextFileWriter(FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> formatter) {
        this.formatter = formatter;
    }

    @Override
    public void writeToFile(String filePath, List<LinkedHashMap<String, String>> data, boolean append) throws IOException {

        File file = new File(filePath);
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
            String encodedData = formatter.encode(data);
            writer.print(encodedData);
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }



    }

    

    public FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> getFormatter() {
        return formatter;
    }

    public void setFormatter(FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> formatter) {
        this.formatter = formatter;
    }
    
    public static void main(String[] args) throws IOException {
       LinkedHashMap<String, String> map1 = new LinkedHashMap<>();
       map1.put("Hours Parked: ", "22.0");
       map1.put("Fees Collected", "$10.00");
       
       LinkedHashMap<String, String> map2 = new LinkedHashMap<>();
       map2.put("Hours Parked: ", "3.25");
       map2.put("Fees Collected", "$2.50");
       
       List<LinkedHashMap<String, String>> data = new ArrayList<>();
       
       data.add(map1);
       data.add(map2);
       
       TextFileWriter writer = new TextFileWriter(new CsvCommaFormat(true));
       String filePath = File.separatorChar + "temp" + File.separatorChar 
                + "parkedHours.txt";
       
       writer.writeToFile(filePath, data, true);
       
    }
}
