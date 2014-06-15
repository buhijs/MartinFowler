package pessimisticOfflineLock;

import optimisticOfflineLock.IdentityMap;

/**
 * Created by cassandra on 6/15/14.
 */
public class AppSession {
    private String user;
    private String id;
    private IdentityMap identityMap;

    public AppSession(String user, String id, IdentityMap identityMap) {
        this.user = user;
        this.id = id;
        this.identityMap = identityMap;
    }
}
