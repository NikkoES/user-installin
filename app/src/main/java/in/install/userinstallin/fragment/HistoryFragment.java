package in.install.userinstallin.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import in.install.userinstallin.R;
import in.install.userinstallin.adapter.HistoryAdapter;
import in.install.userinstallin.api.BaseApiService;
import in.install.userinstallin.model.data.History;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private RecyclerView rvHistory;
    private HistoryAdapter adapter;
    List<History> listHistory = new ArrayList<>();

    ProgressDialog loading;

    BaseApiService apiService;


    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        rvHistory = (RecyclerView) v.findViewById(R.id.rv_product);

        adapter = new HistoryAdapter(getContext(), listHistory);

//        apiService = UtilsApi.getAPIService();

        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHistory.setAdapter(adapter);

        refresh();

        return v;
    }

    public void refresh() {
        loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);

        listHistory.add(new History("1", "Windows 10", "x64", "Rp. 20.000", "", "28 Januari 2018", "On Confirming"));
        listHistory.add(new History("2", "Windows 8", "x86", "Rp. 20.000", "", "29 Januari 2018", "On Progress"));
        listHistory.add(new History("3", "Windows 7", "x64", "Rp. 20.000", "", "30 Mei 2018", "On Progress"));
        listHistory.add(new History("4", "Windows XP", "x86", "Rp. 20.000", "", "17 Agustus 2018", "Done"));
        adapter.notifyDataSetChanged();
        loading.dismiss();
    }

}
