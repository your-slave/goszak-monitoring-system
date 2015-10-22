package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "received_information", schema = "", catalog = "goszakupk_monitoring_system_db")
public class ReceivedInformation {
    private int idReceivedInformation;
//    private Integer idCriteria;
    private String briefDescription;
    private String companyRegion;
//    private Integer idCountry;
    private Notification notification;
    private String number;
    private String cost;
    private Date requestEndTime;
    //private Set<Notification> notifications = new HashSet<Notification>(0);;
    private Criteria criteria;
    private Country country;
    private String link;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_received_information")
    public int getIdReceivedInformation() {
        return idReceivedInformation;
    }

    public void setIdReceivedInformation(int idReceivedInformation) {
        this.idReceivedInformation = idReceivedInformation;
    }

//    @Basic
//    @Column(name = "id_criteria")
//    public Integer getIdCriteria() {
//        return idCriteria;
//    }
//
//    public void setIdCriteria(Integer idCriteria) {
//        this.idCriteria = idCriteria;
//    }

    @Basic
    @Column(name = "brief_description")
    public String getBriefDescription() {
        return briefDescription;
    }

    public void setBriefDescription(String briefDescription) {
        this.briefDescription = briefDescription;
    }

    @Basic
    @Column(name = "company_region")
    public String getCompanyRegion() {
        return companyRegion;
    }

    public void setCompanyRegion(String companyRegion) {
        this.companyRegion = companyRegion;
    }

//    @Basic
//    @Column(name = "id_country")
//    public Integer getIdCountry() {
//        return idCountry;
//    }
//
//    public void setIdCountry(Integer idCountry) {
//        this.idCountry = idCountry;
//    }

    @Basic
    @Column(name = "link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "cost")
    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "request_end_time")
    public Date getRequestEndTime() {
        return requestEndTime;
    }

    public void setRequestEndTime(Date requestEndTime) {
        this.requestEndTime = requestEndTime;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        ReceivedInformation that = (ReceivedInformation) o;
//
//        if (idReceivedInformation != that.idReceivedInformation) return false;
//        if (idCriteria != null ? !idCriteria.equals(that.idCriteria) : that.idCriteria != null) return false;
//        if (briefDescription != null ? !briefDescription.equals(that.briefDescription) : that.briefDescription != null)
//            return false;
//        if (companyRegion != null ? !companyRegion.equals(that.companyRegion) : that.companyRegion != null)
//            return false;
//        if (idCountry != null ? !idCountry.equals(that.idCountry) : that.idCountry != null) return false;
//        if (number != null ? !number.equals(that.number) : that.number != null) return false;
//        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
//        if (requestEndTime != null ? !requestEndTime.equals(that.requestEndTime) : that.requestEndTime != null)
//            return false;
//        if (sent != null ? !sent.equals(that.sent) : that.sent != null) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idReceivedInformation;
//        result = 31 * result + (idCriteria != null ? idCriteria.hashCode() : 0);
//        result = 31 * result + (briefDescription != null ? briefDescription.hashCode() : 0);
//        result = 31 * result + (companyRegion != null ? companyRegion.hashCode() : 0);
//        result = 31 * result + (idCountry != null ? idCountry.hashCode() : 0);
//        result = 31 * result + (number != null ? number.hashCode() : 0);
//        result = 31 * result + (cost != null ? cost.hashCode() : 0);
//        result = 31 * result + (requestEndTime != null ? requestEndTime.hashCode() : 0);
//        result = 31 * result + (sent != null ? sent.hashCode() : 0);
//        return result;
//    }

//    @OneToMany(fetch=FetchType.LAZY, mappedBy = "receivedInformation")
//    public Set<Notification> getNotifications() {
//        return notifications;
//    }
//
//    public void setNotifications(Set<Notification> notificationsByIdReceivedInformation) {
//        this.notifications = notificationsByIdReceivedInformation;
//    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_notification", referencedColumnName = "id_notification")
    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_criteria", referencedColumnName = "id_criteria")
    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_country", referencedColumnName = "id_country")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
