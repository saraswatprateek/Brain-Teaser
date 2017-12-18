package com.example.prateek.braintrainer;

import android.os.CountDownTimer;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0, noOfQuestions = 0;
    TextView sumTextView, timerTextView;
    Button button0,button1,button2,button3,playAgainButton;
    RelativeLayout gameRelativeLayout;

    TextView resultTextView, pointsTextView;

    public void playAgain(final View view) {

        score = 0;
        noOfQuestions = 0;

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        generateQuestion();

        playAgainButton.setVisibility(view.INVISIBLE);
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");

        new CountDownTimer(30100 , 1000) {

            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf(l / 1000) + "s");

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(view.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your Score "+Integer.toString(score) + "/" + Integer.toString(noOfQuestions) );

                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();

    }

    public void generateQuestion() {

        Random rand = new Random();
        answers.clear();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b) );

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer = 0;
        for(int i=0;i<4;i++) {

            if(i == locationOfCorrectAnswer) {

                answers.add(a + b);

            }
            else {
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(41);

                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view) {

        Log.i("Tag:", (String) view.getTag());
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            resultTextView.setText("Correct!");

        } else {

            resultTextView.setText("Wrong!");

        }
        noOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestions));
        generateQuestion();
    }

    public void start (View view) {

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

    }
}
