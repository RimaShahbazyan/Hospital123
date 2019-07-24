
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        String command;
        try (
                Scanner Sc = new Scanner(System.in);

                Connection connection = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost:1433;database=MyDB",
                        "sa",
                        "1111"
                        );
                )
        {

            String query;
//            PreparedStatement statement= null;
//            query = "CREATE TABLE Patient( ID INT PRIMARY KEY IDENTITY, NAME VARCHAR(100), SURNAME VARCHAR(100) )";
//            statement = connection.prepareStatement(query);
//            statement.execute();
            PreparedStatement statement= null;
            System.out.println("Write the command : ");
            command = Sc.nextLine().toUpperCase();
            while(!command.equals("EXIT")) {
                String[] param = Sc.nextLine().split(" ");
                switch (command) {
                    case "INSERT":

                        query = "INSERT INTO "+param[0]+"(name,surname) VALUES(?,?)";
                        statement = connection.prepareStatement(query);
                        statement.setString(1,param[1]);
                        statement.setString(2,param[2]);
                        statement.execute();
                        break;
                    case "DELETE":
                        query = "DELETE  from "+param[0]+" where name = ? and surname = ?";
                        statement = connection.prepareStatement(query);
                        statement.setString(1,param[1]);
                        statement.setString(2,param[2]);
                        statement.execute();
                        break;
                }
                command = Sc.nextLine().toUpperCase();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        ArrayList<Doctor> AllDocs= new ArrayList<>();
//        ArrayList<Patient> AllPats =  new ArrayList<>();
//        try {
//            ObjectMapper op = new ObjectMapper();
//            FileReader frDocs = new FileReader("Docs.json");
//            FileReader frPats = new FileReader("Pats.json");
//
//            // restore from JSON
//
//            AllDocs = new ArrayList<>( Arrays.asList((op.readValue(frDocs,  Doctor[].class))));
//            AllPats = new ArrayList<>( Arrays.asList((op.readValue(frPats,  Patient[].class))));
//
//
//
//            System.out.print("Enter the command(Add, Remove, Print) :   ");
//            command = Sc.nextLine();
//            System.out.print("Who do you want to " + command +"(ipf: role name surname) :   ");
//            String[] smo = Sc.nextLine().split(" ");
//
//            if(command.equals( "Add"))
//            {
//                switch (smo[0]) {
//                    case "Doctor":
//                        AllDocs.add( new Doctor(smo[1], smo[2], "null")); break;
//                    case "Patient":
//                        AllPats.add( new Patient(smo[1], smo[2], "null")); break;
//                }
//
//            }
//            else if( command.equals( "Remove"))
//            {
//
//            }
//            else
//            {
//
//            }
//
//
//            op.writeValue(new File("Docs.json"), AllDocs);
//            op.writeValue(new File("Pats.json"), AllPats);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println();
    }
}
