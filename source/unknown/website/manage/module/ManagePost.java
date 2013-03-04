package unknown.website.manage.module;

/**
 * 岗位
 */
public class ManagePost extends AbstractManageTable {
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
