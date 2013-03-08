package unknown.framework.business.database;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import unknown.framework.module.database.AbstractSqlBuilder;
import unknown.framework.module.database.FieldMap;
import unknown.framework.module.database.OperationTypes;
import unknown.framework.module.database.Result;
import unknown.framework.module.database.Operation;
import unknown.framework.module.database.TableMap;
import unknown.framework.module.pojo.AbstractDatabasePojo;
import unknown.framework.utility.Trace;

/**
 * 表类
 * 
 * @param <T>
 *            数据库实体
 */
public abstract class AbstractTable<T extends AbstractDatabasePojo> extends
		AbstractView<T> {
	/**
	 * 唯一值
	 * 
	 * @param value
	 *            实体对象
	 * @return 实体对象集合
	 */
	public abstract List<T> queryUnique(T value);

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
			List<T> uniqueValues = this.queryUnique(value);
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
	public Message save(T value) {
		Message result = new Message();
		result.setType(MessageTypes.FAIL);

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
	public Message insert(T value) {
		Message result = new Message();
		result.setType(MessageTypes.FAIL);

		if (value != null) {
			boolean has = this.hasSame(value);
			if (has) {
				result.setType(MessageTypes.HAS_SAME);
			} else {
				Operation operation = new Operation();

				AbstractSqlBuilder sqlBuilder = this.getInstance()
						.getSqlBuilder();
				TableMap tableMap = this.getTableMap();
				List<FieldMap> fieldMaps = tableMap.getFieldMaps();

				String sql = sqlBuilder.getInsertSql(value, tableMap);
				List<Object> parameters = new ArrayList<Object>();
				for (int i = 0; i < fieldMaps.size(); i++) {
					FieldMap fieldMap = fieldMaps.get(i);
					Method method = fieldMap.getGetter();

					try {
						parameters.add(method.invoke(value));
					} catch (IllegalArgumentException e) {
						Trace.getFramework().error(e);
					} catch (IllegalAccessException e) {
						Trace.getFramework().error(e);
					} catch (InvocationTargetException e) {
						Trace.getFramework().error(e);
					}
				}

				operation.setType(OperationTypes.WRITE);
				operation.setSql(sql);
				operation.setParameters(parameters);

				Result taskResult = this.access(operation);
				if (taskResult != null) {
					if (taskResult.isDone()) {
						result.setType(MessageTypes.SUCCESS);
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
	public Message update(T value) {
		Message result = new Message();
		result.setType(MessageTypes.FAIL);

		if (value != null) {
			boolean has = this.hasSame(value);
			if (has) {
				result.setType(MessageTypes.HAS_SAME);
			} else {
				Operation operation = new Operation();

				AbstractSqlBuilder sqlBuilder = this.getInstance()
						.getSqlBuilder();
				TableMap tableMap = this.getTableMap();
				List<FieldMap> fieldMaps = tableMap.getOthers();
				String uuidName = null;
				if (this.getInstance().isIdentifierCapital()) {
					uuidName = AbstractDatabasePojo.UUID.toUpperCase();
				} else {
					uuidName = AbstractDatabasePojo.UUID.toLowerCase();
				}

				String sql = sqlBuilder
						.getUpdateSql(value, uuidName, tableMap);
				List<Object> parameters = new ArrayList<Object>();
				for (int i = 0; i < fieldMaps.size(); i++) {
					FieldMap fieldMap = fieldMaps.get(i);
					Method method = fieldMap.getGetter();

					try {
						parameters.add(method.invoke(value));
					} catch (IllegalArgumentException e) {
						Trace.getFramework().error(e);
					} catch (IllegalAccessException e) {
						Trace.getFramework().error(e);
					} catch (InvocationTargetException e) {
						Trace.getFramework().error(e);
					}
				}
				parameters.add(value.getUuid());

				operation.setType(OperationTypes.WRITE);
				operation.setSql(sql);
				operation.setParameters(parameters);

				Result taskResult = this.access(operation);
				if (taskResult != null) {
					if (taskResult.isDone()) {
						result.setType(MessageTypes.SUCCESS);
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
	public Message delete(T value) {
		Message result = new Message();

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
	public Message delete(T value, boolean cascade) {
		Message result = new Message();
		result.setType(MessageTypes.FAIL);

		if (value != null) {
			boolean done = true;
			if (cascade) {
				done = this.deleteReference(value);
			}
			if (done) {
				boolean has = this.hasReference(value);
				if (has) {
					result.setType(MessageTypes.HAS_REFERENCE);
				} else {
					AbstractSqlBuilder sqlBuilder = this.getInstance()
							.getSqlBuilder();
					TableMap tableMap = this.getTableMap();
					String tableName = tableMap.getName();
					String uuidName = null;
					if (this.getInstance().isIdentifierCapital()) {
						uuidName = AbstractDatabasePojo.UUID.toUpperCase();
					} else {
						uuidName = AbstractDatabasePojo.UUID.toLowerCase();
					}

					String sql = sqlBuilder.getDeleteSql(tableName, uuidName);
					List<Object> parameters = new ArrayList<Object>();
					parameters.add(value.getUuid());

					Operation task = new Operation();
					task.setType(OperationTypes.WRITE);
					task.setSql(sql);
					task.setParameters(parameters);

					Result taskResult = this.access(task);
					if (taskResult != null) {
						if (taskResult.isDone()) {
							result.setType(MessageTypes.SUCCESS);
						}
					}
				}
			}
		}

		return result;
	}
}
