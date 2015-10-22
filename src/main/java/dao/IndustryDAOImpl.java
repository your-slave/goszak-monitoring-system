package dao;

import entity.Industry;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import service.ApplicationContextProvider;
import service.representation.IndustryDictionaryRepresentation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class IndustryDAOImpl implements IndustryDAO
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


	public List<IndustryDictionaryRepresentation> getIndustryDictionary()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		List<Industry> industryResults = null;
		ArrayList<IndustryDictionaryRepresentation> industryDictionaryResults = new ArrayList<IndustryDictionaryRepresentation>();


		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(Industry.class);

			industryResults = cr.list();

			IndustryDictionaryRepresentation temp;

			for(Industry result : industryResults)
			{
				temp = (IndustryDictionaryRepresentation) context.getBean("industryDictionaryRepresentation");
				temp.setId(result.getIdIndustry());
				temp.setSubType(result.getSubtype());
				temp.setName(result.getName());
				industryDictionaryResults.add(temp);
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

		return industryDictionaryResults;
	}

	public Industry getIndustry(int idIndustry)
	{
		Session session = null;
		Transaction transaction = null;
		Industry industry = null;

		try
		{
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Criteria cr = session.createCriteria(Industry.class);
			cr.add(Restrictions.eq("idIndustry", idIndustry));
			if(!cr.list().isEmpty())
			{
				industry = (Industry) cr.list().get(0);
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

		return  industry;
	}

	public HashSet<Integer> getIndustryIds()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		HashSet<Integer> results = new HashSet<Integer>();
		List<Industry> industryResults;


		try
		{
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(Industry.class);

			industryResults = cr.list();

			for(Industry industry : industryResults)
			{
				results.add(industry.getIdIndustry());
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
