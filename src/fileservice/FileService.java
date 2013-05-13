package fileservice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 *File service is a portable service for the reading and writing of files
 * Writing to a file requires the data to be supplied in the form of a List of 
 * linked hash maps
 * Reading from a file returns an ArrayList of linked hash maps
 * 
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public class FileService {

    private FileReaderStrategy reader;
    private FileWriterStrategy writer;
    private static final int ZERO = 0;
    /**
     * constructor 
     * @param reader a reader strategy object used for reading files
     * @param writer a writer strategy object used for writing files
     */
    public FileService(FileReaderStrategy reader, FileWriterStrategy writer) {
        setReader(reader);
        setWriter(writer);
    }
    /**
     * This method is used to write data to a file
     * 
     * @param filePath a String containing the file path where the data is to 
     * be written
     * @param data a list on liked hashmaps containing the data to be written to 
     * the file
     * @param append a boolean true appends to the file, false overwrites the 
     * file
     * @throws Exception 
     */
    public final void writeFile(String filePath, List<LinkedHashMap<String, String>> data,
            boolean append) throws Exception {
        
        if (filePath == null || filePath.length() == ZERO || data == null){
            throw new IllegalArgumentException();
        }

        writer.writeToFile(filePath, data, append);
    }
    /**
     * This method is used to read data from a file
     * 
     * @param filePath a String containing the filepath where the data is to 
     * be read from
     * @return an ArrayList of linked hash maps containing the data from the 
     * file
     * 
     * @throws FileServiceException 
     */
    public final List<LinkedHashMap<String, String>> readFile(String filePath) throws FileServiceException{
        if (filePath == null || filePath.length() == ZERO){
            throw new IllegalArgumentException();
        }
        ArrayList<LinkedHashMap<String, String>> data;
        data = (ArrayList<LinkedHashMap<String, String>>) reader.readFile(filePath);
       
       return data;
    }
    /**
     * This method is used to get the value of the FileReaderStrategy variable
     * 
     * @return FileReaderStrategy
     */
    public final FileReaderStrategy getReader() {
        return reader;
    }
    /**
     * This method is use to set the value of the FileReaderStrategy variable
     * 
     * @param reader a FileReaderStrategy object 
     */
    public final void setReader(FileReaderStrategy reader) {
        if(reader == null){
            throw new IllegalArgumentException();
        }
        this.reader = reader;
    }
    /**
     * This method is used to get the value of the FileWriterStrategy varible
     * @return FileWriterStrategy
     */
    public final FileWriterStrategy getWriter() {
        return writer;
    }
    /**
     * This method is used to set the value of the FileWriterVariable
     * @param writer a FileWriterStrategy object
     */
    public final void setWriter(FileWriterStrategy writer) {
        if(writer == null){
            throw new IllegalArgumentException();
        }
        this.writer = writer;
    }
    /**
     * This method is used to get the current state of the object
     * @return A String containing the values of the reader and write variables
     */
    @Override
    public String toString() {
        return "FileService{" + "reader=" + reader + ", writer=" + writer + '}';
    }
    /**
     * This is a hashcode method
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.reader);
        hash = 11 * hash + Objects.hashCode(this.writer);
        return hash;
    }
 /**
  * This method is used to test of two objects are equal
  * 
  * @param obj the object to be tested
  * @return a boolean
  */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileService other = (FileService) obj;
        if (!Objects.equals(this.reader, other.reader)) {
            return false;
        }
        if (!Objects.equals(this.writer, other.writer)) {
            return false;
        }
        return true;
    }
    

    
}