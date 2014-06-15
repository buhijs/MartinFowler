package pessimisticOfflineLock;

/**
 * Created by cassandra on 6/15/14.
 */
public class AppSessionManager {
    private static ThreadLocal current = new ThreadLocal();

    public static AppSession getSession() {
        return (AppSession) current.get();
    }

    public static void setSession(AppSession session) {
        current.set(session);

    }
}
