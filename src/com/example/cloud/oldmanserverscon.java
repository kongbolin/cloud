package com.example.cloud;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class oldmanserverscon extends Activity {
	private Button btn_oldman_sercancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oldmanserverscon);
		btn_oldman_sercancel=(Button) findViewById(R.id.btn_oldman_sercancel);
		btn_oldman_sercancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			finish();
			}

		});
	}

}
