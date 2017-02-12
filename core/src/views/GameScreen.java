package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import gameworld.GameRenderer;
import gameworld.GameWorld;

/**
 * Created by marti on 27.11.2016.
 */

public class GameScreen implements Screen {

    private GameRenderer gameRenderer;
    private GameWorld gameWorld;

    public GameScreen(GameWorld gameWorld, GameRenderer gameRenderer){
        Gdx.app.log(this.getClass().getName(),"Create game screen");
        this.gameWorld = gameWorld;
        this.gameRenderer = gameRenderer;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gameWorld.update(delta);
        gameRenderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
