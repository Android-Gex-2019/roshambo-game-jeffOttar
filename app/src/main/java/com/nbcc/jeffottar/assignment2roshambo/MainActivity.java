/**
 * @file
 * @author Jeff Ottar-
 * @version 1.0
 *
 *
 * @section DESCRIPTION
 * < >
 *
 *
 * @section LICENSE
 *
 *
 * Copyright 2017
 * Permission to use, copy, modify, and/or distribute this software for
 * any purpose with or without fee is hereby granted, provided that the
 * above copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * @section Academic Integrity
 * I certify that this work is solely my own and complies with
 * NBCC Academic Integrity Policy (policy 1111)
 */

package com.nbcc.jeffottar.assignment2roshambo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Rochambo game = new Rochambo();

    /**
     * the oncreate event that sets up the system
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * The button click event that plays the game
     * @param view
     */
    public void PlayGame(View view) {
        String choice = view.getTag().toString();
        setPlayerChoice(choice);

        //get the images and textview for the game
        ImageView player = findViewById(R.id.player_image);
        ImageView computer = findViewById(R.id.computer_image);
        TextView result = findViewById(R.id.result_text);

        setImages(player, computer);
        result.setText(game.winLoseOrDraw());
        animateImages(player, computer);


    }

    /**
     * set the players choice in the game
     * @param choice
     */
    private void setPlayerChoice(String choice) {
        switch(choice)//player's choice of move
        {
            case "paper":
                game.playerMakesMove(Rochambo.PAPER);
                break;
            case "rock":
                game.playerMakesMove(Rochambo.ROCK);
                break;
            case "scissors":
                game.playerMakesMove(Rochambo.SCISSOR);
                break;
            default:
                break;
        }
    }

    /**
     * set the images of the player and computers choices
     * @param player
     * @param computer
     */
    private void setImages(ImageView player, ImageView computer) {
        for(int i = 0; i < 2; i++)//loop through player and computer setup
        {
            int selection = i % 2 ==0 ? game.getPlayerMove() : game.getGameMove();//set the selection for the computer or the player
            ImageView selectedImage = i % 2 ==0 ? player : computer;//set the selection for the computer or the player images
            switch (selection)//selection 1 is paper, 2 is scissors, 0 is rock
            {
                case 0:
                    selectedImage.setImageResource(R.drawable.rock);
                    break;
                case 1:
                    selectedImage.setImageResource(R.drawable.paper);
                    break;
                case 2:
                    selectedImage.setImageResource(R.drawable.scissors);
                    break;
                    default:
                        break;

            }
        }
    }

    /**
     * animate the images in the game
     * @param player
     * @param computer
     */
    private void animateImages(ImageView player, ImageView computer) {
        // you can animate any prop that there is a setter for
        // player.setRotationX(0f);

        ObjectAnimator animatorPlayer = ObjectAnimator.ofFloat(player,
                "rotationX", 0f, 360f)
                .setDuration(500);

        ObjectAnimator animatorGame = ObjectAnimator.ofFloat(computer,
                "rotationY", 0f, 360f)
                .setDuration(500);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorGame, animatorPlayer);
        set.setInterpolator(new AnticipateOvershootInterpolator());
        set.start();
    }
}
