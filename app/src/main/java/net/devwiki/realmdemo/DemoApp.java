package net.devwiki.realmdemo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * 默认的Application
 * Created by Asia on 2016/8/29.
 */

public class DemoApp extends Application {

    private static Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
    }

    private void initRealm() {
        RealmConfiguration configuration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(configuration);
    }

    public static Realm getRealm() {
        return realm;
    }
}
