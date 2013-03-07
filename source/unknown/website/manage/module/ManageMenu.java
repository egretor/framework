package unknown.website.manage.module;

import unknown.framework.module.annotation.Remark;
import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * 菜单
 */
public class ManageMenu extends AbstractManageTable {
	@Type(value = Types.Uuid)
	@Remark(value = "分类编号")
	private String catalogId;

	/**
	 * 分类编号
	 * 
	 * @return 分类编号
	 */
	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
}
