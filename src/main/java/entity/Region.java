package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "region", schema = "", catalog = "goszakupk_monitoring_system_db")
public class Region {
    private int idRegion;
    private String name;
    private Set<Criteria> criterias = new HashSet<Criteria>(0);;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_region")
    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
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
//        Region that = (Region) o;
//
//        if (idRegion != that.idRegion) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//
//        return true;
//    }

    @Override
    public int hashCode() {
        int result = idRegion;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "regions")
    public Set<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(Set<Criteria> criterias) {
        this.criterias = criterias;
    }
}
