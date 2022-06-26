package com.utstam.taja;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.utstam.taja.databinding.FragmentUserRegisterBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserRegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserRegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserRegisterFragment newInstance(String param1, String param2) {
        UserRegisterFragment fragment = new UserRegisterFragment();
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

    UserApiInterface userApiInterface;
    AccountApiInterface accountApiInterface;
    FragmentUserRegisterBinding fragmentUserRegisterBinding;
    Retrofit retrofit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentUserRegisterBinding = FragmentUserRegisterBinding.inflate(inflater, container, false);

        retrofit = ApiClient.getClient();
        userApiInterface = retrofit.create(UserApiInterface.class);
        accountApiInterface = retrofit.create(AccountApiInterface.class);

        fragmentUserRegisterBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<User> callRegister = userApiInterface.postUser(((WelcomeActivity) getActivity()).username, fragmentUserRegisterBinding.firstName.getText().toString().trim(), fragmentUserRegisterBinding.lastName.getText().toString().trim());
                callRegister.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                            Call<Account> callDelete = accountApiInterface.deleteAccount(((WelcomeActivity) getActivity()).username);
                            callDelete.enqueue(new Callback<Account>() {
                                @Override
                                public void onResponse(Call<Account> call, Response<Account> response) {
                                    ((WelcomeActivity) getActivity()).changeFragment(((WelcomeActivity) getActivity()).activityWelcomeBinding.container.getId(), new LoginFragment());
                                }

                                @Override
                                public void onFailure(Call<Account> call, Throwable t) {
                                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else{
                            Toast.makeText(getActivity(), "User Registered", Toast.LENGTH_SHORT).show();
                            ((WelcomeActivity) getActivity()).changeFragment(((WelcomeActivity) getActivity()).activityWelcomeBinding.container.getId(), new LoginFragment());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        return fragmentUserRegisterBinding.getRoot();
    }
}