import java.io.IOException;
import java.io.ObjectInputStream;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Patient  extends Human implements Serializable  {
    @JsonCreator
    public Patient(@JsonProperty("name") String n,@JsonProperty("surName") String s,@JsonProperty("disease") String d) {
        super(n, s);
        disease = d;

    }

    private String disease;
    private  transient Doctor[] docs = new Doctor[5];

    private void readPatient(ObjectInputStream in)
            throws IOException, ClassNotFoundException{

    }
}
