package unknown.website.manage.module;

import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 用户
 */
public class ManageUser extends AbstractManage {
	/**
	 * 帐号
	 */
	@Type(value = Types.STRING)
	private String account;
	/**
	 * 密码
	 */
	@Type(value = Types.STRING)
	private String password;
	/**
	 * 特权
	 */
	@Type(value = Types.BOOLEAN)
	private boolean prerogative;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPrerogative() {
		return prerogative;
	}

	public void setPrerogative(boolean prerogative) {
		this.prerogative = prerogative;
	}
}
