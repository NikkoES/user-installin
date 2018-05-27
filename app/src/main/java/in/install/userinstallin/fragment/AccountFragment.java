package in.install.userinstallin.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import in.install.userinstallin.R;
import in.install.userinstallin.activity.EditProfileActivity;
import in.install.userinstallin.activity.LoginActivity;
import in.install.userinstallin.activity.MainActivity;
import in.install.userinstallin.activity.RegisterActivity;
import in.install.userinstallin.utils.SharedPreferencesUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    Button btnEditProfile, btnLogout;
    ImageView imgUser;
    TextView txtNama, txtEmail, txtPhone, txtAlamat;

    private SharedPreferencesUtils userDataSharedPreferences;

    JSONObject userProfile;

    String userData;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_account, container, false);

        imgUser = v.findViewById(R.id.img);
        txtNama = v.findViewById(R.id.txt_nama_user);
        txtEmail = v.findViewById(R.id.txt_email_user);
        txtPhone = v.findViewById(R.id.txt_no_hp_user);
        txtAlamat = v.findViewById(R.id.txt_alamat_user);

        userDataSharedPreferences = new SharedPreferencesUtils(getContext(), "UserData");

        try {
            userData = userDataSharedPreferences.getPreferenceData("userProfile");
            userProfile = new JSONObject(userData);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        try {
            txtNama.setText(userProfile.get("name").toString());
            txtEmail.setText(userProfile.get("email").toString());
            txtPhone.setText(userProfile.get("phonenumber").toString());
            txtAlamat.setText(userProfile.get("alamat").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        btnEditProfile = v.findViewById(R.id.btn_edit_profile);
        btnLogout = v.findViewById(R.id.btn_logout);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
                ((Activity) getContext()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Anda yakin ingin keluar ?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                userDataSharedPreferences.removeData("userProfile");
                                getActivity().finish();
                                Intent intentLogin = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intentLogin);
                                ((Activity) getContext()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setCancelable(false)
                        .show();
            }
        });

        return v;
    }

}
