package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zakup_type_2", schema = "", catalog = "goszakupk_monitoring_system_db")
public class ZakupType2 {
    private int idZakupType2;
    private String name;
    private Set<Criteria> criterias = new HashSet<Criteria>(0);;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zakup_type_2")
    public int getIdZakupType2() {
        return idZakupType2;
    }

    public void setIdZakupType2(int idZakupType2) {
        this.idZakupType2 = idZakupType2;
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
//        ZakupType2 that = (ZakupType2) o;
//
//        if (idZakupType2 != that.idZakupType2) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idZakupType2;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        return result;
//    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "zakupType2s")
    public Set<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(Set<Criteria> criterias) {
        this.criterias = criterias;
    }
}
