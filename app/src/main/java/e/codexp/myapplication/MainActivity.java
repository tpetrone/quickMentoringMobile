package e.codexp.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cadastromentor = (Button) findViewById(R.id.btcadmentor);
        Button cadastromentorado = (Button) findViewById(R.id.btcadmentorado);
        Button login = (Button) findViewById(R.id.btconta);
                cadastromentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mentor = "mentor";
                Intent it = new Intent(MainActivity.this, SignUp.class);
                it.putExtra("mentor", mentor);
                startActivity(it);
            }
        });
        cadastromentorado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mentorado = "mentorado";
                Intent it = new Intent(MainActivity.this, SignUp.class);
                it.putExtra("mentorado", mentorado);
                startActivity(it);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it3 = new Intent(MainActivity.this, Login.class);
                startActivity(it3);
            }
        });
    }
}
