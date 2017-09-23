package hello;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	Greeting retVal = null;   
    	
    	
    	   //return DriverManager.getConnection(dbUrl, username, password);
    	   try {
    		   URI dbUri = new URI(System.getenv("DATABASE_URL"));
        	   String username = dbUri.getUserInfo().split(":")[0];
        	   String password = dbUri.getUserInfo().split(":")[1];
        	   String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
			Connection testConnection = DriverManager.getConnection(dbUrl, username, password);
			retVal = new Greeting(counter.incrementAndGet(), "Successful Connection");
    	   } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			retVal = new Greeting(counter.incrementAndGet(), e.getMessage());
    	   } catch (URISyntaxException uriEx) {
    		uriEx.printStackTrace();
    		retVal = new Greeting(counter.incrementAndGet(), uriEx.getMessage());
    	   }


    	   /*
    	 * select query in code
    	 * return statement
    	 */
    	return retVal;
    }
}
