package com.example.ranad.nodalsystems;

import android.app.Application;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.example.ranad.nodalsystems.database.DaoMaster;
import com.example.ranad.nodalsystems.database.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Rana D on 2/3/2018.
 */

public class App extends MultiDexApplication {
    private static Application _INSTANCE = null;

    public static Application getInstance() {
        return _INSTANCE;
    }

    private static DaoSession daoSession = null;
    private static Database db = null;

    @Override
    public void onCreate() {
        super.onCreate();
        _INSTANCE = this;
        //  upgradeSecurityProvider();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "dataholders-db");
        db = helper.getWritableDb();
        Log.d("DB", "onCreate: " + db);
    }

    public static DaoMaster getMaster() {
        return new DaoMaster(db);
    }

    public static DaoSession getNewDaoSession() {
        getMaster().createAllTables(getMaster().getDatabase(), true);
        return getSession(true);
    }

    public static DaoSession getDaoSession() {
        getMaster().createAllTables(getMaster().getDatabase(), true);
        return getSession(false);
    }

    private static DaoSession getSession(boolean newSession) {
        getMaster().createAllTables(getMaster().getDatabase(), true);
        if (newSession) {
            return getMaster().newSession();
        }
        if (daoSession == null) {
            daoSession = getMaster().newSession();
        }
        return daoSession;
    }
  /*  private void upgradeSecurityProvider() {
        ProviderInstaller.installIfNeededAsync(this, new ProviderInstaller.ProviderInstallListener() {
            @Override
            public void onProviderInstalled() {

            }

            @Override
            public void onProviderInstallFailed(int errorCode, Intent recoveryIntent) {
                GooglePlayServicesUtil.showErrorNotification(errorCode, Application.this);
            }
        });
    }*/
}
