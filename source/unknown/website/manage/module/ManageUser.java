package unknown.website.manage.module;

import unknown.framework.module.annotation.Remark;
import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 用户
 */
public class ManageUser extends AbstractManageTable {
	@Type(value = Types.String)
	@Remark(value = "帐号")
	private String account;
	@Type(value = Types.String)
	@Remark(value = "密码")
	private String password;
	@Type(value = Types.Boolean)
	@Remark(value = "特权")
	private boolean prerogative;

	/**
	 * 帐号
	 * 
	 * @return 帐号
	 */
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 密码
	 * 
	 * @return 密码
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 特权
	 * 
	 * @return 特权
	 */
	public boolean isPrerogative() {
		return prerogative;
	}

	public void setPrerogative(boolean prerogative) {
		this.prerogative = prerogative;
	}
}
