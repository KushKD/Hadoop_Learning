package kush.hadoop.mapreduce.FacebookAddsAssignmnets;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableUtils;

public class AddsWritable implements Writable {
	
	public String addId;
	public String timeStamp;
	public String location;
	public String category;
	public String addClicks;
	public String addSales;
	public String ageGroup;
	

	public AddsWritable() {
		super();
		
	}

	public AddsWritable(String addId, String timeStamp, String location, String category, String addClicks,
			String addSales, String ageGroup) {
		
		this.addId = addId;
		this.timeStamp = timeStamp;
		this.location = location;
		this.category = category;
		this.addClicks = addClicks;
		this.addSales = addSales;
		this.ageGroup = ageGroup;
	}

	public String getAddId() {
		return addId;
	}

	public void setAddId(String addId) {
		this.addId = addId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAddClicks() {
		return addClicks;
	}

	public void setAddClicks(String addClicks) {
		this.addClicks = addClicks;
	}

	public String getAddSales() {
		return addSales;
	}

	public void setAddSales(String addSales) {
		this.addSales = addSales;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.addId = WritableUtils.readString(in);
		this.timeStamp = WritableUtils.readString(in);
		this.location = WritableUtils.readString(in);
		this.category = WritableUtils.readString(in);
		this.addClicks = WritableUtils.readString(in);
		this.addSales = WritableUtils.readString(in);
		this.ageGroup = WritableUtils.readString(in);
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		WritableUtils.writeString(out, this.addId);
		WritableUtils.writeString(out, this.timeStamp);
		WritableUtils.writeString(out,this.location);
		WritableUtils.writeString(out, this.category);
		WritableUtils.writeString(out, this.addClicks);
		WritableUtils.writeString(out, this.addSales);
		WritableUtils.writeString(out, this.ageGroup);
	}

	@Override
	public String toString() {
		return "AddsWritable [addId=" + addId + ", timeStamp=" + timeStamp + ", location=" + location + ", category="
				+ category + ", addClicks=" + addClicks + ", addSales=" + addSales + ", ageGroup=" + ageGroup + "]";
	}
	
	

}
