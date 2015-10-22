package dao;

import service.representation.ReceivedInformationRepresntation;

import java.util.List;

public interface ReceivedInformationDAO
{
	public List<ReceivedInformationRepresntation>add(List<ReceivedInformationRepresntation> receivedInformationRepresntations);
	public void updateReceivedInformationNotification(List<ReceivedInformationRepresntation> receivedInformationRepresntations, Integer idNotification);
	public List<ReceivedInformationRepresntation>getLastReceivedInformationRepresntations(int idCriteria);
}
