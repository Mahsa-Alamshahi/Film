package sheypour.mobile.films.component;


import android.support.v4.app.Fragment;

import dagger.Component;
import sheypour.mobile.films.module.ApiServiceModule;
import sheypour.mobile.films.service.IFilmsServicesInterface;


@Component(modules = {ApiServiceModule.class})
public interface NetComponent {
        void inject(Fragment fragment);
        IFilmsServicesInterface getApiService();
}
