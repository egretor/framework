package unknown.framework.module.pojo;

import java.util.List;

/**
 * 表映射类
 */
public class TableMap {
	private String name;
	private FieldMap major;
	private List<FieldMap> others;
	private List<FieldMap> fields;

	/**
	 * 名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 主键
	 * 
	 * @return 主键
	 */
	public FieldMap getMajor() {
		return major;
	}

	public void setMajor(FieldMap major) {
		this.major = major;
	}

	/**
	 * 其他字段集合
	 * 
	 * @return 其他字段集合
	 */
	public List<FieldMap> getOthers() {
		return others;
	}

	public void setOthers(List<FieldMap> others) {
		this.others = others;
	}

	/**
	 * 字段集合
	 * 
	 * @return 字段集合
	 */
	public List<FieldMap> getFields() {
		return fields;
	}

	public void setFields(List<FieldMap> fields) {
		this.fields = fields;
	}
}
