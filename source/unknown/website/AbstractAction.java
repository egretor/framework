package unknown.website;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.PrincipalAware;
import org.apache.struts2.interceptor.PrincipalProxy;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import unknown.website.manage.module.ManageUser;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Action抽象类
 */
public abstract class AbstractAction extends ActionSupport implements
		ServletContextAware, ApplicationAware, SessionAware,
		ServletRequestAware, ServletResponseAware, RequestAware,
		ParameterAware, PrincipalAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 会话用户键
	 */
	private static final String SESSION_USER_KEY = "@SESSION_USER";

	private PrincipalProxy principalProxy;
	private ServletContext servletContext;
	private Map<String, Object> requests;
	private Map<String, String[]> parameters;
	private HttpServletRequest httpServletRequest;
	private HttpServletResponse httpServletResponse;
	private Map<String, Object> applications;
	private Map<String, Object> sessions;

	public PrincipalProxy getPrincipalProxy() {
		return this.principalProxy;
	}

	@Override
	public void setPrincipalProxy(PrincipalProxy principalProxy) {
		this.principalProxy = principalProxy;
	}

	public ServletContext getServletContext() {
		return this.servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public Map<String, Object> getRequest() {
		return this.requests;
	}

	@Override
	public void setRequest(Map<String, Object> requests) {
		this.requests = requests;
	}

	public Map<String, String[]> getParameters() {
		return this.parameters;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

	public HttpServletRequest getServletRequest() {
		return this.httpServletRequest;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	public HttpServletResponse getServletResponse() {
		return this.httpServletResponse;
	}

	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}

	public Map<String, Object> getApplication() {
		return this.applications;
	}

	@Override
	public void setApplication(Map<String, Object> applications) {
		this.applications = applications;
	}

	public Map<String, Object> getSession() {
		return this.sessions;
	}

	@Override
	public void setSession(Map<String, Object> sessions) {
		this.sessions = sessions;
	}

	/**
	 * 允许访问
	 */
	private boolean allow;

	public boolean isAllow() {
		return allow;
	}

	public void setAllow(boolean allow) {
		this.allow = allow;
	}

	public ManageUser getSessionUser() {
		ManageUser result = null;

		Map<String, Object> sessions = this.getSession();
		boolean hasSession = sessions
				.containsKey(AbstractAction.SESSION_USER_KEY);
		if (hasSession) {
			result = (ManageUser) sessions.get(SESSION_USER_KEY);
		}

		return result;
	}

	public void setSessionUser(ManageUser sessionUser) {
		Map<String, Object> sessions = this.getSession();
		sessions.put(SESSION_USER_KEY, sessionUser);
	}
}
