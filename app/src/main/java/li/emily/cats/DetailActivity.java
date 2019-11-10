package li.emily.cats;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.net.URL;

import li.emily.cats.ui.model.Cat;

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
        System.out.println(c);

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
        origin.setText(c.getOrigin());
        lifespan.setText("LIFESPAN: " + c.getLife_span());
        wiki.setText(c.getWikipedia_url());
        dogfriendliness.setText(c.getDog_friendly());
        temperament.setText(c.getTemperament());

        String imperial = c.getWeight().get("imperial");
        String metric = c.getWeight().get("metric");
        weight.setText("Imperial: " + imperial + "\n Metric: " + metric);


        try {
            URL url = new URL("https://cdn2.thecatapi.com/images/gVrhv_yAY.jpg");
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            image.setImageBitmap(bmp);
        } catch (Exception e) {

        }


    }

}
