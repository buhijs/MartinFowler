package pessimisticOfflineLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cassandra on 6/15/14.
 */
public interface Command {

    public void init(HttpServletRequest httpservletRequest, HttpServletResponse
            httpServletResponse);


    public void process() throws Exception;
}
