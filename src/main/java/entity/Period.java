package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "period", schema = "", catalog = "goszakupk_monitoring_system_db")
public class Period {
    private int idPeriod;
    private String text;
    private Set<Criteria> criterias = new HashSet<Criteria>(0);;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_period")
    public int getIdPeriod() {
        return idPeriod;
    }

    public void setIdPeriod(int idPeriod) {
        this.idPeriod = idPeriod;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Period that = (Period) o;
//
//        if (idPeriod != that.idPeriod) return false;
//        if (text != null ? !text.equals(that.text) : that.text != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idPeriod;
//        result = 31 * result + (text != null ? text.hashCode() : 0);
//        return result;
//    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "period")
    public Set<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(Set<Criteria> criterias) {
        this.criterias = criterias;
    }
}
