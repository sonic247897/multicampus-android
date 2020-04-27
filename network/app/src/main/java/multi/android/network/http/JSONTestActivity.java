package multi.android.network.http;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import multi.android.network.R;

public class JSONTestActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ListView listView;
    ArrayList<ProductDTO> list;
    ProductAdapter itemAdapter;
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j_s_o_n_test);
        listView = findViewById(R.id.listView);
        container = findViewById(R.id.container);
        list = new ArrayList<>();
       // progressDialog = new ProgressDialog(this);
        HttpTest task = new HttpTest();
        task.execute();

    }

    // 백단에서 작업!
    class HttpTest extends AsyncTask<Void,Void,String>{

       /* @Override
        protected void onPreExecute() {
            progressDialog.setTitle("HTTP Connect ..");
            progressDialog.setMessage("Please Wait..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }*/
        @Override
        protected String doInBackground(Void... voids) {
            URL url = null;
            BufferedReader in=null;
            // background작업이 모두 완료되면 onPostExecute를 호출하며 전달할 데이터
            //  ===> 웹서버에서 받아온 json데이터
            String data="";
            //progressDialog.dismiss();
            try {
                // 메인스레드에서 네트워크 스레드를 실행하려고 하면
                //android.os.NetworkOnMainThreadException이 발생한다.
                //메인쓰레드는 정지시킬 수 없다.
                // 웹 상의 리소스를 가져오기 위해서 URL객체를 생성
                // JSON 형태로 요청하는 컨트롤러 경로
                String path = "http://70.12.115.55:8088/bigdataShop/product/show_json";
                url = new URL(path);
                // 웹서버에 연결 (java.net)
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                //request에 대한 설정정보
                // 웹 : MIME Type 정의 - 연결된 HttpURLConnection에 정보 저장
                connection.setRequestProperty("Content-Type", "application/json");
                // 응답을 정상으로 받았을 때 실행하겠다는 의미
                if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    // 연결된 서버에서 응답메시지를 받은 경우 응답메시지를
                    // BufferedReader로 읽어온다. (한글포함 - InputStreamReader)
                    // - JSON데이터가 모두 BufferedReader에서 읽을 수 있도록 설정
                    in = new BufferedReader(
                            new InputStreamReader(
                                    connection.getInputStream(), "UTF-8"));
                }
                data = in.readLine(); // JSON데이터가 한줄로 쭉 되어있다.
                Log.d("myhttp", data);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }

        // 끝나고 데이터 받아서 JSON Array로 만드는 작업
        // JSON Array [{"name":"장동건","age":"40"},{"name":"장동건","age":"40"}]
        // JSON Object {"name":"장동건","age":"40"}
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // progressDialog.dismiss();

            // 웹서버에서 가져온 데이터가 json형식이므로
            // 파싱해서 JSON Object에서 3개만 빼서 ProductDTO로 변환
            // 변환한 ProductDTO를 ArrayList에 저장

            // 서버에서 받아온 데이터
            JSONArray ja = null;
            try {
                ja = new JSONArray(s);
                // JSON Object 만들기
                for(int i=0;i<ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);
                    String name = jo.getString("prd_no");
                    String id = jo.getString("prd_nm");
                    String img = jo.getString("img_gen_file_nm");

                    ProductDTO item = new ProductDTO(id,name,img);
                    list.add(item);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            // onPostExecute = UI 스레드이므로 뷰 작업!
            itemAdapter = new ProductAdapter(list);
            listView.setAdapter(itemAdapter);

        }
    }

    // 뷰 홀더, 재활용 코드 빠짐(나중에 추가해야 함!)
    class ProductAdapter extends BaseAdapter {
        ArrayList<ProductDTO> alist;

        public ProductAdapter(ArrayList<ProductDTO> alist) {
            this.alist = alist;
        }

        @Override
        public int getCount() {
            return alist.size();
        }

        @Override
        public Object getItem(int position) {
            return alist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = null;
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.item, container, true);
            TextView prd_no = itemView.findViewById(R.id.textView);
            TextView prd_nm = itemView.findViewById(R.id.textView2);

            final ImageView imageView = itemView.findViewById(R.id.imageView);

            prd_no.setText(alist.get(position).getPrd_no());
            prd_nm.setText(alist.get(position).getPrd_nm());

            // 이미지 파일명 가져옴
            String img = alist.get(position).getImg_gen_file_nm();
            // 리소스가 없으므로 이미지 다운받아서 비트맵으로 만들어서 연결
            // 내 서버 IP/ spring-config.xml에 정의되어 있는 resource를 부르는 방법을 따라야 한다!
            // <resources mapping="/images/**" location="/WEB-INF/static/images/" />
            //          -> bigdataShop/images/
            img = "http://70.12.115.55:8088/bigdataShop/images/product/"+img;
            final String finalImg = img;
            // 어댑터가 실행될때 별도의 작업으로 만들어야 하므로 스레드로 만듬
            // @!안드로이드에서는 >별도의 작업 or 네트워크 관련 작업<은 무조건 스레드로 만들어야 한다!
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    URL url = null;
                    InputStream is = null;
                    try{
                        url = new URL(finalImg);
                        // openStream: 다운로드 받아옴
                        is = url.openStream();
                        // 안드로이드 스투디오: BitmapFactory로 비트맵 이미지로 알아서 만들어옴
                        final Bitmap bm = BitmapFactory.decodeStream(is);
                        // 스레드에서 뷰를 접근하기 위해서 스레드 하나 더 만듬: setting
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bm);
                            }
                        });
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            return itemView;
        }
    }
}
