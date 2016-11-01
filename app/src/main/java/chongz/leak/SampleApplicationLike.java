package chongz.leak;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;

@DefaultLifeCycle(application = "chongz.leak.LeakApplication",flags = ShareConstants.TINKER_ENABLE_ALL)
public class SampleApplicationLike extends DefaultApplicationLike {


    public SampleApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent, Resources[] resources, ClassLoader[] classLoader, AssetManager[] assetManager) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent, resources, classLoader, assetManager);
    }

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplication().getApplicationContext();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this.getApplication()).name("ticket" +
            ".realm").deleteRealmIfMigrationNeeded()
            .build();
        Realm.setDefaultConfiguration(realmConfiguration);

//        enabledStrictMode();
        LeakCanary.install(this.getApplication());
//        Stetho.initializeWithDefaults(this);
    }

    public static Context getContext() {
        return context;
    }

    private void enabledStrictMode() {
        if (SDK_INT >= GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                .detectAll() //
                .penaltyLog() //
                .penaltyDeath() //
                .build());
        }
    }
}
