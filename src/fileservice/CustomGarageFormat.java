package fileservice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *This is a custom garage format for for garage sales totals from the garagepos 
 * project
 * 
 * 
 * @author Mark Van Weelden <mvanweelden1@my.wctc.edu>
 */
public class CustomGarageFormat implements FormatStrategy<List<LinkedHashMap<String, String>>, List<String>> {

    private static final String CRLF = "\n";
    private static final String HOURS_KEY = "totalHours";
    private static final String FEES_KEY = "totalFees";
    /**
     * This method is used to decode the data retrieved from the file
     * @param dataFromSrc A list of linkedhashmaps containing the data from the 
     * file.
     * @return a list of linked has maps containing the decoded data
     */
    @Override
    public final List<LinkedHashMap<String, String>> decode(List<String> dataFromSrc) {
        if(dataFromSrc.isEmpty()){
            return null;
        }
        
        List<LinkedHashMap<String, String>> decodedData =
                new ArrayList<>();

        LinkedHashMap<String, String> valueMap =
                new LinkedHashMap<>();
        valueMap.put(HOURS_KEY, dataFromSrc.get(0));
        valueMap.put(FEES_KEY, dataFromSrc.get(1));
        decodedData.add(valueMap);

        return decodedData;
    }
    /**
     * This method is used to encode data into a string that can then be written
     * to a file
     * @param dataFromSrc a list of linked hash maps
     * @return a String containing the data to be written to the file
     */
    @Override
    public final String encode(List<LinkedHashMap<String, String>> dataFromSrc) {
        if(dataFromSrc == null){
            throw new IllegalArgumentException();
        }
        StringBuilder dataFormatted = new StringBuilder();

        LinkedHashMap<String, String> hours = dataFromSrc.get(0);
        LinkedHashMap<String, String> fees = dataFromSrc.get(1);
        dataFormatted.append(hours.get(HOURS_KEY)).append(CRLF);
        dataFormatted.append(fees.get(FEES_KEY));

        return dataFormatted.toString();
    }
    
    
}
