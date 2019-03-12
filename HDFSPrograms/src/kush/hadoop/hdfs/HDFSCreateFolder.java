package kush.hadoop.hdfs;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HDFSCreateFolder {
	@SuppressWarnings({ "null", "deprecation" })
	public static void main(String args[]) {
		
		//Get the folder name from Folder name
		String folderName = args[0];
		
		System.out.println("--Folder to be Created is--"+folderName);
		
		Configuration configuration = new Configuration();
		
		//Get the filesystem - HDFS
		try {
			FileSystem hadoop_fs  = FileSystem.get(configuration);
			hadoop_fs.getUri();
			System.out.println("Get the URI of Hadoop System:- "+hadoop_fs.getUri()); //hdfs://ip-172-31-45-216.ec2.internal:8020  
			System.out.println("Getting the Working Directory "+hadoop_fs.getWorkingDirectory());  //hdfs://ip-172-31-45-216.ec2.internal:8020/user/hirwuser1249
			
			String WorkingDirectory = hadoop_fs.getWorkingDirectory().toString();
			System.out.println("---Working Directory to String---"+WorkingDirectory);
			
			
				if(folderName == null && folderName.isEmpty()) {
					System.out.println("Please Enter the Folder name.");
				}else {
					//Create the Folder Path
					StringBuilder SB = new StringBuilder();
					SB.append(WorkingDirectory);
					SB.append("/");
					SB.append(folderName);
					System.out.println("--Folder Path--"+ SB.toString());
					Path createFolder = new Path(SB.toString());
					
					if(hadoop_fs.exists(createFolder)) {
						System.out.println("Folder Already Exists");
						System.out.println("Delateing Folder Please Wait !!");
						
						hadoop_fs.delete(createFolder);
						System.out.println("Folder Deleted Successfully at: "+ SB.toString());
						System.out.println("Creating  Folder at Location : "+ SB.toString());
						hadoop_fs.mkdirs(createFolder);
						if(hadoop_fs.mkdirs(createFolder)) {
							System.out.println("Folder Created Successfully at: "+ SB.toString());
						}
						
					}else {
						hadoop_fs.mkdirs(createFolder);
						if(hadoop_fs.mkdirs(createFolder)) {
							System.out.println("Folder Created Successfully at: "+ SB.toString());
						}
					}
					
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
