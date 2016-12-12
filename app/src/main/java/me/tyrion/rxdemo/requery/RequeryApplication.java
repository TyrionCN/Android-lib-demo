package me.tyrion.rxdemo.requery;

import android.app.Application;

import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.rx.RxSupport;
import io.requery.rx.SingleEntityStore;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;
import me.tyrion.rxdemo.BuildConfig;
import me.tyrion.rxdemo.requery.model.Models;

/**
 * Created by taomaogan on 2016/12/9.
 */

public class RequeryApplication extends Application {
    private SingleEntityStore<Persistable> mDataStore;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public SingleEntityStore<Persistable> getData() {
        if (mDataStore == null) {
            // override onUpgrade to handle migrating to a new version
            DatabaseSource source = new DatabaseSource(this, Models.DEFAULT, 1);
            if (BuildConfig.DEBUG) {
                // use this in development mode to drop and recreate the tables on every upgrade
                source.setTableCreationMode(TableCreationMode.DROP_CREATE);
            }
            Configuration configuration = source.getConfiguration();
            mDataStore = RxSupport.toReactiveStore(
                    new EntityDataStore<Persistable>(configuration));
        }
        return mDataStore;
    }
}
