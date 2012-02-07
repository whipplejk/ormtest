package com.slamm.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.slamm.db.model.Device;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "ormlite.db";
	private static final int DATABASE_VERSION = 1;
	
	private Dao<Device, Integer> deviceDao=null;
	private RuntimeExceptionDao<Device, Integer> deviceRuntimeDao = null;
	
	public DatabaseHelper(Context context){
		super ( context, DATABASE_NAME, null, DATABASE_VERSION );
	}
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Device.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
		
		//on Create we are going to create some sample data
		RuntimeExceptionDao<Device, Integer> dao = getDeviceDataDao();
		Device device = new Device ( "Bathroom Light", 1, 100 );
		dao.create(device);
		device = new Device( "Foyer Light", 2, 0 );
		dao.create(device);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
			int newVersion) {
		try {
			Log.i(DatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Device.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}
	
	public Dao<Device, Integer> getDao() throws SQLException {
		if (deviceDao == null){
			deviceDao = getDao(Device.class);
		}
		return deviceDao;
	}
	
	public RuntimeExceptionDao<Device, Integer> getDeviceDataDao() {
		if ( deviceRuntimeDao == null ){
			deviceRuntimeDao = getRuntimeExceptionDao(Device.class);
		}
		
		return deviceRuntimeDao;
	}
	
	@Override
	public void close () {
		super.close();
		deviceRuntimeDao = null;
	}

}
