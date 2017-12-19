package com.socket;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import com.entity.CommandTransfer;
import com.entity.File;
import com.entity.User;


public class Client {
	
	private int choice;
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois; 
	private Scanner input; 
	private CommandTransfer transfer;
	private User user; 
	private FileInputStream fileInputStream; 
	private boolean isLogin = false;
	private String userName; 
	private String userPwd;
    
	public static void main(String[] args) {
		Client client = new Client();
		client.showMainMenu();
		}
	
	public void showMainMenu(){
		input=new Scanner(System.in);
		
		try { 
			socket = new Socket("127.0.0.1", 8800); 
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			//用户可以一直进行发送文件上传或者注册操作,直到用户执行退出操作
			while (true) { 
				System.out.println("*****Welcome use Login*****"); 
				
				 
				if (!isLogin) { 
					System.out.println("1.login");
				} else {
					System.out.println("1.file");
					} 
				
				System.out.println("2.login\n3.exit");
				System.out.println("****************************");
				System.out.print("请输入数字(1-3)"); 
				
				
				while (true) {
					 
					if (input.hasNextInt()) {
						choice = input.nextInt(); 
						
						if (choice > 0 && choice < 4) {
							
							break; 
						} 
					} else {
						
						input.next();
					} 
					    System.out.println("(1-3)!");
				}
				
				 
				switch (choice) {
				
				case 1:
					while(true){
						showLogin();
						
						CommandTransfer transfer = (CommandTransfer) ois.readObject();
						
						if (transfer.getCmd().equals("success")) { 
							System.out.println("login success"); 
							isLogin = true; 
							break; 
							
						} else {
							System.out.println("no");
						} 
					} 
				    showUpload();
				    break; 
				
				case 2: 
				    showRegister();
				    break; 
			   
			    case 3:
				    System.out.println("Thanks,byebye");
				    oos.writeObject(new CommandTransfer("exit"));
				    closeAll();
				    return; 
			    } 
			}
		}catch(IOException e) {
			e.printStackTrace(); 
	    } catch (ClassNotFoundException e) {
	    	e.printStackTrace(); 
	    } 
	    
    } 
	/** 
	 * showLogin()  
	 * @throws IOException 
	 */
	
	private void showLogin() throws IOException{
		transfer = new CommandTransfer("login"); 
		if(!isLogin){ 
			System.out.println("loginname"); 
			userName = input.next();
			System.out.println("password"); 
			userPwd = input.next();
		} 
		user = new User(); 
		user.setUsername(userName);
		user.setPassword(userPwd);
		
		transfer.setData(user);
		
		oos.writeObject(transfer);
		oos.flush();
		
	}
	/** 
	 * showRegister()
	 * @throws IOException 
	 */
	private void showRegister() throws IOException {
		transfer = new CommandTransfer("register"); 
		user = new User(); 
		System.out.println("loginname"); 
		user.setUsername(input.next()); 
		while (true) {
			System.out.println("password");
			user.setPassword(input.next());
			System.out.println("password again"); 
			String rePassword = input.next();
			if (user.getPassword().equals(rePassword)) {
				break;
			}
			System.out.println("no!");
			}
		transfer.setData(user);
		
		oos.writeObject(transfer); 
		oos.flush();
		
		System.out.println("login success");
	
	}
	/** 
	 * showUpload()方法  
	 * @throws IOException 
	 */
	private void showUpload() throws IOException{
		transfer = new CommandTransfer("upload");
		System.out.println("请输入文件的绝对路径,for:e:/wqk/dog.jpg"); 
		
		String path = input.next(); 
		String fileName = path.substring(path.lastIndexOf('/') + 1);
		
		fileInputStream = new FileInputStream(path);
		
		byte[] content = new byte[fileInputStream.available()]; 
		
		fileInputStream.read(content); 
		
		File file = new File(fileName, content); 
		transfer.setData(file);
		
		oos.writeObject(transfer);
		
	}	
	
	
	private void closeAll() throws IOException{
		if (socket != null) {
			socket.close();
		} 
		
		if (ois != null) { 
			ois.close(); 
		} 
		
		if (oos != null) { 
			oos.close(); 
		} 
		
		if (input != null) { 
			input.close();
		} 
		
		if (fileInputStream != null) {
			fileInputStream.close();
		}
	
	}		

}
