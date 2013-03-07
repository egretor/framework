package unknown.framework.business.database;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import unknown.framework.module.database.AbstractSqlBuilder;
import unknown.framework.module.database.OperationTypes;
import unknown.framework.module.database.Result;
import unknown.framework.module.database.Operation;
import unknown.framework.module.pojo.AbstractDatabasePojo;
import unknown.framework.module.pojo.FieldMap;
import unknown.framework.module.pojo.TableMap;
import unknown.framework.module.pojo.TablePojo;
import unknown.framework.utility.Trace;

/**
 * 表
 */
public abstract class AbstractTable<T extends TablePojo> extends
		AbstractView<T> {
	/**
	 * 唯一值
	 * 
	 * @param value
	 *            实体对象
	 * @return 实体对象集合
	 */
	public abstract List<T> Unique(T value);

	/**
	 * 删除引用数据
	 * 
	 * @param value
	 *            实体对象
	 * @return 操作结果
	 */
	public abstract boolean deleteReference(T value);

	/**
	 * 是否存在引用数据
	 * 
	 * @param value
	 *            实体对象
	 * @return 结果
	 */
	public abstract boolean hasReference(T value);

	/**
	 * 是否存在相同数据
	 * 
	 * @param value
	 *            实体对象
	 * @return 结果
	 */
	public boolean hasSame(T value) {
		boolean result = false;

		if (value != null) {
			List<T> uniqueValues = this.Unique(value);
			if (uniqueValues != null) {
				if (uniqueValues.size() > 1) {
					result = true;
				} else {
					T uniqueValue = uniqueValues.get(0);
					String uuid = value.getUuid();
					String uniqueUuid = uniqueValue.getUuid();
					if (uuid == null) {
						result = true;
					} else {
						if (!uniqueUuid.equals(uuid)) {
							result = true;
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * 保存
	 * 
	 * @param value
	 *            实体对象
	 * @return 操作结果
	 */
	public Results save(T value) {
		Results result = Results.Fail;

		if (value != null) {
			String uuid = value.getUuid();
			T pojo = this.query(uuid);
			if (pojo == null) {
				result = this.insert(value);
			} else {
				result = this.update(value);
			}
		}

		return result;
	}

	/**
	 * 增加
	 * 
	 * @param value
	 *            数据库实体
	 * @return 操作结果
	 */
	public Results insert(T value) {
		Results result = Results.Fail;

		if (value != null) {
			boolean has = this.hasSame(value);
			if (has) {
				result = Results.HasSame;
			} else {
				TableMap tableMap = this.getTableMap();
				List<FieldMap> fieldMaps = tableMap.getFields();

				if (fieldMaps != null) {
					if (fieldMaps.size() > 0) {
						List<Object> parameters = new ArrayList<Object>();
						for (int i = 0; i < fieldMaps.size(); i++) {
							FieldMap fieldMap = fieldMaps.get(i);
							Method method = fieldMap.getGetter();

							try {
								parameters.add(method.invoke(value));
							} catch (IllegalArgumentException e) {
								Trace.logger().error(e);
							} catch (IllegalAccessException e) {
								Trace.logger().error(e);
							} catch (InvocationTargetException e) {
								Trace.logger().error(e);
							}
						}

						AbstractSqlBuilder sqlBuilder = this.getInstance()
								.getSqlBuilder();
						String sql = sqlBuilder.insert(value, tableMap);

						Operation task = new Operation();
						task.setOperationType(OperationTypes.Write);
						task.setSql(sql);
						task.setParameters(parameters);

						Result taskResult = this.access(task);
						if (taskResult != null) {
							if (taskResult.isDone()) {
								result = Results.Success;
							}
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * 修改
	 * 
	 * @param value
	 *            数据库实体
	 * @return 操作结果
	 */
	public Results update(T value) {
		Results result = Results.Fail;

		if (value != null) {
			boolean has = this.hasSame(value);
			if (has) {
				result = Results.HasSame;
			} else {
				String uuidField = null;
				if (this.getInstance().isIdentifierCapital()) {
					uuidField = AbstractDatabasePojo.UUID.toUpperCase();
				} else {
					uuidField = AbstractDatabasePojo.UUID.toLowerCase();
				}
				TableMap tableMap = this.getTableMap();
				List<FieldMap> fieldMaps = tableMap.getOthers();

				if (fieldMaps != null) {
					if (fieldMaps.size() > 0) {
						List<Object> parameters = new ArrayList<Object>();
						for (int i = 0; i < fieldMaps.size(); i++) {
							FieldMap fieldMap = fieldMaps.get(i);
							Method method = fieldMap.getGetter();

							try {
								parameters.add(method.invoke(value));
							} catch (IllegalArgumentException e) {
								Trace.logger().error(e);
							} catch (IllegalAccessException e) {
								Trace.logger().error(e);
							} catch (InvocationTargetException e) {
								Trace.logger().error(e);
							}
						}
						parameters.add(value.getUuid());

						AbstractSqlBuilder sqlBuilder = this.getInstance()
								.getSqlBuilder();
						String sql = sqlBuilder.update(value, uuidField, tableMap);

						Operation task = new Operation();
						task.setOperationType(OperationTypes.Write);
						task.setSql(sql);
						task.setParameters(parameters);

						Result taskResult = this.access(task);
						if (taskResult != null) {
							if (taskResult.isDone()) {
								result = Results.Success;
							}
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * 删除
	 * 
	 * @param value
	 *            数据库实体
	 * @return 操作结果
	 */
	public Results delete(T value) {
		Results result = Results.Fail;

		result = this.delete(value, false);

		return result;
	}

	/**
	 * 删除
	 * 
	 * @param value
	 *            数据库实体
	 * @param cascade
	 *            级联
	 * @return 操作结果
	 */
	public Results delete(T value, boolean cascade) {
		Results result = Results.Fail;

		if (value != null) {
			boolean done = true;
			if (cascade) {
				done = this.deleteReference(value);
			}
			if (done) {
				boolean has = this.hasReference(value);
				if (has) {
					result = Results.HasReference;
				} else {
					String uuidField = null;
					if (this.getInstance().isIdentifierCapital()) {
						uuidField = AbstractDatabasePojo.UUID.toUpperCase();
					} else {
						uuidField = AbstractDatabasePojo.UUID.toLowerCase();
					}
					TableMap tableMap = this.getTableMap();
					String tableName = tableMap.getName();

					AbstractSqlBuilder sqlBuilder = this.getInstance()
							.getSqlBuilder();

					String sql = sqlBuilder.delete(tableName, uuidField);
					List<Object> parameters = new ArrayList<Object>();
					parameters.add(value.getUuid());

					Operation task = new Operation();
					task.setOperationType(OperationTypes.Write);
					task.setSql(sql);
					task.setParameters(parameters);

					Result taskResult = this.access(task);
					if (taskResult != null) {
						if (taskResult.isDone()) {
							result = Results.Success;
						}
					}
				}
			}
		}

		return result;
	}
}
