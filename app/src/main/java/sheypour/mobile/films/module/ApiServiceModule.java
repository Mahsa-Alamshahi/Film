package sheypour.mobile.films.module;

import common.URL;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sheypour.mobile.films.service.IFilmsServicesInterface;


@Module(includes = NetworkModule.class)
public class ApiServiceModule {

    private static final String BASE_URL = URL.BASEURL;

    @Provides
    public IFilmsServicesInterface apiService(Retrofit retrofit) {
        return retrofit.create(IFilmsServicesInterface.class);
    }

    @Provides
    public Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
