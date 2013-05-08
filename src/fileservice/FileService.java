package fileservice;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *File service is a portable service for the reading and writing of files
 * Writing to a file requires the data to be passed in the form of a List of 
 * linked hash maps
 * Reading from a file returns an ArrayList of linked hash maps
 * 
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public class FileService {

    private FileReaderStrategy reader;
    private FileWriterStrategy writer;

    public FileService(FileReaderStrategy reader, FileWriterStrategy writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void writeFile(String filePath, List<LinkedHashMap<String, String>> data,
            boolean append) throws Exception {

        writer.writeToFile(filePath, data, append);
    }

    public List<LinkedHashMap<String, String>> readFile(String filePath) throws FileServiceException{
        ArrayList<LinkedHashMap<String, String>> data;
        data = (ArrayList<LinkedHashMap<String, String>>) reader.readFile(filePath);
       
       return data;
    }

    public FileReaderStrategy getReader() {
        return reader;
    }

    public void setReader(FileReaderStrategy reader) {
        this.reader = reader;
    }

    public FileWriterStrategy getWriter() {
        return writer;
    }

    public void setWriter(FileWriterStrategy writer) {
        this.writer = writer;
    }

    public static void main(String[] args) throws FileServiceException{
        FileService fs = null;
        try {
            fs = new FileService(new TextFileReader(new CsvCommaFormat(true)), new TextFileWriter(new CsvCommaFormat(true)));
        } catch (FileServiceException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

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
//        String filePath = File.separatorChar + "temp" + File.separatorChar
//                + "parkedHours.txt";
//        try {
//            fs.writeFile(filePath, data, true);
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex.getMessage());
//        }
          String filePath = File.separatorChar + "temp" + File.separatorChar
                + "parkedHours.txt";
        try {
            List<LinkedHashMap<String, String>> data = fs.readFile
                (File.separatorChar + "temp" + File.separatorChar 
                + "parkedHours.txt");
        
        for (LinkedHashMap<String, String> linkedHashMap : data) {
            System.out.println(linkedHashMap);
        }
           
        } catch (FileServiceException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }


    }
}