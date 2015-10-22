package entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bonaparte on 24.05.2015.
 */
@Entity
@Table(name = "criteria", schema = "", catalog = "goszakupk_monitoring_system_db")
public class Criteria {
    private int idCriteria;
//    private int idUser;
//    private int idNotificationType;
//    private Integer idEstablishment;
//    private Integer idCompany;
//    private Integer idZakupNumber;
//    private Integer idPeriod;
//    private Integer idSearchText;
//    private Integer idOkrbSubtype;
    private Date creationFrom;
    private Date creationTo;
    private Date requestEndTimeFrom;
    private Date requestEndTimeTo;
    private String okrbSubtype;
    private Period period;
    private String searchText;
    private String zakupNumber;
    private String company;
    private Establishment establishment;
    private NotificationType notificationType;
    private User user;
    private String sendingFrequency;
    private boolean international;
    private byte status;
    private Set<Country> countries = new HashSet<Country>(0);
    private Set<Industry> industries = new HashSet<Industry>(0);
    private Set<Region> regions = new HashSet<Region>(0);
    private Set<ZakupType1> zakupType1s = new HashSet<ZakupType1>(0);
    private Set<ZakupType2> zakupType2s = new HashSet<ZakupType2>(0);
    private Set<ReceivedInformation> receivedInformations = new HashSet<ReceivedInformation>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_criteria")
    public int getIdCriteria() {
        return idCriteria;
    }

    public void setIdCriteria(int idCriteria) {
        this.idCriteria = idCriteria;
    }

    @Basic
    @Column(name = "creation_from")
    public Date getCreationFrom() {
        return creationFrom;
    }

    public void setCreationFrom(Date creationFrom) {
        this.creationFrom = creationFrom;
    }

    @Basic
    @Column(name = "creation_to")
    public Date getCreationTo() {
        return creationTo;
    }

    public void setCreationTo(Date creationTo) {
        this.creationTo = creationTo;
    }

    @Basic
    @Column(name = "request_end_time_from")
    public Date getRequestEndTimeFrom() {
        return requestEndTimeFrom;
    }

    public void setRequestEndTimeFrom(Date requestEndTimeFrom) {
        this.requestEndTimeFrom = requestEndTimeFrom;
    }

    @Basic
    @Column(name = "request_end_time_to")
    public Date getRequestEndTimeTo() {
        return requestEndTimeTo;
    }

    public void setRequestEndTimeTo(Date requestEndTimeTo) {
        this.requestEndTimeTo = requestEndTimeTo;
    }

    @Basic
    @Column(name = "international", columnDefinition = "TINYINT", length = 1)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public boolean getInternational() {
        return international;
    }

    public void setInternational(boolean international) {
        this.international = international;
    }

    @Basic
    @Column(name = "status")
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }


    @Basic
    @Column(name = "company")
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "search_text")
    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @Basic
    @Column(name = "zakup_number")
    public String getZakupNumber() {
        return zakupNumber;
    }

    public void setZakupNumber(String zakupNumber) {
        this.zakupNumber = zakupNumber;
    }

    @Basic
    @Column(name = "okrb_subtype")
    public String getOkrbSubtype() {
        return okrbSubtype;
    }

    public void setOkrbSubtype(String okrbSubtype) {
        this.okrbSubtype = okrbSubtype;
    }

    @Basic
    @Column(name = "sending_frequency")
    public String getSendingFrequency() {
        return sendingFrequency;
    }

    public void setSendingFrequency(String sendingFrequency) {
        this.sendingFrequency = sendingFrequency;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Criteria that = (Criteria) o;
//
//        if (idCriteria != that.idCriteria) return false;
//        if (idUser != that.idUser) return false;
//        if (idNotificationType != that.idNotificationType) return false;
//        if (sendingTime != null ? !sendingTime.equals(that.sendingTime) : that.sendingTime != null) return false;
//        if (idEstablishment != null ? !idEstablishment.equals(that.idEstablishment) : that.idEstablishment != null)
//            return false;
//        if (idCompany != null ? !idCompany.equals(that.idCompany) : that.idCompany != null) return false;
//        if (idZakupNumber != null ? !idZakupNumber.equals(that.idZakupNumber) : that.idZakupNumber != null)
//            return false;
//        if (idPeriod != null ? !idPeriod.equals(that.idPeriod) : that.idPeriod != null) return false;
//        if (idSearchText != null ? !idSearchText.equals(that.idSearchText) : that.idSearchText != null) return false;
//        if (idOkrbSubtype != null ? !idOkrbSubtype.equals(that.idOkrbSubtype) : that.idOkrbSubtype != null)
//            return false;
//        if (creationFrom != null ? !creationFrom.equals(that.creationFrom) : that.creationFrom != null) return false;
//        if (creationTo != null ? !creationTo.equals(that.creationTo) : that.creationTo != null) return false;
//        if (requestEndTimeFrom != null ? !requestEndTimeFrom.equals(that.requestEndTimeFrom) : that.requestEndTimeFrom != null)
//            return false;
//        if (requestEndTimeTo != null ? !requestEndTimeTo.equals(that.requestEndTimeTo) : that.requestEndTimeTo != null)
//            return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = idCriteria;
//        result = 31 * result + idUser;
//        result = 31 * result + idNotificationType;
//        result = 31 * result + (sendingTime != null ? sendingTime.hashCode() : 0);
//        result = 31 * result + (idEstablishment != null ? idEstablishment.hashCode() : 0);
//        result = 31 * result + (idCompany != null ? idCompany.hashCode() : 0);
//        result = 31 * result + (idZakupNumber != null ? idZakupNumber.hashCode() : 0);
//        result = 31 * result + (idPeriod != null ? idPeriod.hashCode() : 0);
//        result = 31 * result + (idSearchText != null ? idSearchText.hashCode() : 0);
//        result = 31 * result + (idOkrbSubtype != null ? idOkrbSubtype.hashCode() : 0);
//        result = 31 * result + (creationFrom != null ? creationFrom.hashCode() : 0);
//        result = 31 * result + (creationTo != null ? creationTo.hashCode() : 0);
//        result = 31 * result + (requestEndTimeFrom != null ? requestEndTimeFrom.hashCode() : 0);
//        result = 31 * result + (requestEndTimeTo != null ? requestEndTimeTo.hashCode() : 0);
//        return result;
//    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_establishment", referencedColumnName = "id_establishment")
    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_period", referencedColumnName = "id_period")
    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_notification_type", referencedColumnName = "id_notification_type", nullable = false)
    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "criteria_country", schema = "", catalog = "goszakupk_monitoring_system_db", joinColumns = {
            @JoinColumn(name = "id_criteria", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_country",
                    nullable = false, updatable = false) })
    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "criteria_industry", schema = "", catalog = "goszakupk_monitoring_system_db", joinColumns = {
            @JoinColumn(name = "id_criteria", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_industry",
                    nullable = false, updatable = false) })
    public Set<Industry> getIndustries() {
        return industries;
    }

    public void setIndustries(Set<Industry> industries) {
        this.industries = industries;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "criteria_region", schema = "", catalog = "goszakupk_monitoring_system_db", joinColumns = {
            @JoinColumn(name = "id_criteria", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_region",
                    nullable = false, updatable = false) })
    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "criteria_zakup_type_1", schema = "", catalog = "goszakupk_monitoring_system_db", joinColumns = {
            @JoinColumn(name = "id_criteria", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_zakup_type_1",
                    nullable = false, updatable = false) })
    public Set<ZakupType1> getZakupType1s() {
        return zakupType1s;
    }

    public void setZakupType1s(Set<ZakupType1> zakupType1s) {
        this.zakupType1s = zakupType1s;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "criteria_zakup_type_2", schema = "", catalog = "goszakupk_monitoring_system_db", joinColumns = {
            @JoinColumn(name = "id_criteria", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "id_zakup_type_2",
                    nullable = false, updatable = false) })
    public Set<ZakupType2> getZakupType2s() {
        return zakupType2s;
    }

    public void setZakupType2s(Set<ZakupType2> zakupType2s) {
        this.zakupType2s = zakupType2s;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "criteria")
    public Set<ReceivedInformation> getReceivedInformations() {
        return receivedInformations;
    }

    public void setReceivedInformations(Set<ReceivedInformation> receivedInformations) {
        this.receivedInformations = receivedInformations;
    }


}
