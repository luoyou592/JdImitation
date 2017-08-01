package com.young.jdmall.bean;

/**
 * һ����Ϣ��JavaBean
 */
public class ChatMsgEntity {
	private String name;//��Ϣ����
	private String date;//��Ϣ����
	private String message;//��Ϣ����
	private boolean isComMeg = true;// �Ƿ�Ϊ�յ�����Ϣ

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getMsgType() {
		return isComMeg;
	}

	public void setMsgType(boolean isComMsg) {
		isComMeg = isComMsg;
	}

	public ChatMsgEntity() {
	}

	public ChatMsgEntity(String name, String date, String text, boolean isComMsg) {
		super();
		this.name = name;
		this.date = date;
		this.message = text;
		this.isComMeg = isComMsg;
	}

}
