package service.logic;

import dao.CountryDAO;
import service.ApplicationContextProvider;
import service.representation.CriteriaRepresentation;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class LinkGeneratorImpl implements LinkGenerator
{
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	private String basicLink = "http://www.icetrade.by/search/";
	private String tail = "&sort=num%3Adesc&onPage=100";

	public String generate(CriteriaRepresentation criteriaRepresentation)
	{
		String result = basicLink;

		if (criteriaRepresentation.getInternational())
		{
			result+="foreign?";

			if(criteriaRepresentation.getIdCountries()!=null)
			{
				CountryDAO countryDAO = (CountryDAO) ApplicationContextProvider.getApplicationContext().getBean("countryDAO");

				for(Integer idCountry : criteriaRepresentation.getIdCountries())
				{
					result+="countries[]="+countryDAO.getCountryAcronymById(idCountry);
				}
			}
		}
		else
		{
			result+="auctions?";
			if(criteriaRepresentation.getIdZakupType1s()==null)
				result+="zakup_type[1]=1&zakup_type[2]=1";
			else
				{
					for (Integer idZakupType1 : criteriaRepresentation.getIdZakupType1s())
					{
						result+="&zzakup_type[" + idZakupType1 + "]=1";
					}
				}

			if(criteriaRepresentation.getOkrbSubptype()!=null)
				result+="&okrb=" + criteriaRepresentation.getOkrbSubptype();
			if(criteriaRepresentation.getIdEstablishment()!=null)
				result+="&establishment=" + criteriaRepresentation.getIdEstablishment();

			if(criteriaRepresentation.getIdZakupType2s()!=null)
			{
				for(Integer idZakupTyp2 : criteriaRepresentation.getIdZakupType2s())
				{
					switch (idZakupTyp2)
					{
						case(1):
							result+="&t[Trade]=1";
							break;
						case(2):
							result+="&t[eTrade]=1";
							break;
						case(3):
							result+="&t[Request]=1";
							break;
						case(4):
							result+="&t[singleSource]=1";
							break;
						case(5):
							result+="&t[Auction]=1";
							break;
						case(6):
							result+="&t[Other]=1";
							break;
						case(7):
							result+="&t[contractingTrades]=1";
							break;
						case(8):
							result+="&t[negotiations]=1";
							break;
					}
				}
			}

			if(criteriaRepresentation.getIdRegiones()!=null)
			{

				for (Integer idRegion : criteriaRepresentation.getIdRegiones())
				{
					result += "&r[" + idRegion + "]=" + idRegion;
				}
			}

		}

		if(criteriaRepresentation.getSearchText()!=null)
			result+="&search_text=" + criteriaRepresentation.getSearchText();
		if(criteriaRepresentation.getZakupNumber()!=null)
			result+="%auc_number=" + criteriaRepresentation.getZakupNumber();
		if(criteriaRepresentation.getCompany()!=null)
			result+="&company_title=" + criteriaRepresentation.getCompany();

		if(criteriaRepresentation.getIdIndustries()!=null)
		{

			result += "&industries=";

			for (Integer idIndustry : criteriaRepresentation.getIdIndustries())
			{
				result += idIndustry + ".";
			}

			result = result.substring(0, result.length() - 2);
		}

		if(criteriaRepresentation.getIdPeriod()!=null)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());

			switch (criteriaRepresentation.getIdPeriod())
			{
				case (1):
					calendar.add(Calendar.DATE, -1);
					break;
				case (2):
					calendar.add(Calendar.DATE, -3);
					break;
				case (3):
					calendar.add(Calendar.DATE, -7);
					break;
				case (4):
					calendar.add(Calendar.DATE, -14);
					break;
				case (5):
					calendar.add(Calendar.MONTH, -1);
					break;
			}

			result+="&created_from=" + dateFormat.format(calendar.getTime());

			calendar.setTime(new Date());

			result+="&created_to=" + dateFormat.format(calendar.getTime());
		}
		else
		{
			if(criteriaRepresentation.getCreationFrom()!=null)
				result+="&created_from=" + criteriaRepresentation.getCreationFrom();
			if(criteriaRepresentation.getCreationTo()!=null)
				result+="&created_to=" + criteriaRepresentation.getCreationTo();
		}

		if(criteriaRepresentation.getRequestEndTimeFrom()!=null)
			result+="&request_end_from=" + criteriaRepresentation.getRequestEndTimeFrom();
		if(criteriaRepresentation.getRequestEndTimeTo()!=null)
			result+="&request_end_to=" + criteriaRepresentation.getRequestEndTimeTo();

		result += tail;

		return result;
	}
}
