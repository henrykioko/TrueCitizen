package com.example.truecitizen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button falseButton, trueButton;
    private TextView question;
    private ImageButton nextButton, prevButton;
    private int currentQuestionIndex= 0;

    private Question[] questionBank = new Question[]{
            new Question(R.string.questionDeclaration, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, false),
            new Question(R.string.question5, true),
            new Question(R.string.question6, false)


    };

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Question questionConst = new Question(R.string.questionDeclaration, true);

        falseButton = findViewById(R.id.btnFalse);
        trueButton = findViewById(R.id.btnTrue);
        question = findViewById(R.id.tvAnswer);
        nextButton = findViewById(R.id.btnNext);
        prevButton = findViewById(R.id.btnPrev);

//        falseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//
//
//        });
//
//        trueButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        Register our buttons to listen to click events

        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);

        prevButton.setVisibility(View.INVISIBLE);


    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnFalse:
//                Toast.makeText(MainActivity.this, "False",
//                        Toast.LENGTH_SHORT).show();
                checkAnswer(false);
                break;

            case R.id.btnTrue:
//                Toast.makeText(MainActivity.this, "True",
//                        Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                break;

            case R.id.btnNext:
//                go to next question


                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
//                Log.d("Current", "onClick: " +currentQuestionIndex);
                hidePrevButton();
//
//                question.setText(questionBank[currentQuestionIndex].getAnswerResId());
                updateQuestion();
                hideNextButton();
                break;
            case R.id.btnPrev:

                if (currentQuestionIndex > 0){




                    currentQuestionIndex = (currentQuestionIndex -1) % questionBank.length;
                    updateQuestion();
                    hideNextButton();
                    hidePrevButton();
                }



        }

    }

    private void updateQuestion(){

        question.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }

    private void checkAnswer(boolean userChooseCorrect){

        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();
        int toastMessageId;

        if (userChooseCorrect == answerIsTrue){

            toastMessageId= R.string.correctAnswer;
        }else{

            toastMessageId = R.string.wrongAnswer;
        }

        Toast.makeText(MainActivity.this, toastMessageId,
                Toast.LENGTH_SHORT).show();
    }

    private void hidePrevButton(){
        if (currentQuestionIndex==0) {
            prevButton.setVisibility(View.INVISIBLE);
        }else{

            prevButton.setVisibility(View.VISIBLE);
        }
    }

    private void hideNextButton(){

        if (currentQuestionIndex==questionBank.length-1){

            nextButton.setVisibility(View.INVISIBLE);

        }else{

            nextButton.setVisibility(View.VISIBLE);
        }
    }

}
