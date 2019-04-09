package kush.hadoop.mapreduce.FileInputMethords;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.util.LineReader;


public class XMLRecordReader extends RecordReader<LongWritable, Text> {
	
	private final String startTag = "<MOVIES>";
	private final String endTag = "</MOVIES>";
	
	private long curr_position = 0;  //Current Position is initialized to zero
	private long startOfFile;
	private long endOfFile;
	
	private LineReader lineReader;
	
	//Two wirtables <key,Value> i.e. <LongWritable, Text>
	LongWritable key = new LongWritable();
	Text value = new Text();
	
	//FirstMethord is the Initialize Methord
	//It runs only one time i.e. for the first time just to initialize the class
	@Override
	public void initialize(InputSplit inputsplit, TaskAttemptContext taskAttemptcontext) throws IOException, InterruptedException {
		
		// Get the access of that split
		FileSplit filesplit  = (FileSplit)inputsplit;
		
		//Creating the object of Configuration class
		Configuration configuration = taskAttemptcontext.getConfiguration();
		
		startOfFile = filesplit.getStart();  //It points to the value where the input split starts. As it atarts from 0 offset, so the startOfFile will be zero
		endOfFile = startOfFile+ filesplit.getLength(); //GetLength() == will return the total length of InputSplit.
		
		//Get path of the file to get the split
		Path filePath = filesplit.getPath();
		
		//Get the HDFS File System Access
		FileSystem fileSystem = filePath.getFileSystem(configuration); //Points to the file system where file is to be get from HDFS
		
		/*
		 * Untill Now we have got a Split its filePath and its Permission in HDFS
		 */
		
		//Now to Open and read a File
		FSDataInputStream fileSysteDataInputStream = fileSystem.open(filePath);
		fileSysteDataInputStream.seek(startOfFile);
		
		//Reading LineByLine
		//lineReader  purpose is to read the Line One by One from the Input File
		lineReader = new LineReader(fileSysteDataInputStream, configuration);
		
		//Current Position of Pointer
		this.curr_position = startOfFile;
		
	}
	
	//After the Initialze methord we have the next Key Value Methord
	//This is the actual method that desides what is the key and what is the value
	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		//Setting the value of Key as current value
		key.set(curr_position);
		//Clear any previous Values
		value.clear();
		
		Text line = new Text();
		boolean startFound = false;
		
		while(curr_position<endOfFile) {
			long lineLength = lineReader.readLine(line);
			curr_position = curr_position + lineLength;
			
			
		    if (!startFound &&line.toString().equalsIgnoreCase(this.startTag))
		    {
			/* start tag found and start reading further */
			startFound = true;
			
		    }else if(startFound && line.toString().equalsIgnoreCase(this.endTag)) {
		    	
		    	/*End Tag Found . Stop Reading End Tag*/
		    	/* end tag found,  stop reading, remove last comma  */
		    	String withoutComma = value.toString().substring(0,  value.toString().length()-1);
		    	value.set(withoutComma);
		    return true;  //Program Execution Goes To next MEthord from this true

		    	
		    }else if (startFound)
			    {
				/* read all data between start and end tag */
				String S1 = line.toString();
				/* remove xml tags from line */
				String content = S1.replaceAll("<[^>]+>", "");    // content = Titanic
				
				value.append(content.getBytes("utf-8"), 0, content.length());  //value = Titanic
				value.append(",".getBytes("utf-8"), 0, ",".length());		   //value = Titanic,
			    }

		    

			
			
			
			
		}
		
		
		return false;
	}	
	
	@Override
	public LongWritable getCurrentKey() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		// returning key to framework whihc got calculated in above method
		return null;
	}

	@Override
	public Text getCurrentValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		// returning value to framework whihc got calculated in above method

		return null;
	}
	

    @Override
    public float getProgress()throws IOException, InterruptedException
    {
	/* return percentage of file read so far */
	return (curr_position - startOfFile) / (float) (endOfFile - startOfFile);
    }      //   37         - 0                       900 - 0    0.041                                      

    @Override
    public void close()	throws IOException 
    {
    	///close the linereader
	if (lineReader != null)
		
	    lineReader.close();
    }


	

}
