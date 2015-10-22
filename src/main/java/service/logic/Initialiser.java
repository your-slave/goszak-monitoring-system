package service.logic;

//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;


import dao.*;
import entity.Industry;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import service.ApplicationContextProvider;
import service.representation.CriteriaRepresentation;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

public class Initialiser implements ServletContextListener
{

	public void contextInitialized(ServletContextEvent arg0)
	{
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();

		CriteriaDAO criteriaDAO = (CriteriaDAO) context.getBean("criteriaDAO");
		List<CriteriaRepresentation> criteriaRepresentations = criteriaDAO.getCriteriasRepresintationsForStart();

		TaskScheduler scheduler = (TaskScheduler) context.getBean("scheduler");
		HashMap<Integer, ScheduledFuture> map = (HashMap<Integer, ScheduledFuture>) context.getBean("schedulesMap");
		LinkGenerator linkGenerator = (LinkGenerator)context.getBean("linkGenerator");
		String link = null;

		for(CriteriaRepresentation criteriaRepresentation : criteriaRepresentations)
		{

			link = linkGenerator.generate(criteriaRepresentation);
			Job grabber = new Job(criteriaRepresentation.getIdCriteria(), link, criteriaRepresentation.getIdNotificationType(), criteriaDAO.getDestination(criteriaRepresentation.getIdCriteria()), criteriaRepresentation.getInternational());
			ScheduledFuture scheduledFuture = scheduler.schedule(grabber, new CronTrigger(criteriaRepresentation.getSendingFrequency()));
			map.put(criteriaRepresentation.getIdCriteria(), scheduledFuture);
		}


		HashSet<Integer> countryIds = (HashSet<Integer>) context.getBean("countryIdSet");
		CountryDAO countryDAO = (CountryDAO) context.getBean("countryDAO");
		countryIds.addAll(countryDAO.getCountryIds());

		HashSet<Integer> establishmentIds = (HashSet<Integer>) context.getBean("establishmentIdSet");
		EstablishmentDAO establishmentDAO = (EstablishmentDAO) context.getBean("establishmentDAO");
		establishmentIds.addAll(establishmentDAO.getEstablishmentIds());

		HashSet<Integer> industryIds = (HashSet<Integer>) context.getBean("industryIdSet");
		IndustryDAO industryDAO = (IndustryDAO) context.getBean("industryDAO");
		industryIds.addAll(industryDAO.getIndustryIds());

		HashSet<Integer> notificationTypeIds = (HashSet<Integer>) context.getBean("notificationTypeIdSet");
		NotificationTypeDAO notificationTypeDAO = (NotificationTypeDAO) context.getBean("notificationTypeDAO");
		notificationTypeIds.addAll(notificationTypeDAO.getNotificationTypeIds());

		HashSet<Integer> periodIds = (HashSet<Integer>) context.getBean("periodIdSet");
		PeriodDAO periodDAO = (PeriodDAO) context.getBean("periodDAO");
		periodIds.addAll(periodDAO.getPeriodIds());

		HashSet<Integer> regionIds = (HashSet<Integer>) context.getBean("regionIdSet");
		RegionDAO regionDAO = (RegionDAO) context.getBean("regionDAO");
		regionIds.addAll(regionDAO.getRegionIds());

		HashSet<Integer> zakupType1IdSet = (HashSet<Integer>) context.getBean("zakupType1IdSet");
		ZakupType1DAO zakupType1DAO = (ZakupType1DAO) context.getBean("zakupType1DAO");
		zakupType1IdSet.addAll(zakupType1DAO.getZakupType1Ids());

		HashSet<Integer> zakupType2IdSet = (HashSet<Integer>) context.getBean("zakupType2IdSet");
		ZakupType2DAO zakupType2DAO = (ZakupType2DAO) context.getBean("zakupType2DAO");
		zakupType2IdSet.addAll(zakupType2DAO.getZakupType2Ids());

	}

	public void contextDestroyed(ServletContextEvent arg0)
	{

	}//end constextDestroyed method


}
