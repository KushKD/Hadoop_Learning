package kush.hadoop.hdfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class ListFoldersInDirectory {
	
	public static void main (String args[]) {
		
		 String dirName ="KD_Folder";
		
		try {
			FileSystem hadoop_hdfs = FileSystem.get(new Configuration());
			
			String workingDirectory = hadoop_hdfs.getWorkingDirectory().toString();
			
			StringBuilder SB = new StringBuilder();
			SB.append(workingDirectory);
			SB.append("/");
			SB.append(dirName);
			
			System.out.println("---Getting all the Subfolders from the Directory---- "+ SB.toString());
			
			FileStatus folders [] = hadoop_hdfs.listStatus(new Path(SB.toString()));
			
			System.out.println("---Folders Array Contains--"+folders.toString());
			
			//hadoop_hdfs.listStatus(path);
			
			for(int i=0; i<folders.length;i++) {
				folders[i].getPath();
				System.out.println(folders[i].getPath());
				System.out.println("\t \t"+folders[i].getPermission());
				System.out.println("\t \t"+folders[i].getBlockSize());
				System.out.println("\t \t"+folders[i].getOwner());
				System.out.println("\t \t"+folders[i].getReplication());
				

			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}

}
