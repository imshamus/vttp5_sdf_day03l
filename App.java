import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.swing.plaf.TreeUI;

import people.Person;
import service.CSVManagement;

public class App {

    public static void main(String[] args) throws IOException {

        // arg[0] arg[1] arg[2]
        // arg[0] "c:\data"
        // arg[1] myfile.txt
        
        String dirPath = args[0];   // what is arguments
        String fileName = args[1];  //
        String dirPathFileName = dirPath+ File.separator + fileName; 
        // this adds directory tgt w file name, c:\data\myfile.txt
        // what is file.separator? 
        // can just use "\" instead of File.separator also, but sometimes need "\\" so might have error
        System.out.println(dirPathFileName); // to view path 

        //check if a directory exists
        // if directory dne, create the directory
        
        File directory = new File(dirPath);

        if(directory.exists()) 
        { // read exist()
            System.out.println("Directory " + directory.toString() + "had alr been created."); // read on .tostring()
        }
        else
        {
            directory.mkdir();
        } 

        //check if a file exists
        // if file dne, create the file

        File file = new File(dirPathFileName);

        if (file.exists()) 
        {
            System.out.println("File " + file.toString() + "alr created");
        }
        else
        {
            file.createNewFile(); // had red zig line, quick fix throw exception (?)
        }

        Console console = System.console();
        String keyboardInput = console.readLine("Enter/Input a sentence: ");



        // // Example 1
        // // Use FileWriter 
        // // 1. Use console to read in a string of text (sentence)
        // // 2. Use FileWriter to write the content to the file

        // FileWriter fw = new FileWriter(file, true); // FileWriter() has many overloading functions (?) what is overloading (?) appned (?)
        
        // fw.write(keyboardInput); // What is filewriter()?
        // fw.flush();
        // fw.close();

        // // Use FileReader to read the file

        // FileReader fr = new FileReader(file);

        // int dataRead = fr.read(); // return type is integer, like 8 represent certain characters

        // // need a loop as its reading streams, 1 by 1 (?)
        // while(dataRead != -1) // -1 is end of file (?) lecture slide is while fr.read() = -1?
        // {
        //     System.out.println((char) dataRead); // dataRead is int, we want to change it to char??? ASCII table. What is typecast?
        //     dataRead = fr.read(); // if this line miss out becomes endless loop? if no have this line, it wont read the next one, keep reading same
        // }
        // fr.close();  
        


        // // Example 2
        // //Can use BufferedWriter to write to file

        // // find buffer reader documentation on oracle, read() a single at a time, readLine() line by line
        // // Buffer vs File?
        // // FileWriter slower thn Buffer as buffer is batch by batch (?)

        //  FilterWriter fw = new FileWriter(file, true);
        // BufferedWriter bw = new BufferedWriter(fw);

        // bw.append(keyboardInput);
        // fw.flush();
        // fw.close();
 
        // // Use BufferedReader to read file content 1, without try catch
        // //  ================================================================
        // FileReader fr = new FileReader(file);
        // BufferedReader br = new BufferedReader(fr);
        // String line = "";

        // while(br.readLine() != null) // string no have -1 so null (?)
        // {
        //     System.out.println(line);
        // }

        // br.close(); // close in reverse sequence
        // fr.close(); 
        // //  ======================================================================

        // // Use BufferedReader to read file content 2 
        //  try(FileReader fr = new FileReader(file))
        // { 
        //     try(BufferedReader br = new BufferedReader(fr))
        //     {
        //         String line = "";
        //         while ((line = br.readLine()) != null) 
        //         {
        //             System.out.println(line);    
        //         }
        //     }
        // };
        // BufferedReader br = new BufferedReader(fr);
        // String line = "";

        // while(br.readLine() != null) // string no have -1 so null (?)
        // {
        //     System.out.println(line);
        // }

        // br.close(); // close in reverse sequence
        // fr.close();



        // // example 3
        // // use FileOutputStream to write to file

        // // FileOutputStream fos = new FileOutputStream(file, true);
        // // byte[] byteContent = keyboardInput.getBytes();

        // // fos.write(byteContent);
        // // fos.flush();
        // // fos.close();

        // // // use FileInputStream to read the file content
        // // FileInputStream fis = new FileInputStream(file);
        // // int contentRead = 0;

        // // while((contentRead = fis.read()) != -1)
        // // {
        // //     System.out.println((char) contentRead);
        // // }

        // // fis.close();



        // Example 4 
        // Use FileOutputStream and DataOutputStream to write the content

        // FileOutputStream fos = new FileOutputStream(file, true); // default file, only for read and create file output, open and close
        // DataOutputStream dos = new DataOutputStream(fos); // only read bytes

        // byte[] byteContent = keyboardInput.getBytes();

        // dos.write(byteContent);
        // dos.flush(); // only for writing
        // dos.close();
        // fos.close();

        // // Use FileInputStream and DataInputStream to read the content

        // FileInputStream fis = new FileInputStream(file);
        // DataInputStream dis = new DataInputStream(fis);

        // int contentRead = 0;

        // while((contentRead = dis.read()) != -1)
        // {
        //     System.out.println((char) contentRead);
        // }



        // Example 5 (Decorator pattern)
        // Use FileOutputStream and GZIPOutputStream to write to file
        // FileOutputStream fos = new FileOutputStream(file);
        // GZIPOutputStream gos = new GZIPOutputStream(fos);
        // byte[] contents = keyboardInput.getBytes();

        // gos.write(contents);
        // gos.flush();
        // gos.close();
        // fos.close();

        // // Use FileInputStream and GZipInputStream to read the content
        // FileInputStream fis = new FileInputStream(file);
        // GZIPInputStream gis = new GZIPInputStream(fis);
        // int gisContent = 0;

        // while((gisContent = gis.read()) != -1)
        // {
        //     System.out.println((char) gisContent);
        // }

        // gis.close();
        // fis.close();




        // CSV Management, file read and write example

        List<Person> persons = new ArrayList<>();
        
        CSVManagement csv = new CSVManagement();
        persons = csv.readCSV(dirPathFileName);

        // menu
        // 1. Enter new Person details
        // 2. Save to file (Prompt for new csv file name)
        // 3. Quit and terminate program

        Console consoleSelection = System.console();
        Integer selection = 0;

        while (selection != 3)
        {
            System.out.println("1. Enter new Person details");
            System.out.println("2. Save to new csv file");
            System.out.println("3. Quit program");

            selection = Integer.parseInt(consoleSelection.readLine(">>>"));
        
            switch (selection) 
            {
                case 1: 
                    Console con1 = System.console();

                    String personName = con1.readLine("Enter person name: ");
                    String personRegion = con1.readLine("Enter region/area: ");
                    String personYOB = con1.readLine("Enter year of birth: ");

                    Person p = new Person(personName, personRegion, Integer.parseInt(personYOB));
                    persons.add(p);
                    break;

                case 2: 
                    Console con2 = System.console();

                    String newFileName = con2.readLine("Enter a CSV file to save (filename only): ");

                    csv.writeCSV(dirPath + File.separator + newFileName, persons);
                    break;
                
                default:
                    break;

            }
        }
        


    }
}