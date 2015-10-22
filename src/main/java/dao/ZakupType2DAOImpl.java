package dao;

import entity.ZakupType2;
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

public class ZakupType2DAOImpl implements ZakupType2DAO
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

	public List<DefaultDictionaryRepresntation> getZakupType2Dictionary()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		List<ZakupType2> zakupType2Results = null;
		ArrayList<DefaultDictionaryRepresntation> zakupType2DictionaryResults = new ArrayList<DefaultDictionaryRepresntation>();

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(ZakupType2.class);

			zakupType2Results = cr.list();

			DefaultDictionaryRepresntation temp;

			for(ZakupType2 result : zakupType2Results)
			{
				temp = (DefaultDictionaryRepresntation) context.getBean("defaultDictionaryRepresntation");
				temp.setId(result.getIdZakupType2());
				temp.setName(result.getName());
				zakupType2DictionaryResults.add(temp);
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

		return zakupType2DictionaryResults;
	}

	public ZakupType2 getZakupType2(int idZakupType2)
	{
		Session session = null;
		Transaction transaction = null;
		ZakupType2 zakupType2 = null;

		try
		{
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Criteria cr = session.createCriteria(ZakupType2.class);
			cr.add(Restrictions.eq("idZakupType2", idZakupType2));
			if(!cr.list().isEmpty())
			{
				zakupType2 = (ZakupType2) cr.list().get(0);
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

		return  zakupType2;
	}

	public HashSet<Integer> getZakupType2Ids()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		HashSet<Integer> results = new HashSet<Integer>();
		List<ZakupType2> zakupType2Resulrs;


		try
		{
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(ZakupType2.class);

			zakupType2Resulrs = cr.list();

			for(ZakupType2 zakupType2 : zakupType2Resulrs)
			{
				results.add(zakupType2.getIdZakupType2());
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
