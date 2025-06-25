package toll.tcm.Hibernate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


@Entity
@Table(name = "Mvw_Toll_Lane_Master")
public class Mvw_Toll_Lane_Master {
	@Id
    @Column(name = "Lane_ID")
    private Long Lane_id;
	public Long getLane_ID() {
        return Lane_id;
    }

    public void setLane_ID(Long lane_ID) {
    	Lane_id = lane_ID;
    }
    
	public static void main(String[] args) {
		
	
	 SessionFactory factory = null;
     Session session=null;

     try {
         // Create a Hibernate configuration and build a session factory
//         factory = new Configuration().configure(System.getProperty("user.dir")+"\\src\\test\\java\\toll\\tcm\\Hibernate\\hibernate.cfg.xml").buildSessionFactory();
    	 factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//    	 Configuration configuration = new Configuration();
//    	 configuration.configure(System.getProperty("user.dir")+"\\src\\test\\java\\hibernate.cfg.xml");

         // Open a session
         session = factory.openSession();

         // Begin a transaction
         Transaction transaction = session.beginTransaction();

         // Fetch data using HQL (Hibernate Query Language) or Criteria API
         List resultList = session.createQuery("FROM Mvw_Toll_Lane_Master").list();

         // Commit the transaction
         transaction.commit();
         for (int i = 0; i < resultList.size(); i++) {
        	    Mvw_Toll_Lane_Master lane = (Mvw_Toll_Lane_Master) resultList.get(i);
        	    System.out.println("Lane ID: " + lane.getLane_ID());
        	}
     } catch (Exception e) {
         if (session != null && session.getTransaction() != null) {
             // Rollback the transaction in case of an exception
             session.getTransaction().rollback();
         }
         e.printStackTrace();  // Handle the exception as needed
     } finally {
         if (session != null) {
             // Close the session
             session.close();
         }
         if (factory != null) {
             // Close the session factory
             factory.close();
         }
     }
}
}
