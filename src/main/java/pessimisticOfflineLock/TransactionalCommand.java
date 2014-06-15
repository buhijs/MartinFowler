package pessimisticOfflineLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cassandra on 6/16/14.
 */
public class TransactionalCommand implements Command {

    private final Command command;

    public TransactionalCommand(Command command) {
        this.command = command;
    }

    @Override
    public void init(HttpServletRequest httpservletRequest, HttpServletResponse httpServletResponse) {

    }

    @Override
    public void process() throws Exception {
        beginSystemTransaction();

        try {
            command.process();
            commitSystemTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    private void commitSystemTransaction() {
    }

    private void beginSystemTransaction() {

    }
}
