package fanes.irfan.myapplication12;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Movie_Adapter extends RecyclerView.Adapter<Movie_Adapter.MovieViewHolder> {

//    private String[] gambar;
//    private String[] title;
//    private String[] subtitle;
//    private String[] description;

    private ArrayList<String> gambar = new ArrayList<String>();
    private ArrayList<String> judul = new ArrayList<String>();
    //private ArrayList<String> bahasa = new ArrayList<String>();
    private ArrayList<String> deskripsi = new ArrayList<String>();
    private ArrayList<Integer> id = new ArrayList<Integer>();

    private Context konteks;


    public Movie_Adapter(Context context, ArrayList<String> posterUrl, ArrayList<String> title, ArrayList<String> description, ArrayList<Integer> identitas) {

        konteks = context;
        gambar = posterUrl;
        judul = title;
       // bahasa = subtitle;
        deskripsi = description;
        id = identitas;
    }

    @Override
    public Movie_Adapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(konteks).inflate(R.layout.list_item_movie, parent, false);
        MovieViewHolder mvh = new MovieViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(judul.get(position));
        //holder.data.setText(movies.get(position));
        holder.movieDescription.setText(deskripsi.get(position));

        //holder.rating.setText(movies.get(position).getVoteAverage().toString());

        Picasso.with(konteks).load((String)gambar.get(position)).into(holder.backbg);
        holder.backbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(konteks,MovieDetail.class);
                intent.putExtra("id",id.get(position) );
                intent.putExtra( "poster",gambar.get(position));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                konteks.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return judul.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
       // TextView data;
        TextView movieDescription;

        ImageView backbg;


        public MovieViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title2);
            //data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);

            backbg = (ImageView) v.findViewById(R.id.backbg);
        }
    }
}