package com.zs.pms.po;

import java.io.Serializable;

public class TRole implements Serializable{

	/**
	 * –Ú¡–∫≈
	 */
	private static final long serialVersionUID = -1962827445200282876L;

	
	private int id;
	private String ename;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	
}
