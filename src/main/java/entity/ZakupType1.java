package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zakup_type_1", schema = "", catalog = "goszakupk_monitoring_system_db")
public class ZakupType1 {
    private int idZakupType1;
    private String name;
    private Set<Criteria> criterias = new HashSet<Criteria>(0);;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zakup_type_1")
    public int getIdZakupType1() {
        return idZakupType1;
    }

    public void setIdZakupType1(int idZakupType1) {
        this.idZakupType1 = idZakupType1;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        entity.ZakupType1 that = (entity.ZakupType1) o;

        if (idZakupType1 != that.idZakupType1) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idZakupType1;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "zakupType1s")
    public Set<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(Set<Criteria> criterias) {
        this.criterias = criterias;
    }
}
