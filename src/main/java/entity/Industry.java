package entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "industry", schema = "", catalog = "goszakupk_monitoring_system_db")
public class Industry {
    private int idIndustry;
    private String name;
    private boolean subtype;
    private Collection<Criteria> citerias = new HashSet<Criteria>(0);;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_industry")
    public int getIdIndustry() {
        return idIndustry;
    }

    public void setIdIndustry(int idIndustry) {
        this.idIndustry = idIndustry;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "subtype", columnDefinition = "TINYINT", length = 1)
    public boolean getSubtype() {
        return subtype;
    }

    public void setSubtype(boolean subtype) {
        this.subtype = subtype;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        IndustryDAOImpl that = (IndustryDAOImpl) o;
//
//        if (idIndustry != that.idIndustry) return false;
//        if (subtype != that.subtype) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idIndustry;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (int) subtype;
//        return result;
//    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "industries")
    public Collection<Criteria> getCiterias() {
        return citerias;
    }

    public void setCiterias(Collection<Criteria> citerias) {
        this.citerias = citerias;
    }
}
