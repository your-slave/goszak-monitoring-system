package dao;

import entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import service.ApplicationContextProvider;
import service.representation.UserRepresentation;
import util.PasswordEncoderUtilImpl;

import java.io.UnsupportedEncodingException;


public class UserDAOImpl implements UserDAO
{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void addUser(UserRepresentation userRepresentation)
    {
        Session session = null;
        Transaction transaction = null;
        User newUser = null;

        try
        {
            ApplicationContext context = ApplicationContextProvider.getApplicationContext();
            newUser = (User) context.getBean("user");
            newUser.setEmail(userRepresentation.getEmail());
            newUser.setPhoneNumber(userRepresentation.getPhoneNumber());
            newUser.setFirstName(userRepresentation.getFirstName());
            newUser.setLastName(userRepresentation.getLastName());
            newUser.setMiddleName(userRepresentation.getMiddleName());

            try
            {
                newUser.setPassword(PasswordEncoderUtilImpl.Encode(userRepresentation.getPassword()).getBytes("UTF-8"));
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }

            session = sessionFactory.getCurrentSession();

            transaction = session.beginTransaction();
            session.save(newUser);

            if (!transaction.wasCommitted())
                transaction.commit();
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            if(session != null && session.isOpen())
                session.close();
        }
    }

    public User getUserByEmail(String email)
    {
        Session session = null;
        Transaction transaction = null;
        User user = null;

        try
        {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(User.class);
            cr.add(Restrictions.eq("email", email));
            user = (User)cr.list().get(0);
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            if(session != null && session.isOpen())
                session.close();
        }

        return  user;
    }

//    public List<User> getUsers() {
//        Session session = null;
//        Transaction transaction = null;
//        List<User> results = null;
//
//        try
//        {
//            session = sessionFactory.getCurrentSession();
//            transaction = session.beginTransaction();
//            Criteria cr = session.createCriteria(User.class);
//            results = cr.list();
//        }
//
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            if (transaction != null)
//            {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//        finally
//        {
//            if(session != null && session.isOpen())
//                session.close();
//        }
//
//        return  results;
//    }

}
