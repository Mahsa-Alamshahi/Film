package sheypour.mobile.films.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import sheypour.mobile.films.R;
import sheypour.mobile.films.model.FilmListResponse;


public class FilmDetailsFragment extends Fragment implements View.OnClickListener {


    private View mView;
    private IFragmentListener mIFragmentListener;
    private FilmListResponse mFilmListResponse;

    @BindView(R.id.img_filmImage)
    protected ImageView mFilmImage;

    @BindView(R.id.img_back)
    protected ImageView mBackImage;

    @BindView(R.id.txt_filmSummary)
    protected TextView mFilmSummary;

    @BindView(R.id.txt_filmName)
    protected TextView mFilmName;

    @BindView(R.id.txt_filmTime)
    protected TextView mFilmDuration;


    public FilmDetailsFragment() {
    }

    public void setInterface(IFragmentListener getInterface) {
        mIFragmentListener = getInterface;
    }


    public void getSelectedFilm(FilmListResponse filmListResponse) {
        mFilmListResponse = filmListResponse;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_film_details, container, false);

        ButterKnife.bind(this, mView);
        mBackImage.setOnClickListener(this);

        Picasso
                .with(getContext())
                .load(mFilmListResponse.getImage().getOriginal())
                .into(mFilmImage);

        mFilmName.setText(mFilmListResponse.getName());
        mFilmDuration.setText(mFilmListResponse.getPremiered().split("-")[0] + " " + "\u2022" + mFilmListResponse.getSchedule().getTime() + " min " + "\u2022" + mFilmListResponse.getRating().getAverage() + "/10");
        mFilmSummary.setText(Html.fromHtml(mFilmListResponse.getSummary()));


        return mView;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                mIFragmentListener.onFilmList();
                break;
        }
    }

}
