package multi.android.intent;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReturnDataSecondActivity extends AppCompatActivity {
	String code;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.second2);
	    Button bt1 = (Button)findViewById(R.id.btnClose1);
		Intent intent = getIntent();
		code = intent.getStringExtra("code");
	    bt1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch(code){
					case "call1":
						String data = intent.getStringExtra("info_data");
						Toast.makeText(ReturnDataSecondActivity.this,data,Toast.LENGTH_LONG).show();
				}
			}
		});
	}

}
