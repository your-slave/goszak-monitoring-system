package dao;

import entity.ZakupType1;
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

public class ZakupType1DAOImpl implements ZakupType1DAO
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

	public List<DefaultDictionaryRepresntation> getZakupType1Dictionary()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		List<ZakupType1> zakupType1Results = null;
		ArrayList<DefaultDictionaryRepresntation> zakupType1DictionaryResults = new ArrayList<DefaultDictionaryRepresntation>();

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(ZakupType1.class);

			zakupType1Results = cr.list();

			DefaultDictionaryRepresntation temp;

			for(ZakupType1 result : zakupType1Results)
			{
				temp = (DefaultDictionaryRepresntation) context.getBean("defaultDictionaryRepresntation");
				temp.setId(result.getIdZakupType1());
				temp.setName(result.getName());
				zakupType1DictionaryResults.add(temp);
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

		return zakupType1DictionaryResults;
	}

	public ZakupType1 getZakupType1(int idZakupType1)
	{
		Session session = null;
		Transaction transaction = null;
		ZakupType1 zakupType1 = null;

		try
		{
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Criteria cr = session.createCriteria(ZakupType1.class);
			cr.add(Restrictions.eq("idZakupType1", idZakupType1));
			if(!cr.list().isEmpty())
			{
				zakupType1 = (ZakupType1) cr.list().get(0);
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

		return  zakupType1;
	}

	public HashSet<Integer> getZakupType1Ids()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		HashSet<Integer> results = new HashSet<Integer>();
		List<ZakupType1> zakupType1Resulrs;


		try
		{
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(ZakupType1.class);

			zakupType1Resulrs = cr.list();

			for(ZakupType1 zakupType1 : zakupType1Resulrs)
			{
				results.add(zakupType1.getIdZakupType1());
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
