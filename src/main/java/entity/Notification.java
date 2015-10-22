package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "notification", schema = "", catalog = "goszakupk_monitoring_system_db")
public class Notification {
    private int idNotification;
    private Set<ReceivedInformation> receivedInformations = new HashSet<ReceivedInformation>(0);;
//    private int idReceivedInformation;
    private String subject;
    private String text;
    private Timestamp sentDate;
//    private ReceivedInformation receivedInformation;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification")
    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

//    @Basic
//    @Column(name = "id_received_information")
//    public int getIdReceivedInformation() {
//        return idReceivedInformation;
//    }
//
//    public void setIdReceivedInformation(int idReceivedInformation) {
//        this.idReceivedInformation = idReceivedInformation;
//    }

    @Basic
    @Column(name = "subject")
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "sent_date")
    public Timestamp getSentDate() {
        return sentDate;
    }

    public void setSentDate(Timestamp sentDate) {
        this.sentDate = sentDate;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Notification that = (Notification) o;
//
//        if (idNotification != that.idNotification) return false;
//        if (idReceivedInformation != that.idReceivedInformation) return false;
//        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
//        if (text != null ? !text.equals(that.text) : that.text != null) return false;
//        if (sentDate != null ? !sentDate.equals(that.sentDate) : that.sentDate != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idNotification;
//        result = 31 * result + idReceivedInformation;
//        result = 31 * result + (subject != null ? subject.hashCode() : 0);
//        result = 31 * result + (text != null ? text.hashCode() : 0);
//        result = 31 * result + (sentDate != null ? sentDate.hashCode() : 0);
//        return result;
//    }


    @OneToMany(fetch=FetchType.LAZY, mappedBy = "notification")
    public Set<ReceivedInformation> getReceivedInformations() {
        return receivedInformations;
    }

    public void setReceivedInformations(Set<ReceivedInformation> receivedInformations) { this.receivedInformations = receivedInformations; }

}
