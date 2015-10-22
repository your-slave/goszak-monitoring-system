package service.logic;

import dao.NotificationDAO;
import dao.ReceivedInformationDAO;
import org.springframework.context.ApplicationContext;
import service.ApplicationContextProvider;
import service.representation.NotificationRepresentation;
import service.representation.ReceivedInformationRepresntation;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Job implements Runnable

{
	private String link;
	private int notificationType;
	private String destination;
	private int id;
	private Boolean international;
	private List<ReceivedInformationRepresntation> receivedInformationRepresntations;
	private NotificationRepresentation notificationRepresentation;
	private ApplicationContext context;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Calendar calendar = Calendar.getInstance();


	public Job(int id, String link, int notificationType, String destination, boolean international)
	{
		this.id = id;
		this.link = link;
		this.notificationType = notificationType;
		this.destination = destination;
		this.international = international;

		context = ApplicationContextProvider.getApplicationContext();
	}

	public void run()
	{
		Parser pareser = new ParserImpl();
		receivedInformationRepresntations = pareser.parse(link, international);

		if(receivedInformationRepresntations.size()!=0)
		{
			ReceivedInformationDAO receivedInformationDAO = (ReceivedInformationDAO) context.getBean("receivedInformationDAO");

			for(ReceivedInformationRepresntation receivedInformationRepresntation : receivedInformationRepresntations)
			{
				receivedInformationRepresntation.setIdCrriteria(id);
			}

			receivedInformationRepresntations = receivedInformationDAO.add(receivedInformationRepresntations);

			if(receivedInformationRepresntations!=null)
			{
				switch(notificationType)
				{
					case(1):
						Presenter presenter = (Presenter) context.getBean("emailPresenter");
						notificationRepresentation = presenter.present(receivedInformationRepresntations, international);
						Sender sender = (Sender) context.getBean("emailSender");
						sender.send(destination, notificationRepresentation.getSubjsect(), notificationRepresentation.getText());
						break;
				}

				calendar.setTime(new Date());

				notificationRepresentation.setSendingTime(dateFormat.format(calendar.getTime()));

				NotificationDAO notificationDAO = (NotificationDAO) context.getBean("notificationDAO");
				Integer idNotification = notificationDAO.add(notificationRepresentation);
				receivedInformationDAO.updateReceivedInformationNotification(receivedInformationRepresntations, idNotification);
			}

		}




//		if(notificationRepresentation!=null)
//		{
//			Presenter presenter = new PresenterImpl();
//			notificationRepresentation = presenter.present(receivedInformationRepresntations);
//		}
//
//
//		switch(notificationType)
//		{
//			case(1):
//				Sender sender = (Sender) context.getBean("emailSender");
//				sender.send(destination, notificationRepresentation.getSubjsect(), notificationRepresentation.getText());
//		}

		//NotificationDAO notificationDAO = (NotificationDAO) context.getBean("notificationDAO");
		//notificationDAO.add(notificationRepresentations);

	}
}
