package service.representation;


import java.util.List;

public class CriteriaRepresentation
{
	private Integer idCriteria;
	private Boolean international;
	private List<Integer> idCountries;
	private Integer idEstablishment;
	private List<Integer> idIndustries;
	private Integer idNotificationType;
	private Integer idPeriod;
	private List<Integer> idRegiones;
	private List<Integer> idZakupType1s;
	private List<Integer> IdZakupType2s;
	private String searchText;
	private String zakupNumber;
	private String okrbSubptype;
	private String sendingFrequency;
	private String company;
	private String creationFrom;
	private String creationTo;
	private String requestEndTimeFrom;
	private String requestEndTimeTo;
	private Boolean active;

	public Boolean getActive()
	{
		return active;
	}

	public void setActive(Boolean active)
	{
		this.active = active;
	}

	public Integer getIdCriteria()
	{
		return idCriteria;
	}

	public void setIdCriteria(Integer idCriteria)
	{
		this.idCriteria = idCriteria;
	}

	public List<Integer> getIdCountries()
	{
		return idCountries;
	}

	public void setIdCountries(List<Integer> idCountries)
	{
		this.idCountries = idCountries;
	}

	public Integer getIdEstablishment()
	{
		return idEstablishment;
	}

	public void setIdEstablishment(Integer idEstablishment)
	{
		this.idEstablishment = idEstablishment;
	}

	public List<Integer> getIdIndustries()
	{
		return idIndustries;
	}

	public void setIdIndustries(List<Integer> idIndustries)
	{
		this.idIndustries = idIndustries;
	}

	public Integer getIdNotificationType()
	{
		return idNotificationType;
	}

	public void setIdNotificationType(Integer idNotificatioType)
	{
		this.idNotificationType = idNotificatioType;
	}

	public List<Integer> getIdRegiones()
	{
		return idRegiones;
	}

	public void setIdRegiones(List<Integer> idRegiones)
	{
		this.idRegiones = idRegiones;
	}

	public List<Integer> getIdZakupType1s()
	{
		return idZakupType1s;
	}

	public void setIdZakupType1s(List<Integer> idZakupType1s)
	{
		this.idZakupType1s = idZakupType1s;
	}

	public List<Integer> getIdZakupType2s()
	{
		return IdZakupType2s;
	}

	public void setIdZakupType2s(List<Integer> idZakupType2s)
	{
		IdZakupType2s = idZakupType2s;
	}

	public String getSearchText()
	{
		return searchText;
	}

	public void setSearchText(String searchText)
	{
		this.searchText = searchText;
	}

	public String getZakupNumber()
	{
		return zakupNumber;
	}

	public void setZakupNumber(String zakupNumber)
	{
		this.zakupNumber = zakupNumber;
	}

	public String getOkrbSubptype()
	{
		return okrbSubptype;
	}

	public void setOkrbSubptype(String okrbSubptype)
	{
		this.okrbSubptype = okrbSubptype;
	}

	public String getSendingFrequency()
	{
		return sendingFrequency;
	}

	public void setSendingFrequency(String sendingFrequency)
	{
		this.sendingFrequency = sendingFrequency;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getCreationFrom()
	{
		return creationFrom;
	}

	public void setCreationFrom(String creationFrom)
	{
		this.creationFrom = creationFrom;
	}

	public String getCreationTo()
	{
		return creationTo;
	}

	public void setCreationTo(String creationTo)
	{
		this.creationTo = creationTo;
	}

	public String getRequestEndTimeFrom()
	{
		return requestEndTimeFrom;
	}

	public void setRequestEndTimeFrom(String requestEndTimeFrom)
	{
		this.requestEndTimeFrom = requestEndTimeFrom;
	}

	public String getRequestEndTimeTo()
	{
		return requestEndTimeTo;
	}

	public void setRequestEndTimeTo(String requestEndTimeTo)
	{
		this.requestEndTimeTo = requestEndTimeTo;
	}

	public Boolean getInternational()
	{
		return international;
	}

	public void setInternational(Boolean international)
	{
		this.international = international;
	}

	public Integer getIdPeriod()
	{
		return idPeriod;
	}

	public void setIdPeriod(Integer idPeriod)
	{
		this.idPeriod = idPeriod;
	}
}
