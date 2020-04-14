package multi.android.datamanagementpro.sqlite.exam;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

import multi.android.datamanagementpro.R;
import multi.android.datamanagementpro.sqlite.DBHelper;


public class MainActivity extends
		AppCompatActivity implements AdapterView.OnItemClickListener,OnClickListener {
	DBHandler handler;
	EditText edtName;
	EditText edtSu;
	EditText edtPrice;
	ListView listview;
	ArrayList<HashMap<String,String>> list;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		handler = DBHandler.open(this);

		findViewById(R.id.btnIns).setOnClickListener(this);
		findViewById(R.id.btnResult).setOnClickListener(this);
		findViewById(R.id.btnResult2).setOnClickListener(this);
		findViewById(R.id.btnSearch).setOnClickListener(this);
		listview = findViewById(R.id.list);
		listview.setOnItemClickListener(this);

		edtName = (EditText)findViewById(R.id.edtName);
		edtSu = (EditText)findViewById(R.id.edtSu);
		edtPrice = (EditText)findViewById(R.id.edtPrice);

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.btnIns:
				handler.insert(edtName.getText().toString(),
						edtSu.getText().toString(), edtPrice.getText().toString());
				break;
			case R.id.btnResult:
				printView(handler.list(), "list1");
				break;
			case R.id.btnResult2:
				printView(handler.list(), "list2");
				break;
			case R.id.btnSearch:
				printView(handler.search(edtName.getText().toString()), "search");
				break;
		}

	}
	// 뷰 출력 (원래 뷰에서 파싱하면 안되므로 CustomAdapter를 사용해야 한다)
	public void printView(ArrayList<Product> productList, String listView){
		if(listView.equals("list1")){
			list = new ArrayList<>();
			HashMap<String,String> map = null;
			for (Product product: productList) {
				map = new HashMap<String, String>();
				map.put("id", product._id+"");
				map.put("id", product._id+"");
				map.put("all", product._id+", "+product.name+", 수량: "+product.price
						+", 가격: "+product.su+", 전체가격: "+product.totPrice);
				list.add(map);
			}
			SimpleAdapter adapter = new SimpleAdapter(this,
					list, android.R.layout.simple_list_item_1,
					new String[]{"all"},
					new int[]{android.R.id.text1}
			);
			listview.setAdapter(adapter);
		}else {
			list = new ArrayList<>();
			HashMap<String, String> map = null;
			for (Product product : productList) {
				map = new HashMap<String, String>();
				map.put("id", product._id+"");
				map.put("name", product.name);
				map.put("rest", "가격: " + product.price
						+ ", 수량: " + product.su + ", 전체가격: " + product.totPrice);
				list.add(map);
			}
			SimpleAdapter adapter = new SimpleAdapter(this,
					list, android.R.layout.simple_list_item_2,
					new String[]{"name", "rest"},
					new int[]{android.R.id.text1, android.R.id.text2}
			);
			listview.setAdapter(adapter);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// intent 넘겨서 read.xml로
		Intent intent = new Intent(this, ReadActivity.class);
		Log.d("click", list.get(position).get("id"));
		Product product = handler.find(list.get(position).get("id"));
		intent.putExtra("product", product);
		startActivity(intent);
	}
}













