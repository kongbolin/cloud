package com.example.cloud;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class edit extends Activity {
	private String tv_phoneNumber1 = "1223445";
	private Button btn_cancel;
	private Button btn_affirm;
	private EditText text_otherneed;
	private String temp_other;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		temp_other = bundle.getString("something");

		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		btn_affirm = (Button) findViewById(R.id.btn_affirm);
		text_otherneed = (EditText) findViewById(R.id.text_otherneed);
		text_otherneed.setText(temp_other);
		btn_affirm.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// resultCode----结果码
				Intent intent = new Intent();
				// intent.putExtra("otherneed",
				// text_otherneed.getText().toString());
				intent.putExtra("otherneed", text_otherneed.getText().toString());
				setResult(0, intent);
				finish();
			}

		});
		btn_cancel.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// resultCode----结果码
				// Intent intent = new Intent();
				//// intent.putExtra("otherneed",
				// text_otherneed.getText().toString());
				// intent.putExtra("otherneed", null);
				// setResult(0, intent);
				finish();
			}

		});
	}

}
