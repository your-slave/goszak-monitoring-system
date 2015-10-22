package service.logic;


import com.sun.syndication.feed.synd.*;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;
import service.representation.ReceivedInformationRepresntation;


import java.util.*;

public class RssGenerator
{
	private VelocityEngine velocityEngine;

	public void setVelocityEngine(VelocityEngine velocityEngine)
	{
		this.velocityEngine = velocityEngine;
	}

	public SyndFeed generate(List<ReceivedInformationRepresntation> receivedInformationRepresntations, boolean international)
	{
		SyndFeed feed = new SyndFeedImpl();
		feed.setFeedType( "rss_2.0" );
		feed.setTitle( "Информация полученная о тендерах государственных закупок" );
		feed.setLink("http://http://www.icetrade.by/");
		feed.setDescription("Информация о тендерах государственных закупок");

		List<SyndEntry> entries = new ArrayList<SyndEntry>();
		SyndEntry entry = null;
		SyndContent description = null;

		String text;

		for(ReceivedInformationRepresntation receivedInformationRepresntation : receivedInformationRepresntations)
		{
			entry = new SyndEntryImpl();
			entry.setTitle("Тендер номер: " + receivedInformationRepresntation.getNumber());
			entry.setLink(receivedInformationRepresntation.getLink());
			entry.setPublishedDate( new Date() );

			description = new SyndContentImpl();
			description.setType("text/html");

//			text+="Краткое описание: ";
//			text+=receivedInformationRepresntation.getBriefDescription();
//			text+="<br>";
//			if(international)
//				text+="Регион: " + receivedInformationRepresntation.getCompanyRegion();
//			else
//				text+="Компания: " + receivedInformationRepresntation.getCompanyRegion();
//			text+="<br>";
//			text+="Стоимостью: " + receivedInformationRepresntation.getCost();
//			text+="<br>";
//			text+="Предложение действительно до: " + receivedInformationRepresntation.getRequestEndTme();
//			text+="<br>";

			Map model = new HashMap();
			model.put("receivedInformation", receivedInformationRepresntation);
			model.put("international", international);
			text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "feed-entry-template.vm", "UTF-8", model);

			description.setValue(text);
			entry.setDescription( description );

			entries.add( entry );
		}


		feed.setEntries(entries);

		return feed;
	}
}
