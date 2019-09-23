package kr.hs.emirim.sookhee.donerpets_final;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class SawdFragment8 extends Fragment {
    public SawdFragment8() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sawd8, container, false);

        ImageView sawd8 = (ImageView)view.findViewById(R.id.img_sawd8);
        Picasso.get().load("https://bit.ly/2m6xEZS").into(sawd8);


        return view;
    }



}