package multi.android.datamanagementpro.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

// 헬퍼클래스는 앱 별로 하나씩 만든다
// DB 버전 관리 - 데이터베이스가 업데이트 되거나 DB를 처음 생성할 때
public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 3;
    // 안드로이드 대부분 프로그램: 생성자 만들때 super(Context) 필요
    public DBHelper(Context context){
        // 파일 형태로 데이터가 저장된다. (내부저장소) - 이 자체가 데이터베이스를 오픈하고 연결하는 작업
        // CursorFactory: 자바의 ResultSet과 같은개념
        // DB_VERSION: 테이블의 스키마를 변경하면 버전이 하나 올라간다!
        // .db 파일 = 데이터베이스 파일
        super(context, "test.db", null, DB_VERSION);
    }
    // 앱이 설치되고 SQLiteOpenHelper가 최초로 호출될 때 한 번만 실행
    // 테이블이 생성되고 필요하면 초기화 작업
    // 앱을 최초로 다운받는 사람들을 위해서 만들어 놓은 메소드 - 항상 최신으로 유지
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("dbtest", "데이터베이스가 생성되었습니다.");
        // 테이블 생성
        String sql = "create table if not exists member("
                    + "idx integer primary key autoincrement,"
                    + "id text,"
                    + "name text,"
                    + "age integer)";
        db.execSQL(sql);
    }
    // -> data>data>패키지명>databases>test.db

    // 데이터베이스의 버전이 변경될 때마다 호출되는 메소드
    // 스키마가 변경되면 호출되어 업데이트에 관련된 여러가지 처리를 구현
    // 기존 사용자들이 변경된 내용을 반영하려 할 때 호출되는 메소드 - DB_VERSION이 바뀌면 호출
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("dbtest",
                "데이터베이스의 스키마가 변경되었습니다. oldVersion: "
                        +oldVersion+"==>newVersion: "+newVersion);
        switch (oldVersion){
            // break 안 걸어서 처리해야할 것들 쭉 처리한다.
            case 1:
                // 1버전에서 2버전으로 넘어갈 때 처리해야 하는 일들을 구현(DB에 한함)
                Log.d("dbtest", "2버전으로 변경");
            case 2:
                // 2버전에서 3버전으로 넘어갈 때
            case 3:
                // 3버전에서 4버전으로 넘어갈 때

        }
    }
}
