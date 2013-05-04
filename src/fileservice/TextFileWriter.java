package fileservice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public class TextFileWriter implements FileWriterStrategy {

    private File file;

    public TextFileWriter(String filePath) {
        setFile(new File(filePath));
    }

    @Override
    public void writeLineToFile(String data, boolean append) throws IOException{
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
            writer.println(data);

        } catch (IOException ex) {
            throw ex;
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }

    public final File getFile() {
        return file;
    }

    public final void setFile(File file) {
        this.file = file;
    }

    @Override
    public void writeAllToFile(List<String> data, boolean append) throws IOException{

        List<String> inputData = new ArrayList<String>(data);
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
             for (String string : inputData) {
                writer.println(string);
                
            }
        } catch (IOException ioe) {
            throw ioe;
        }finally{
            try {
                writer.close();
            } catch (Exception e) {
            }
        }



    }

    public static void main(String[] args) throws IOException {
        TextFileWriter writer1 = new TextFileWriter("src/test.out");

        List<String> data = new ArrayList<String>();
        data.add("Jane,Smith,555 West Ave.,Waukesha,WI,55555,e@b.com,555-555-5558");
        data.add("John,Smith,555 West Ave.,Waukesha,WI,55555,f@b.com,555-555-5558");
        data.add("Jack,Anderson,555 West Ave.,Waukesha,WI,55555,g@b.com,555-555-5558");

//        for (String string : data) {
//            writer1.writeLineToFile(string, true);
//        }
        
        writer1.writeAllToFile(data, false);
    }
}
