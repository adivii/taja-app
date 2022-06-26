package com.utstam.taja;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.utstam.taja.databinding.FragmentLoginBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

    FragmentLoginBinding fragmentLoginBinding;
    AccountApiInterface accountApiInterface;
    Retrofit retrofit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        WelcomeActivity welcomeActivity = (WelcomeActivity) getActivity();

        retrofit = ApiClient.getClient();
        accountApiInterface = retrofit.create(AccountApiInterface.class);

        fragmentLoginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<List<Account>> call = accountApiInterface.getAccount(fragmentLoginBinding.username.getText().toString().trim());
                call.enqueue(new Callback<List<Account>>() {
                    @Override
                    public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        List<Account> accounts = response.body();
                        Log.d(TAG, accounts.get(0).getPassword());

                        if(accounts.get(0).getPassword().equals(fragmentLoginBinding.password.getText().toString().trim())){
                            Toast.makeText(getActivity(), "Login Success", Toast.LENGTH_SHORT).show();
                            if(accounts.get(0).getRole().equals("farmer")){
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.putExtra("username", accounts.get(0).getUsername());
                                intent.putExtra("role", "Farmer");
                                startActivity(intent);
                                welcomeActivity.finish();
                            }
                        }else{
                            Toast.makeText(getActivity(), "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Account>> call, Throwable t) {
                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        fragmentLoginBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeActivity.changeFragment(welcomeActivity.activityWelcomeBinding.container.getId(), new AccountRegisterFragment());
            }
        });

        return fragmentLoginBinding.getRoot();
    }
}