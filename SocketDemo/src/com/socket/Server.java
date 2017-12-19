package com.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class Server {
	public static void main(String[] args) {

	    ServerSocket serverSocket=null;
	    int count=1;

	    try {
		     
	         serverSocket = new ServerSocket(8800); 
		   
		    while (true) {
			     
			     Socket socket = serverSocket.accept();
			     
			     ServerThread thread = new ServerThread(socket); 
			
			      
			     thread.start(); 
			
			     System.out.println("当前客户端连接ID：" + count++);
			}
		} catch (IOException e) {
			e.printStackTrace(); 
			
			
			try {
				serverSocket.close();
			} catch (IOException e1) { 
					e1.printStackTrace();
			}			
		}
    }
}
