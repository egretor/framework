package unknown.website.manage.business;

import unknown.framework.business.database.AbstractView;
import unknown.framework.module.database.Instance;
import unknown.website.ManageInstance;
import unknown.website.manage.module.AbstractManageView;

/**
 * 管理模块视图
 */
public abstract class AbstractViewBusiness<T extends AbstractManageView> extends AbstractView<T> {
	@Override
	public Instance getInstance() {
		return ManageInstance.getInstance();
	}
}
