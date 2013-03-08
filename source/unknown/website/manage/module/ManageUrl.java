package unknown.website.manage.module;

import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * Url
 */
public class ManageUrl extends AbstractManage {
	/**
	 * 菜单编号
	 */
	@Type(value = Types.UUID)
	private String menuId;
	/**
	 * 值
	 */
	@Type(value = Types.STRING)
	private String value;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
