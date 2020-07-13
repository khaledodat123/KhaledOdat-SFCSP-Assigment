package com.khaledodat.assessment.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;
import com.khaledodat.assessment.R;
import com.khaledodat.assessment.data.entities.Quote;
import com.khaledodat.assessment.databinding.FragmentLandpageBinding;
import com.khaledodat.assessment.eventbus.MessageEvent;
import com.khaledodat.assessment.utils.PermissionChecker;
import com.khaledodat.assessment.view.base.BaseFragment;
import com.khaledodat.assessment.viewmodel.LandpageViewModel;

import javax.inject.Inject;

public class LandPageFragment extends BaseFragment<LandpageViewModel, FragmentLandpageBinding> {
    private static final String TAG = "LandPageFragment";

    @Inject
    PermissionChecker mPermissionChecker;

    public static LandPageFragment newInstance() {
        Bundle args = new Bundle();
        LandPageFragment fragment = new LandPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<LandpageViewModel> getViewModel() {
        return LandpageViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_landpage;
    }

    @Override
    protected void onTransitionEnd() {


    }


    @Override
    protected void trackEntranceEvent() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        viewModel.loadRandomQuote();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return _rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataBinding.swipeRefresh.setRefreshing(true);

        dataBinding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.loadRandomQuote();
                dataBinding.swipeRefresh.setRefreshing(true);
            }
        });
        viewModel.getQuoteMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Quote>() {
            @Override
            public void onChanged(Quote quoteResponse) {
                Log.d(TAG, "onChanged: ");
                dataBinding.setQuote(quoteResponse);
                dataBinding.swipeRefresh.setRefreshing(false);
            }
        });

        viewModel.getShowErrorMessage().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Snackbar.make(getView(), "No Internet Access", Snackbar.LENGTH_LONG).show();
                dataBinding.swipeRefresh.setRefreshing(false);

                viewModel.loadSavedQuote().observe(getViewLifecycleOwner(), new Observer<Quote>() {
                    @Override
                    public void onChanged(Quote quote) {
                        dataBinding.setQuote(quote);
                    }
                });
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.csfcp_demo_title));
    }

    @Override
    public void onMessageEvent(MessageEvent event) {
        if (event.getSentTo() != this.getClass())
            return;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        getActivity().getMenuInflater().inflate(R.menu.menu_landpage, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_refresh){
            viewModel.loadRandomQuote();
        }
        return super.onOptionsItemSelected(item);
    }


}
