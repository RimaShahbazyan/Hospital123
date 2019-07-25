import com.sun.org.apache.bcel.internal.generic.Select;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {


    private static String nameGenerator() {
        String[] RandNames = ("Rima Emma Rosie Shushan Mane Qnarik" +
                " Erik Khachatur Edgar Davit Rafael Armen").split(" ");
        String[] RandSurname = ("Sargsyan Tunyan Manukyan Papikyan Babayan " +
                "Avetyan Avetisyan Karapetyan Darbinyan Mirakyan").split(" ");
        return RandNames[(int) (RandNames.length * Math.random())] + " "
                + RandSurname[(int) (RandSurname.length * Math.random())];

    }

    private static String PassportGenerator() {
        Random random = new Random();
        char[] Letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        return "" + Letters[random.nextInt(Letters.length)] + Letters[random.nextInt(Letters.length)]
                + String.valueOf(random.nextInt(8999999) + 1000000);
    }

    public static void main(String[] args) {

        try (
                //Scanner Sc = new Scanner(System.in);

                Connection connection = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost:1433;database=MyDB",
                        "sa",
                        "1111"
                )
        )
        {
            ArrayList<Integer> DocId = new ArrayList<>();
            ArrayList<Integer> PatId = new ArrayList<>();

            ResultSet Doctors = connection.prepareStatement("Select ID from Doctor" ).executeQuery();
            ResultSet Patients = connection.prepareStatement("Select ID from Patient" ).executeQuery();
            while (Doctors.next())
            {
                DocId.add(Doctors.getInt("ID"));
            }
            while (Patients.next())
            {
                PatId.add(Patients.getInt("ID"));
            }
            Random random = new Random();
            PreparedStatement statement = null;
            statement=
                    connection.prepareStatement("INSERT INTO doc_pat (D_Id, P_id) VALUES(?,?)");
            for (int id : PatId)
            {

                statement.setString(1, String.valueOf(DocId.get(random.nextInt(DocId.size()))));
                statement.setString(2, String.valueOf(id));
                statement.addBatch();

            }
            statement.executeLargeBatch();
//            PreparedStatement statement = null;
//            String query = "UPDATE Doctor" +
//                    " SET PassportID = ? WHERE ID = ?";
//            statement = connection.prepareStatement(query);
//            for (int i = 1; i < 111; i++) {
//                statement.setString(1, PassportGenerator());
//                statement.setInt(2,i);
//                statement.addBatch();
//
//            }
//
//            statement.executeBatch();
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
                switch (smo[0]) {
                    case "Doctor":
                        for(Doctor d : AllDocs)
                        {
                            if(d.getName().equals(smo[1]) && d.getSurName().equals(smo[2])) {
                               AllDocs.remove(d);
                            }
                        }
                        break;
                    case "Patient":
                        for(Patient p : AllPats)
                        {
                            if(p.getName().equals(smo[1]) && p.getSurName().equals(smo[2])) {
                                AllDocs.remove(p);
                            }
                        }
                        break;                }
            }
            else
            {
                System.out.println("Command was typed incorrect");
            }
//
//            op.writeValue(new File("Docs.json"), AllDocs);
//            op.writeValue(new File("Pats.json"), AllPats);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println();
    }

    private static void ToSQL(String com, String fullname) {
        String command = com.toUpperCase();
        try (
                //Scanner Sc = new Scanner(System.in);

                Connection connection = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost:1433;database=MyDB",
                        "sa",
                        "1111"
                );
        ) {

            String query;
//            PreparedStatement statement= null;
//            query = "CREATE TABLE Patient( ID INT PRIMARY KEY IDENTITY, NAME VARCHAR(100), SURNAME VARCHAR(100) )";
//            statement = connection.prepareStatement(query);
//            statement.execute();
            PreparedStatement statement = null;
            // System.out.println("Write the command : ");
            //command = Sc.nextLine().toUpperCase();
            //while(!command.equals("EXIT")) {
            String[] param = fullname.split(" ");
            switch (command) {
                case "INSERT":

                    query = "INSERT INTO " + param[0] + "(name,surname,passport_id) VALUES(?,?,?)";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, param[1]);
                    statement.setString(2, param[2]);
                    statement.setString(3,PassportGenerator());
                    statement.execute();
                    break;
                case "DELETE":
                    query = "DELETE  from " + param[0] + " where name = ? and surname = ?";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, param[1]);
                    statement.setString(2, param[2]);
                    statement.execute();
                    break;
            }
            //command = Sc.nextLine().toUpperCase();
            //}

        } catch (SQLException  e ) {

            e.printStackTrace();
        }
    }

}
