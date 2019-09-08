package ee.sda.mackirill.entities;

import javax.persistence.*;

@Entity
@Table(name = "person_type")
public class PersonType {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "type", nullable = false)
    private String type;
    @OneToOne(mappedBy = "personType", cascade = CascadeType.ALL)
    private Person person;

    public PersonType() {
    }

    public PersonType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
