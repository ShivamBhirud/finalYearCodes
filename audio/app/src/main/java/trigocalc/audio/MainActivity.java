package trigocalc.audio;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public Button sng1,sng2,sng3;
    int flag1, flag2, flag3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sng1 = (Button) findViewById(R.id.sng1);
        Button sng2 = (Button)findViewById(R.id.sng2);
        Button sng3 = (Button)findViewById(R.id.sng3);
        final MediaPlayer mp1 = MediaPlayer.create(MainActivity.this,R.raw.humma);
        final MediaPlayer mp2 = MediaPlayer.create(MainActivity.this, R.raw.halka);
        final MediaPlayer mp3 = MediaPlayer.create(MainActivity.this, R.raw.humma);
        sng1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp1.isPlaying()){
                    mp1.seekTo(0);
                }else{
                    flag1 = 1;
                    if(flag2 == 1){
                        mp2.pause();
                        flag2 = 0;
                    }

                    if(flag3 == 1) {
                        mp3.pause();
                        flag3 = 0;
                    }

                    mp1.start();

                }
            }
        });

        sng2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp2.isPlaying()) {
                    mp2.seekTo(0);

                } else{
                    flag2 = 1;
                    if(flag1 == 1){
                        mp1.pause();
                        flag1 = 0;
                    }

                    if(flag3 == 1) {
                        mp3.pause();
                        flag3 = 0;
                    }
                    mp2.start();

                }
            }
        });
        sng3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp3.isPlaying()){
                    mp3.seekTo(0);
                }else{
                    flag3 = 1;
                    if(flag1 == 1){
                        mp1.pause();
                        flag1 = 0;
                    }

                    if(flag2 == 1) {
                        mp2.pause();
                        flag2 = 0;
                    }
                    mp3.start();

                }

            }
        });
    }


}
