package pessimisticOfflineLock;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Created by cassandra on 6/15/14.
 */
public class LockRemove implements HttpSessionBindingListener {
    private final String sessionId;

    public LockRemove(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {

    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        try {
            beginSystemTransaction();
            ExclusiveReadLockManager.INSTANCE.releaseAllLocks(this.sessionId);
        } catch (Exception e) {
            handleSeriousErros(e);
        }
    }

    private void handleSeriousErros(Exception e) {

    }

    private void beginSystemTransaction() {

    }

}
