package service.logic;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;
import dao.*;

import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import service.ApplicationContextProvider;
import service.representation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ScheduledFuture;


@Path("/")
public class RestService
{

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Calendar calendar = Calendar.getInstance();


    @POST
    @Path("/add")
    public Response Add(CriteriaRepresentation criteriaRepresentation)
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (CriteriaRepresentationValidator.Validate(criteriaRepresentation, false))
        {
            CriteriaDAO criteriaDAO = (CriteriaDAO) context.getBean("criteriaDAO");
            Integer result = criteriaDAO.addCritera(authentication.getName(), criteriaRepresentation);

            if (result > -1)
            {
                criteriaRepresentation.setIdCriteria(result);

                LinkGenerator linkGenerator = (LinkGenerator) context.getBean("linkGenerator");
                String link = linkGenerator.generate(criteriaRepresentation);

                Job grabber = new Job(result, link, criteriaRepresentation.getIdNotificationType(), criteriaDAO.getDestination(result), criteriaRepresentation.getInternational());
                TaskScheduler scheduler = (TaskScheduler) context.getBean("scheduler");
                ScheduledFuture scheduledFuture = scheduler.schedule(grabber, new CronTrigger(criteriaRepresentation.getSendingFrequency()));
                HashMap<Integer, ScheduledFuture> map = (HashMap<Integer, ScheduledFuture>) context.getBean("schedulesMap");
                map.put(result, scheduledFuture);

                return Response.status(Response.Status.OK)
                        .entity("Input error").type("text/plain").build();
            }
            else
            {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Interanl Server Error").type("text/plain").build();
            }
        }
        else
        {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Input error").type("text/plain").build();
        }

    }



    @POST
    @Path("/upd")
    public Response Update(CriteriaRepresentation criteriaRepresentation)
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(CriteriaRepresentationValidator.Validate(criteriaRepresentation, true))
        {
            CriteriaDAO criteriaDAO = (CriteriaDAO)context.getBean("criteriaDAO");
            Integer result = criteriaDAO.updateCriteria(authentication.getName(), criteriaRepresentation);

            if(result>-1)
            {
                LinkGenerator linkGenerator = (LinkGenerator)context.getBean("linkGenerator");
                String link = linkGenerator.generate(criteriaRepresentation);

                Job grabber = new Job(result, link, criteriaRepresentation.getIdNotificationType(), criteriaDAO.getDestination(result), criteriaRepresentation.getInternational());
                TaskScheduler scheduler = (TaskScheduler) context.getBean("scheduler");
                ScheduledFuture scheduledFuture = scheduler.schedule(grabber, new CronTrigger(criteriaRepresentation.getSendingFrequency()));
                HashMap<Integer, ScheduledFuture> map = (HashMap<Integer, ScheduledFuture>) context.getBean("schedulesMap");
                map.get(result).cancel(true);
                map.put(result, scheduledFuture);

                return Response.status(Response.Status.OK)
                        .entity("Input error").type("text/plain").build();
            }
            else
            {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Can't find criteria with given id").type("text/plain").build();
            }
        }
        else
        {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Input error").type("text/plain").build();
        }
    }

    @PUT
    @Path("/activate/{id}")
    public void Activate(@PathParam("id")int id)
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CriteriaDAO criteriaDAO = (CriteriaDAO)context.getBean("criteriaDAO");
        criteriaDAO.activateCriteria(authentication.getName(), id);

        HashMap<Integer, ScheduledFuture> map = (HashMap<Integer, ScheduledFuture>) context.getBean("schedulesMap");
        map.get(id).cancel(false);
    }

    @DELETE
    @Path("/del/{id}")
    public void Delete(@PathParam("id")int id)
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CriteriaDAO criteriaDAO = (CriteriaDAO)context.getBean("criteriaDAO");
        criteriaDAO.deleteCreteria(authentication.getName(), id);

        HashMap<Integer, ScheduledFuture> map = (HashMap<Integer, ScheduledFuture>) context.getBean("schedulesMap");
        map.get(id).cancel(true);
        map.remove(id);
    }

    @PUT
    @Path("/suspend/{id}")
    public void Suspend(@PathParam("id")int id)
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CriteriaDAO criteriaDAO = (CriteriaDAO)context.getBean("criteriaDAO");
        criteriaDAO.suspendCriteria(authentication.getName(), id);

        HashMap<Integer, ScheduledFuture> map = (HashMap<Integer, ScheduledFuture>) context.getBean("schedulesMap");
        map.get(id).cancel(true);
    }

    @GET
    @Path("/getCountries")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CountryDictionaryRepresentation> getCountryDictionary()
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        CountryDAO countryDAO = (CountryDAO)context.getBean("countryDAO");
        List<CountryDictionaryRepresentation> countryDictionaryRepresentationss = countryDAO.getCountriesDictionary();
        return countryDictionaryRepresentationss;
    }

    @GET
    @Path("/getIndustries")
    @Produces(MediaType.APPLICATION_JSON)
    public List<IndustryDictionaryRepresentation> getInsustryDictionary()
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        IndustryDAO industryDAO = (IndustryDAO)context.getBean("industryDAO");
        List<IndustryDictionaryRepresentation> industryDictionaryRepresentations = industryDAO.getIndustryDictionary();
        return industryDictionaryRepresentations;
    }

    @GET
    @Path("/getEstablishments")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DefaultDictionaryRepresntation> getEstablishmentDictionary()
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        EstablishmentDAO establishmentDAO = (EstablishmentDAO)context.getBean("establishmentDAO");
        List<DefaultDictionaryRepresntation> defaultDictionaryRepresntations = establishmentDAO.getEstablishmentsDictionary();
        return defaultDictionaryRepresntations;
    }

    @GET
    @Path("/getNotificationTypes")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DefaultDictionaryRepresntation> getNotificationTypeDictionary()
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        NotificationTypeDAO notificationTypeDAO = (NotificationTypeDAO)context.getBean("notificationTypeDAO");
        List<DefaultDictionaryRepresntation> defaultDictionaryRepresntations = notificationTypeDAO.getNotificationTypeDictionary();
        return defaultDictionaryRepresntations;
    }

    @GET
    @Path("/getZakup1Types")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DefaultDictionaryRepresntation> getZakupType1Dictionary()
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        ZakupType1DAO zakupType1DAO = (ZakupType1DAO)context.getBean("zakupType1DAO");
        List<DefaultDictionaryRepresntation> defaultDictionaryRepresntations = zakupType1DAO.getZakupType1Dictionary();
        return defaultDictionaryRepresntations;
    }

    @GET
    @Path("/getZakup2Types")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DefaultDictionaryRepresntation> getZakupType2Dictionary()
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        ZakupType2DAO zakupType2DAO = (ZakupType2DAO)context.getBean("zakupType2DAO");
        List<DefaultDictionaryRepresntation> defaultDictionaryRepresntations = zakupType2DAO.getZakupType2Dictionary();
        return defaultDictionaryRepresntations;
    }

    @GET
    @Path("/getRegiones")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DefaultDictionaryRepresntation> getRegionDictionary()
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        RegionDAO regionDAO = (RegionDAO)context.getBean("regionDAO");
        List<DefaultDictionaryRepresntation> defaultDictionaryRepresntations = regionDAO.getRegionDictionary();
        return defaultDictionaryRepresntations;
    }


    @GET
    @Path("/getCriterias")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CriteriaRepresentation> getCriterias()
    {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CriteriaDAO criteriaDAO = (CriteriaDAO)context.getBean("criteriaDAO");

        List<CriteriaRepresentation> criteriaRepresentations = criteriaDAO.getUsersCriteriasRepresintations(authentication.getName());
        return criteriaRepresentations;
    }

    @POST
    @Path("/registrate")
    public Response Registrate(UserRepresentation userRepresentation)
    {
        if(UserRepresentationValidator.validate(userRepresentation))
        {

            ApplicationContext context = ApplicationContextProvider.getApplicationContext();

            UserDAO userDAO = (UserDAO) context.getBean("userDAO");

            userDAO.addUser(userRepresentation);
            return Response.status(Response.Status.OK)
                    .entity("Input error").type("text/plain").build();
        }

        else
        {
            return  Response.status(Response.Status.BAD_REQUEST)
                    .entity("Input error").type("text/plain").build();
        }
    }

    @GET
    @Path("/getFeed/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getFeed(@PathParam("id")int id)
    {
		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		CriteriaDAO criteriaDAO = (CriteriaDAO)context.getBean("criteriaDAO");
		CriteriaRepresentation criteriaRepresentation = criteriaDAO.getCriteriaRepresentation(authentication.getName(), id);

		if(criteriaRepresentation!=null)
		{
			LinkGenerator linkGenerator = (LinkGenerator)context.getBean("linkGenerator");
			String link = linkGenerator.generate(criteriaRepresentation);

			Parser pareser = new ParserImpl();
			List<ReceivedInformationRepresntation> receivedInformationRepresntations = pareser.parse(link, criteriaRepresentation.getInternational());

			for(ReceivedInformationRepresntation receivedInformationRepresntation : receivedInformationRepresntations)
			{
				receivedInformationRepresntation.setIdCrriteria(id);
			}

			ReceivedInformationDAO receivedInformationDAO = (ReceivedInformationDAO) context.getBean("receivedInformationDAO");
			receivedInformationDAO.add(receivedInformationRepresntations);
			receivedInformationRepresntations = receivedInformationDAO.getLastReceivedInformationRepresntations(id);

			RssGenerator rssGenerator = (RssGenerator) context.getBean("rssGenerator");
			SyndFeed feed = rssGenerator.generate(receivedInformationRepresntations, criteriaRepresentation.getInternational());

			final SyndFeedOutput output = new SyndFeedOutput();
			final Writer writer = new StringWriter();

			try
			{
				output.output(feed, writer);
			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (FeedException e)
			{
				e.printStackTrace();
			}

			NotificationRepresentation notificationRepresentation = (NotificationRepresentation) context.getBean("notificationRepresentation");
			notificationRepresentation.setText(writer.toString());
			notificationRepresentation.setSubjsect("RSS рассылка");
			notificationRepresentation.setSendingTime(dateFormat.format(calendar.getTime()));

			NotificationDAO notificationDAO = (NotificationDAO) context.getBean("notificationDAO");

			Integer idNotification = notificationDAO.add(notificationRepresentation);
			receivedInformationDAO.updateReceivedInformationNotification(receivedInformationRepresntations, idNotification);

			return Response.ok(writer.toString()).build();
		}

        return  Response.status(Response.Status.BAD_REQUEST)
				.entity("Input error").type("text/plain").build();

    }






}


