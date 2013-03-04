package unknown.website.manage.module;

/**
 * Url
 */
public class ManageUrl extends AbstractManageTable {
	private String menuId;
	private String name;
	private String value;

	/**
	 * 菜单编号
	 * 
	 * @return 菜单编号
	 */
	public String fgetMenuId() {
		return menuId;
	}

	public void fsetMenuId(String menuId) {
		this.menuId = menuId;
	}

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
	 * 值
	 * 
	 * @return 值
	 */
	public String fgetValue() {
		return value;
	}

	public void fsetValue(String value) {
		this.value = value;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
