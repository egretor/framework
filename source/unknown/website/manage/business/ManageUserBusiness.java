package unknown.website.manage.business;

import java.util.ArrayList;
import java.util.List;

import unknown.framework.module.database.Operation;
import unknown.framework.module.database.OperationTypes;
import unknown.framework.module.database.Result;
import unknown.website.ManageInstance;
import unknown.website.manage.module.ManageUser;

public class ManageUserBusiness extends AbstractTableBusiness<ManageUser> {
	public final static String QUERY_BY_ACCOUNT = String.format(
			"QueryByAccount@%s", ManageUserBusiness.class.getName());

	@Override
	public List<ManageUser> Unique(ManageUser value) {
		List<ManageUser> results = null;

		String sql = ManageInstance.getInstance().getSql(
				ManageUserBusiness.QUERY_BY_ACCOUNT);
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(value.getAccount());

		Operation operation = new Operation();
		operation.setOperationType(OperationTypes.Read);
		operation.setSql(sql);
		operation.setParameters(parameters);
		operation.setPaging(null);
		Result result = this.access(operation);
		if (result != null) {
			if (result.isDone()) {
				results = this.Parse(result.getTable());
			}
		}

		return results;
	}

	@Override
	public boolean deleteReference(ManageUser value) {
		return false;
	}

	@Override
	public boolean hasReference(ManageUser value) {
		return false;
	}

	@Override
	public ManageUser initializePojo() {
		return new ManageUser();
	}
}
