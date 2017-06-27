package com.example.cloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class yuesaoedit extends Activity {

	private Button btn_yuesao_cancel;
	private Button btn_yuesao_affirm;
	private EditText text_yuesao_otherneed;
	private String temp_other;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yuesaoedit);
		btn_yuesao_cancel = (Button) findViewById(R.id.btn_yuesao_cancel);
		btn_yuesao_affirm = (Button) findViewById(R.id.btn_yuesao_affirm);
		text_yuesao_otherneed = (EditText) findViewById(R.id.text_yuesao_otherneed);
		Intent intent = this.getIntent(); // ��ȡ���е�intent����
		Bundle bundle = intent.getExtras(); // ��ȡintent�����bundle����
		temp_other = bundle.getString("something");
		text_yuesao_otherneed.setText(temp_other);
		btn_yuesao_affirm.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// resultCode----�����
				Intent intent = new Intent();
				// intent.putExtra("otherneed",
				// text_otherneed.getText().toString());
				intent.putExtra("otherneed", text_yuesao_otherneed.getText().toString());
				setResult(0, intent);
				finish();
			}

		});
		btn_yuesao_cancel.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// resultCode----�����
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
