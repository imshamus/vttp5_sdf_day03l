package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import people.Person;

public class CSVManagement 
{
    //filename: dirPath + file separator + filename
    // public void readCSV(String filename) throws IOException // ioexception vs filenotfoundexception
    public List<Person> readCSV(String filename) throws IOException // returns a person obj instead of nth
    // why return List<Person> in read? you need to trf data for java to use?
    
    {
        // Use BufferReader
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);

        List<Person> persons = new ArrayList<>();
        String line = "";
        while((line = br.readLine()) != null)
        {
            System.out.println(line);

            //LineData[0], LineData[1] , LineDate[2]
            String[] lineData = line.split(","); //split the lines that was read that was seperated by ,

            // put the extracted line data into a Person object
            Person p = new Person(lineData[0], lineData[1], Integer.parseInt(lineData[2].trim())); // last one because year must be converted from str to int
            // why trim?
            persons.add(p);
            
        }
        br.close();
        fr.close();

        return persons;
    }    

    public void writeCSV(String fullDirPathFilename, List<Person> persons) throws IOException
    {
        FileWriter fw = new FileWriter(fullDirPathFilename, false);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Person p : persons)
        {
            bw.append(p.getName());
            bw.append(",");
            bw.append(p.getRegion());
            bw.append(",");
            bw.append(String.valueOf(p.getYearOfBirth()));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        fw.close();

    }
}
