package fileservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public class TextFileReader implements FileReaderStrategy{
    private FormatStrategy<List<LinkedHashMap<String, String>>,List<String>> formatter;
    

    public TextFileReader(FormatStrategy<List<LinkedHashMap<String, String>>,List<String>> formatter) {
        this.formatter = formatter;
        
    }
    
    

    @Override
    public List<LinkedHashMap<String, String>> readFile(String filepath) throws IOException{
        File file = new File(filepath); 
        BufferedReader in = null;
        List<String> rawData = null;
        try {
            in = new BufferedReader(new FileReader(file));
            
            String line = in.readLine();
            rawData = new ArrayList<String>();
            
            while(line != null){
               rawData.add(line); 
               line = in.readLine();
            }
        } catch (IOException ioe) {
            throw ioe;
        }finally{
            try {
                in.close();
            } catch (IOException ioe) {
                throw ioe;
            }
        }
        return formatter.decode(rawData);
        
    }

    
    public static void main(String[] args) throws IOException {
        
//        TextFileReader reader = new TextFileReader(new CsvCommaFormat(false));
//        
//        List<LinkedHashMap<String, String>> data = reader.readFile
//                (File.separatorChar + "temp" + File.separatorChar 
//                + "contactlist.txt");
        
        TextFileReader reader = new TextFileReader(new CsvCommaFormat(true));
        
        List<LinkedHashMap<String, String>> data = reader.readFile
                (File.separatorChar + "temp" + File.separatorChar 
                + "contactlistWithHeaders.txt");
        
        for (LinkedHashMap<String, String> linkedHashMap : data) {
            System.out.println(linkedHashMap);
        }
        
     
        
       
    }

}
