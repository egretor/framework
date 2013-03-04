package unknown.website.manage.module;

/**
 * 用户
 */
public class ManageUser extends AbstractManageTable {
	private String name;
	private String account;
	private String password;
	private boolean prerogative;

	/**
	 * 名称
	 * 
	 * @return 名称
	 */
	public String fgetName() {
		return name;
	}

	public void fsetName(String name) {
		this.name = name;
	}

	/**
	 * 帐号
	 * 
	 * @return 帐号
	 */
	public String fgetAccount() {
		return account;
	}

	public void fsetAccount(String account) {
		this.account = account;
	}

	/**
	 * 密码
	 * 
	 * @return 密码
	 */
	public String fgetPassword() {
		return password;
	}

	public void fsetPassword(String password) {
		this.password = password;
	}

	/**
	 * 特权
	 * 
	 * @return 特权
	 */
	public boolean fgetPrerogative() {
		return prerogative;
	}

	public void fsetPrerogative(boolean prerogative) {
		this.prerogative = prerogative;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
