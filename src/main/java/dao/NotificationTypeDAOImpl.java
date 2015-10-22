package dao;

import entity.NotificationType;
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


public class NotificationTypeDAOImpl implements NotificationTypeDAO
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

	public List<DefaultDictionaryRepresntation> getNotificationTypeDictionary()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		List<NotificationType> notificationTypeResults = null;
		ArrayList<DefaultDictionaryRepresntation> notificationTypeDictionaryResults = new ArrayList<DefaultDictionaryRepresntation>();

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(NotificationType.class);

			notificationTypeResults = cr.list();

			DefaultDictionaryRepresntation temp;

			for(NotificationType result : notificationTypeResults)
			{
				temp = (DefaultDictionaryRepresntation) context.getBean("defaultDictionaryRepresntation");
				temp.setId(result.getIdNotificationType());
				temp.setName(result.getName());
				notificationTypeDictionaryResults.add(temp);
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

		return notificationTypeDictionaryResults;
	}

	public NotificationType getNotificationType(int idNotificationType)
	{
		Session session = null;
		Transaction transaction = null;
		NotificationType notificationType = null;

		try
		{
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			Criteria cr = session.createCriteria(NotificationType.class);
			cr.add(Restrictions.eq("idNotificationType", idNotificationType));
			if(!cr.list().isEmpty())
			{
				notificationType = (NotificationType) cr.list().get(0);
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

		return  notificationType;
	}


	public HashSet<Integer> getNotificationTypeIds()
	{
		Session session = null;
		Transaction transaction = null;
		Criteria cr = null;

		HashSet<Integer> results = new HashSet<Integer>();
		List<NotificationType> notificationTypeResults;


		try
		{
			session = sessionFactory.getCurrentSession();

			transaction = session.beginTransaction();

			cr = session.createCriteria(NotificationType.class);

			notificationTypeResults = cr.list();

			for(NotificationType notificationType : notificationTypeResults)
			{
				results.add(notificationType.getIdNotificationType());
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
