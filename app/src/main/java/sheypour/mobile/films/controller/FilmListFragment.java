package sheypour.mobile.films.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sheypour.mobile.films.R;
import sheypour.mobile.films.model.FilmListResponse;
import sheypour.mobile.films.service.FilmWebService;


public class FilmListFragment extends Fragment implements View.OnClickListener {


    private View mView;
    private IFragmentListener mIFragmentListener;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private ArrayList<FilmListResponse> mFilmList = new ArrayList<>();
    private EndlessRecyclerViewScrollListener mScrollListener;

    @BindView(R.id.recyclerview_filmList)
    protected RecyclerView mFilmListRecyclerView;

    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;

    @BindView(R.id.imgb_retry)
    protected ImageButton mRetry;


    public FilmListFragment() {
    }


    public void setInterface(IFragmentListener getInterface) {
        mIFragmentListener = getInterface;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_film_list, container, false);

        ButterKnife.bind(this, mView);

        mProgressBar.setVisibility(View.GONE);
        mRecyclerViewAdapter = new RecyclerViewAdapter(getContext(), mFilmList, mIFragmentListener);
        mFilmListRecyclerView.setAdapter(mRecyclerViewAdapter);

        mRetry.setOnClickListener(this);

        if (mFilmList.size() == 0) {
            getFilmList(0);
        }

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mFilmListRecyclerView.setLayoutManager(layoutManager);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());


        mScrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                loadNextDataFromApi(page);
            }
        };

        mFilmListRecyclerView.addOnScrollListener(mScrollListener);

        return mView;
    }


    private void getFilmList(int page) {
        mProgressBar.setVisibility(View.VISIBLE);
        try {

            Call<ArrayList<FilmListResponse>> getFilmList = FilmWebService.getClient().filmList();

            getFilmList.enqueue(new Callback<ArrayList<FilmListResponse>>() {
                @Override
                public void onResponse(Call<ArrayList<FilmListResponse>> call, Response<ArrayList<FilmListResponse>> response) {
                    mProgressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {

                        mFilmList.addAll(response.body());
                        mRecyclerViewAdapter = new RecyclerViewAdapter(getContext(), mFilmList, mIFragmentListener);
                        mFilmListRecyclerView.setAdapter(mRecyclerViewAdapter);

                        mProgressBar.setVisibility(View.GONE);
                        mRetry.setVisibility(View.GONE);

                    } else {
                        mProgressBar.setVisibility(View.GONE);
                        mRetry.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "An error accured.Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<FilmListResponse>> call, Throwable t) {
                    mProgressBar.setVisibility(View.GONE);
                    mRetry.setVisibility(View.VISIBLE);

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
            mProgressBar.setVisibility(View.GONE);
            mRetry.setVisibility(View.VISIBLE);
        }


    }


    public void loadNextDataFromApi(int offset) {
        getFilmList(offset);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgb_retry:
                mFilmList.clear();
                mProgressBar.setVisibility(View.VISIBLE);
                mRetry.setVisibility(View.GONE);
                getFilmList(0);
                break;
        }
    }
}
