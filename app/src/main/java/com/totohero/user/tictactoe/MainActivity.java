package com.totohero.user.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int gamestate[] = {2,2,2,2,2,2,2,2,2};
    int winstates[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int counter=0;
    boolean gameactive = true;
    public void move(View view){
        ImageView box = (ImageView) view;
        int tag = Integer.parseInt(box.getTag().toString());
        if(gamestate[tag] == 2 && gameactive) {
            if (counter%2 == 0) {
                gamestate[tag] = 0;
                counter++;
                box.setImageResource(R.drawable.x);
            } else {
                gamestate[tag] = 1;
                counter++;
                box.setImageResource(R.drawable.o);
            }
            box.animate().alpha(1).setDuration(500);
            for (int winstate[] : winstates) {
                if (gamestate[winstate[0]] == gamestate[winstate[1]] && gamestate[winstate[1]] == gamestate[winstate[2]] && gamestate[winstate[0]] != 2) {
                    gameactive = false;
                    TextView text = (TextView)findViewById(R.id.textView);
                    Button playAgain = (Button) findViewById(R.id.button);
                    if (gamestate[tag] == 0){
                        text.setText("X won");
                        text.setVisibility(View.VISIBLE);
                        playAgain.setVisibility(View.VISIBLE);
                    }

                    else {
                        text.setText("O won");
                        text.setVisibility(View.VISIBLE);
                        playAgain.setVisibility(View.VISIBLE);
                    }

                }
            }
            if(counter == 9){
                TextView text = (TextView)findViewById(R.id.textView);
                Button playAgain = (Button) findViewById(R.id.button);
                text.setText("Match Draw");
                playAgain.setVisibility(View.VISIBLE);
                text.setVisibility(View.VISIBLE);
            }
        }
    }
    public void playAgain(View view){
        TextView text = (TextView)findViewById(R.id.textView);
        Button playAgain = (Button) findViewById(R.id.button);
        text.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);
        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0; i<grid.getChildCount(); i++){
            ImageView box = (ImageView) grid.getChildAt(i);
            box.setImageDrawable(null);
        }
        for(int i=0; i<gamestate.length; i++){
            gamestate[i] = 2;
        }
        counter=0;
        gameactive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
