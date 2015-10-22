package dao;

import entity.Country;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import service.ApplicationContextProvider;
import service.representation.CountryDictionaryRepresentation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class CountryDAOImpl implements CountryDAO
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

	public ArrayList<CountryDictionaryRepresentation> getCountriesDictionary()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		List<Country> countryResults = null;
		ArrayList<CountryDictionaryRepresentation> countryDictionaryResults = new ArrayList<CountryDictionaryRepresentation>();


		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(Country.class);

			countryResults = cr.list();

			CountryDictionaryRepresentation temp;

			for(Country result : countryResults)
			{
				temp = (CountryDictionaryRepresentation) context.getBean("countryDictionaryRepresentation");
				temp.setId(result.getIdCountry());
				temp.setAkronym(result.getAcronym());
				temp.setName(result.getName());
				countryDictionaryResults.add(temp);
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

		return countryDictionaryResults;
	}

	public Country getCountry(int idCountry)
	{
		Session session = null;
		Transaction transaction = null;
		Country country = null;

		try
		{
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Criteria cr = session.createCriteria(Country.class);
			cr.add(Restrictions.eq("idCountry", idCountry));
			if(!cr.list().isEmpty())
			{
				country = (Country) cr.list().get(0);
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

		return  country;
	}

	public Country getCountryByName(String countryName)
	{
		Session session = null;
		Transaction transaction = null;
		Integer countryId = null;
		Country country = null;

		try
		{
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Criteria cr = session.createCriteria(Country.class);
			cr.add(Restrictions.eq("name", countryName));
			if(!cr.list().isEmpty())
			{
				country = (Country) cr.list().get(0);
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

		return  country;
	}

	public String getCountryAcronymById(Integer idCountry)
	{
		Session session = null;
		Transaction transaction = null;
		String acronym = null;
		Country country = null;

		try
		{
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Criteria cr = session.createCriteria(Country.class);
			cr.add(Restrictions.eq("idCountry", idCountry));
			if(!cr.list().isEmpty())
			{
				country = (Country) cr.list().get(0);
				acronym = country.getAcronym();
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

		return  acronym;
	}

	public HashSet<Integer> getCountryIds()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		HashSet<Integer> results = new HashSet<Integer>();
		List<Country> countryResults;


		try
		{
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(Country.class);

			countryResults = cr.list();

			for(Country countryResult : countryResults)
			{
				results.add(countryResult.getIdCountry());
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

		return results;
	}
}
