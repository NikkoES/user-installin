package in.install.userinstallin.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.install.userinstallin.R;
import in.install.userinstallin.adapter.FragmentSlider;
import in.install.userinstallin.adapter.ProductAdapter;
import in.install.userinstallin.adapter.SliderIndicator;
import in.install.userinstallin.adapter.SliderPagerAdapter;
import in.install.userinstallin.adapter.SliderView;
import in.install.userinstallin.api.BaseApiService;
import in.install.userinstallin.api.UtilsApi;
import in.install.userinstallin.model.data.Product;
import in.install.userinstallin.model.response.ResponseProduct;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    private FragmentActivity myContext;

    private RecyclerView rvHome;
    private ProductAdapter adapter;
    List<Product> listProduct = new ArrayList<>();

    ProgressDialog loading;

    BaseApiService apiService;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        sliderView = (SliderView) v.findViewById(R.id.sliderView);
        mLinearLayout = (LinearLayout) v.findViewById(R.id.pagesContainer);
//        setupSlider();

        rvHome = (RecyclerView) v.findViewById(R.id.rv_product);

        adapter = new ProductAdapter(getContext(), listProduct);

        apiService = UtilsApi.getAPIService();

        rvHome.setHasFixedSize(true);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHome.setAdapter(adapter);

        refresh();

        return v;
    }

    public void refresh() {
        loading = ProgressDialog.show(getContext(), null, "Harap Tunggu...", true, false);

        apiService.getAllProduct().enqueue(new Callback<ResponseProduct>() {
            @Override
            public void onResponse(Call<ResponseProduct> call, Response<ResponseProduct> response) {
                if (response.isSuccessful()){
                    loading.dismiss();

                    listProduct = response.body().getListProduct();

                    rvHome.setAdapter(new ProductAdapter(getContext(), listProduct));
                    adapter.notifyDataSetChanged();
                }
                else {
                    loading.dismiss();
                    Toast.makeText(getContext(), "Failed to Fetch Data !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseProduct> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getContext(), "Failed to Connect Internet !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://image.tmdb.org/t/p/w250_and_h141_bestv2/zYFQM9G5j9cRsMNMuZAX64nmUMf.jpg"));
        fragments.add(FragmentSlider.newInstance("https://image.tmdb.org/t/p/w250_and_h141_bestv2/rXBB8F6XpHAwci2dihBCcixIHrK.jpg"));
        fragments.add(FragmentSlider.newInstance("https://image.tmdb.org/t/p/w250_and_h141_bestv2/biN2sqExViEh8IYSJrXlNKjpjxx.jpg"));
        fragments.add(FragmentSlider.newInstance("https://image.tmdb.org/t/p/w250_and_h141_bestv2/o9OKe3M06QMLOzTl3l6GStYtnE9.jpg"));

        mAdapter = new SliderPagerAdapter(myContext.getSupportFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getContext(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }

}
