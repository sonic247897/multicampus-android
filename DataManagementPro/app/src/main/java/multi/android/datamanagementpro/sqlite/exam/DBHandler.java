package multi.android.datamanagementpro.sqlite.exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import multi.android.datamanagementpro.sqlite.DBHelper;

// DAO
public class DBHandler {
    static ExamDBHelper dbHelper;
    static SQLiteDatabase db;

    public static DBHandler open(Context context) {
        // 1. DBhelper 생성
        dbHelper = new ExamDBHelper(context);
        // 2. SQLiteDatabase객체 생성
        // getReadableDatabase: 읽기 전용, getWritableDatabase: 읽기/쓰기 전용
        // (DB가 파일로 관리되기 때문에 가능)
        db = dbHelper.getWritableDatabase();
        return new DBHandler();
    }

    public void insert(String edtName, String edtSu, String edtPrice) {
        // 컬럼에 저장할 값을 관리하는 ContentValues를 이용
        // Map 구조 - key, value
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", edtName);
        contentValues.put("su", edtSu);
        contentValues.put("price", edtPrice);
        contentValues.put("totPrice", Integer.parseInt(edtSu)*Integer.parseInt(edtPrice));

        db.insert("product", null, contentValues);
        Log.d("insert", "삽입 성공");
    }

    public ArrayList<Product> list() {
        ArrayList<Product> productList = new ArrayList<Product>();
        Product product = null;

        Cursor cursor = db.query("product", null, null,
                null, null, null, null);
        int count = cursor.getCount(); //레코드 개수를 반환
        Log.d("lsit", "조회된 row: " + count);
        while (cursor.moveToNext()) {
            int idx = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            int su = cursor.getInt(3);
            int totPrice = cursor.getInt(4);
            product = new Product(idx, name, price, su, totPrice);
            productList.add(product);
        }
        return productList;
    }


    public ArrayList<Product> search(String id) {
        ArrayList<Product> productList = new ArrayList<Product>();
        Product product = null;

        Cursor cursor = db.query("product", null, "name like ?",
                new String[]{"%"+id+"%"}, null, null, null);
        int count = cursor.getCount(); //레코드 개수를 반환
        Log.d("lsit", "조회된 row: " + count);
        while (cursor.moveToNext()) {
            int idx = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            int su = cursor.getInt(3);
            int totPrice = cursor.getInt(4);
            product = new Product(idx, name, price, su, totPrice);
            productList.add(product);
        }
        return productList;
    }

    public Product find(String id) {
        Product product = null;
        Cursor cursor = db.query("product", null, "_id = ?",
                new String[]{id}, null, null, null);
        // 원소가 하나만 있어도 커서를 한번 넘겨야 한다(ResultSet과 같음)
        if (cursor.moveToNext()) {
            int idx = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            int su = cursor.getInt(3);
            int totPrice = cursor.getInt(4);
            product =  new Product(idx, name, price, su, totPrice);
        }
        return product;
    }
}
