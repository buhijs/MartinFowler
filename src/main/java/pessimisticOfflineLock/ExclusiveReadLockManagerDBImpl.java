package pessimisticOfflineLock;

import org.springframework.dao.ConcurrencyFailureException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
        if (!hashLock(lockable, owner)) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            try {
                connection = ConnectionManager.INSTANCE.getConnection("urlString");
                preparedStatement = connection.prepareStatement(INSERT_SQL);
                preparedStatement.setLong(1, lockable.longValue());
                preparedStatement.setString(2, owner);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new ConcurrencyFailureException("unable to lock" + lockable);
            } finally {
                closeDbResources(connection, preparedStatement);
            }
        }
    }

    @Override
    public void releaseLock(Long lockable, String owner) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionManager.INSTANCE.getConnection("urlString");
            preparedStatement = connection.prepareStatement(DELETE_SINGLE_SQL);
            preparedStatement.setLong(1, lockable.longValue());
            preparedStatement.setString(2, owner);
            preparedStatement.executeUpdate();
        } catch (SQLException sql) {
            throw new RuntimeException(sql);
        } finally {
            closeDbResources(connection, preparedStatement);
        }
    }

    private void closeDbResources(Connection connection, PreparedStatement preparedStatement) {

    }

    private boolean hashLock(Long lockable, String owner) {
        return false;
    }


    @Override
    public void releaseAllLocks(String owner) {

    }

}
