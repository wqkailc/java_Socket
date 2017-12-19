package com.entity;

import java.io.Serializable;

/** 
 * 
 * @author wqk
 */

public class CommandTransfer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Object data;
	private String cmd;
	private String result;
	private boolean flag;
	public CommandTransfer(){ 
		
	} 
	public CommandTransfer(String cmd){
		this.cmd = cmd;
	} 
	public boolean isFlag() {
		return flag;
	}
    public void setFlag(boolean flag) { 
    	this.flag = flag;
    } 
    public Object getData() {
    	return data; 
    } 
    public void setData(Object data) { 
    	this.data = data;
    } 
    public String getCmd() {
    	return cmd;
    } 
    public void setCmd(String cmd) {
    	this.cmd = cmd;
    } 
    public String getResult() { 
    	return result;
    }
    public void setResult(String result) {
    	this.result = result;
    }
    
    @Override 
    public String toString() { 
    	return "CommandTransfer [data=" + data + ", cmd=" + cmd + ", result="
				+ result + ", flag=" + flag + "]";
	}
	

}
