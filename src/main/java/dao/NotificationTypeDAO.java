package dao;

import entity.NotificationType;
import service.representation.DefaultDictionaryRepresntation;

import java.util.HashSet;
import java.util.List;

public interface NotificationTypeDAO
{
	public List<DefaultDictionaryRepresntation> getNotificationTypeDictionary();
	public NotificationType getNotificationType(int idNotificationType);
	public HashSet<Integer> getNotificationTypeIds();
}
