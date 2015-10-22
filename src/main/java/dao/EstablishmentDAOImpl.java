package dao;

import entity.Establishment;
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


public class EstablishmentDAOImpl implements EstablishmentDAO
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

	public List<DefaultDictionaryRepresntation> getEstablishmentsDictionary()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		List<Establishment> establishmentResults = null;
		ArrayList<DefaultDictionaryRepresntation> establishmentDictionaryResults = new ArrayList<DefaultDictionaryRepresntation>();

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(Establishment.class);

			establishmentResults = cr.list();

			DefaultDictionaryRepresntation temp;

			for(Establishment result : establishmentResults)
			{
				temp = (DefaultDictionaryRepresntation) context.getBean("defaultDictionaryRepresntation");
				temp.setId(result.getIdEstablishment());
				temp.setName(result.getName());
				establishmentDictionaryResults.add(temp);
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

		return establishmentDictionaryResults;
	}

	public Establishment getEstablishment(int idEstablishment)
	{
		Session session = null;
		Transaction transaction = null;
		Establishment establishment = null;

		try
		{
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Criteria cr = session.createCriteria(Establishment.class);
			cr.add(Restrictions.eq("idEstablishment", idEstablishment));
			if(!cr.list().isEmpty())
			{
				establishment = (Establishment) cr.list().get(0);
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

		return  establishment;
	}


	public HashSet<Integer> getEstablishmentIds()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		HashSet<Integer> results = new HashSet<Integer>();
		List<Establishment> establishmentResults;


		try
		{
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(Establishment.class);

			establishmentResults = cr.list();

			for(Establishment establishment : establishmentResults)
			{
				results.add(establishment.getIdEstablishment());
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
