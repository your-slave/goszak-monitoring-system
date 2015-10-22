package dao;

import entity.Criteria;
import entity.Notification;
import entity.ReceivedInformation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import service.ApplicationContextProvider;
import service.representation.NotificationRepresentation;
import service.representation.ReceivedInformationRepresntation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReceivedInformationDAOImpl implements ReceivedInformationDAO
{
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public List<ReceivedInformationRepresntation> add(List<ReceivedInformationRepresntation> receivedInformationRepresntations)
	{
		Session session = null;
		Transaction transaction = null;
		org.hibernate.Criteria cr = null;


		ReceivedInformation receivedInformation = null;
		ArrayList<ReceivedInformationRepresntation> represntationResults = new ArrayList<ReceivedInformationRepresntation>();
		Criteria criteria = null;


		try
		{
			if(receivedInformationRepresntations.isEmpty())
				return  null;

			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			CriteriaDAO criteriaDAO = (CriteriaDAO)context.getBean("criteriaDAO");
			CountryDAO countryDAO = (CountryDAO)context.getBean("countryDAO");
			criteria = criteriaDAO.getCriteriaById(receivedInformationRepresntations.get(0).getIdCrriteria());

			cr = session.createCriteria(ReceivedInformation.class);

			cr.add(Restrictions.eq("criteria", criteria));
			cr.addOrder(Order.desc("number"));

			int index = 0;
			boolean found = false;

			if(!cr.list().isEmpty())
			{
				receivedInformation = (ReceivedInformation) cr.list().get(0);

				for(index = 0; index < receivedInformationRepresntations.size(); index++)
				{
					if(receivedInformation.getNumber().equals(receivedInformationRepresntations.get(index).getNumber()))
					{
						found = true;
						break;
					}
				}
			}

			if(found)
				receivedInformationRepresntations.subList(index, receivedInformationRepresntations.size()).clear();
			if(receivedInformationRepresntations.size()==0)
				return null;

			ReceivedInformation tempReceivedInformation;

			for (ReceivedInformationRepresntation receivedInformationRepresntation : receivedInformationRepresntations)
			{
				tempReceivedInformation = (ReceivedInformation) context.getBean("recievedInformation");


				tempReceivedInformation.setBriefDescription(receivedInformationRepresntation.getBriefDescription());
				tempReceivedInformation.setCompanyRegion(receivedInformationRepresntation.getCompanyRegion());
				tempReceivedInformation.setCost(receivedInformationRepresntation.getCost());
				tempReceivedInformation.setCountry(countryDAO.getCountryByName(receivedInformationRepresntation.getCountry()));
				tempReceivedInformation.setCriteria(criteriaDAO.getCriteriaById(receivedInformationRepresntation.getIdCrriteria()));
				tempReceivedInformation.setLink(receivedInformationRepresntation.getLink());
				tempReceivedInformation.setNumber(receivedInformationRepresntation.getNumber());

				SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
				Date parsed = null;
				try
				{
					parsed = format.parse(receivedInformationRepresntation.getRequestEndTme());
				} catch (ParseException e)
				{
					e.printStackTrace();
				}
				java.sql.Date sql = new java.sql.Date(parsed.getTime());

				tempReceivedInformation.setRequestEndTime(sql);
				session.save(tempReceivedInformation);
				receivedInformationRepresntation.setId(tempReceivedInformation.getIdReceivedInformation());
			}

			if (!transaction.wasCommitted())
				transaction.commit();
		}

		catch (Exception e)
		{
			System.out.println(e.getMessage());
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();

		}
		finally
		{
			if(session != null && session.isOpen())
				session.close();
		}

		return receivedInformationRepresntations;
	}

	public List<ReceivedInformationRepresntation>getLastReceivedInformationRepresntations(int idCriteria)
	{
		List<ReceivedInformationRepresntation> receivedInformationRepresntations = new ArrayList<ReceivedInformationRepresntation>();

		ReceivedInformationRepresntation tempReceivedInformationRepresntation;
		List<ReceivedInformation> receivedInformations;

		Session session = null;
		Transaction transaction = null;
		org.hibernate.Criteria cr = null;


		ReceivedInformation receivedInformation = null;
		ArrayList<ReceivedInformationRepresntation> represntationResults = new ArrayList<ReceivedInformationRepresntation>();
		Criteria criteria = null;


		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			CriteriaDAO criteriaDAO = (CriteriaDAO) context.getBean("criteriaDAO");
			CountryDAO countryDAO = (CountryDAO) context.getBean("countryDAO");
			criteria = criteriaDAO.getCriteriaById(idCriteria);

			cr = session.createCriteria(ReceivedInformation.class);

			cr.add(Restrictions.eq("criteria", criteria));
			cr.addOrder(Order.desc("number"));

			if (cr.list().size() < 100)
				receivedInformations = cr.list();
			else
				receivedInformations = cr.list().subList(0, 100);

			for (ReceivedInformation receivedInfo : receivedInformations)
			{
				tempReceivedInformationRepresntation = (ReceivedInformationRepresntation) context.getBean("receivedInformationRepresntation");

				tempReceivedInformationRepresntation.setIdCrriteria(receivedInfo.getCriteria().getIdCriteria());
				tempReceivedInformationRepresntation.setId(receivedInfo.getIdReceivedInformation());
				tempReceivedInformationRepresntation.setNumber(receivedInfo.getNumber());
				tempReceivedInformationRepresntation.setLink(receivedInfo.getLink());
				tempReceivedInformationRepresntation.setRequestEndTme(receivedInfo.getRequestEndTime().toString());
				tempReceivedInformationRepresntation.setBriefDescription(receivedInfo.getBriefDescription());
				tempReceivedInformationRepresntation.setCompanyRegion(receivedInfo.getCompanyRegion());
				tempReceivedInformationRepresntation.setCost(receivedInfo.getCost());
				if(receivedInfo.getCountry()!=null)
					tempReceivedInformationRepresntation.setCountry(receivedInfo.getCountry().getName());

				receivedInformationRepresntations.add(tempReceivedInformationRepresntation);
			}

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();

		}
		finally
		{
			if(session != null && session.isOpen())
				session.close();
		}

		return  receivedInformationRepresntations;
	}

	public void updateReceivedInformationNotification(List<ReceivedInformationRepresntation> receivedInformationRepresntations, Integer idNotification)
	{
		Session session = null;
		Transaction transaction = null;
		ReceivedInformation receivedInformation = null;

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			for(ReceivedInformationRepresntation receivedInformationRepresntation : receivedInformationRepresntations)
			{

				receivedInformation = (ReceivedInformation) session.get(ReceivedInformation.class, receivedInformationRepresntation.getId());
				receivedInformation.setNotification((Notification) session.get(Notification.class, idNotification));
				session.update(receivedInformation);
			}

			if (!transaction.wasCommitted())
				transaction.commit();
		}

		catch (Exception e)
		{
			System.out.println(e.getMessage());
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();

		}
		finally
		{
			if(session != null && session.isOpen())
				session.close();
		}
	}


}
