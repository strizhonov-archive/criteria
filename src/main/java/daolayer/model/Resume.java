package daolayer.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "resume")
public class Resume implements Serializable {

    private static final long serialVersionUID = 4L;

    @Column(name = "id")
    private long id;

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

    private Set<Contact> contacts = new HashSet<>();

    private Set<String> technologies = new HashSet<>();

    public Resume() {
        // Empty bean constructor
    }

    public Resume(final long id, final String name, final String surname, final String secondName,
                  final Date birthday, final String gender, final Set<Contact> contacts, final Set<String> technologies) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.birthday = birthday;
        this.gender = gender;
        this.contacts = contacts;
        this.technologies = technologies;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(final String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(final String gender) {
        this.gender = gender;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(final Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<String> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(final Set<String> technologies) {
        this.technologies = technologies;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Resume resume = (Resume) o;
        return id == resume.id &&
                Objects.equals(name, resume.name) &&
                Objects.equals(surname, resume.surname) &&
                Objects.equals(secondName, resume.secondName) &&
                Objects.equals(birthday, resume.birthday) &&
                Objects.equals(gender, resume.gender) &&
                Objects.equals(contacts, resume.contacts) &&
                Objects.equals(technologies, resume.technologies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, secondName, birthday, gender, contacts, technologies);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", contacts=" + contacts +
                ", technologies=" + technologies +
                '}';
    }

}
