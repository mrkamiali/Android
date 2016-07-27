package com.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView scoreViewA, scoreViewB;
    int scoreTeamA = 0 ;
    int scoreTeamB = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreViewA = (TextView) findViewById(R.id.team_a_score);
        scoreViewB = (TextView) findViewById(R.id.team_b_score);
    }
    public void displayForTeamA(int score) {
        scoreViewA.setText(String.valueOf(score));
    }
    public void displayForTeamB(int score){
            scoreViewB.setText(String.valueOf(score));
    }
    public void addThreePointForTeamA(View view){
            scoreTeamA = scoreTeamA +3 ;
            displayForTeamA(scoreTeamA);
        }
    public void addTwoPointForTeamA(View view){
        scoreTeamA = scoreTeamA + 2;
        displayForTeamA(scoreTeamA);
    }
    public void addFreeThrowPointForTeamA(View view){
       scoreTeamA = scoreTeamA + 1 ;
        displayForTeamA(scoreTeamA);
    }
    public void addThreePointForTeamB(View view){
        scoreTeamB = scoreTeamB +3 ;

        displayForTeamB(scoreTeamB);
    }
    public void addTwoPointForTeamB(View view){
        scoreTeamB = scoreTeamB + 2;
        displayForTeamB(scoreTeamB);
    }
    public void addFreeThrowPointForTeamB(View view){
        scoreTeamB = scoreTeamB + 1 ;
        displayForTeamB(scoreTeamB);

    }
    public void displayResetScore(View view){

        scoreTeamA = 0 ;
        scoreTeamB = 0 ;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

}
