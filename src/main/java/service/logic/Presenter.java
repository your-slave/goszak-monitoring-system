package service.logic;

import service.representation.NotificationRepresentation;
import service.representation.ReceivedInformationRepresntation;

import java.util.List;

public interface Presenter
{
	public NotificationRepresentation present(List<ReceivedInformationRepresntation> receivedInformationRepresntations, boolean international);
}
