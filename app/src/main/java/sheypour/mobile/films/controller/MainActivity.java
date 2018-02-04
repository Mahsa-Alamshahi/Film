package sheypour.mobile.films.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import sheypour.mobile.films.R;
import sheypour.mobile.films.model.FilmListResponse;

public class MainActivity extends AppCompatActivity implements IFragmentListener {


    private FragmentEnum mCurrentFragment;
    private FilmListFragment mFilmListFragment;
    private FilmDetailsFragment mFilmDetailsFragment;
    private LinearLayout mToolbarLayout;


    enum FragmentEnum {
        filmsList,
        filmDetails
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbarLayout = (LinearLayout) findViewById(R.id.toolbarLayout);
        onFilmsListFragment();
    }


    private void setActiveFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }


    private void onFilmsListFragment(){
        mToolbarLayout.setVisibility(View.VISIBLE);
        mCurrentFragment = FragmentEnum.filmsList;
        if (mFilmListFragment == null) {
            mFilmListFragment = new FilmListFragment();
        }
        mFilmListFragment.setInterface(this);
        setActiveFragment(mFilmListFragment);
    }



    @Override
    public void onFilmDetails(FilmListResponse filmListResponse) {
        mToolbarLayout.setVisibility(View.GONE);
        mCurrentFragment = FragmentEnum.filmDetails;
        if (mFilmDetailsFragment == null) {
            mFilmDetailsFragment = new FilmDetailsFragment();
        }
        mFilmDetailsFragment.getSelectedFilm(filmListResponse);
        mFilmDetailsFragment.setInterface(this);
        setActiveFragment(mFilmDetailsFragment);
    }



    @Override
    public void onFilmList() {
        onFilmsListFragment();
    }



    @Override
    public void onBackPressed() {
        if (mCurrentFragment == FragmentEnum.filmsList){
            finish();
            return;
        }else if (mCurrentFragment == FragmentEnum.filmDetails){
            onFilmsListFragment();
            return;
        }
    }
}
