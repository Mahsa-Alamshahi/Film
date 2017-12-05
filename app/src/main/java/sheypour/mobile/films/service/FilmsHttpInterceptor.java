package sheypour.mobile.films.service;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class FilmsHttpInterceptor implements Interceptor {


    @Override
    public Response intercept(Chain chain) throws IOException {

        Request originalRequest = chain.request();
        Request newRequest = originalRequest.newBuilder()
                .build();

        return chain.proceed(newRequest);
    }
}
