package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "establishment", schema = "", catalog = "goszakupk_monitoring_system_db")
public class Establishment {
    private int idEstablishment;
    private String name;
    private Set<Criteria> criterias = new HashSet<Criteria>(0);;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_establishment")
    public int getIdEstablishment() {
        return idEstablishment;
    }

    public void setIdEstablishment(int idEstablishment) {
        this.idEstablishment = idEstablishment;
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
//        Establishment that = (Establishment) o;
//
//        if (idEstablishment != that.idEstablishment) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idEstablishment;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        return result;
//    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "establishment")
    public Set<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(Set<Criteria> criterias) {
        this.criterias = criterias;
    }
}
