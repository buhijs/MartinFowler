package optimisticOfflineLock;

import com.google.common.collect.Maps;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by cassandra on 6/13/14.
 */
public class OOLSession {

    private static Map<Integer, Object> sessionObjects = Maps.newHashMapWithExpectedSize(0);
    private static JdbcTemplate jdbcTemplate;

    public OOLSession(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static Map<Integer, Object> getSessionObjects() {
        return sessionObjects;
    }

    public static void setSessionObjects(Map<Integer, Object> sessionObjects) {
        OOLSession.sessionObjects = sessionObjects;
    }

    public static void addObject(Customer object) {
        sessionObjects.put(object.getId(), object);
    }


    public Customer getCustomer(int id) {
        if (sessionObjects.get(id) != null) {
            return (Customer) sessionObjects.get(id);
        } else {
            Customer customer = queryCustomers(id);
            if (customer != null) {
                sessionObjects.put(customer.getId(), customer);
            }
            return customer;
        }
    }

    private Customer queryCustomers(int id) {
        try {
            String query = "Select * from optimisticOfflineLock  where id = ?";
            return (Customer) jdbcTemplate.queryForObject(query, new Object[]{id}, new BeanPropertyRowMapper(Customer.class));
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void getCustomers() {

    }

    private List<Customer> getAllCustomers() {
        try {
            String query = "Select * from optimisticOfflineLock";
            return jdbcTemplate.queryForList(query, Customer.class);
        } catch (DataAccessException e) {
            return null;
        }
    }


    public int updateRecords(Customer customer) {
        String query = "UPDATE optimisticOfflineLock SET name = ?, age = ?, " +
                " version = version +1 WHERE id = ? AND version = ? ";
        return jdbcTemplate.update(query, new Object[]{customer.getName(), customer.getAge(),
                customer.getId(), customer.getVersion()});
    }

}
