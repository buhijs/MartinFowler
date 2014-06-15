package pessimisticOfflineLock;

import org.springframework.dao.ConcurrencyFailureException;

/**
 * Created by cassandra on 6/15/14.
 */
public class ExclusiveReadLockManagerDBImpl implements ExclusiveReadLockManager {

    private static final String INSERT_SQL = "insert into lock values (?,?) ";
    private static final String DELETE_SINGLE_SQL = "delete from lock where lockableid = ? and " +
            "ownerid = ?";
    private static final String DELETE_ALL_SQL = "delete from lock where ownerid = ?";
    private static final String CHECK_SQL = "select locableid from lock where lockableid = > and " +
            "ownereid = ?";


    @Override
    public void acquireLock(Long lockable, String owner) throws ConcurrencyFailureException {

    }

    @Override
    public void releaseLock(Long lockable, String owner) {

    }

    @Override
    public void relaseAllLocks(String owner) {

    }
}
