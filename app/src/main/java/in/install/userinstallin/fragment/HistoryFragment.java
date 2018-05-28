package in.install.userinstallin.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.install.userinstallin.R;
import in.install.userinstallin.adapter.HistoryAdapter;
import in.install.userinstallin.api.BaseApiService;
import in.install.userinstallin.api.UtilsApi;
import in.install.userinstallin.model.data.History;
import in.install.userinstallin.model.response.ResponseListHistory;
import in.install.userinstallin.utils.SharedPreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private RecyclerView rvHistory;
    private HistoryAdapter adapter;
    List<History> listHistory = new ArrayList<>();

    ProgressDialog loading;

    BaseApiService apiService;

    private SharedPreferencesUtils userDataSharedPreferences;

    JSONObject userProfile;

    String userData, idUser;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        rvHistory = (RecyclerView) v.findViewById(R.id.rv_product);

        userDataSharedPreferences = new SharedPreferencesUtils(getContext(), "UserData");

        try {
            userData = userDataSharedPreferences.getPreferenceData("userProfile");
            userProfile = new JSONObject(userData);
            idUser = userProfile.get("id_user").toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        adapter = new HistoryAdapter(getContext(), listHistory);

        apiService = UtilsApi.getAPIService();

        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHistory.setAdapter(adapter);

        refresh();

        return v;
    }

    public void refresh() {
        loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);

        apiService.getAllOrderById(idUser).enqueue(new Callback<ResponseListHistory>() {
            @Override
            public void onResponse(Call<ResponseListHistory> call, Response<ResponseListHistory> response) {
                if (response.isSuccessful()){
                    loading.dismiss();

                    listHistory = response.body().getListHistory();

                    rvHistory.setAdapter(new HistoryAdapter(getContext(), listHistory));
                    adapter.notifyDataSetChanged();
                }
                else {
                    loading.dismiss();
                    Toast.makeText(getContext(), "Failed to Fetch Data !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListHistory> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getContext(), "Failed to Connect Internet !", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
