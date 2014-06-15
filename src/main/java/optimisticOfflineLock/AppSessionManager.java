package optimisticOfflineLock;

/**
 * Created by cassandra on 6/14/14.
 */
public class AppSessionManager {
    private static Session session;

    public static Session getSession() {
        return session;
    }
}
