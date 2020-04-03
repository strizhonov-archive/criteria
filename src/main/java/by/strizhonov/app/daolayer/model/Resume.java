package by.strizhonov.app.daolayer.model;

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
        if (this.birthday == null) {
            return null;
        } else {
            return new Date(birthday.getTime());
        }
    }

    public void setBirthday(final Date birthday) {
        if (birthday == null) {
            this.birthday = null;
        } else {
            this.birthday = new Date(birthday.getTime());
        }
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


    public static final class ResumeBuilder {

        private long id;
        private String name;
        private String surname;
        private String secondName;
        private Date birthday;
        private String gender;
        private Set<Contact> contacts = new HashSet<>();
        private Set<String> technologies = new HashSet<>();


        public ResumeBuilder() {
            // Empty builder constructor
        }


        public ResumeBuilder id(final long id) {
            this.id = id;
            return this;
        }

        public ResumeBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public ResumeBuilder surname(final String surname) {
            this.surname = surname;
            return this;
        }

        public ResumeBuilder secondName(final String secondName) {
            this.secondName = secondName;
            return this;
        }

        public ResumeBuilder birthday(final Date birthday) {
            if (birthday == null) {
                this.birthday = null;
            } else {
                this.birthday = new Date(birthday.getTime());
            }
            return this;
        }

        public ResumeBuilder gender(final String gender) {
            this.gender = gender;
            return this;
        }

        public ResumeBuilder contacts(final Set<Contact> contacts) {
            this.contacts = contacts;
            return this;
        }

        public ResumeBuilder technologies(final Set<String> technologies) {
            this.technologies = technologies;
            return this;
        }

        public Resume build() {
            Resume resume = new Resume();
            resume.setId(id);
            resume.setName(name);
            resume.setSurname(surname);
            resume.setSecondName(secondName);
            resume.setBirthday(birthday);
            resume.setGender(gender);
            resume.setContacts(contacts);
            resume.setTechnologies(technologies);
            return resume;
        }
    }
}
