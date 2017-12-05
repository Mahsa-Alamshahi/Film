package sheypour.mobile.films.service;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import sheypour.mobile.films.model.FilmListResponse;


public interface IFilmsServicesInterface {

    @GET("show/")
    Call<ArrayList<FilmListResponse>> filmList();

}
