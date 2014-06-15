package pessimisticOfflineLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by cassandra on 6/15/14.
 */
class BusinessTransactionCommand implements Command {

    private static final String APP_SESSION = "app_session";
    private static final String LOCK_REMOVE = "lock_remove";
    private HttpServletResponse httpServletResponse;
    private HttpServletRequest httpservletRequest;

    @Override
    public void init(HttpServletRequest httpservletRequest, HttpServletResponse httpServletResponse) {
        this.httpservletRequest = httpservletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public void process() throws Exception {

    }

    protected void startNewBusinessTransaction() {
        HttpSession httpSession = getHttpservletRequest().getSession();
        AppSession appSession = (AppSession) httpSession.getAttribute(APP_SESSION);
        AppSessionManager.setAttribute(appSession);
        httpSession.setAttribute(APP_SESSION, appSession);
        httpSession.setAttribute(LOCK_REMOVE, new LockRemove(
                appSession.getId()));
    }

    protected void continueBusinessTransaction() {
        HttpSession httpSession = getHttpservletRequest().getSession();
        AppSession appSession = (AppSession) httpSession.getAttribute(APP_SESSION);
        AppSessionManager.setSession(appSession);
    }

    public HttpServletRequest getHttpservletRequest() {
        return httpservletRequest;
    }

    public void setHttpservletRequest(HttpServletRequest httpservletRequest) {
        this.httpservletRequest = httpservletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }
}
