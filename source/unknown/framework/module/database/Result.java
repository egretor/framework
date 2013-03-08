package unknown.framework.module.database;

/**
 * 执行结果类
 */
public class Result {
	/**
	 * 成败
	 */
	private boolean done;
	/**
	 * 表
	 */
	private Table table;
	/**
	 * 分页器
	 */
	private Paging paging;

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}
}
