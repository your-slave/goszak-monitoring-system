package dao;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import service.ApplicationContextProvider;
import service.representation.CriteriaRepresentation;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CriteriaDAOImpl implements CriteriaDAO
{
	private SessionFactory sessionFactory;
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}


	public boolean deleteCreteria(String userEmail, int idCriteria)
	{
		Session session = null;
		Transaction transaction = null;
		org.hibernate.Criteria cr = null;
		UserDAO useDAO = null;
		User user = null;

		boolean success = true;

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			useDAO = (UserDAO)context.getBean("userDAO");
			user = useDAO.getUserByEmail(userEmail);

			transaction = session.beginTransaction();

			cr = session.createCriteria(Criteria.class);
			cr.add(Restrictions.eq("idCriteria", idCriteria));
			cr.add(Restrictions.eq("user", user));

			if(!cr.list().isEmpty())
			{
				Criteria criteriaForDelete = (Criteria) cr.list().get(0);
				criteriaForDelete.setStatus(Status.DELETED.value);
				session.update(criteriaForDelete);
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

			success = false;

		}
		finally
		{
			if(session != null && session.isOpen())
				session.close();
		}

		return success;
	}

	public List<CriteriaRepresentation> getCriteriasRepresintationsForStart()
	{
		Session session = null;
		Transaction transaction = null;
		org.hibernate.Criteria cr = null;

		List<Criteria> results = null;
		ArrayList<CriteriaRepresentation> criteriaRepresentationsResults = new ArrayList<CriteriaRepresentation>();

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(Criteria.class);

			CriteriaRepresentation temp;
			ArrayList<Integer> tempIds = new ArrayList<Integer>();


			cr.add(Restrictions.eq("status", Status.ENABLED.value));

			results = cr.list();

			for(Criteria result : results)
			{
				temp = (CriteriaRepresentation) context.getBean("criteriaRepresntation");

				temp.setInternational(result.getInternational());
				temp.setIdNotificationType(result.getNotificationType().getIdNotificationType());
				temp.setSendingFrequency(result.getSendingFrequency());
				temp.setIdCriteria(result.getIdCriteria());

				if(result.getCountries()!=null)
				{
					for(Country country : result.getCountries())
						tempIds.add(country.getIdCountry());
					temp.setIdCountries(tempIds);
					tempIds.clear();
				}

				if(result.getIndustries()!=null)
				{
					for(Industry industry : result.getIndustries())
						tempIds.add(industry.getIdIndustry());
					temp.setIdIndustries(tempIds);
					tempIds.clear();
				}

				if(result.getRegions()!=null)
				{
					for(Region region : result.getRegions())
						tempIds.add(region.getIdRegion());
					temp.setIdRegiones(tempIds);
					tempIds.clear();
				}


				if(result.getZakupType1s()!=null)
				{
					for(ZakupType1 zakupType1 : result.getZakupType1s())
						tempIds.add(zakupType1.getIdZakupType1());
					temp.setIdZakupType1s(tempIds);
					tempIds.clear();
				}

				if(result.getZakupType2s()!=null)
				{
					for(ZakupType2 zakupType2 : result.getZakupType2s())
						tempIds.add(zakupType2.getIdZakupType2());
					temp.setIdZakupType2s(tempIds);
					tempIds.clear();
				}

				if(result.getPeriod()!=null)
					temp.setIdPeriod(result.getPeriod().getIdPeriod());

				if(result.getEstablishment()!=null)
					temp.setIdEstablishment(result.getEstablishment().getIdEstablishment());

				if(result.getSearchText()!=null)
					temp.setSearchText(result.getSearchText());

				if(result.getZakupNumber()!=null)
					temp.setZakupNumber(result.getZakupNumber());

				if(result.getOkrbSubtype()!=null)
					temp.setOkrbSubptype(result.getOkrbSubtype());

				if(result.getCompany()!=null)
					temp.setCompany(result.getCompany());

				if(result.getCreationFrom()!=null)
					temp.setCreationTo(result.getCreationFrom().toString());

				if(result.getCreationTo()!=null)
					temp.setCreationTo(result.getCreationTo().toString());

				if(result.getRequestEndTimeFrom()!=null)
					temp.setRequestEndTimeFrom(result.getRequestEndTimeFrom().toString());

				if(result.getRequestEndTimeTo()!=null)
					temp.setRequestEndTimeTo(result.getRequestEndTimeTo().toString());


				criteriaRepresentationsResults.add(temp);
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

		return criteriaRepresentationsResults;
	}

	public Criteria getCriteriaById(int idCriteria)
	{
		Session session = null;
		Transaction transaction = null;

		Criteria criteria = null;

		try
		{
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			org.hibernate.Criteria cr = session.createCriteria(Criteria.class);
			cr.add(Restrictions.eq("idCriteria", idCriteria));
			if(!cr.list().isEmpty())
			{
				criteria = (Criteria) cr.list().get(0);
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

		return  criteria;
	}


	public Integer addCritera(String userEmail, CriteriaRepresentation criteriaRepresentation)
	{
		Session session = null;
		Transaction transaction = null;
		Integer result = null;

		Criteria newCriteria=null;

		UserDAO useDAO = null;
		User user = null;

		NotificationTypeDAO notificationTypeDAO = null;
		NotificationType notificationType = null;

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			newCriteria = (Criteria)context.getBean("criteria");

			useDAO = (UserDAO)context.getBean("userDAO");
			user = useDAO.getUserByEmail(userEmail);

			newCriteria.setUser(user);

			newCriteria.setStatus(Status.ENABLED.value);

			notificationTypeDAO = (NotificationTypeDAO)context.getBean("notificationTypeDAO");
			notificationType = notificationTypeDAO.getNotificationType(criteriaRepresentation.getIdNotificationType());

			newCriteria.setNotificationType(notificationType);

			transaction = session.beginTransaction();

			if(!criteriaRepresentation.getInternational())
			{
				newCriteria.setInternational(false);

				if(criteriaRepresentation.getIdZakupType1s()!=null)
				{
					ZakupType1DAO zakupType1DAO = (ZakupType1DAO)context.getBean("zakupType1DAO");
					Set<ZakupType1> zakupType1Set = new HashSet<ZakupType1>();
					for(int id : criteriaRepresentation.getIdZakupType1s())
					{
						zakupType1Set.add(zakupType1DAO.getZakupType1(id));
					}

					newCriteria.setZakupType1s(zakupType1Set);
				}

				if(criteriaRepresentation.getIdZakupType1s()!=null)
				{
					ZakupType2DAO zakupType2DAO = (ZakupType2DAO)context.getBean("zakupType2DAO");
					Set<ZakupType2> zakupType2Set = new HashSet<ZakupType2>();
					for(int id : criteriaRepresentation.getIdZakupType2s())
					{
						zakupType2Set.add(zakupType2DAO.getZakupType2(id));
					}

					newCriteria.setZakupType2s(zakupType2Set);
				}

				if(criteriaRepresentation.getOkrbSubptype()!=null)
				{
					newCriteria.setOkrbSubtype(criteriaRepresentation.getOkrbSubptype());
				}

				if(criteriaRepresentation.getIdEstablishment()!=null)
				{
					EstablishmentDAO establishmentDAO = (EstablishmentDAO)context.getBean("establishmentDAO");
					newCriteria.setEstablishment(establishmentDAO.getEstablishment(criteriaRepresentation.getIdEstablishment()));
				}

				if(criteriaRepresentation.getIdRegiones()!=null)
				{
					RegionDAO regionDAO = (RegionDAO)context.getBean("regionDAO");
					Set<Region> regionSet = new HashSet<Region>();
					for(int id : criteriaRepresentation.getIdRegiones())
					{
						regionSet.add(regionDAO.getRegion(id));
					}

					newCriteria.setRegions(regionSet);
				}

			}

			else
			{
				newCriteria.setInternational(true);
				if(criteriaRepresentation.getIdCountries()!=null)
				{
					CountryDAO countryDAO = (CountryDAO)context.getBean("countryDAO");
					Set<Country> countrySet = new HashSet<Country>();
					for(int id : criteriaRepresentation.getIdCountries())
					{
						countrySet.add(countryDAO.getCountry(id));
					}

					newCriteria.setCountries(countrySet);
				}
			}

			newCriteria.setSendingFrequency(criteriaRepresentation.getSendingFrequency());


			if(criteriaRepresentation.getSearchText()!=null)
			{
				newCriteria.setSearchText(criteriaRepresentation.getSearchText());
			}

			if(criteriaRepresentation.getCompany()!=null)
			{
				newCriteria.setCompany(criteriaRepresentation.getCompany());
			}

			if(criteriaRepresentation.getIdIndustries()!=null)
			{
				IndustryDAO industryDAO = (IndustryDAO)context.getBean("industryDAO");
				Set<Industry> industrySet = new HashSet<Industry>();
				for(int id : criteriaRepresentation.getIdIndustries())
				{
					industrySet.add(industryDAO.getIndustry(id));
				}

				newCriteria.setIndustries(industrySet);
			}

			if(criteriaRepresentation.getIdPeriod()!=null)
			{
				PeriodDAO periodDAO = (PeriodDAO)context.getBean("periodDAO");
				newCriteria.setPeriod(periodDAO.getPeriod(criteriaRepresentation.getIdPeriod()));
			}
			else
			{
				if(criteriaRepresentation.getCreationFrom()!=null)
				{
					Date creationFromDate = new java.sql.Date(format.parse(criteriaRepresentation.getCreationFrom()).getTime());
					newCriteria.setCreationFrom(creationFromDate);
				}

				if(criteriaRepresentation.getCreationTo()!=null)
				{
					Date creationToDate = new java.sql.Date(format.parse(criteriaRepresentation.getCreationTo()).getTime());
					newCriteria.setCreationTo(creationToDate);
				}

				if(criteriaRepresentation.getRequestEndTimeFrom()!=null)
				{
					Date requestEndFrom = new java.sql.Date(format.parse(criteriaRepresentation.getRequestEndTimeFrom()).getTime());
					newCriteria.setRequestEndTimeFrom(requestEndFrom);
				}

				if(criteriaRepresentation.getRequestEndTimeTo()!=null)
				{
					Date requestEndTo = new java.sql.Date(format.parse(criteriaRepresentation.getRequestEndTimeTo()).getTime());
					newCriteria.setRequestEndTimeFrom(requestEndTo);
				}

			}

			if(criteriaRepresentation.getActive())
				newCriteria.setStatus(Status.ENABLED.value);
			else
				newCriteria.setStatus(Status.UNENABLED.value);

			session.save(newCriteria);

			if (!transaction.wasCommitted())
				transaction.commit();

			result = newCriteria.getIdCriteria();
		}

		catch (Exception e)
		{
			System.out.println(e.getMessage());
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();

			result = -1;

		}
		finally
		{
			if(session != null && session.isOpen())
				session.close();
		}

		return result;

	}

	public Integer updateCriteria(String userEmail,CriteriaRepresentation criteriaRepresentation)
	{
		Session session = null;
		Transaction transaction = null;
		Integer result = null;

		Criteria updatedCriteria=null;

		NotificationTypeDAO notificationTypeDAO = null;
		NotificationType notificationType = null;
		User user = null;
		UserDAO useDAO = null;

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			useDAO = (UserDAO)context.getBean("userDAO");
			user = useDAO.getUserByEmail(userEmail);

			transaction = session.beginTransaction();

			org.hibernate.Criteria cr = session.createCriteria(Criteria.class);
			cr.add(Restrictions.eq("idCriteria", criteriaRepresentation.getIdCriteria()));
			cr.add(Restrictions.eq("user", user));

			if(!cr.list().isEmpty())
			{
				updatedCriteria = (Criteria) cr.list().get(0);
			}
			else
			{
				return -1;
			}

			notificationTypeDAO = (NotificationTypeDAO)context.getBean("notificationTypeDAO");
			notificationType = notificationTypeDAO.getNotificationType(criteriaRepresentation.getIdNotificationType());

			updatedCriteria.setNotificationType(notificationType);


			if(!criteriaRepresentation.getInternational())
			{
				updatedCriteria.setInternational(false);

				if(criteriaRepresentation.getIdZakupType1s()!=null)
				{
					ZakupType1DAO zakupType1DAO = (ZakupType1DAO)context.getBean("zakupType1DAO");
					Set<ZakupType1> zakupType1Set = new HashSet<ZakupType1>();
					for(int id : criteriaRepresentation.getIdZakupType1s())
					{
						zakupType1Set.add(zakupType1DAO.getZakupType1(id));
					}

					updatedCriteria.setZakupType1s(zakupType1Set);
				}

				if(criteriaRepresentation.getIdZakupType1s()!=null)
				{
					ZakupType2DAO zakupType2DAO = (ZakupType2DAO)context.getBean("zakupType2DAO");
					Set<ZakupType2> zakupType2Set = new HashSet<ZakupType2>();
					for(int id : criteriaRepresentation.getIdZakupType2s())
					{
						zakupType2Set.add(zakupType2DAO.getZakupType2(id));
					}

					updatedCriteria.setZakupType2s(zakupType2Set);
				}

				if(criteriaRepresentation.getOkrbSubptype()!=null)
				{
					updatedCriteria.setOkrbSubtype(criteriaRepresentation.getOkrbSubptype());
				}

				if(criteriaRepresentation.getIdEstablishment()!=null)
				{
					EstablishmentDAO establishmentDAO = (EstablishmentDAO)context.getBean("establishmentDAO");
					updatedCriteria.setEstablishment(establishmentDAO.getEstablishment(criteriaRepresentation.getIdEstablishment()));
				}

				if(criteriaRepresentation.getIdRegiones()!=null)
				{
					RegionDAO regionDAO = (RegionDAO)context.getBean("regionDAO");
					Set<Region> regionSet = new HashSet<Region>();
					for(int id : criteriaRepresentation.getIdRegiones())
					{
						regionSet.add(regionDAO.getRegion(id));
					}

					updatedCriteria.setRegions(regionSet);
				}

			}

			else
			{

				updatedCriteria.setInternational(true);

				if(criteriaRepresentation.getIdCountries()!=null)
				{
					CountryDAO countryDAO = (CountryDAO)context.getBean("countryDAO");
					Set<Country> countrySet = new HashSet<Country>();
					for(int id : criteriaRepresentation.getIdCountries())
					{
						countrySet.add(countryDAO.getCountry(id));
					}

					updatedCriteria.setCountries(countrySet);
				}
			}

			updatedCriteria.setSendingFrequency(criteriaRepresentation.getSendingFrequency());

			updatedCriteria.setSearchText(criteriaRepresentation.getSearchText());

			updatedCriteria.setCompany(criteriaRepresentation.getCompany());


			if(criteriaRepresentation.getIdIndustries()!=null)
			{
				IndustryDAO industryDAO = (IndustryDAO)context.getBean("industryDAO");
				Set<Industry> industrySet = new HashSet<Industry>();
				for(int id : criteriaRepresentation.getIdIndustries())
				{
					industrySet.add(industryDAO.getIndustry(id));
				}

				updatedCriteria.setIndustries(industrySet);
			}

			if(criteriaRepresentation.getIdPeriod()!=null)
			{
				PeriodDAO periodDAO = (PeriodDAO)context.getBean("periodDAO");
				updatedCriteria.setPeriod(periodDAO.getPeriod(criteriaRepresentation.getIdPeriod()));
			}
			else
			{
				if(criteriaRepresentation.getCreationFrom()!=null)
				{
					Date creationFromDate = new java.sql.Date(format.parse(criteriaRepresentation.getRequestEndTimeFrom()).getTime());
					updatedCriteria.setCreationFrom(creationFromDate);
				}

				if(criteriaRepresentation.getCreationTo()!=null)
				{
					Date creationToDate = new java.sql.Date(format.parse(criteriaRepresentation.getRequestEndTimeTo()).getTime());
					updatedCriteria.setCreationTo(creationToDate);
				}

				if(criteriaRepresentation.getRequestEndTimeFrom()!=null)
				{
					Date requestEndFrom = new java.sql.Date(format.parse(criteriaRepresentation.getRequestEndTimeFrom()).getTime());
					updatedCriteria.setRequestEndTimeFrom(requestEndFrom);
				}

				if(criteriaRepresentation.getRequestEndTimeTo()!=null)
				{
					Date requestEndTo = new java.sql.Date(format.parse(criteriaRepresentation.getRequestEndTimeTo()).getTime());
					updatedCriteria.setRequestEndTimeFrom(requestEndTo);
				}

			}

			if(criteriaRepresentation.getActive())
				updatedCriteria.setStatus(Status.ENABLED.value);
			else
				updatedCriteria.setStatus(Status.UNENABLED.value);


			session.update(updatedCriteria);

			if (!transaction.wasCommitted())
				transaction.commit();

			result = updatedCriteria.getIdCriteria();
		}

		catch (Exception e)
		{
			System.out.println(e.getMessage());
			if (transaction != null)
			{
				transaction.rollback();
			}
			e.printStackTrace();

			result = -1;

		}
		finally
		{
			if(session != null && session.isOpen())
				session.close();
		}

		return result;
	}

	public List<CriteriaRepresentation> getUsersCriteriasRepresintations(String userEmail)
	{
		Session session = null;
		Transaction transaction = null;
		org.hibernate.Criteria cr = null;

		UserDAO useDAO = null;
		User user = null;

		List<Criteria> results = null;
		ArrayList<CriteriaRepresentation> criteriaRepresentationsResults = new ArrayList<CriteriaRepresentation>();

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			useDAO = (UserDAO)context.getBean("userDAO");
			user = useDAO.getUserByEmail(userEmail);

			transaction = session.beginTransaction();

			cr = session.createCriteria(Criteria.class);
			cr.add(Restrictions.eq("user", user));
			cr.add(Restrictions.ne("status", Status.DELETED.value));

			CriteriaRepresentation temp;
			ArrayList<Integer> tempIds = new ArrayList<Integer>();

			results = cr.list();

			for(Criteria result : results)
			{
				temp = (CriteriaRepresentation) context.getBean("criteriaRepresntation");

				temp.setInternational(result.getInternational());
				temp.setIdNotificationType(result.getNotificationType().getIdNotificationType());
				temp.setSendingFrequency(result.getSendingFrequency());
				temp.setIdCriteria(result.getIdCriteria());

				if(result.getCountries()!=null)
				{
					for(Country country : result.getCountries())
						tempIds.add(country.getIdCountry());
					temp.setIdCountries(tempIds);
					tempIds.clear();
				}

				if(result.getIndustries()!=null)
				{
					for(Industry industry : result.getIndustries())
						tempIds.add(industry.getIdIndustry());
					temp.setIdIndustries(tempIds);
					tempIds.clear();
				}

				if(result.getRegions()!=null)
				{
					for(Region region : result.getRegions())
						tempIds.add(region.getIdRegion());
					temp.setIdRegiones(tempIds);
					tempIds.clear();
				}


				if(result.getZakupType1s()!=null)
				{
					for(ZakupType1 zakupType1 : result.getZakupType1s())
						tempIds.add(zakupType1.getIdZakupType1());
					temp.setIdZakupType1s(tempIds);
					tempIds.clear();
				}

				if(result.getZakupType2s()!=null)
				{
					for(ZakupType2 zakupType2 : result.getZakupType2s())
						tempIds.add(zakupType2.getIdZakupType2());
					temp.setIdZakupType2s(tempIds);
					tempIds.clear();
				}

				if(result.getPeriod()!=null)
					temp.setIdPeriod(result.getPeriod().getIdPeriod());

				if(result.getEstablishment()!=null)
					temp.setIdEstablishment(result.getEstablishment().getIdEstablishment());

				if(result.getSearchText()!=null)
					temp.setSearchText(result.getSearchText());

				if(result.getZakupNumber()!=null)
					temp.setZakupNumber(result.getZakupNumber());

				if(result.getOkrbSubtype()!=null)
					temp.setOkrbSubptype(result.getOkrbSubtype());

				if(result.getCompany()!=null)
					temp.setCompany(result.getCompany());

				if(result.getCreationFrom()!=null)
					temp.setCreationTo(result.getCreationFrom().toString());

				if(result.getCreationTo()!=null)
					temp.setCreationTo(result.getCreationTo().toString());

				if(result.getRequestEndTimeFrom()!=null)
					temp.setRequestEndTimeFrom(result.getRequestEndTimeFrom().toString());

				if(result.getRequestEndTimeTo()!=null)
					temp.setRequestEndTimeTo(result.getRequestEndTimeTo().toString());

				switch (result.getStatus())
				{
					case(0):
						temp.setActive(true);
						break;
					case(1):
						temp.setActive(false);
						break;
				}

				criteriaRepresentationsResults.add(temp);
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

		return criteriaRepresentationsResults;
	}


	public String getDestination(Integer idCriteria)
	{
		Session session = null;
		Transaction transaction = null;

		Criteria criteria = null;

		String destination = null;

		try
		{
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			org.hibernate.Criteria cr = session.createCriteria(Criteria.class);
			cr.add(Restrictions.eq("idCriteria", idCriteria));
			if(!cr.list().isEmpty())
			{
				criteria = (Criteria) cr.list().get(0);
				switch (criteria.getNotificationType().getIdNotificationType())
				{
					case(1):
						destination = criteria.getUser().getEmail();
						break;
					case(2):
						destination =  criteria.getUser().getPhoneNumber();
						break;
				}
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

		return  destination;
	}

	public boolean suspendCriteria(String userEmail, int idCriteria)
	{
		Session session = null;
		Transaction transaction = null;
		org.hibernate.Criteria cr = null;
		UserDAO useDAO = null;
		User user = null;

		boolean success = true;

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			useDAO = (UserDAO)context.getBean("userDAO");
			user = useDAO.getUserByEmail(userEmail);

			transaction = session.beginTransaction();

			cr = session.createCriteria(Criteria.class);
			cr.add(Restrictions.eq("idCriteria", idCriteria));
			cr.add(Restrictions.eq("user", user));

			if(!cr.list().isEmpty())
			{
				Criteria criteriaForDelete = (Criteria) cr.list().get(0);
				criteriaForDelete.setStatus(Status.UNENABLED.value);
				session.update(criteriaForDelete);
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

			success = false;

		}
		finally
		{
			if(session != null && session.isOpen())
				session.close();
		}

		return success;
	}

	public boolean activateCriteria(String userEmail, int idCriteria)
	{
		Session session = null;
		Transaction transaction = null;
		org.hibernate.Criteria cr = null;
		UserDAO useDAO = null;
		User user = null;

		boolean success = true;

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			useDAO = (UserDAO)context.getBean("userDAO");
			user = useDAO.getUserByEmail(userEmail);

			transaction = session.beginTransaction();

			cr = session.createCriteria(Criteria.class);
			cr.add(Restrictions.eq("idCriteria", idCriteria));
			cr.add(Restrictions.eq("user", user));

			if(!cr.list().isEmpty())
			{
				Criteria criteriaForDelete = (Criteria) cr.list().get(0);
				criteriaForDelete.setStatus(Status.ENABLED.value);
				session.update(criteriaForDelete);
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

			success = false;

		}
		finally
		{
			if(session != null && session.isOpen())
				session.close();
		}

		return success;
	}

	public CriteriaRepresentation getCriteriaRepresentation(String userEmail, int idCriteria)
	{
		Session session = null;
		Transaction transaction = null;
		org.hibernate.Criteria cr = null;
		UserDAO useDAO = null;
		User user = null;
		CriteriaRepresentation result = null;

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			useDAO = (UserDAO) context.getBean("userDAO");
			user = useDAO.getUserByEmail(userEmail);

			Criteria criteria = null;
			result = (CriteriaRepresentation) context.getBean("criteriaRepresntation");

			transaction = session.beginTransaction();

			ArrayList<Integer> tempIds = new ArrayList<Integer>();

			cr = session.createCriteria(Criteria.class);
			cr.add(Restrictions.eq("idCriteria", idCriteria));
			cr.add(Restrictions.eq("user", user));

			if (!cr.list().isEmpty())
			{
				criteria = (Criteria) cr.list().get(0);

				result = (CriteriaRepresentation) context.getBean("criteriaRepresntation");

				result.setInternational(criteria.getInternational());
				result.setIdNotificationType(criteria.getNotificationType().getIdNotificationType());
				result.setSendingFrequency(criteria.getSendingFrequency());
				result.setIdCriteria(criteria.getIdCriteria());

				if(criteria.getCountries()!=null)
				{
					for(Country country : criteria.getCountries())
						tempIds.add(country.getIdCountry());
					result.setIdCountries(tempIds);
					tempIds.clear();
				}

				if(criteria.getIndustries()!=null)
				{
					for(Industry industry : criteria.getIndustries())
						tempIds.add(industry.getIdIndustry());
					result.setIdIndustries(tempIds);
					tempIds.clear();
				}

				if(criteria.getRegions()!=null)
				{
					for(Region region : criteria.getRegions())
						tempIds.add(region.getIdRegion());
					result.setIdRegiones(tempIds);
					tempIds.clear();
				}


				if(criteria.getZakupType1s()!=null)
				{
					for(ZakupType1 zakupType1 : criteria.getZakupType1s())
						tempIds.add(zakupType1.getIdZakupType1());
					result.setIdZakupType1s(tempIds);
					tempIds.clear();
				}

				if(criteria.getZakupType2s()!=null)
				{
					for(ZakupType2 zakupType2 : criteria.getZakupType2s())
						tempIds.add(zakupType2.getIdZakupType2());
					result.setIdZakupType2s(tempIds);
					tempIds.clear();
				}

				if(criteria.getPeriod()!=null)
					result.setIdPeriod(criteria.getPeriod().getIdPeriod());

				if(criteria.getEstablishment()!=null)
					result.setIdEstablishment(criteria.getEstablishment().getIdEstablishment());

				if(criteria.getSearchText()!=null)
					result.setSearchText(criteria.getSearchText());

				if(criteria.getZakupNumber()!=null)
					result.setZakupNumber(criteria.getZakupNumber());

				if(criteria.getOkrbSubtype()!=null)
					result.setOkrbSubptype(criteria.getOkrbSubtype());

				if(criteria.getCompany()!=null)
					result.setCompany(criteria.getCompany());

				if(criteria.getCreationFrom()!=null)
					result.setCreationTo(criteria.getCreationFrom().toString());

				if(criteria.getCreationTo()!=null)
					result.setCreationTo(criteria.getCreationTo().toString());

				if(criteria.getRequestEndTimeFrom()!=null)
					result.setRequestEndTimeFrom(criteria.getRequestEndTimeFrom().toString());

				if(criteria.getRequestEndTimeTo()!=null)
					result.setRequestEndTimeTo(criteria.getRequestEndTimeTo().toString());
			}
			else
			{
				return null;
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

		return result;
	}


	private enum Status
	{
		ENABLED((byte)0), UNENABLED((byte)1), DELETED((byte)2);

		private final byte value;
		private Status(byte value) {
			this.value = value;
		}
	}



}

