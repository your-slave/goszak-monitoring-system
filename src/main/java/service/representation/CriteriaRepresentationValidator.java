package service.representation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CriteriaRepresentationValidator
{
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

	public static boolean Validate(CriteriaRepresentation criteriaRepresentation, boolean forUpdate)
	{

		if(criteriaRepresentation.getCompany()!=null)
			if(criteriaRepresentation.getCompany().length()>255)
				return false;
		if(criteriaRepresentation.getZakupNumber()!=null)
			if(criteriaRepresentation.getZakupNumber().length()>255)
				return false;
		if(criteriaRepresentation.getOkrbSubptype()!=null)
			if(criteriaRepresentation.getOkrbSubptype().length()>255)
				return false;

		if(criteriaRepresentation.getSearchText()!=null)
			if (criteriaRepresentation.getSearchText().length()>255)
				return false;

		if(criteriaRepresentation.getInternational()==null)
		{
			return  false;
		}

		if(criteriaRepresentation.getIdNotificationType()==null)
		{
			return false;
		}

		if(criteriaRepresentation.getSendingFrequency()==null)
		{
			return false;
		}

		if(forUpdate && criteriaRepresentation.getIdCriteria()==null)
		{
			return  false;
		}

		if(!org.quartz.CronExpression.isValidExpression(criteriaRepresentation.getSendingFrequency()))
		{
			return false;
		}

		if(criteriaRepresentation.getCreationFrom()!=null)
		{
			try
			{
				simpleDateFormat.parse(criteriaRepresentation.getCreationFrom());
			}
			catch (ParseException e) {

				e.printStackTrace();
				return false;
			}
		}


		if(criteriaRepresentation.getCreationTo()!=null)
		{
			try
			{
				simpleDateFormat.parse(criteriaRepresentation.getCreationTo());
			}
			catch (ParseException e) {

				e.printStackTrace();
				return false;
			}
		}


		if(criteriaRepresentation.getRequestEndTimeFrom()!=null)
		{
			try
			{
				simpleDateFormat.parse(criteriaRepresentation.getRequestEndTimeFrom());
			}
			catch (ParseException e) {

				e.printStackTrace();
				return false;
			}
		}

		if(criteriaRepresentation.getRequestEndTimeTo()!=null)
		{
			try
			{
				simpleDateFormat.parse(criteriaRepresentation.getRequestEndTimeTo());
			}
			catch (ParseException e) {

				e.printStackTrace();
				return false;
			}
		}


		if(criteriaRepresentation.getInternational())
		{
			if(criteriaRepresentation.getIdZakupType1s()!=null)
			{
				return  false;
			}

			if(criteriaRepresentation.getIdZakupType2s()!=null)
			{
				return  false;
			}

			if(criteriaRepresentation.getIdEstablishment()!=null)
			{
				return  false;
			}

			if(criteriaRepresentation.getOkrbSubptype()!=null)
			{
				return  false;
			}

			if(criteriaRepresentation.getIdRegiones()!=null)
			{
				return  false;
			}
		}

		return true;
	}
}
