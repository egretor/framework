package unknown.website.manage.business;

import unknown.framework.business.database.AbstractTable;
import unknown.framework.module.database.Instance;
import unknown.website.ManageInstance;
import unknown.website.manage.module.AbstractManageTable;

/**
 * 管理模块表
 */
public abstract class AbstractTableBusiness<T extends AbstractManageTable> extends AbstractTable<T> {

	@Override
	public Instance getInstance() {
		return ManageInstance.getInstance();
	}
}
