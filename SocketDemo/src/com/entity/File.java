package com.entity;

import java.io.Serializable;
import java.util.Arrays;

/** 
 * 
 * @author wqk
 */


public class File implements Serializable{
	
    private static final long serialVersionUID = 1L;  
   
    private int id;
   
    private String fname;  
    //private String username; 
    
    private byte[] fcontent;  
    
    public File() {  
        super();  
        // TODO Auto-generated constructor stub  
    }  
  
    public File(String fname,  byte[] fcontent) {  
        super();  
        this.fname = fname;  
 //       this.username = username;  
        this.fcontent = fcontent;  
    }  
  
    public int getId() {
    	return id; 
    }
    public void setId(int id) {
    	this.id = id;
    }
  
//    public String getUsername() {  
//        return username;  
//    }   
//    public void setUsername(String username) {  
//        this.username = username;  
//    }  
  
    public String getFname() {  
        return fname;  
    }  
    public void setFname(String fname) {  
        this.fname = fname;  
    } 
    
    public byte[] getFcontent() {  
        return fcontent;  
    }  
    public void setFcontent(byte[] fcontent) {  
        this.fcontent = fcontent;  
    }  
    
    @Override
    public String toString() {
    	return "File [id=" + id + ", name=" + fname + ", content="
				+ Arrays.toString(fcontent) + "]"; 
    	}


}
