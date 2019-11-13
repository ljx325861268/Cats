package li.emily.cats.ui.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import li.emily.cats.R;
import li.emily.cats.ui.adapter.FavouritesAdapter;
import li.emily.cats.ui.model.Favourites;

public class FavouritesFragment extends Fragment {

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