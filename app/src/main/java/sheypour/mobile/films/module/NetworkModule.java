package sheypour.mobile.films.module;

import android.content.Context;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;


@Module(includes = ContextModule.class)
public class NetworkModule {


    @Provides
    public OkHttpClient okHttpClient(Cache cache) {
        return new OkHttpClient.Builder()
                .cache(cache)
                .build();
    }


    @Provides
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 5 * 1000 * 1000); //5MB cache;
    }


    @Provides
    public File file(Context context) {
        File cacheFile = new File(context.getCacheDir(), "okhttp_cache");
        cacheFile.mkdirs();
        return cacheFile;
    }

}