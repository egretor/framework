package unknown.website.manage.module;

/**
 * Action
 */
public class ManageAction extends AbstractManageTable {
	private String name;
	private boolean remove;

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
	 * 移除
	 * 
	 * @return 移除
	 */
	public boolean fgetRemove() {
		return remove;
	}

	public void fsetRemove(boolean remove) {
		this.remove = remove;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}
}
