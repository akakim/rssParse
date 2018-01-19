package com.akakim.bluehousereaderapp.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.ui.BlueHouseFragment;
import com.akakim.bluehousereaderapp.ui.activity.BlueHouseBoardFragment;
import com.akakim.bluehousereaderapp.ui.activity.BlueHouseContentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RyoRyeong Kim on 2018-01-19.
 */

public class BlueHouseFragmentAdapter  extends FragmentStatePagerAdapter{

    List<BlueHouseFragment> fragmentList = new ArrayList<>();
    ArrayList<BoardData> boardDataList = new ArrayList<>();

    ArrayList< ArrayList<BoardData>> boardDataListList = new ArrayList<>();
    int defaultSize;

    public BlueHouseFragmentAdapter(FragmentManager fm, List<BlueHouseFragment> fragmentList ) {
        super(fm);

        this.fragmentList = fragmentList;
        this.boardDataListList = boardDataListList;

    }

    public BlueHouseFragmentAdapter(FragmentManager fm, List<BlueHouseFragment> fragmentList, ArrayList< ArrayList<BoardData>> boardDataListList ) {
        super(fm);

        this.fragmentList = fragmentList;
        this.boardDataListList = boardDataListList;

    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        Object obj  = super.instantiateItem(container, position);

        if (obj instanceof Fragment ){

            Bundle bundle  =new Bundle();

            if( position == 0 ){
                bundle.putBoolean(BlueHouseBoardFragment.IS_UPDATE_KEY,false);
                bundle.putParcelableArrayList( BoardData.BOARD_ITEMS_KEY , this.boardDataListList.get(position));

            }else {
                ArrayList<BoardData> empty = new ArrayList<>();
                bundle.putBoolean(BlueHouseBoardFragment.IS_UPDATE_KEY,true);
                bundle.putParcelableArrayList( BoardData.BOARD_ITEMS_KEY ,this.boardDataListList.get(position) );
            }

            ((Fragment) obj).setArguments( bundle );
        }
        return obj;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
