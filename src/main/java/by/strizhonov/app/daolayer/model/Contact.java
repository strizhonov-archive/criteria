package by.strizhonov.app.daolayer.model;

import java.io.Serializable;
import java.util.Objects;

@Table(name = "contact")
public class Contact implements Serializable {

    private static final long serialVersionUID = 4L;

    @Column(name = "id")
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "value")
    private String value;

    public Contact() {
        // Empty bean constructor
    }

    public Contact(final long id, final String type, final String value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Contact contact = (Contact) o;
        return id == contact.id &&
                Objects.equals(type, contact.type) &&
                Objects.equals(value, contact.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, value);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}
