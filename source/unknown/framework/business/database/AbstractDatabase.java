package unknown.framework.business.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import unknown.framework.utility.Trace;
import unknown.framework.module.database.AbstractSqlBuilder;
import unknown.framework.module.database.AbstractTypeConverter;
import unknown.framework.module.database.Instance;
import unknown.framework.module.database.OperationTypes;
import unknown.framework.module.database.Paging;
import unknown.framework.module.database.Result;
import unknown.framework.module.database.Row;
import unknown.framework.module.database.Table;
import unknown.framework.module.database.Operation;

/**
 * 数据库类
 */
public abstract class AbstractDatabase {
	/**
	 * 驱动集合
	 */
	protected final static Map<String, Boolean> DRIVERS = new HashMap<String, Boolean>();
	/**
	 * 链接集合
	 */
	protected final static Map<String, Connection> CONNECTIONS = new HashMap<String, Connection>();

	/**
	 * 数据库实例
	 * 
	 * @return 数据库实例
	 */
	public abstract Instance getInstance();

	/**
	 * 跟踪SQL
	 * 
	 * @param sql
	 *            SQL语句
	 * @param parameters
	 *            参数
	 */
	private void traceSql(String sql, List<Object> parameters) {
		String message = null;

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(sql);
		stringBuilder.append(Convention.ENTER);
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				stringBuilder.append("{");
				stringBuilder.append(parameters.get(i));
				stringBuilder.append("} ");
			}
		}

		message = stringBuilder.toString();

		Trace.getDatabase().info(message);
	}

	/**
	 * 访问数据库
	 * 
	 * @param operation
	 *            操作
	 * @return 结果
	 */
	public Result access(Operation operation) {
		Result result = null;

		List<Operation> operations = new ArrayList<Operation>();
		operations.add(operation);

		List<Result> results = this.access(operations);

		if ((results != null) && (results.size() > 0)) {
			result = results.get(0);
		}

		return result;
	}

	/**
	 * 访问数据库
	 * 
	 * @param operations
	 *            操作集合
	 * @return 结果集合
	 */
	public List<Result> access(List<Operation> operations) {
		List<Result> results = null;

		if (operations != null) {
			Instance instance = this.getInstance();
			String key = instance.getClass().getName();
			String driver = instance.getDriver();
			String url = instance.getUrl();
			String user = instance.getUser();
			String password = instance.getPassword();

			boolean hasDriver = AbstractDatabase.DRIVERS.containsKey(driver);
			if (!hasDriver) {
				try {
					Class.forName(driver);
					AbstractDatabase.DRIVERS.put(driver, true);
				} catch (ClassNotFoundException e) {
					Trace.getFramework().error(e);
				}
			}

			Connection connection = null;
			boolean hasConnection = AbstractDatabase.CONNECTIONS
					.containsKey(key);
			if (hasConnection) {
				connection = AbstractDatabase.CONNECTIONS.get(key);
			} else {
				try {
					connection = DriverManager.getConnection(url, user,
							password);
					AbstractDatabase.CONNECTIONS.put(key, connection);
				} catch (SQLException e) {
					Trace.getFramework().error(e);
				}
			}

			results = this.statement(connection, operations);
		}

		return results;
	}

	/**
	 * 准备语句
	 * 
	 * @param connection
	 *            数据库链接
	 * @param Operations
	 *            操作集合
	 * @return 结果集合
	 */
	protected List<Result> statement(Connection connection,
			List<Operation> operations) {
		List<Result> results = null;

		PreparedStatement preparedStatement = null;
		results = new ArrayList<Result>();
		AbstractTypeConverter typeConverter = this.getInstance()
				.getTypeConverter();
		AbstractSqlBuilder sqlBuilder = this.getInstance().getSqlBuilder();

		try {
			for (int i = 0; i < operations.size(); i++) {
				Result result = null;

				Operation operation = operations.get(i);
				if (operation != null) {
					OperationTypes operationType = operation.getType();
					String sql = operation.getSql();
					List<Object> parameters = operation.getParameters();
					Paging paging = operation.getPaging();

					this.traceSql(sql, parameters);

					preparedStatement = connection.prepareStatement(sql);

					preparedStatement.clearParameters();
					if (parameters != null) {
						int index = 0;
						while (index < parameters.size()) {
							Object value = parameters.get(index);
							index++;

							Object databaseValue = typeConverter
									.getDatabase(value);
							preparedStatement.setObject(index, databaseValue);
						}
					}

					switch (operationType) {
					case READ:
						if (paging == null) {
							result = this.executeQuery(preparedStatement);
						} else {
							String countRowSql = sqlBuilder.getCountRowSql(sql);
							result = this.executePagingQuery(preparedStatement,
									paging, countRowSql);
						}
						break;
					case WRITE:
						result = this.executeUpdate(preparedStatement);
						break;
					}

					preparedStatement.close();
				}

				results.add(result);
			}
		} catch (SQLException e) {
			Trace.getFramework().error(e);
		}

		return results;
	}

	/**
	 * 执行查询
	 * 
	 * @param preparedStatement
	 *            语句
	 * @return 结果
	 */
	protected Result executeQuery(PreparedStatement preparedStatement) {
		Result result = new Result();

		result.setDone(false);

		try {
			Table table = new Table();
			List<String> fields = new ArrayList<String>();
			List<Row> rows = new ArrayList<Row>();

			ResultSet resultSet = preparedStatement.executeQuery();

			ResultSetMetaData metaData = resultSet.getMetaData();
			int index = 0;
			int count = metaData.getColumnCount();
			while (index < count) {
				index++;
				String field = metaData.getColumnName(index);
				fields.add(field);
			}

			while (resultSet.next()) {
				Row row = new Row();

				List<Object> values = new ArrayList<Object>();
				index = 0;
				while (index < count) {
					index++;
					Object value = resultSet.getObject(index);
					values.add(value);
				}

				row.setValues(values);

				rows.add(row);
			}

			resultSet.close();

			table.setFields(fields);
			table.setRows(rows);

			result.setDone(true);
			result.setTable(table);
		} catch (SQLException e) {
			Trace.getFramework().error(e);
		}

		return result;
	}

	/**
	 * 执行分页查询
	 * 
	 * @param preparedStatement
	 *            语句
	 * @param paging
	 *            分页器
	 * @param countRowSql
	 *            合计行数SQL语句
	 * @return 结果
	 */
	protected Result executePagingQuery(PreparedStatement preparedStatement,
			Paging paging, String countRowSql) {
		Result result = new Result();

		result.setDone(false);

		try {
			Table table = new Table();
			List<String> fields = new ArrayList<String>();
			List<Row> rows = new ArrayList<Row>();

			ResultSet resultSet = preparedStatement.executeQuery();

			ResultSetMetaData metaData = resultSet.getMetaData();
			int index = 0;
			int count = metaData.getColumnCount();
			while (index < count) {
				index++;
				String field = metaData.getColumnName(index);
				fields.add(field);
			}

			int currentPageRowCount = 0;
			int currentPageFirstRow = paging.getCurrentPageFirstRow();
			int rowsPerPage = paging.getRowsPerPage();
			resultSet.absolute(currentPageFirstRow);
			if (!resultSet.isAfterLast()) {
				do {
					Row row = new Row();

					List<Object> values = new ArrayList<Object>();
					index = 0;
					while (index < count) {
						index++;
						Object value = resultSet.getObject(index);
						values.add(value);
					}

					row.setValues(values);

					rows.add(row);

					currentPageRowCount++;
					if (currentPageRowCount == rowsPerPage) {
						break;
					}
				} while (resultSet.next());
			}

			resultSet.close();

			table.setFields(fields);
			table.setRows(rows);

			int rowCount = 0;

			resultSet = preparedStatement.executeQuery(countRowSql);
			while (resultSet.next()) {
				rowCount = resultSet.getInt(1);
			}
			resultSet.close();

			paging.setRowCount(rowCount);

			result.setDone(true);
			result.setTable(table);
			result.setPaging(paging);
		} catch (SQLException e) {
			Trace.getFramework().error(e);
		}

		return result;
	}

	/**
	 * 执行更新
	 * 
	 * @param preparedStatement
	 *            语句
	 * @return 结果
	 */
	protected Result executeUpdate(PreparedStatement preparedStatement) {
		Result result = new Result();

		result.setDone(false);

		try {
			preparedStatement.executeUpdate();
			result.setDone(true);
		} catch (SQLException e) {
			Trace.getFramework().error(e);
		}

		return result;
	}
}
