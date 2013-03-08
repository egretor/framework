package unknown.website.manage.module;

import unknown.framework.module.annotation.Type;
import unknown.framework.module.annotation.Types;

/**
 * Action
 */
public class ManageAction extends AbstractManage {
	/**
	 * 移除
	 */
	@Type(value = Types.BOOLEAN)
	private boolean remove;

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}
}
