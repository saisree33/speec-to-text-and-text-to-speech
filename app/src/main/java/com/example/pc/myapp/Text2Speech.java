package com.example.pc.myapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

public class Text2Speech extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    private ImageButton bt;
    private EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text2speech);

        tts = new TextToSpeech(this, this);
        bt = (ImageButton) findViewById(R.id.imageButton);
        txt = (EditText) findViewById(R.id.editText);
    }

    public void onclick(View v) {
        Toast.makeText(this,"Speech Created",Toast.LENGTH_SHORT).show();
        speak();
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","This Language is not supported");
            } else {
                bt.setEnabled(true);
                speak();
            }

        } else {

            Log.e("TTS", "Initilization Failed!");
        }
    }

    private void speak() {
        String text = txt.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
