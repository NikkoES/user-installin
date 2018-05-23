package in.install.userinstallin.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
import in.install.userinstallin.R;
import in.install.userinstallin.activity.EditProfileActivity;
import in.install.userinstallin.activity.LoginActivity;
import in.install.userinstallin.activity.MainActivity;
import in.install.userinstallin.activity.RegisterActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {


    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account, container, false);

        ButterKnife.bind(getContext(), v);

        return v;
    }

    @OnClick(R.id.btn_edit_profile)
    public void editProfile(){
        startActivity(new Intent(getActivity(), EditProfileActivity.class));
        ((Activity) getContext()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @OnClick(R.id.btn_logout)
    public void logout(){
        startActivity(new Intent(getActivity(), LoginActivity.class));
        ((Activity) getContext()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

}
