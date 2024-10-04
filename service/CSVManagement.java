package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import people.Person;

public class CSVManagement 
{
    //filename: dirPath + file separator + filename
    // public void readCSV(String filename) throws IOException // ioexception vs filenotfoundexception
    public List<Person> readCSV(String filename) throws IOException // returns a person obj instead of nth
    {
        // Use BufferReader
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        while((line = br.readLine()) != null)
        {
            System.out.println(line);

            //LineData[0], LineData[1] , LineDate[2]
        }
        br.close();
        fr.close();
    }    

    public void writeCSV(String filename)
    {
        // FileWriter fw = new FilterWriter(filename);
    }
}
