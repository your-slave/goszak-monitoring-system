package dao;

import entity.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import service.ApplicationContextProvider;
import service.representation.NotificationRepresentation;

import java.sql.Timestamp;
import java.util.List;

public class NotificationDAOImpl implements NotificationDAO
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

	public Integer add(NotificationRepresentation notificationRepresentation)
	{
		Session session = null;
		Transaction transaction = null;
		org.hibernate.Criteria cr = null;

		Notification notification = null;

		try
		{
			ApplicationContext context = ApplicationContextProvider.getApplicationContext();
			session = sessionFactory.getCurrentSession();

			notification = (Notification) context.getBean("notification");

			ReceivedInformationDAO receivedInformationDAO = (ReceivedInformationDAO)context.getBean("receivedInformationDAO");

			transaction = session.beginTransaction();

			cr = session.createCriteria(Notification.class);

			notification.setSentDate(Timestamp.valueOf(notificationRepresentation.getSendingTime()));
			notification.setSubject(notificationRepresentation.getSubjsect());
			notification.setText(notificationRepresentation.getText());

			session.save(notification);

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

		return notification.getIdNotification();

	}
}
