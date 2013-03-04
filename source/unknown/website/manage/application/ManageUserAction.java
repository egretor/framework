package unknown.website.manage.application;

import java.util.List;

import unknown.website.AbstractAction;
import unknown.website.manage.business.ManageUserBusiness;
import unknown.website.manage.module.ManageUser;

import com.opensymphony.xwork2.ActionSupport;

public class ManageUserAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<ManageUser> manageUsers;

	public String list() {

		ManageUserBusiness bs = new ManageUserBusiness();
		this.manageUsers = bs.query();
		return ActionSupport.SUCCESS;
	}

	public List<ManageUser> getManageUsers() {
		return manageUsers;
	}

	public void setManageUsers(List<ManageUser> manageUsers) {
		this.manageUsers = manageUsers;
	}

}
