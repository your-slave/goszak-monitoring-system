package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user", schema = "", catalog = "goszakupk_monitoring_system_db")
public class User {
    private int idUser;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private byte[] password;
    private Set<Criteria> criterias = new HashSet<Criteria>(0);;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "password")
    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
         @Column(name = "last_name")
         public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        User that = (User) o;
//
//        if (idUser != that.idUser) return false;
//        if (phoneNumber != that.phoneNumber) return false;
//        if (email != null ? !email.equals(that.email) : that.email != null) return false;
//        if (!Arrays.equals(password, that.password)) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idUser;
//        result = 31 * result + (email != null ? email.hashCode() : 0);
//        result = 31 * result + phoneNumber;
//        result = 31 * result + (password != null ? Arrays.hashCode(password) : 0);
//        return result;
//    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "user")
    public Set<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(Set<Criteria> criterias) {
        this.criterias = criterias;
    }

    @Override public String toString()
    {
        return "idUser:" + idUser;
    }
}
