package com.akakim.bluehousereaderapp.ui;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import com.akakim.bluehousereaderapp.adapter.FloodingAdapter;
import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.manager.WrappingLayoutManager;

import java.util.ArrayList;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2018-07-22
 * @since 0.0.1
 */

public class FloodingPresenter extends RecyclerView.OnScrollListener {

    private boolean         isMoreLoading = true;


    private RecyclerView    recyclerView;
    private FloodingAdapter adapter;
    private WrappingLayoutManager layoutManager;
    ArrayList<? extends BoardData> viewData;
    private LoadListener                                    loadListener;

    private int firstVisibleItem, visibleItemCount, totalItemCount, lastVisibleItem;


    private int visibleThreshold = 1;


    private Handler handler;
    private int currentPage;

    public FloodingPresenter(RecyclerView recyclerView, ArrayList<? extends BoardData> viewData , LoadListener loadListener ) {

        this ( recyclerView, viewData , new WrappingLayoutManager( recyclerView.getContext() ) , loadListener );
    }



    public FloodingPresenter(RecyclerView recyclerView, ArrayList<? extends BoardData> viewData, WrappingLayoutManager layoutManager, LoadListener loadListener) {

        this.recyclerView       = recyclerView;
        this.viewData           = viewData;
        this.layoutManager      = layoutManager;
        this.loadListener       = loadListener;


        adapter = new FloodingAdapter(recyclerView.getContext(),viewData );


        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setAdapter( adapter );
        recyclerView.addOnScrollListener( this );

        handler = new Handler();
        currentPage = 1;
    }


    public boolean isMoreLoading() {
        return isMoreLoading;
    }

    public void setMoreLoading(boolean moreLoading) {
        isMoreLoading = moreLoading;
    }


    public boolean isProgressing(){
        return adapter.isLoading();
    }

    public void removeProgress(){

        adapter.removeDummyProgressView();
//        adapter.notifyItemRemoved( adapter.getItemCount() -1  );
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    /**
     * 스크롤 중이면 계속 동작하는 콜백이다.
     * @param recyclerView
     * @param dx
     * @param dy
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int newState = recyclerView.getScrollState();
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING ){
            visibleItemCount    = recyclerView.getChildCount();
            totalItemCount      = layoutManager.getItemCount();
            firstVisibleItem    = layoutManager.findFirstCompletelyVisibleItemPosition();
            lastVisibleItem     = layoutManager.findLastVisibleItemPosition();


            if( isMoreLoading &&
                    (totalItemCount - visibleItemCount ) <= (firstVisibleItem + visibleThreshold)) {
                    isMoreLoading = false;

                    if( loadListener != null ){

//                        recyclerView.post()
//                        adapter

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                adapter.addDummyProgressView();
                            }
                        });

                        loadListener.onLoad("page",currentPage + 1 );
                    }

            }


        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void updateCurrentPage(){
        this.currentPage++;
    }

    public void notifyDataSetChanged(){
        adapter.notifyDataSetChanged();
    }

    public interface LoadListener {


        void onLoad(String parameter, int nextPage);
    }
}
