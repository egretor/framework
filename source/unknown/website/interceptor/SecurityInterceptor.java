package unknown.website.interceptor;

import unknown.website.manage.application.ManageUserAction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SecurityInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		String result = ActionSupport.LOGIN;

		actionInvocation.getInvocationContext().getSession();
		System.out.println(actionInvocation.getProxy().getMethod());

		Object action = actionInvocation.getAction();
		ManageUserAction aa = (ManageUserAction) action;
		boolean hasAction = false;
		if (action != null) {
			hasAction = true;
			aa.setAllow(false);
		}
		if (hasAction) {
			aa.setAllow(true);
			return actionInvocation.invoke();
		} else {
			return result;
		}
	}

}
