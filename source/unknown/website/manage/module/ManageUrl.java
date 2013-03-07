package unknown.website.manage.module;

import unknown.framework.module.annotation.Remark;
import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * Url
 */
public class ManageUrl extends AbstractManageTable {
	@Type(value = Types.Uuid)
	@Remark(value = "菜单编号")
	private String menuId;
	@Type(value = Types.String)
	@Remark(value = "值")
	private String value;

	/**
	 * 菜单编号
	 * 
	 * @return 菜单编号
	 */
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 值
	 * 
	 * @return 值
	 */
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
