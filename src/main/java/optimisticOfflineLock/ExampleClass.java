package optimisticOfflineLock;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by cassandra on 6/13/14.
 */
public class ExampleClass {

    public static void main(String arp[]) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("spring-config.xml");
        DataSource dataSource = (DataSource) appContext.getBean("dataSource");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        OOLSession oolSession1 = new OOLSession(jdbcTemplate);
        Customer customer = oolSession1.getCustomer(2);
        customer.setName("Jamal1");
        int i = oolSession1.updateRecords(customer);
        if (i == 0) {
            throw new RuntimeException("cannot update stale object, " +
                    "please take latest and update");
        }


        OOLSession oolSession2 = new OOLSession(jdbcTemplate);
        Customer customer1 = oolSession2.getCustomer(2);
        customer.setAge(213);

        //Update session one object
        oolSession1.updateRecords(customer);

        int dirtyCheck = oolSession2.updateRecords(customer1);
        if (dirtyCheck == 0) {
            throw new RuntimeException("cannot update stale object, " +
                    "please take latest and update");
        }

    }
}