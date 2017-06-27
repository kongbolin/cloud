package model;

public class ordermodelserver {
	private String orderid;
	private String userid;
	private String contact;
	private String typename;
	private String ifnew;
	private String state;
	private String ifdelete;
	private String ifpay;

	public String getIfnew() {
		return ifnew;
	}

	public void setIfnew(String ifnew) {
		this.ifnew = ifnew;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIfdelete() {
		return ifdelete;
	}

	public void setIfdelete(String ifdelete) {
		this.ifdelete = ifdelete;
	}

	public String getIfpay() {
		return ifpay;
	}

	public void setIfpay(String ifpay) {
		this.ifpay = ifpay;
	}

	// private String otherneed;
	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	// public String getOtherneed() {
	// return otherneed;
	// }
	// public void setOtherneed(String otherneed) {
	// this.otherneed = otherneed;
	// }

}
