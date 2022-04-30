package com.utstam.taja;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.utstam.taja.databinding.ActivityMainBinding;
import com.utstam.taja.databinding.FragmentProfileBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
    FragmentProfileBinding fragmentProfileBinding;
    FragmentChangeListener fragmentChangeListener;

    // Variable to handle view component
    Button aboutButton;
    TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize Variable
        activityMainBinding = ActivityMainBinding.inflate(inflater);
        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false);
        fragmentChangeListener = (FragmentChangeListener) getActivity();
        aboutButton = fragmentProfileBinding.btnAbout;

        // Fill the name slot
        name = fragmentProfileBinding.name;
        name.setText(String.format("%s %s", getString(R.string.first_name), getString(R.string.last_name)));

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentChangeListener.changeFragment(activityMainBinding.container.getId(), new AboutFragment());
            }
        });

        return fragmentProfileBinding.getRoot();
    }
}