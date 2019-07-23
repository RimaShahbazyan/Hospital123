
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        ArrayList<Doctor> AllDocs= new ArrayList<>();
        ArrayList<Patient> AllPats =  new ArrayList<>();
        try {
            ObjectMapper op = new ObjectMapper();
            FileReader frDocs = new FileReader("Docs.json");
            FileReader frPats = new FileReader("Pats.json");

            // restore from JSON

            AllDocs = new ArrayList<>( Arrays.asList((op.readValue(frDocs,  Doctor[].class))));
            AllPats = new ArrayList<>( Arrays.asList((op.readValue(frPats,  Patient[].class))));


            Scanner Sc = new Scanner(System.in);
            System.out.print("Enter the command(Add, Remove, Print) :   ");
            String command = Sc.nextLine();
            System.out.print("Who do you want to " + command +"(ipf: role name surname) :   ");
            String[] smo = Sc.nextLine().split(" ");

            if(command.equals( "Add"))
            {
                switch (smo[0]) {
                    case "Doctor":
                        AllDocs.add( new Doctor(smo[1], smo[2], "null")); break;
                    case "Patient":
                        AllPats.add( new Patient(smo[1], smo[2], "null")); break;
                }

            }
            else if( command.equals( "Remove"))
            {

            }
            else
            {

            }


            op.writeValue(new File("Docs.json"), AllDocs);
            op.writeValue(new File("Pats.json"), AllPats);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
