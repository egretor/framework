package unknown.website.manage.application;

import java.util.List;

import unknown.website.manage.business.ManageUserBusiness;
import unknown.website.manage.module.ManageUser;

import com.opensymphony.xwork2.ActionSupport;

public class ManageUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean show;

	private List<ManageUser> manageUsers;

	public String list() {

		ManageUserBusiness bs = new ManageUserBusiness();
		this.manageUsers = bs.query();
		return ActionSupport.SUCCESS;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

	public List<ManageUser> getManageUsers() {
		return manageUsers;
	}

	public void setManageUsers(List<ManageUser> manageUsers) {
		this.manageUsers = manageUsers;
	}

}
