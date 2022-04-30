package com.utstam.taja;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.utstam.taja.databinding.ActivityMainBinding;
import com.utstam.taja.databinding.FragmentContentBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContentFragment newInstance(String param1, String param2) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    ActivityMainBinding activityMainBinding;
    FragmentContentBinding fragmentContentBinding;
    FragmentChangeListener fragmentChangeListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activityMainBinding = ActivityMainBinding.inflate(inflater);
        fragmentContentBinding = FragmentContentBinding.inflate(inflater, container, false);
        fragmentChangeListener = (FragmentChangeListener) getActivity();

        // Load Image
        Picasso.
                with(getContext()).
                load(R.drawable.tutorial_segment_background).
                fit().
                centerCrop().
                into(fragmentContentBinding.tutorialImage);

        Picasso.
                with(getContext()).
                load(R.drawable.event_segment_background).
                fit().
                centerCrop().
                into(fragmentContentBinding.eventImage);

        fragmentContentBinding.tutorialClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentChangeListener.changeFragment(activityMainBinding.container.getId(), new TutorialFragment());
            }
        });

        fragmentContentBinding.eventClickable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentChangeListener.changeFragment(activityMainBinding.container.getId(), new EventFragment());
            }
        });

        return fragmentContentBinding.getRoot();
    }
}