package service.logic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.ApplicationContextProvider;
import service.representation.ReceivedInformationRepresntation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParserImpl implements Parser

{
	private  URL url = null;;

	public List<ReceivedInformationRepresntation> parse(String link, Boolean international)
	{

		boolean success = false;

		try
		{
			url = new URL(link);
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}

		Document document = null;

		while (!success)
		{
			try
			{
				document = Jsoup.parse(url, 3000);
				success = true;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		//Elements els = document.getElementsByClass("lst top");
		Elements elements = document.select("tr[class~=rw.*]");

		ArrayList<ReceivedInformationRepresntation> receivedInformationRepresntations = new ArrayList<ReceivedInformationRepresntation>();

		ReceivedInformationRepresntation temp = (ReceivedInformationRepresntation) ApplicationContextProvider.getApplicationContext().getBean("receivedInformationRepresntation");


		//Elements elements = document.select("tr[class^=rw]");


		if(international)
		{
			for (Element element : elements)
			{
				temp = (ReceivedInformationRepresntation) ApplicationContextProvider.getApplicationContext().getBean("receivedInformationRepresntation");

				try
				{

					temp.setBriefDescription(element.childNode(1).childNode(0).childNode(0).toString());

					if(element.childNode(5).childNodes().size()>0)
						temp.setCompanyRegion(element.childNode(5).childNode(0).toString());
					temp.setCountry(element.childNode(3).childNode(0).toString());
					temp.setCost(element.childNode(7).childNode(0).toString().trim());
					temp.setRequestEndTme(element.childNode(11).childNode(0).toString());
					temp.setNumber(element.childNode(9).childNode(0).toString());
					temp.setLink(element.childNode(1).childNode(0).attr("href"));
				}
				catch (Exception ex)
				{
					temp.toString();
				}

				receivedInformationRepresntations.add(temp);

			}
		}

		else
		{
			for (Element element : elements)
			{
				temp = (ReceivedInformationRepresntation) ApplicationContextProvider.getApplicationContext().getBean("receivedInformationRepresntation");

				temp.setBriefDescription(element.childNode(1).childNode(1).childNode(0).toString().trim());
				temp.setCompanyRegion(element.childNode(3).childNode(0).toString());
				temp.setCountry(element.childNode(5).childNode(0).toString());
				temp.setCost(element.childNode(9).childNode(1).childNode(0) + " " + element.childNode(9).childNode(2));
				temp.setRequestEndTme(element.childNode(11).childNode(0).toString());
				temp.setNumber(element.childNode(7).childNode(0).toString().trim());
				temp.setLink(element.childNode(1).childNode(1).attributes().get("href"));

				receivedInformationRepresntations.add(temp);
			}
		}


//		private Integer idCrriteria;
//		private String briefDescription;
//		private String companyRegion;
//		private String country;
//		private String cost;
//		private String requestEndTme;
//		private String number;
//		private String link;
//		private Integer id;

		return receivedInformationRepresntations;
	}
}
