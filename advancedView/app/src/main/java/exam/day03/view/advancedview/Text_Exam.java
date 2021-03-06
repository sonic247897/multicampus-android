package exam.day03.view.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Text_Exam extends AppCompatActivity {
    EditText txtarea1;
    EditText txtarea2;
    EditText inputdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_exam);
        txtarea1 = findViewById(R.id.area1);
        txtarea2 = findViewById(R.id.area2);
        inputdata = findViewById(R.id.input);
        Button send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            // setText: 교체
            // append: 누적
            @Override
            public void onClick(View v) {
                txtarea1.append(inputdata.getText()+"\n");
                txtarea2.append(inputdata.getText()+"\n");
                inputdata.setText("");
            }
        });
    }
}
