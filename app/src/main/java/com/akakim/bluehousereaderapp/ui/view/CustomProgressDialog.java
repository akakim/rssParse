package com.akakim.bluehousereaderapp.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akakim.bluehousereaderapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-24
 * @since 0.0.1
 */

public class CustomProgressDialog extends Dialog {

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.tvContent)
    TextView tvContent;


    public CustomProgressDialog(@NonNull Context context) {
        super(context);

        setContentView( R.layout.layout_custom_dialog);


        ButterKnife.bind( this );
    }

    @Override
    public void show() {
        super.show();
        progressBar.animate();
    }

    public void setContent(String msg){
        tvContent.setText( msg );
    }


}
