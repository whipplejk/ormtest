package com.slamm.db.model;

import com.j256.ormlite.field.DatabaseField;

public class Device {

	@DatabaseField(generatedId = true)
	int id;
	
	@DatabaseField(index=true)
	String name;
	
	@DatabaseField
	int device_type;
	
	@DatabaseField
	int value;
	
	Device(){
		
	}
	
	public Device ( String name, int deviceType, int value){
		this.device_type = deviceType;
		this.name = name;
		this.value = value;
	}
}
