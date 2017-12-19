package com.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

import com.entity.CommandTransfer;
import com.entity.File;
import com.entity.User;
import com.service.FileService;
import com.service.UserService;

public class ServerThread extends Thread {
	private boolean isLogin = false; 
	private Socket socket; 
	private UserService userService;
	private User user; 
	private ObjectOutputStream oos;
	private ObjectInputStream ois; 
	private CommandTransfer transfer;
	private File file;
	private FileService fileService;
	
	
	@Override 
	public void run() { 
		execute(socket);
	} 
	
	 
	public ServerThread(Socket socket) {
		this.socket = socket;
	} 
	
	private void execute(Socket socket) { 
		
		String order = "";
		
		userService = new UserService(); 
		
		try { 
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			while (!order.equals("exit")) { 
				
				transfer = (CommandTransfer) ois.readObject();
				
				order = transfer.getCmd(); 
				
				switch (transfer.getCmd()){
				
				case "login": 
					executeLogin(transfer);
					
					if (isLogin) { 
						oos.writeObject(new CommandTransfer("success"));
						oos.flush();
					
						transfer = (CommandTransfer) ois.readObject(); 
						executeUpload(transfer);
					} else {
						oos.writeObject(new CommandTransfer("fail")); 
						oos.flush(); 
					} 
					break; 
				
				case "register":
					executeRegister(transfer); 
					isLogin = false;
					break; 
				
				case "exit":
					System.out.println("¿Í»§¶ËÍË³öµÇÂ¼");
					isLogin = false; 
					break; 
				} 
			}
		    closeAll(); 
		} catch (IOException e) { 
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		} catch (ClassNotFoundException e) { 
			 e.printStackTrace();
		}		
	}
	
	private void executeLogin(CommandTransfer transfer) throws SQLException, ClassNotFoundException, IOException { 
		user = (User) transfer.getData();
		List<User> users = userService.query();
		
		for (User u : users) { 
			if (u.getUsername().equals(user.getUsername())) {
				if (u.getPassword().equals(user.getPassword())) { 
					System.out.println("login success"); 
					isLogin = true; break;
				} 
			}
		}
		System.out.println(user);
	}
    
	
	private void executeRegister(CommandTransfer transfer) throws SQLException { 
		userService = new UserService(); 
		user = (User) transfer.getData();
		userService.insert(user); 
	}
	
	private void executeUpload(CommandTransfer transfer) throws SQLException { 
		file = (File) transfer.getData(); 
		fileService = new FileService();
		fileService.insert(file);
	}
	
	private void closeAll() throws IOException { 
		socket.close(); 
		ois.close(); 
		oos.close();
	}

    
}
