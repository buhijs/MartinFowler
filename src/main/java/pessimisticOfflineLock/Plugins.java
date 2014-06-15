package pessimisticOfflineLock;

/**
 * Created by cassandra on 6/15/14.
 */
public class Plugins {
    public static ExclusiveReadLockManager getPlugin(Class<ExclusiveReadLockManager>
                                                             exclusiveReadLockManagerClass) {
        return null;
    }
}
