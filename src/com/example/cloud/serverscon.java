package com.example.cloud;

import java.net.ContentHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class serverscon extends Activity {
	private Button btn_sercancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	setContentView(R.layout.serverscon);
	btn_sercancel=(Button) findViewById(R.id.btn_sercancel);
	btn_sercancel.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		finish();
		}

	});
	}

}
