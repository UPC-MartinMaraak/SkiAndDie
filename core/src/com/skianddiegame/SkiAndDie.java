package com.skianddiegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import gameworld.GameRenderer;
import gameworld.GameWorld;
import gameworld.ObstacleHandler;
import utils.AssetLoader;
import views.GameScreen;

/**
 * Created by marti on 27.11.2016.
 */

public class SkiAndDie extends Game {

    GameScreen gameScreen;
    GameWorld gameWorld;
    GameRenderer gameRenderer;


    @Override
    public void create() {
        Gdx.app.log(SkiAndDie.class.getName(),"Created");

        AssetLoader.loadTextures();

        gameWorld = new GameWorld();
        gameRenderer = new GameRenderer(gameWorld);

        this.gameScreen = new GameScreen(gameWorld,gameRenderer);

        // Set the game screen
        setScreen(gameScreen);


    }

    @Override
    public void dispose() {

        super.dispose();
        AssetLoader.dispose();
    }
}
