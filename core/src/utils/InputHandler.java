package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import gameworld.GameWorld;
import player.Skier;


/**
 * Created by marti on 27.11.2016.
 */

public class InputHandler implements InputProcessor {

    private final GameWorld gameWorld;
    private Skier skier;
    public final float screenWidth;
    public final float screenHeight;

    public InputHandler(Skier skier, GameWorld gameWorld){
        this.skier = skier;
         screenWidth = Gdx.graphics.getWidth();
         screenHeight = Gdx.graphics.getHeight();

        this.gameWorld = gameWorld;

    }



    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        skier.accelerate();
        gameWorld.screenTapped(screenX,screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        skier.setDesiredXposition(convertToGameWidth(Float.valueOf(screenX)));

        return true;
    }

    private float convertToGameWidth(Float aFloat) {

        return (aFloat/this.screenWidth)*600;


    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
