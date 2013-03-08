package unknown.framework.business.database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import unknown.framework.module.annotation.Type;
import unknown.framework.module.database.AbstractSqlBuilder;
import unknown.framework.module.database.AbstractTypeConverter;
import unknown.framework.module.database.FieldMap;
import unknown.framework.module.database.OperationTypes;
import unknown.framework.module.database.Paging;
import unknown.framework.module.database.Result;
import unknown.framework.module.database.Row;
import unknown.framework.module.database.Table;
import unknown.framework.module.database.Operation;
import unknown.framework.module.database.TableMap;
import unknown.framework.module.pojo.AbstractDatabasePojo;
import unknown.framework.utility.Trace;

/**
 * 视图类
 * 
 * @param <T>
 *            数据库实体
 */
public abstract class AbstractView<T extends AbstractDatabasePojo> extends
		AbstractDatabase {
	/**
	 * 表映射集合
	 */
	protected final static Map<String, TableMap> TABLEMAP = new HashMap<String, TableMap>();

	/**
	 * 
	 * @return 实体对象
	 */
	public abstract T initializePojo();

	/**
	 * 获取UUID值
	 * 
	 * @return UUID值
	 */
	public String getUuid() {
		String result = null;

		String uuid = UUID.randomUUID().toString().toUpperCase();

		StringBuilder stringBuilder = new StringBuilder();

		if (uuid != null) {
			if (!uuid.isEmpty()) {
				for (int i = 0; i < uuid.length(); i++) {
					char currentChar = uuid.charAt(i);
					boolean isChar = (currentChar >= 'A')
							&& (currentChar <= 'Z');
					boolean isNumber = (currentChar >= '0')
							&& (currentChar <= '9');
					if (isChar || isNumber) {
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

		AbstractSqlBuilder sqlBuilder = this.getInstance().getSqlBuilder();
		Convention convention = new Convention();
		boolean identifierCapital = this.getInstance().isIdentifierCapital();
		T value = this.initializePojo();
		Class<?> classType = value.getClass();
		String key = classType.getName();

		if (AbstractView.TABLEMAP.containsKey(key)) {
			result = AbstractView.TABLEMAP.get(key);
		} else {
			result = new TableMap();

			String name = convention.decodeName(classType.getSimpleName(),
					identifierCapital);
			FieldMap major = new FieldMap();
			List<FieldMap> others = new ArrayList<FieldMap>();
			List<FieldMap> fieldMaps = new ArrayList<FieldMap>();

			String sql = sqlBuilder.getSql(key, key);
			if (sql != null) {
				if (!sql.isEmpty()) {
					name = sql;
				}
			}

			List<Field> fields = new ArrayList<Field>();
			Class<?> supperclassType = classType;
			do {
				Field[] declaredFields = supperclassType.getDeclaredFields();
				if (declaredFields != null) {
					for (int i = 0; i < declaredFields.length; i++) {
						fields.add(declaredFields[i]);
					}
				}
				supperclassType = supperclassType.getSuperclass();
			} while (!supperclassType.equals(Object.class));

			for (int i = 0; i < fields.size(); i++) {
				Field field = fields.get(i);
				Type type = field.getAnnotation(Type.class);
				if (type != null) {
					FieldMap fieldMap = new FieldMap();

					String fieldName = field.getName();
					Class<?> parameterType = field.getType();

					String filedMapName = convention.decodeName(fieldName,
							identifierCapital);
					String getterName = null;
					String setterName = null;
					Method getter = null;
					Method setter = null;

					switch (type.value()) {
					case BOOLEAN:
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
						Trace.getFramework().error(e);
					} catch (NoSuchMethodException e) {
						Trace.getFramework().error(e);
					}

					fieldMap.setName(filedMapName);
					fieldMap.setType(type.value());
					fieldMap.setGetter(getter);
					fieldMap.setSetter(setter);

					if (type.major()) {
						major = fieldMap;
					} else {
						others.add(fieldMap);
					}

					fieldMaps.add(fieldMap);
				}
			}

			result.setName(name);
			result.setMajor(major);
			result.setOthers(others);
			result.setFieldMaps(fieldMaps);

			AbstractView.TABLEMAP.put(key, result);
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
	protected T parse(List<String> fields, Row row) {
		T result = this.initializePojo();

		AbstractTypeConverter typeConverter = this.getInstance()
				.getTypeConverter();

		List<Object> values = row.getValues();

		TableMap tableMap = this.getTableMap();
		List<FieldMap> fieldMaps = tableMap.getFieldMaps();
		for (int i = 0; i < fields.size(); i++) {
			Object column = values.get(i);
			String field = fields.get(i);

			for (int j = 0; j < fieldMaps.size(); j++) {
				FieldMap fieldMap = fieldMaps.get(j);

				if (field.equalsIgnoreCase(fieldMap.getName())) {
					try {
						Method method = fieldMap.getSetter();
						Object value = typeConverter.getJava(fieldMap, column);
						method.invoke(result, value);
					} catch (IllegalArgumentException e) {
						Trace.getFramework().error(e);
					} catch (IllegalAccessException e) {
						Trace.getFramework().error(e);
					} catch (InvocationTargetException e) {
						Trace.getFramework().error(e);
					}
					break;
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
	protected List<T> parse(Table table) {
		List<T> results = null;

		if (table != null) {
			List<String> fields = table.getFields();
			List<Row> rows = table.getRows();

			if (rows != null) {
				results = new ArrayList<T>();

				for (int i = 0; i < rows.size(); i++) {
					Row row = rows.get(i);
					T currentValue = this.parse(fields, row);
					results.add(currentValue);
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
	 *            分页器
	 * @return 实体对象集合
	 */
	public List<T> query(Paging paging) {
		List<T> results = null;

		T value = this.initializePojo();
		if (value != null) {
			AbstractSqlBuilder sqlBuilder = this.getInstance().getSqlBuilder();

			TableMap tableMap = this.getTableMap();
			String tableName = tableMap.getName();
			String sql = sqlBuilder.getQuerySql(tableName);

			Operation operation = new Operation();
			operation.setType(OperationTypes.READ);
			operation.setSql(sql);
			operation.setParameters(null);
			operation.setPaging(paging);

			Result operationResult = this.access(operation);
			if (operationResult != null) {
				if (operationResult.isDone()) {
					Table table = operationResult.getTable();
					results = this.parse(table);
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
			AbstractSqlBuilder sqlBuilder = this.getInstance().getSqlBuilder();
			
			String uuidName = null;
			if (this.getInstance().isIdentifierCapital()) {
				uuidName = AbstractDatabasePojo.UUID.toUpperCase();
			} else {
				uuidName = AbstractDatabasePojo.UUID.toLowerCase();
			}
			TableMap tableMap = this.getTableMap();
			String tableName = tableMap.getName();
			String sql = sqlBuilder.getQueryByUuidSql(tableName, uuidName);
			List<Object> parameters = new ArrayList<Object>();
			parameters.add(uuid);

			Operation operation = new Operation();
			operation.setType(OperationTypes.READ);
			operation.setSql(sql);
			operation.setParameters(parameters);
			operation.setPaging(null);

			Result operationResult = this.access(operation);
			if (operationResult != null) {
				if (operationResult.isDone()) {
					Table table = operationResult.getTable();
					List<T> values = this.parse(table);
					if ((values != null) && (values.size() > 0)) {
						result = values.get(0);
					}
				}
			}
		}

		return result;
	}
}
