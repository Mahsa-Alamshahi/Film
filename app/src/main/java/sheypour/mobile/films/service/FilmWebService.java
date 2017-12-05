package sheypour.mobile.films.service;

import java.io.IOException;

import common.URL;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FilmWebService {


    private static IFilmsServicesInterface mRestService = null;

    public FilmWebService() {
    }

    public static IFilmsServicesInterface getClient() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request newRequest = originalRequest.newBuilder()
                                .build();
                        return chain.proceed(newRequest);
                    }
                }).build();


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        mRestService = retrofit.create(IFilmsServicesInterface.class);
        return mRestService;
    }
}
