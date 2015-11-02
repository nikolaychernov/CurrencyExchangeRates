package com.nikolaychernov.currencyexchangerates;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import uk.co.ribot.easyadapter.EasyRecyclerAdapter;

public class MainActivity extends AppCompatActivity {

    //@Bind(R.id.recycler_valutes)
    RecyclerView mCharactersRecycler;

    //@Bind(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefresh;


    private EasyRecyclerAdapter<Valute> mEasyRecycleAdapter;
    private CompositeSubscription mSubscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mCharactersRecycler = (RecyclerView) findViewById(R.id.recycler_valutes);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        mSubscriptions = new CompositeSubscription();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setupRecyclerView();
        reSyncValute();
    }

    private void setupRecyclerView() {
        mCharactersRecycler.setLayoutManager(new LinearLayoutManager(this));
        mEasyRecycleAdapter = new EasyRecyclerAdapter<>(this, ValuteHolder.class);
        mCharactersRecycler.setAdapter(mEasyRecycleAdapter);

        mSwipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reSyncValute();
            }
        });
    }

    private void reSyncValute() {
        RetrofitHelper helper = new RetrofitHelper();
        CentrobankService centrobankService = helper.newCentrobankService();
        mSubscriptions.add(centrobankService.getValute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ValCurs>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable error) {
                    }

                    @Override
                    public void onNext(ValCurs valCurs) {
                        mSwipeRefresh.setRefreshing(false);
                        mEasyRecycleAdapter.setItems(valCurs.valutes);
                        mEasyRecycleAdapter.notifyDataSetChanged();
                    }
                }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
