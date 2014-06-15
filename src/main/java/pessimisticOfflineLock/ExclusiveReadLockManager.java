package pessimisticOfflineLock;

import org.springframework.dao.ConcurrencyFailureException;

/**
 * Created by cassandra on 6/15/14.
 */
public interface ExclusiveReadLockManager {

    public static final ExclusiveReadLockManager INSTANCE = (ExclusiveReadLockManager)
            Plugins.getPlugin(ExclusiveReadLockManager.class);

    public void acquireLock(Long lockable, String owner) throws ConcurrencyFailureException;

    public void releaseLock(Long lockable, String owner);

    public void releaseAllLocks(String owner);
}
