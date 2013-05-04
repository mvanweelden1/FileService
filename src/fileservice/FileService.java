package fileservice;



/**
 *
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public class FileService {
    private FileReaderStrategy reader;
    private FileWriterStrategy writer;

    public FileService() {
    }
    
    public FileService(FileReaderStrategy reader, FileWriterStrategy writer) {
        this.reader = reader;
        this.writer = writer;
    }
    
    public void writeFile(){
        
    }
    
    public void readFile(){
        
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
    
 
}