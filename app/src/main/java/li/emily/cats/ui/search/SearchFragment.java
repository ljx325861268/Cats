package li.emily.cats.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;


import java.util.HashMap;

import li.emily.cats.DetailActivity;
import li.emily.cats.R;
import li.emily.cats.ui.adapter.RecyclerViewAdapter;
import li.emily.cats.ui.model.Cat;
import li.emily.cats.ui.model.Image;

public class SearchFragment extends Fragment implements RecyclerViewAdapter.OnNoteListener{

    private EditText editText;
    private ImageView icon;
    private HashMap<Integer, Cat> catMap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        return root;
    }

    public void onViewCreated(View v, Bundle savedInstnceState){
        editText = getView().findViewById(R.id.id_searchtext);
        icon = getView().findViewById(R.id.id_searchicon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAPI(editText.getText().toString());
            }
        });

        catMap = new HashMap<Integer, Cat>();
    }

    public void callAPI(String searchTerm){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="https://api.thecatapi.com/v1/breeds/search?q=" + searchTerm;
        //String url = "https://api.thecatapi.com/v1/images/search?breed_id=" + searchTerm;
        catMap.clear();

        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Cat[] res = new Gson().fromJson(response, Cat[].class);
                for(int i = 0; i < res.length; i++){
                    catMap.put(i, res[i]);
                }
                updateRecyclerView();
            }
        };

        final Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("uh oh");
            }
        };
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void updateRecyclerView(){
        RecyclerView recyclerView = getView().findViewById(R.id.id_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapter itemAdapter = new RecyclerViewAdapter(catMap, this);
        recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public void onNoteClick(int position) {
        Cat cat = catMap.get(position);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("cat", new Gson().toJson(cat));
        startActivity(intent);
    }


}