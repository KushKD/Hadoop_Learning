package kush.hadoop.hdfs;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class copyFromLocal {
	
	public static void main (String args[]) {
		
		
		try {
			FileSystem hadoop_fs = FileSystem.get(new Configuration());
			
			
			FileStatus[] folders = hadoop_fs.listStatus(new Path("/home/hirwuser1249/FilesToCopy"));
			
			for (int i = 0; i < folders.length; i++) {
				System.out.println("-----"+folders[i].getPath());
			}
			 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
