package daolayer.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Table(name = "resume")
public class Resume implements Serializable {

    private static final long serialVersionUID = 4L;

    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "gender")
    private String gender;

    private Map<ContactType, ContactValue> contacts;

    private Set<Technology> technologies;

    public Resume() {
    }


}
