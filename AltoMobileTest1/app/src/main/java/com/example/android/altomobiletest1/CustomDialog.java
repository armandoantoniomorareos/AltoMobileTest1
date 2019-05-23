package com.example.android.altomobiletest1;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CustomDialog extends Dialog implements View.OnClickListener {

    private Button dismiss;
    private TextView msgView;
    private String msg;
    public CustomDialog(Activity a, String msg) {
        super(a);
        this.msg = msg;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_layout);
        dismiss = findViewById(R.id.dismiss_button);
        msgView = findViewById(R.id.custom_dialog_msg);
        msgView.setText(msg);
        dismiss.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dismiss_button:
                dismiss();
                break;
        }
    }
}
