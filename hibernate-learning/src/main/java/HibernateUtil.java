import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
  private static SessionFactory sessionFactory;

  // executes when class loads in the memory
  static {
    try {
      sessionFactory = new Configuration()
          .configure("hibernate.cfg.xml")
          .addAnnotatedClass(UserClassHibernate.class)
          .buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }

  public static Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  public static void close() {
    sessionFactory.close();
  }
}
