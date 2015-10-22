package dao;

import entity.Period;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import service.ApplicationContextProvider;
import service.representation.DefaultDictionaryRepresntation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PeriodDAOImpl implements PeriodDAO
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

	public List<DefaultDictionaryRepresntation> getPeriodDictionary()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		List<Period> periodResults = null;
		ArrayList<DefaultDictionaryRepresntation> periodDictionaryResults = new ArrayList<DefaultDictionaryRepresntation>();

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(Period.class);

			periodResults = cr.list();

			DefaultDictionaryRepresntation temp;
			for(Period result : periodResults)
			{
				temp = (DefaultDictionaryRepresntation) context.getBean("defaultDictionaryRepresntation");
				temp.setId(result.getIdPeriod());
				temp.setName(result.getText());
				periodDictionaryResults.add(temp);
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

		return periodDictionaryResults;
	}



	public Period getPeriod(int idPeriod)
	{
		Session session = null;
		Transaction transaction = null;
		Period period = null;

		try
		{
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Criteria cr = session.createCriteria(Period.class);
			cr.add(Restrictions.eq("idPeriod", idPeriod));
			if(!cr.list().isEmpty())
			{
				period = (Period) cr.list().get(0);
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

		return  period;
	}

	public HashSet<Integer> getPeriodIds()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		HashSet<Integer> results = new HashSet<Integer>();
		List<Period> periodResults;


		try
		{
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(Period.class);

			periodResults = cr.list();

			for(Period period : periodResults)
			{
				results.add(period.getIdPeriod());
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
