import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Doctor extends Human implements Serializable {


    public String specialization;
    public transient  Patient[] patients = new Patient[100];
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
