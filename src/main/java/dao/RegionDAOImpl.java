package dao;

import entity.Region;
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

public class RegionDAOImpl implements RegionDAO
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

	public List<DefaultDictionaryRepresntation> getRegionDictionary()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		List<Region> regionResults = null;
		ArrayList<DefaultDictionaryRepresntation> regionDictionaryResults = new ArrayList<DefaultDictionaryRepresntation>();

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(Region.class);

			regionResults = cr.list();

			DefaultDictionaryRepresntation temp;

			for(Region result : regionResults)
			{
				temp = (DefaultDictionaryRepresntation) context.getBean("defaultDictionaryRepresntation");
				temp.setId(result.getIdRegion());
				temp.setName(result.getName());
				regionDictionaryResults.add(temp);
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

		return regionDictionaryResults;
	}

	public Region getRegion(int idRegion)
	{
		Session session = null;
		Transaction transaction = null;
		Region region = null;

		try
		{
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Criteria cr = session.createCriteria(Region.class);
			cr.add(Restrictions.eq("idRegion", idRegion));
			if(!cr.list().isEmpty())
			{
				region = (Region) cr.list().get(0);
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

		return  region;
	}

	public HashSet<Integer> getRegionIds()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		HashSet<Integer> results = new HashSet<Integer>();
		List<Region> regionResults;


		try
		{
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(Region.class);

			regionResults = cr.list();

			for(Region region : regionResults)
			{
				results.add(region.getIdRegion());
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
