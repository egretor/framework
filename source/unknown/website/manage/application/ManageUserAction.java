package unknown.website.manage.application;

import java.util.Date;
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

	private String test;

	public String list() {
		ManageUserBusiness bs = new ManageUserBusiness();

		Date now = new Date();
		ManageUser user = bs.query("E2C773DE1E1B4554B524DAA66B20360B");
		/*
		 * user.setUuid(bs.getUuid()); user.setInsertUserId(user.getUuid());
		 * user.setInsertTime(now); user.setUpdateUserId(user.getUuid());
		 * user.setUpdateTime(now); user.setRemark("test");
		 * 
		 * user.setName("root"); user.setAccount(user.getUuid());
		 * user.setPassword("password"); user.setPrerogative(true);
		 * 
		 * //bs.insert(user);
		 */

		user.setAccount("testsdflsk");
		this.test = bs.update(user).toString();

		this.manageUsers = bs.query();
		return ActionSupport.SUCCESS;
	}

	public List<ManageUser> getManageUsers() {
		return manageUsers;
	}

	public void setManageUsers(List<ManageUser> manageUsers) {
		this.manageUsers = manageUsers;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

}
