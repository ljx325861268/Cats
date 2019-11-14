package li.emily.cats;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.net.URL;
import java.util.Set;

import li.emily.cats.ui.model.Cat;
import li.emily.cats.ui.model.Favourites;
import li.emily.cats.ui.model.Image;

public class DetailActivity extends AppCompatActivity {
    TextView name;
    TextView description;
    TextView weight;
    TextView temperament;
    TextView origin;
    TextView lifespan;
    TextView wiki;
    TextView dogfriendliness;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_information);

        Intent intent = getIntent();
        Cat c = new Gson().fromJson(intent.getStringExtra("cat"), Cat.class);

        String catId = c.getId();
        if(catId != null){
            callImageAPI(catId);
        }

        name= findViewById(R.id.id_name);
        description = findViewById(R.id.id_discribution);
        origin = findViewById(R.id.id_origin);
        lifespan = findViewById(R.id.id_lifespan);
        wiki = findViewById(R.id.id_wiki);
        dogfriendliness = findViewById(R.id.id_dogfriendliness);
        weight = findViewById(R.id.id_weight);
        temperament = findViewById(R.id.id_temperament);
        image = findViewById(R.id.id_image);

        name.setText(c.getName());
        description.setText(c.getDescription());
        origin.setText("Origin: " + c.getOrigin());
        lifespan.setText("Lifespan: " + c.getLife_span());
        wiki.setText("Wikipedia URL：\n" + c.getWikipedia_url());
        dogfriendliness.setText("Dog Friendliness: " + c.getDog_friendly());
        temperament.setText("Temperament: "+ c.getTemperament());

        String imperial = c.getWeight().get("imperial");
        String metric = c.getWeight().get("metric");
        weight.setText("Weight (imperial): " + imperial + "\nWeight (metric): " + metric);



    }



    public void getImage(String imageUrl){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = imageUrl;

        final Response.Listener<Bitmap> listener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                image.setImageBitmap(response);
            }
        };

        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error");
            }
        };
        // Request a string response from the provided URL.
        ImageRequest imageRequest = new ImageRequest(url, listener, 0, 0, null, errorListener);

        // Add the request to the RequestQueue.
        queue.add(imageRequest);
    }

    public void callImageAPI(String breed_id){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + breed_id;

        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Image[] url = new Gson().fromJson(response, Image[].class);
                if(url != null && url.length > 0 &&  url[0] != null && url[0].getUrl() != null){
                    getImage(url[0].getUrl());
                }
            }
        };

        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("uh oh");
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);

        queue.add(stringRequest);
    }

    public void onClickFavourites(View v){
        String n = name.getText().toString();
        if(!Favourites.getFavourites().contains(n)) {
            Favourites.addFavourite(n);
        }
    }

}
