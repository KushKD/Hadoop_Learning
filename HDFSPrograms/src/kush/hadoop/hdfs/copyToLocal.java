package kush.hadoop.hdfs;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class copyToLocal {
	
	public static void main(String args[]) {
		
		String source_Folder_file = args[0];
		String destination_Folder = args[1];
		
		String working_Directory = null;
		
		try {
			FileSystem hadoop_fs = FileSystem.get(new Configuration());
			working_Directory = hadoop_fs.getWorkingDirectory().toString();
			String file_directory = hadoop_fs.getHomeDirectory().toString();
			System.out.println("--- File Directory ----"+ file_directory);
			
			
			String Source_Path = createPath(working_Directory,source_Folder_file,null);
			System.out.println("---Source Path HDFS----"+ Source_Path);
//			String Destination_Path = createPath(working_Directory,null,destination_Folder);
//			System.out.println("---Destination Path----"+ Destination_Path);
			//Input stream for the file in local file system to be written to HDFS
		//	InputStream in = new BufferedInputStream(new FileInputStream(destination_Folder));
			//System.out.println("No Idea What it is.."+in);
			FileStatus[] status = hadoop_fs.listStatus(new Path(Source_Path));
	        for(int i=0;i<status.length;i++){
	            System.out.println(status[i].getPath());
	            hadoop_fs.copyToLocalFile(false, status[i].getPath(), new Path("/home/hirwuser1249/random"));
	        }
			
		
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String createPath(String working_Directory, String source_Folder_file ,String destination_Folder) {
		// TODO Auto-generated method stub
		
		StringBuilder SB = new StringBuilder();
		SB.append(working_Directory);
		SB.append("/");
		  
		if(source_Folder_file == null) {
			SB.append(destination_Folder);
		}else {
			SB.append(source_Folder_file);
		}
		
		return SB.toString();
	}
	
	
	

}
