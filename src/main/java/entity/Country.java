package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country", schema = "", catalog = "goszakupk_monitoring_system_db")
public class Country {
    private int idCountry;
    private String acronym;
    private String name;
    private Set<Criteria> criterias = new HashSet<Criteria>(0);
    private Set<ReceivedInformation> receivedInformationes = new HashSet<ReceivedInformation>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country")
    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    @Basic
    @Column(name = "acronym")
    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Country that = (Country) o;
//
//        if (idCountry != that.idCountry) return false;
//        if (acronym != null ? !acronym.equals(that.acronym) : that.acronym != null) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idCountry;
//        result = 31 * result + (acronym != null ? acronym.hashCode() : 0);
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        return result;
//    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "countries")
    public Set<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(Set<Criteria> criterias) {
        this.criterias = criterias;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "country")
         public Set<ReceivedInformation> getReceivedInformationes() {
        return receivedInformationes;
    }

    public void setReceivedInformationes(Set<ReceivedInformation> receivedInformationes) {
        this.receivedInformationes = receivedInformationes;
    }
}
