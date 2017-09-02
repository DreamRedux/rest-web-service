package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
       /** grab jdbc connection
        * confirm it's not null
        * if not null, print "true"
        */
    	/**
    	 * select query in code
    	 * return statement
    	 */
    	return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
