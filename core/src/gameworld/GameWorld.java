package gameworld;

import com.badlogic.gdx.Gdx;

import player.ESkierState;
import player.Skier;
import utils.AssetLoader;
import utils.InputHandler;

/**
 * Created by marti on 27.11.2016.
 */

public class GameWorld {

    private final BackgroundHandler backgroundHandler;
    private Skier skier;
    private InputHandler inputHandler;
    private ObstacleHandler obstacleHandler;
    private int score = 0;
    private EGameState gameState = EGameState.WAITING;

    public GameWorld(){
        skier = new Skier(AssetLoader.skierAnimation,AssetLoader.snowSpray, AssetLoader.skierCrashing,AssetLoader.skierWaiting,AssetLoader.skierCrashed);
        inputHandler = new InputHandler(skier,this);
        Gdx.input.setInputProcessor(inputHandler);
        obstacleHandler = new ObstacleHandler(skier,20);
        backgroundHandler = new BackgroundHandler(AssetLoader.snowBackground,Math.round(inputHandler.screenHeight),skier);


    }

    public void setGameState(EGameState state){

        if(state == EGameState.WAITING){
            resetGame();
        }

        this.gameState = state;
    }

    private void resetGame() {
        score = 0;
        skier.reset();
        obstacleHandler.reset();
    }

    public EGameState getGameState() {
        return gameState;
    }

    public void update(float delta) {
        skier.update(delta);
        obstacleHandler.update(delta);
        backgroundHandler.update(delta);
        score = score + Math.round(skier.getSpeed()*delta/10);

        if(obstacleHandler.hasCollided(skier)){
            stopGame();
        }

    }

    public int getScore(){
        return score;
    }

    private void stopGame() {

        skier.setState(ESkierState.CRASHING);

        setGameState(EGameState.GAME_OVER);

        //Show blood

        //Show score

        // Ask to reset game
    }

    public Skier getSkier() {
        return skier;
    }

    public ObstacleHandler getObstacleHandler() {
        return obstacleHandler;
    }

    public BackgroundHandler getBackgroundHandler(){
        return backgroundHandler;
    }

    public void screenTapped(int screenX, int screenY) {
        if(gameState == EGameState.WAITING){
            setGameState(EGameState.RUNNING);
        }else if(gameState == EGameState.GAME_OVER){
            setGameState(EGameState.WAITING);
        }
    }
}
