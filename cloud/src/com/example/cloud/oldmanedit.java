package com.example.cloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class oldmanedit extends Activity {

	private Button btn_oldman_cancel;
	private Button btn_oldman_affirm;
	private EditText text_oldman_otherneed;
	private String temp_other;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oldmanedit);
		btn_oldman_cancel = (Button) findViewById(R.id.btn_oldman_cancel);
		btn_oldman_affirm = (Button) findViewById(R.id.btn_oldman_affirm);
		text_oldman_otherneed = (EditText) findViewById(R.id.text_oldman_otherneed);
		Intent intent = this.getIntent(); // 获取已有的intent对象
		Bundle bundle = intent.getExtras(); // 获取intent里面的bundle对象
		temp_other = bundle.getString("something");
		text_oldman_otherneed.setText(temp_other);
		btn_oldman_affirm.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// resultCode----结果码
				Intent intent = new Intent();
				// intent.putExtra("otherneed",
				// text_otherneed.getText().toString());
				intent.putExtra("otherneed", text_oldman_otherneed.getText().toString());
				setResult(0, intent);
				finish();
			}

		});
		btn_oldman_cancel.setOnClickListener(new Button.OnClickListener() {
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
