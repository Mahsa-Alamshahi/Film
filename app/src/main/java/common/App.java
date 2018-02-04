package common;

import android.app.Application;

import sheypour.mobile.films.component.DaggerNetComponent;
import sheypour.mobile.films.component.NetComponent;
import sheypour.mobile.films.module.ApiServiceModule;
import sheypour.mobile.films.module.ContextModule;
import sheypour.mobile.films.module.NetworkModule;



public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

         mNetComponent = DaggerNetComponent.builder()
                .networkModule(new NetworkModule())
                .apiServiceModule(new ApiServiceModule())
                .contextModule(new ContextModule(this))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
