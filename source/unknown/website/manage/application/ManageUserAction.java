package unknown.website.manage.application;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import unknown.framework.business.database.Convention;
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
		Convention convention = new Convention();
		ManageUserBusiness bs = new ManageUserBusiness();

		Date now = new Date();
		ManageUser user = new ManageUser();
		user.fsetUuid(convention.Uuid());
		user.fsetInsertUserId(user.fgetUuid());
		user.fsetInsertTime(now);
		user.fsetUpdateUserId(user.fgetUuid());
		user.fsetUpdateTime(now);
		user.fsetRemark("test");

		user.fsetName("root");
		user.fsetAccount(user.fgetUuid());
		user.fsetPassword("password");
		user.fsetPrerogative(true);

		 bs.insert(user);

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
