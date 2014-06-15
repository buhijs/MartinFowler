package pessimisticOfflineLock;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by cassandra on 6/16/14.
 */
public class ControllerServlet extends HttpServlet {

    private Map commads;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String cmdName = req.getParameter("command");
            Command cmd = getCommand(cmdName);
            cmd.init(req, resp);
            cmd.process();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Command getCommand(String cmdName) {
        try {
            String className = (String) commads.get(cmdName);
            Command command = (Command) Class.forName(className).newInstance();
            return new TransactionalCommand(command);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
