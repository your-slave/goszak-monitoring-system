package service.logic;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.velocity.VelocityEngineUtils;
import service.ApplicationContextProvider;
import service.representation.NotificationRepresentation;
import service.representation.ReceivedInformationRepresntation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PresenterImpl implements Presenter
{
	private VelocityEngine velocityEngine;

	public void setVelocityEngine(VelocityEngine velocityEngine)
	{
		this.velocityEngine = velocityEngine;
	}




	public NotificationRepresentation present(List<ReceivedInformationRepresntation> receivedInformationRepresntations, boolean international)
	{
//		NotificationRepresentation notificationRepresentation = (NotificationRepresentation) ApplicationContextProvider.getApplicationContext().getBean("notificationRepresentation");
//		notificationRepresentation.setSubjsect("Для вас получена информация о " + receivedInformationRepresntations.size() + " тендерах государственных закупок");
//
//		String text = "Здравствуйте.\n Для вас получена следующая информация о тендерах государственных закупок:";
//
//		for(ReceivedInformationRepresntation receivedInformationRepresntation : receivedInformationRepresntations)
//		{
//			text+="\n\n";
//			text+="Номер тендера:" + receivedInformationRepresntation.getNumber();
//			text+="\n";
//			text+=receivedInformationRepresntation.getBriefDescription();
//			text+="\n";
//			text+="Регион: " + receivedInformationRepresntation.getCompanyRegion();
//			text+="\n";
//			text+="Стоимостью: " + receivedInformationRepresntation.getCost();
//			text+="\n";
//			text+="Предложение действительно до: " + receivedInformationRepresntation.getRequestEndTme();
//			text+="\n";
//			text+="Для более подробной информации нажмите " + "<a href=\"" + receivedInformationRepresntation.getLink() + "\">здесь</a>.";
//		}
//
//		//notificationRepresentation.setText();
//
//		notificationRepresentation.setText(text);
//
////		private String briefDescription;
////		private String companyRegion;
////		private String country;
////		private String cost;
////		private String requestEndTme;
////		private String number;
////		private String link;
//
//
//		return notificationRepresentation;


		NotificationRepresentation notificationRepresentation = (NotificationRepresentation) ApplicationContextProvider.getApplicationContext().getBean("notificationRepresentation");

		String subject = "Для вас получена информация о " + receivedInformationRepresntations.size() + " тендерах государственных закупок";
		notificationRepresentation.setSubjsect(subject);

		Map model = new HashMap();
		model.put("receivedInformations", receivedInformationRepresntations);
		model.put("international", international);
		String message = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "email-template.vm", "UTF-8", model);
		notificationRepresentation.setText(message);


		//Map model1 = new HashMap();

		return notificationRepresentation;


	}
}
