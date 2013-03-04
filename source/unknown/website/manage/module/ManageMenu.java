package unknown.website.manage.module;

/**
 * 菜单
 */
public class ManageMenu extends AbstractManageTable {
	private String catalogId;
	private String name;

	/**
	 * 分类编号
	 * 
	 * @return 分类编号
	 */
	public String fgetCatalogId() {
		return catalogId;
	}

	public void fsetCatalogId(String catalogId) {
		this.catalogId = catalogId;
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

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
