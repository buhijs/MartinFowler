package optimisticOfflineLock;

/**
 * Created by cassandra on 6/14/14.
 */
public class AbstractMapper {

    private final String table;
    private final String[] columns;

    public AbstractMapper(String tableName, String[] columns) {
        this.table = tableName;
        this.columns = columns;
        buildStatement();
    }

    public DomainObject find(long id) {
        DomainObject domainObject = AppSessionManager.getSession().getIdentityMap().get(id);

        return null;
    }

    private void buildStatement() {


    }


}
