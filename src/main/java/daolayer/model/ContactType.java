package daolayer.model;

import java.io.Serializable;

public class ContactType implements Serializable {

    private static final long serialVersionUID = 4L;

    private long id;

    @Column(name = "type")
    private String type;

}
