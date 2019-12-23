package com.xch.study.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;



public class FtpUtils {
	public static FTPClient ftpClient;
	private String ip = ""; // 服务器IP地址
	private String userName = ""; // 用户名
	private String userPwd = ""; // 密码
	private int port = 0; // 端口号
	private String path = ""; // 读取文件的存放目录

	/**
	 * init ftp servere
	 */
	public FtpUtils(String ftpUserId,String ftpPw,String ftpIp,Integer ftpPort,String ftpPath) {
		if (ftpClient == null || !ftpClient.isConnected()) {
//			String fileUrl = PropertyConfigurer.getProperty("fileUrl");
			String fileUrl = null;
			userName = ftpUserId;
			userPwd = ftpPw;
			ip = ftpIp;
			port = ftpPort;
			path = ftpPath;
			this.reSet();
		}
	}

	public void reSet() {
		this.connectServer(ip, port, userName, userPwd, path);
	}

	/**
	 * @param ip
	 * @param port
	 * @param userName
	 * @param userPwd
	 * @param path
	 * @throws SocketException
	 * @throws IOException
	 *             function:连接到服务器
	 */
	public void connectServer(String ip, int port, String userName,
			String userPwd, String path) {
		ftpClient = new FTPClient();
		try {
			// 连接
			ftpClient.connect(ip, port);
			System.out.println("连接服务器成功");
			// 登录
			ftpClient.login(userName, userPwd);
			System.out.println("登录成功");
			if (path != null && path.length() > 0) {
				// 跳转到指定目录
				ftpClient.changeWorkingDirectory(path);
				System.out.println("跳转到指定目录下");
			}
		} catch (SocketException e) {
			System.out.println("IP地址或者端口错误，无法连接到服务器");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IP地址或者端口错误，无法连接到服务器");
			e.printStackTrace();
		}
	}

	/**
	 * @throws IOException
	 *             function:关闭连接
	 */
	public void closeServer() {
		if (ftpClient.isConnected()) {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param path
	 * @return function:读取指定目录下的文件名
	 * @throws IOException
	 */
	public List<String> getFileList(String path) {
		List<String> fileLists = new ArrayList<String>();
		// 获得指定目录下所有文件名
		FTPFile[] ftpFiles = null;
		try {
			ftpClient.enterLocalPassiveMode();
			ftpFiles = ftpClient.listFiles(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; ftpFiles != null && i < ftpFiles.length; i++) {
			FTPFile file = ftpFiles[i];
			if (file.isFile()) {
				fileLists.add(file.getName());
			}
		}
		return fileLists;
	}

	/**
	 * @param fileName
	 * @return function:从服务器上读取指定的文件
	 * @throws ParseException
	 * @throws IOException
	 */
	public InputStream readFile(String fileName) throws ParseException {
		InputStream ins = null;
		try {
			// 从服务器上读取指定的文件
			ins = ftpClient.retrieveFileStream(fileName);
			// if (ins != null) {
			// ins.close();
			// }
			// 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
			ftpClient.getReply();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ins;
	}

	/**
	 * @param fileName
	 *            function:删除文件
	 */
	public void deleteFile(String fileName) {
		try {
			ftpClient.deleteFile(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 文件上传
	public static boolean uploadFile(String filename, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			// ftp.changeWorkingDirectory(path);
			ftp.storeFile(filename, input);
			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		FtpUtils ftp =new FtpUtils("upload","upload","128.64.151.251",21,"");
		List<String> list=ftp.getFileList("");
		for(String o:list){
			ftp.deleteFile(o);
		}

	}

	public void createDir(String dirname) {
		try {
			ftpClient.makeDirectory(dirname);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());

		}

	}
}
