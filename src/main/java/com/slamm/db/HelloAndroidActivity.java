package com.slamm.db;

import java.util.List;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.slamm.db.model.Device;

import android.os.Bundle;
import android.util.Log;

public class HelloAndroidActivity extends OrmLiteBaseActivity<DatabaseHelper> {

    private static String TAG = "ormlite-test";

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate at " + System.currentTimeMillis());
        
		doSampleDatabaseStuff("onCreate");
		setContentView(R.layout.main);
    }

	private void doSampleDatabaseStuff(String action) {
		RuntimeExceptionDao<Device, Integer> deviceDao = getHelper().getDeviceDataDao();
		List<Device> device = deviceDao.queryForAll();
		Log.i(TAG, "Available Devices" + device.size());	
	}

}

