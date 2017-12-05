package sheypour.mobile.films;


import org.junit.Test;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import sheypour.mobile.films.model.FilmListResponse;
import sheypour.mobile.films.service.FilmWebService;

public class WebServiceUnitTest {

    @Test
    public void getFilmListUnitTest() throws Exception {

        Response<ArrayList<FilmListResponse>> list;

        Call<ArrayList<FilmListResponse>> getFilmList = FilmWebService.getClient().filmList();

        list = getFilmList.execute();

       // assertEquals(list.isSuccessful(), true);

    }
}
