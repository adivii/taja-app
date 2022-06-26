package com.utstam.taja;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.utstam.taja.databinding.FragmentAccountRegisterBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountRegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountRegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountRegisterFragment newInstance(String param1, String param2) {
        AccountRegisterFragment fragment = new AccountRegisterFragment();
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

    FragmentAccountRegisterBinding fragmentAccountRegisterBinding;
    Retrofit retrofit;
    AccountApiInterface accountApiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentAccountRegisterBinding = FragmentAccountRegisterBinding.inflate(inflater, container, false);
        WelcomeActivity welcomeActivity = (WelcomeActivity) getActivity();

        retrofit = ApiClient.getClient();
        accountApiInterface = retrofit.create(AccountApiInterface.class);

        fragmentAccountRegisterBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeActivity.changeFragment(welcomeActivity.activityWelcomeBinding.container.getId(), new LoginFragment());
            }
        });

        fragmentAccountRegisterBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentAccountRegisterBinding.password.getText().toString().trim().equals(fragmentAccountRegisterBinding.passwordRe.getText().toString().trim())){
                    Call<List<Account>> callCheck = accountApiInterface.getAccount(fragmentAccountRegisterBinding.username.getText().toString().trim());
                    callCheck.enqueue(new Callback<List<Account>>() {
                        @Override
                        public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                            if(!response.isSuccessful()){
                                Call<Account> callRegister = accountApiInterface.postAccount(fragmentAccountRegisterBinding.username.getText().toString().trim(),
                                        fragmentAccountRegisterBinding.password.getText().toString().trim(),
                                        "farmer");
                                callRegister.enqueue(new Callback<Account>() {
                                    @Override
                                    public void onResponse(Call<Account> call, Response<Account> response) {
                                        if(response.isSuccessful()){
                                            ((WelcomeActivity) getActivity()).username = fragmentAccountRegisterBinding.username.getText().toString().trim();
                                            Toast.makeText(getActivity(), "Account Registered", Toast.LENGTH_SHORT).show();
                                            welcomeActivity.changeFragment(welcomeActivity.activityWelcomeBinding.container.getId(), new UserRegisterFragment());
                                        }else{
                                            Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Account> call, Throwable t) {
                                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                Toast.makeText(getActivity(), "Username Sudah Ada", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Account>> call, Throwable t) {
                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });

        return fragmentAccountRegisterBinding.getRoot();
    }
}