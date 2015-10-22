package service.representation;

public class ReceivedInformationRepresntation
{
	private Integer idCrriteria;
	private String briefDescription;
	private String companyRegion;
	private String country;
	private String cost;
	private String requestEndTme;
	private String number;
	private String link;


	private Integer id;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getCompanyRegion()
	{
		return companyRegion;
	}

	public void setCompanyRegion(String companyRegion)
	{
		this.companyRegion = companyRegion;
	}

	public String getRequestEndTme()
	{
		return requestEndTme;
	}

	public void setRequestEndTme(String requestEndTme)
	{
		this.requestEndTme = requestEndTme;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public Integer getIdCrriteria()
	{
		return idCrriteria;
	}

	public void setIdCrriteria(Integer idCrriteria)
	{
		this.idCrriteria = idCrriteria;
	}

	public String getBriefDescription()
	{
		return briefDescription;
	}

	public void setBriefDescription(String briefDescription)
	{
		this.briefDescription = briefDescription;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getCost()
	{
		return cost;
	}

	public void setCost(String cost)
	{
		this.cost = cost;
	}

}
