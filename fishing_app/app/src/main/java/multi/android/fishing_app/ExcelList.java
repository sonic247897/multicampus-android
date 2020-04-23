package multi.android.fishing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelList extends AppCompatActivity {
    ListView list_excel;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel_list);
        list_excel = (ListView)findViewById(R.id.list_excel);
        arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        Excel();
    }

    public void Excel() {
        // 엑셀 IO에 사용될 객체
        Workbook workbook = null;
        Sheet sheet = null;
        try {
            InputStream inputStream = getBaseContext().getResources()
                                    .getAssets().open("Freshwater.xlsx");
            // 액셀 객체 생성 - 이거 null뜸!
            workbook = Workbook.getWorkbook(inputStream);
            // 1번째 시트 불러오기
            sheet = workbook.getSheet(0);
            int MaxColumn = 3;
            int RowStart = 0;
            int RowEnd = sheet.getColumn(MaxColumn - 1).length -1;
            int ColumnStart = 2;
            int ColumnEnd = sheet.getRow(2).length - 1;
            for(int row = RowStart; row <= RowEnd; row++) {
                String excelload = sheet.getCell(ColumnStart, row).getContents();
                arrayAdapter.add(excelload);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {
            list_excel.setAdapter(arrayAdapter);
            if(workbook != null){
                workbook.close();
            }
        }
    }

}
