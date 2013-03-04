package unknown.website.manage.application;

import java.util.List;

import unknown.website.manage.business.MaUserBusiness;
import unknown.website.manage.module.MaUser;

import com.opensymphony.xwork2.ActionSupport;

public class MaUserAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean show;

	private List<MaUser> maUsers;

	public String list() {
		
		MaUserBusiness bs = new MaUserBusiness();
		this.maUsers = bs.query();
		return ActionSupport.SUCCESS;
	}

	public List<MaUser> getMaUsers() {
		return maUsers;
	}

	public void setMaUsers(List<MaUser> maUsers) {
		this.maUsers = maUsers;
	}

	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}

}
