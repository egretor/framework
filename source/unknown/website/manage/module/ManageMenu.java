package unknown.website.manage.module;

import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 菜单
 */
public class ManageMenu extends AbstractManage {
	/**
	 * 分类编号
	 */
	@Type(value = Types.UUID)
	private String catalogId;

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
}
