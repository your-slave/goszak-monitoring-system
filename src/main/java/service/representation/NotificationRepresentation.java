package service.representation;

import java.util.Date;

public class NotificationRepresentation
{
	private Integer idReceivedInformation;
	private String subjsect;
	private String text;
	private String sendingTime;

	public Integer getIdReceivedInformation()
	{
		return idReceivedInformation;
	}

	public void setIdReceivedInformation(Integer idReceivedInformation)
	{
		this.idReceivedInformation = idReceivedInformation;
	}

	public String getSubjsect()
	{
		return subjsect;
	}

	public void setSubjsect(String subjsect)
	{
		this.subjsect = subjsect;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getSendingTime()
	{
		return sendingTime;
	}

	public void setSendingTime(String sendingTime)
	{
		this.sendingTime = sendingTime;
	}
}
