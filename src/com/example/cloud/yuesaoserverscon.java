package com.example.cloud;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class yuesaoserverscon extends Activity {
	private Button btn_yuesao_sercancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yuesaoserverscon);
		btn_yuesao_sercancel=(Button) findViewById(R.id.btn_yuesao_sercancel);
		btn_yuesao_sercancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			finish();
			}

		});
	}

}
