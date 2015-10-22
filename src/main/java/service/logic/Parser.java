package service.logic;

import service.representation.ReceivedInformationRepresntation;

import java.util.List;

public interface Parser
{
	public List<ReceivedInformationRepresntation> parse(String link, Boolean international);
}
