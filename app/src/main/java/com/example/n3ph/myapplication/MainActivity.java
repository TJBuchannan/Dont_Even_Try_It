package com.example.n3ph.myapplication;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;

import me.grantland.widget.AutofitHelper;


public class MainActivity extends ActionBarActivity {
    SoundPool mySound;
    TextView textView3;
    TextView theScore;
    Button button;
    RelativeLayout root;
    Random rand;
    int vegetaHuahId;
    String scoreText;
    int randomColor;
    int randomTextColor;
    int bonerId;
    int soundFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        vegetaHuahId = mySound.load(this, R.raw.vegeta_huah, 1);
        bonerId = mySound.load(this, R.raw.boner, 1);
        textView3 =(TextView) findViewById(R.id.whatUp);
        theScore = (TextView) findViewById(R.id.keepScore);
        button = (Button) findViewById(R.id.clickMeButton);
        root =(RelativeLayout)findViewById(R.id.root);
        rand = new Random();
        AutofitHelper.create(theScore);
        soundFlag=0;



        button.setOnClickListener(new View.OnClickListener() {
            /* When the button is clicked change the background color to a random color and
            * change the text*/
            int flag = 1;

            @Override
             public void onClick(View v) {
                 if(soundFlag == 0){
                     mySound.play(vegetaHuahId, 1, 1,0, 0, 1f);
                 }
                else if(soundFlag == 1){
                     mySound.play(bonerId, 1, 1,0, 0, 1f);
                 }
                //Random color generator
                randomColor = Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                randomTextColor = Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                while (randomColor==randomTextColor){
                    randomTextColor=Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                    }
                //increase the text score
                scoreText=theScore.getText().toString();
                int scoreToInt = Integer.parseInt(scoreText);
                scoreToInt++;
                theScore.setText(Integer.toString(scoreToInt));

                //Text auto resize
                if(theScore.getText().length()>scoreText.length()){
                    AutofitHelper.create(theScore);
                }

                if(Integer.parseInt(theScore.getText().toString())%10==0){
                    soundFlag=1;
                }

                if(flag==1){

                    textView3.setText("Please Stop!");
                    root.setBackgroundColor(randomColor);
                    textView3.setTextColor(randomTextColor);
                    flag=0;}
                else{
                    textView3.setText("I said Stop!");
                    flag=1;
                    root.setBackgroundColor(randomColor);
                    textView3.setTextColor(randomTextColor);
                }


            }
        }
        );


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
