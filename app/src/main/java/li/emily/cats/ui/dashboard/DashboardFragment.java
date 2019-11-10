package li.emily.cats.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import li.emily.cats.R;
import li.emily.cats.ui.adapter.FavouritesAdapter;
import li.emily.cats.ui.adapter.RecyclerViewAdapter;
import li.emily.cats.ui.model.Cat;
import li.emily.cats.ui.model.Favourites;

public class DashboardFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);



        return root;
    }

    public void onViewCreated(View v, Bundle savedInstnceState){
        RecyclerView recyclerView = getView().findViewById(R.id.id_rv_favourites);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        FavouritesAdapter itemAdapter = new FavouritesAdapter(Favourites.getFavourites());
        recyclerView.setAdapter(itemAdapter);
    }
}