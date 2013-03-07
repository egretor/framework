package unknown.framework.business.database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import unknown.framework.module.annotation.Remark;
import unknown.framework.module.annotation.Type;
import unknown.framework.module.database.AbstractSqlBuilder;
import unknown.framework.module.database.AbstractTypeConverter;
import unknown.framework.module.database.OperationTypes;
import unknown.framework.module.database.Paging;
import unknown.framework.module.database.Result;
import unknown.framework.module.database.Row;
import unknown.framework.module.database.Table;
import unknown.framework.module.database.Operation;
import unknown.framework.module.pojo.AbstractDatabasePojo;
import unknown.framework.module.pojo.FieldMap;
import unknown.framework.module.pojo.TableMap;
import unknown.framework.module.pojo.ViewPojo;
import unknown.framework.utility.Trace;

/**
 * 视图
 * 
 * @param <T>
 *            数据库视图实体
 */
public abstract class AbstractView<T extends ViewPojo> extends AbstractDatabase {

	protected final static Map<String, TableMap> tableMapCache = new HashMap<String, TableMap>();

	/**
	 * 
	 * @return 实体对象
	 */
	public abstract T initializePojo();

	/**
	 * UUID
	 * 
	 * @return UUID
	 */
	public String getUuid() {
		String result = null;

		String uuid = UUID.randomUUID().toString().toUpperCase();
		StringBuilder stringBuilder = new StringBuilder();
		if (uuid != null) {
			if (!uuid.isEmpty()) {
				for (int i = 0; i < uuid.length(); i++) {
					char currentChar = uuid.charAt(i);
					if (((currentChar >= 'A') && (currentChar <= 'Z'))
							|| ((currentChar >= '0') && (currentChar <= '9'))) {
						stringBuilder.append(currentChar);
					}
				}
			}
		}
		result = stringBuilder.toString();

		return result;
	}

	/**
	 * 表映射
	 * 
	 * @return 表映射
	 */
	public TableMap getTableMap() {
		TableMap result = null;

		Convention convention = new Convention();
		boolean identifierCapital = this.getInstance().isIdentifierCapital();
		T value = this.initializePojo();
		Class<?> classType = value.getClass();
		String key = classType.getName();
		if (AbstractView.tableMapCache.containsKey(key)) {
			result = AbstractView.tableMapCache.get(key);
		} else {
			String name = convention.decodeName(classType.getSimpleName(),
					identifierCapital);
			FieldMap major = new FieldMap();
			List<FieldMap> others = new ArrayList<FieldMap>();
			List<FieldMap> fields = new ArrayList<FieldMap>();

			List<Field> allFields = new ArrayList<Field>();

			Class<?> supperclassType = classType;
			do {
				Field[] declaredFields = supperclassType.getDeclaredFields();
				if (declaredFields != null) {
					for (int i = 0; i < declaredFields.length; i++) {
						allFields.add(declaredFields[i]);
					}
				}
				supperclassType = supperclassType.getSuperclass();
			} while (!supperclassType.equals(Object.class));

			for (int i = 0; i < allFields.size(); i++) {
				Field field = allFields.get(i);
				Type type = field.getAnnotation(Type.class);
				Remark remark = field.getAnnotation(Remark.class);
				if (type != null) {
					String fieldName = field.getName();
					String filedMapName = convention.decodeName(fieldName,
							identifierCapital);
					String getterName = null;
					String setterName = null;
					Method getter = null;
					Method setter = null;
					Class<?> parameterType = field.getType();

					switch (type.value()) {
					case Boolean:
						getterName = convention
								.encodeBooleanGetMethodName(fieldName);
						setterName = convention
								.encodeBooleanSetMethodName(fieldName);
						break;
					default:
						getterName = convention.encodeGetMethodName(fieldName);
						setterName = convention.encodeSetMethodName(fieldName);
						break;
					}

					try {
						getter = classType.getMethod(getterName);
						setter = classType.getMethod(setterName, parameterType);
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}

					FieldMap fieldMap = new FieldMap();
					fieldMap.setName(filedMapName);
					fieldMap.setType(type.value());
					fieldMap.setRemark(remark.value());
					fieldMap.setGetter(getter);
					fieldMap.setSetter(setter);

					if (type.major()) {
						major = fieldMap;
					} else {
						others.add(fieldMap);
					}
					fields.add(fieldMap);
				}
			}

			String sql = this.getInstance().getSql(key);
			if (sql != null) {
				if (!sql.isEmpty()) {
					name = sql;
				}
			}

			result = new TableMap();
			result.setName(name);
			result.setMajor(major);
			result.setOthers(others);
			result.setFields(fields);
			AbstractView.tableMapCache.put(key, result);
		}

		return result;
	}

	/**
	 * 数据转换
	 * 
	 * @param fields
	 *            表字段集合
	 * @param row
	 *            行数据
	 * @return 实体对象
	 */
	protected T Parse(List<String> fields, Row row) {
		T result = this.initializePojo();

		AbstractTypeConverter typeConverter = this.getInstance()
				.getTypeConverter();

		List<Object> values = row.getValues();

		TableMap tableMap = this.getTableMap();
		List<FieldMap> fieldMaps = tableMap.getFields();

		if (fieldMaps != null) {
			for (int j = 0; j < fields.size(); j++) {
				Object column = values.get(j);
				String field = fields.get(j);
				for (int k = 0; k < fieldMaps.size(); k++) {
					FieldMap fieldMap = fieldMaps.get(k);
					if (field.equalsIgnoreCase(fieldMap.getName())) {
						try {
							Method method = fieldMap.getSetter();
							Object value = typeConverter.java(fieldMap, column);
							method.invoke(result, value);
						} catch (IllegalArgumentException e) {
							Trace.logger().error(e);
						} catch (IllegalAccessException e) {
							Trace.logger().error(e);
						} catch (InvocationTargetException e) {
							Trace.logger().error(e);
						}
						break;
					}
				}
			}
		}

		return result;
	}

	/**
	 * 数据转换
	 * 
	 * @param table
	 *            表数据
	 * @return 实体对象集合
	 */
	protected List<T> Parse(Table table) {
		List<T> results = null;

		if (table != null) {
			List<String> fields = table.getFields();
			List<Row> rows = table.getRows();
			if ((fields != null) && (fields.size() > 0)) {
				if (rows != null) {
					results = new ArrayList<T>();
					for (int i = 0; i < rows.size(); i++) {
						Row row = rows.get(i);
						T currentValue = this.Parse(fields, row);
						results.add(currentValue);
					}
				}
			}
		}

		return results;
	}

	/**
	 * 查询
	 * 
	 * @return 所有实体对象集合
	 */
	public List<T> query() {
		List<T> results = null;

		Paging paging = null;
		results = this.query(paging);

		return results;
	}

	/**
	 * 分页查询
	 * 
	 * @param paging
	 *            分页
	 * @return 实体对象集合
	 */
	public List<T> query(Paging paging) {
		List<T> results = null;

		T value = this.initializePojo();
		if (value != null) {
			TableMap tableMap = this.getTableMap();
			String tableName = tableMap.getName();

			AbstractSqlBuilder sqlBuilder = this.getInstance().getSqlBuilder();
			String sql = sqlBuilder.query(tableName);

			List<Object> parameters = new ArrayList<Object>();

			Operation operation = new Operation();
			operation.setOperationType(OperationTypes.Read);
			operation.setSql(sql);
			operation.setParameters(parameters);
			operation.setPaging(paging);

			Result operationResult = this.access(operation);
			if (operationResult != null) {
				if (operationResult.isDone()) {
					Table table = operationResult.getTable();
					results = this.Parse(table);
				}
			}
		}

		return results;
	}

	/**
	 * 根据代理主键查询
	 * 
	 * @param uuid
	 *            代理主键
	 * @return 实体对象
	 */
	public T query(String uuid) {
		T result = null;

		T value = this.initializePojo();
		if (value != null) {
			String uuidField = null;
			if (this.getInstance().isIdentifierCapital()) {
				uuidField = AbstractDatabasePojo.UUID.toUpperCase();
			} else {
				uuidField = AbstractDatabasePojo.UUID.toLowerCase();
			}
			TableMap tableMap = this.getTableMap();
			String tableName = tableMap.getName();

			AbstractSqlBuilder sqlBuilder = this.getInstance().getSqlBuilder();
			String sql = sqlBuilder.queryByUuid(tableName, uuidField);
			List<Object> parameters = new ArrayList<Object>();
			parameters.add(uuid);

			Operation operation = new Operation();
			operation.setOperationType(OperationTypes.Read);
			operation.setSql(sql);
			operation.setParameters(parameters);
			operation.setPaging(null);

			Result operationResult = this.access(operation);
			if (operationResult != null) {
				if (operationResult.isDone()) {
					Table table = operationResult.getTable();
					List<T> values = this.Parse(table);
					if ((values != null) && (values.size() > 0)) {
						result = values.get(0);
					}
				}
			}
		}

		return result;
	}
}
