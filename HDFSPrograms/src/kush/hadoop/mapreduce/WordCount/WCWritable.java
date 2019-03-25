package kush.hadoop.mapreduce.WordCount;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

public class WCWritable implements WritableComparable<WCWritable> {
	
	
	public String word;
	
	
	    
	   

	public WCWritable() {
		setWord("");
		// TODO Auto-generated constructor stub
	}

	public WCWritable(String word) {
		
		setWord(word);
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		this.word = WritableUtils.readString(arg0);
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		// TODO Auto-generated method stub
		WritableUtils.writeString(arg0, this.word);
	}

	@Override
	public int compareTo(WCWritable o) {
		// TODO Auto-generated method stub
		return this.word.compareTo(o.getWord());
	}
	
	  @Override
	    public String toString() 
	    {
	        return this.word;
	    }

}
