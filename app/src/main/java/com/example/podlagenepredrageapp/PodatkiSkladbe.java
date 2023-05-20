package com.example.podlagenepredrageapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class PodatkiSkladbe  extends AppCompatActivity {

    TextView playerposition,playerduration;
    SeekBar seek;
    ImageView btRew,btPlay,btPause,btForward;
    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Runnable runnable;
    Skladba s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.podatkiskladbe);

        /*
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(PodatkiSkladbe.this, MainActivity.class);
                startActivity(intent);
                }

        });*/

        Intent intent = getIntent();
        s = (Skladba) intent.getSerializableExtra("skladba");
        //System.out.println(title);
        TextView t = findViewById(R.id.naslov);
        t.setText(String.valueOf(s.vrniNaslov()));
        TextView Podatki_avtor = findViewById(R.id.Podatki_avtor);
        TextView Podatki_cena = findViewById(R.id.Podatki_Cena);
        TextView Podatki_zvrst = findViewById(R.id.Podatki_zvrst);
        Podatki_avtor.setText("Produciral: " + s.VrniAvtorja());
        Podatki_cena.setText( "Cena najema: " + s.VrniCeno() + " Cena Ekskluzivnega nakupa: " + s.VrniCenoEksluzivno());
        Podatki_zvrst.setText("Spada pod zvrst: " + s.VrniZvrst());




        //player
        playerduration = findViewById(R.id.player_duration);
        playerposition = findViewById(R.id.player_position);
        seek = findViewById(R.id.seek_bar);
        btRew = findViewById(R.id.bt_rew);
        btPlay = findViewById(R.id.bt_play);
        btPause = findViewById(R.id.bt_pause);
        btForward = findViewById(R.id.bt_ff);

        mediaPlayer = MediaPlayer.create(this,R.raw.song);

        runnable = new Runnable() {
            @Override
            public void run() {
                seek.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,500);
            }
        };
        int duration = mediaPlayer.getDuration();
        String sDuration = convertFormat(duration);
        playerduration.setText(sDuration);
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlay.setVisibility(View.GONE);
                btPause.setVisibility(View.VISIBLE);
                mediaPlayer.start();
                seek.setMax(mediaPlayer.getDuration());
                handler.postDelayed(runnable,0);

            }
        });

        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPause.setVisibility(View.GONE);
                btPlay.setVisibility(View.VISIBLE);
                mediaPlayer.pause();
                handler.removeCallbacks(runnable);

            }
        });

        btForward.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                int duration = mediaPlayer.getDuration();
                if( mediaPlayer.isPlaying() && duration != currentPosition){
                    currentPosition = currentPosition + 5000;
                    playerposition.setText(convertFormat(currentPosition));
                    mediaPlayer.seekTo(currentPosition);
                }
            }

        });
        btRew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                if(mediaPlayer.isPlaying() && currentPosition > 5000){
                    currentPosition = currentPosition - 5000;
                    playerposition.setText(convertFormat(currentPosition));
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }
                playerposition.setText(convertFormat(mediaPlayer.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btPause.setVisibility(View.GONE);
                btPlay.setVisibility(View.VISIBLE);
                mediaPlayer.seekTo(0);

            }
        });
    }
       @SuppressLint("DefaultLocale")
       private String convertFormat(int duration){
            return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration),
                    TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
        }


    public void Pricni_Nakup(View view) {
        if(btPause.getVisibility() != View.GONE){
        btPause.setVisibility(View.GONE);
        btPlay.setVisibility(View.VISIBLE);
        mediaPlayer.pause();
        handler.removeCallbacks(runnable);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Izberite tip nakupa")
                .setPositiveButton("Ekskluziven Nakup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent placilo = new Intent(PodatkiSkladbe.this,ZmUporabnikKupiSkladbo_.class);
                        placilo.putExtra("skladba_placilo", s);
                        placilo.putExtra("tip","Ekskluzivno");
                        startActivity(placilo);
                    }
                })
                .setNegativeButton("Najem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent placilo = new Intent(PodatkiSkladbe.this,ZmUporabnikKupiSkladbo_.class);
                        placilo.putExtra("skladba_placilo", s);
                        placilo.putExtra("tip","Najem");
                        startActivity(placilo);
                    }
                })
                .setNeutralButton("Prekliči", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(true)
                .show();
    }

    public void nazaj_button(View view) {
        Intent intent = new Intent(PodatkiSkladbe.this, MainActivity.class);
        startActivity(intent);
    }
}
