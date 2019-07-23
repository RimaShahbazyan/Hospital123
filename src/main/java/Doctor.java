import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.LinkedList;

public class Doctor extends Human implements Serializable {


    public String specialization;
    private LinkedList<Patient> patients = new LinkedList<>();
    private void readDoctor(ObjectInputStream in)
            throws IOException, ClassNotFoundException{

    }
    @JsonCreator
    public Doctor(@JsonProperty("name") String n, @JsonProperty("surName")String s, @JsonProperty("specialization")String spec)
    {

        super(n,s);
        specialization = spec ;
    }



}
