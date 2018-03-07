package e.codexp.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.InputStream;
import java.net.URL;

public class Profile extends AppCompatActivity {
    private ImageButton imgperfil;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imgperfil = (ImageButton) findViewById(R.id.imgperfil);
        imgperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent image = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(image, RESULT_LOAD_IMAGE);}
                protected void onActivityResult(int requestCode, int resultCode, Intent data){
                    Profile.super.onActivityResult(requestCode, resultCode, data);
                if (resultCode == RESULT_OK) {
                         class PhotoFromUri extends AsyncTask<Void, Bitmap, Bitmap> {
                        private Uri fotoUrl;

                        public PhotoFromUri(Uri fotoUrl) {
                            this.fotoUrl = fotoUrl;
                        }

                        @Override
                        protected Bitmap doInBackground(Void... params) {
                            Bitmap bitmap = null;

                            try {
                                InputStream is = new URL(fotoUrl.toString()).openStream();
                                bitmap = BitmapFactory.decodeStream(is);
                                is.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                Log.e("PhotoFromUri", ex.getMessage());
                            }

                            return bitmap;
                        }
                    }
                }
            }
        });

    }}


