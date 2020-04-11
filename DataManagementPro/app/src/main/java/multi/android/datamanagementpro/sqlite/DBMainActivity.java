package multi.android.datamanagementpro.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import multi.android.datamanagementpro.R;

// SQL문을 이용해서 작업 - SQL문을 문자열로 표현
// 원래는 뷰(액티비티)와 분리해서 DAO(DB Handler, DB Adapter) 만들어서 자바클래스로 빼야한다.
public class DBMainActivity extends AppCompatActivity {
    EditText id;
    EditText name;
    EditText age;
    TextView result;
    DBHelper dbHelper ; //데이터베이스 파일 생성, 테이블 생성, ..
    SQLiteDatabase db ; //로컬DB연동을 위한 핵심클래스
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbjob_main);
        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        result = findViewById(R.id.result);

        // 1. DBhelper 생성
        dbHelper = new DBHelper(this);
        // 2. SQLiteDatabase객체 생성
        // getReadableDatabase: 읽기 전용, getWritableDatabase: 읽기/쓰기 전용
        // (DB가 파일로 관리되기 때문에 가능)
        db = dbHelper.getWritableDatabase();
    }
    // View v가 매개변수로 가는 이유: 온클릭에 붙이는 메소드라서
    public void insert(View v){
        String sql = "insert into member(id, name, age) values(?,?,?)";
        // void 리턴
        db.execSQL(sql, new String[]{id.getText().toString(),
                                    name.getText().toString(),
                                    age.getText().toString()});
        Toast.makeText(this, "삽입성공", Toast.LENGTH_LONG).show();
    }
    public void selectAll(View v){
        result.setText(""); // append 계속되지 않게 초기화
        String sql = "select * from member";
        // select문은 rawQuery: 노멀하게 사용. 데이터를 받아옴(Cursor: ResultSet과 비슷)
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount(); //레코드 개수를 반환
        Toast.makeText(this, "조회된 row: "+count, Toast.LENGTH_LONG).show();
        while(cursor.moveToNext()) {
            int idx = cursor.getInt(0);
            String id = cursor.getString(1);
            String name = cursor.getString(2);
            int age = cursor.getInt(3);

            result.append("번호: " + idx + "\n" +
                    "아이디: " + id + "\n" +
                    "성명: " + name + "\n"+
                    "나이: "+age+"\n"+
                    "=========================\n");
        }
    }
    // id 기준으로 나이 변경
    public void update(View v){
        String sql = "update member set age = ? where id=?";
        db.execSQL(sql, new String[]{age.getText().toString(),
                id.getText().toString()});
        Toast.makeText(this, "수정성공", Toast.LENGTH_LONG).show();
    }

    public void delete(View v){
        String sql = "delete from member where id=?";
        db.execSQL(sql, new String[]{id.getText().toString()});
        Toast.makeText(this, "삭제성공", Toast.LENGTH_LONG).show();
    }
}
