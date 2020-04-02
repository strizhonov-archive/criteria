package daolayer.model;

import java.io.Serializable;

public class ContactValue implements Serializable {

    private static final long serialVersionUID = 4L;

    private long id;

    @Column(name = "value")
    private String value;
}
