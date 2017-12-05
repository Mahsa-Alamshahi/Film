package sheypour.mobile.films.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import sheypour.mobile.films.R;
import sheypour.mobile.films.model.FilmListResponse;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private ImageView mFilmThumbnailImageView;
    private Context mContext;
    private ArrayList<FilmListResponse> mFilmList;
    private IFragmentListener mFragmentListener;


    public RecyclerViewAdapter(Context context, ArrayList<FilmListResponse> filmList, IFragmentListener fragmentListener) {
        mContext = context;
        mFilmList = filmList;
        mFragmentListener = fragmentListener;
    }


    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.recycler_view_item, parent, false);
        RecyclerViewAdapter.MyViewHolder viewHolder = new RecyclerViewAdapter.MyViewHolder(photoView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, final int position) {
        mFilmThumbnailImageView = holder.mFilmThumbnails;

        Picasso
                .with(mContext)
                .load( mFilmList.get(position).getImage().getMedium())
                .into(holder.mFilmThumbnails);



//        mFilmThumbnailImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mFragmentListener.onFilmDetails(mFilmList.get(position));
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return mFilmList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private ImageView mFilmThumbnails;

        public MyViewHolder(View itemView) {
            super(itemView);
            mFilmThumbnails = (ImageView) itemView.findViewById(R.id.img_filmThumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                mFragmentListener.onFilmDetails(mFilmList.get(position));
            }
        }
    }


}
