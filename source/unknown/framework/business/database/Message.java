package unknown.framework.business.database;

/**
 * 消息类
 */
public class Message {
	/**
	 * 类型
	 */
	private MessageTypes type;
	/**
	 * 内容
	 */
	private String content;

	public MessageTypes getType() {
		return type;
	}

	public void setType(MessageTypes type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
