package pessimisticOfflineLock;

import optimisticOfflineLock.Customer;

/**
 * Created by cassandra on 6/16/14.
 */
public class EditCustomerCommand extends BusinessTransactionCommand {
    @Override
    public void process() throws Exception {
        startNewBusinessTransaction();
        Long customerId = new Long(getHttpservletRequest().getParameter("customer_id"));
        ExclusiveReadLockManager.INSTANCE.acquireLock(customerId, AppSessionManager.getSession()
                .getId());
        Mapper customerMapper = MapperRegistry.INSTANCE.getMapper(Customer.class);
        Customer customer = (Customer) customerMapper.find(customerId);
        getHttpservletRequest().getSession().setAttribute("customer", customer);
        forward("/editCustomer.jsp");
    }

    private void forward(String s) {

    }
}
