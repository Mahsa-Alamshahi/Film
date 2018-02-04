package sheypour.mobile.films.controller;


import sheypour.mobile.films.model.FilmListResponse;


public interface IFragmentListener {

    public void onFilmDetails(FilmListResponse filmListResponse);
    public void onFilmList();
}
