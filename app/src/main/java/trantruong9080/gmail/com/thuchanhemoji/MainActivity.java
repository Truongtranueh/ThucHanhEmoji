package trantruong9080.gmail.com.thuchanhemoji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<Emoji> data;
    EmojiAdapter adapter;
    int myrandom;
    int wrong = 0;
    TextView txtselect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gr_view);
        data = new ArrayList<>();
        data.add(new Emoji("Buon",R.drawable.buon));
        data.add(new Emoji("Cuoi",R.drawable.cuoi));
        data.add(new Emoji("Cuoi nuoc mat",R.drawable.cuoinuocmat));
        data.add(new Emoji("Nhay Mat",R.drawable.nhaymat));
        data.add(new Emoji("Tuc Gian",R.drawable.tucgian));
        data.add(new Emoji("Tu Tin",R.drawable.tutin));
        data.add(new Emoji("Mang khau trang",R.drawable.mangkhautrang));
        data.add(new Emoji("Mang kinh",R.drawable.mangkinh));
        data.add(new Emoji("Khoc",R.drawable.khoc));


        Random rd = new Random();
        myrandom = rd.nextInt(data.size());
        txtselect = findViewById(R.id.txtviewselect);
        txtselect.setText(data.get(myrandom).name);

        adapter = new EmojiAdapter(this,R.layout.dongemoji,data);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String select = data.get(i).name;
                if(txtselect.getText().toString() == select){
                    data.remove(i);
                    adapter.notifyDataSetChanged();
                    Intent sucess = new Intent(MainActivity.this,Sucessful.class);
                    startActivity(sucess);
                }
                else {
                    wrong++;
                    Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_LONG).show();
                    if(wrong > 3){
                        Intent fail = new Intent(MainActivity.this,Failed.class);
                        startActivity(fail);
                    }

                }
            }
        });
    }
}