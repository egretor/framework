package unknown.website.manage.module;

import unknown.framework.module.annotation.Remark;
import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * Action
 */
public class ManageAction extends AbstractManageTable {
	@Type(value = Types.Boolean)
	@Remark(value = "移除")
	private boolean remove;

	/**
	 * 移除
	 * 
	 * @return 移除
	 */
	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}
}
