package test.mongodb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.dell.jpa.crossdatastore.entity.CommonUser;

@WebServlet(name = "persist", urlPatterns = {"/persist"})
public class JpaDemoServlet extends HttpServlet {

	private static final long serialVersionUID = -7075159164600457298L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleCmd(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleCmd(response);
    }
    protected void handleCmd(HttpServletResponse response) throws IOException, ServletException {
    	EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("mongoPersistence1");
        
        EntityManager em2 = emf2.createEntityManager();
        
        CommonUser user = new CommonUser();
        user.setUserId(1);
        user.setFirstName("John");
        user.setLastName("Smith");
        
        PrintWriter out = response.getWriter();

        out.println("Mongo DB in OpenShift: ");
        out.println("OPENSHIFT_MONGODB_DB_HOST: " + System.getenv("OPENSHIFT_MONGODB_DB_HOST"));
        out.println("OPENSHIFT_MONGODB_DB_PORT: " + System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
        out.println("OPENSHIFT_MONGODB_DB_USERNAME: " + System.getenv("OPENSHIFT_MONGODB_DB_USERNAME"));
        out.println("OPENSHIFT_MONGODB_DB_PASSWORD: " + System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD"));
        out.println("OPENSHIFT_APP_NAME: " + System.getenv("OPENSHIFT_APP_NAME"));
        out.println("\nMySQL DB in OpenShift: ");
        out.println("OPENSHIFT_MYSQL_DB_HOST: " + System.getenv("OPENSHIFT_MYSQL_DB_HOST"));
        out.println("OPENSHIFT_MYSQL_DB_HOST: " + System.getenv("OPENSHIFT_MYSQL_DB_HOST"));
        out.println("OPENSHIFT_MYSQL_DB_USERNAME: " + System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"));
        out.println("OPENSHIFT_MYSQL_DB_PASSWORD: " + System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD"));
        out.println("OPENSHIFT_APP_NAME: " + System.getenv("OPENSHIFT_APP_NAME"));
        
        out.println("\npersisting user to Mongo DB: "+ " firstName: " + user.getFirstName() + "; lastName: " + user.getLastName() +"; id: " + user.getUserId());
        em2.persist(user);

        em2.clear();
           
        CommonUser userFound = em2.find(CommonUser.class, 1);
        out.println("querying user from Mongo DB, user found: " + " firstName: " + userFound.getFirstName() + "; lastName: " + userFound.getLastName() +"; id: " + userFound.getUserId());
    
        emf2.close();
        em2.close();
        
        //persist same entity in mySQL       
        EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("mysqlPersistence1");
        EntityManager em3 = emf3.createEntityManager();

        out.println("\npersisting user to mySQL DB: "+ " firstName: " + user.getFirstName() + "; lastName: " + user.getLastName() +"; id: " + user.getUserId());

        em3.persist(user);

        em3.clear();
           
        userFound = em3.find(CommonUser.class, 1);
        out.println("querying user from mySQL DB, user found: " + " firstName: " + userFound.getFirstName() + "; lastName: " + userFound.getLastName() +"; id: " + userFound.getUserId());
  

        emf3.close();
        em3.close(); 
    }
  
}
