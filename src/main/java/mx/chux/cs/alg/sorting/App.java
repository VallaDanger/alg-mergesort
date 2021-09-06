package mx.chux.cs.alg.sorting;

import java.util.Arrays;
import java.util.logging.Logger;

public class App {
    
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());
    
    public static void main( String[] args ) {
        
        Integer[] array = new Integer[] { 
               43,
                3,
               55,
               23,
               90,
                5,
               41,
               69,
               18,
               62,
               68,
               22,
               42,
               35,
               37,
               58,
               54,
               79,
               34,
                9,
               70,
               84,
               50,
               52,
               31,
               10,
                2,
               77,
               11,
               98,
               67,
               76,
               24,
               93,
               15,
               63,
               26,
               28,
              100,
               86,
               81,
               72,
               97,
               48,
               13,
               40,
               78,
               19,
               47,
               99,
               85,
                7,
               57,
               51,
               27,
               73,
               60,
               61,
               92,
                4,
               53,
               96,
               80,
               88,
               45,
               30,
               21,
               29,
               16,
               66,
               65,
               64,
                8,
               91,
               94,
               71,
               56,
               12,
               17,
               36,
               49,
               75,
               32,
                1,
                6,
               44,
               33,
               89,
               20,
               82,
               46,
               39,
               74,
               25,
               95,
               83,
               38,
               87,
               14,
               59
        };
        
        Mergesort<Integer> sorter = Mergesort.sorter(array);
        
        String sortedArray = Arrays.toString(sorter.sort());
        
        LOGGER.info(sortedArray);
        
        sortedArray = Arrays.toString(sorter.sortInPlace());
        
        LOGGER.info(sortedArray);
        
    }
    
}
