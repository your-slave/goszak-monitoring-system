package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bonaparte on 24.05.2015.
 */
@Entity
@Table(name = "notification_type", schema = "", catalog = "goszakupk_monitoring_system_db")
public class NotificationType {
    private int idNotificationType;
    private String name;
    private Set<Criteria> criterias = new HashSet<Criteria>(0);;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification_type")
    public int getIdNotificationType() {
        return idNotificationType;
    }

    public void setIdNotificationType(int idNotificationType) {
        this.idNotificationType = idNotificationType;
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
//        NotificationType that = (NotificationType) o;
//
//        if (idNotificationType != that.idNotificationType) return false;
//        if (name != null ? !name.equals(that.name) : that.name != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idNotificationType;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        return result;
//    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "notificationType")
    public Set<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(Set<Criteria> criterias) {
        this.criterias = criterias;
    }
}
