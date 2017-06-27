package com.example.cloud;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class baomuserverscon extends Activity {
	private Button btn_baomu_sercancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.baomuserverscon);
		btn_baomu_sercancel=(Button) findViewById(R.id.btn_baomu_sercancel);
		btn_baomu_sercancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			finish();
			}

		});
	}

}
