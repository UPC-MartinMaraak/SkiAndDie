package gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import player.Blood;
import player.ESkierState;
import player.Skier;
import utils.AssetLoader;

/**
 * Created by marti on 27.11.2016.
 */

public class GameRenderer {

    private final Skier skier;
    private Blood blood;
    private final ObstacleHandler obstacleHandler;
    private final BackgroundHandler backgroundHandler;
    private GameWorld world;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch spriteBatch;
    private String scoreString = "0";
    private float stateTime = 0f;

    private boolean crashed = false;
    private String gameOverString = "YOU DIED!";

    private String restartString = "tap to restart";

    public GameRenderer(GameWorld world){
        this.world = world;
        this.cam = new OrthographicCamera();
        cam.setToOrtho(true,600,900);
        this.shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(cam.combined);

        this.skier = world.getSkier();
        this.obstacleHandler = world.getObstacleHandler();

        this.backgroundHandler = world.getBackgroundHandler();

    }

    public void render() {

        Gdx.gl.glClearColor(10,10,10,10);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time


        spriteBatch.begin();
        spriteBatch.disableBlending();

        for(Background background : backgroundHandler.getBackgrounds()){
            spriteBatch.draw(background.getTexture(),background.getPosition().x,background.getPosition().y,background.getTexture().getRegionWidth(),background.getTexture().getRegionHeight());

        }


        spriteBatch.enableBlending();

        // Render skier
        Vector2 skierPos = skier.getPosition();

        if(skier.getState() == ESkierState.SKIING) {
            spriteBatch.draw(skier.snowSplash.getKeyFrame(stateTime, true),
                    world.getSkier().getPosition().x + (skier.getWidth() / 2) - 22, world.getSkier().getPosition().y - 15, 32, 32);
        }


        if(skier.isBleeding()){
            Blood b = skier.getBlood();

            float y = skierPos.y+45;//- b.getBloodTrack().size();
            for(int i = b.getBloodTrack().size()-1; i>-1;i--){
               // Texture bldTexture = new Texture(bloodPix, Pixmap.Format.RGB888, false);
                Texture bloodPix = b.getBloodTrack().get(i);
                if(y >-1) {
                    spriteBatch.draw(bloodPix, skier.getPosition().x+10, y);
                }
                y--;
            }

        }

        Animation skierAnimation = skier.getSkierAnimation();


        if(! (skier.getState() == ESkierState.CRASHING )) {
            spriteBatch.draw(skierAnimation.getKeyFrame(stateTime, true),
                    world.getSkier().getPosition().x, world.getSkier().getPosition().y, skier.getWidth(), skier.getSkierHeight());
        }else{
           if(!crashed){
               stateTime = 0;
               crashed = true;
           }
            boolean done = false;
            if(skierAnimation.getKeyFrameIndex(stateTime) == skierAnimation.getKeyFrames().length-1){
                done = true;
            }


            spriteBatch.draw(skierAnimation.getKeyFrame(stateTime, !done),
                    world.getSkier().getPosition().x, world.getSkier().getPosition().y, skier.getWidth(), skier.getSkierHeight());
        }

        for(Obstacle obstacle : obstacleHandler.getObstacles()){
            spriteBatch.draw(obstacle.getTexture(),
                    obstacle.getPosition().x, obstacle.getPosition().y, obstacle.getWidth(), obstacle.getHeight());

        }


        scoreString = world.getScore()+"";

        if(world.getGameState() == EGameState.GAME_OVER){

            AssetLoader.shadow.draw(spriteBatch, gameOverString, 100 - (3 * scoreString.length()), 250);
            AssetLoader.font.draw(spriteBatch, restartString, 100 - (3 * scoreString.length()), 600);

            AssetLoader.shadow.draw(spriteBatch, "SCORE: " + scoreString, 100 - (3 * scoreString.length()), 350);


        }else if(world.getGameState() == EGameState.RUNNING) {

            AssetLoader.shadow.draw(spriteBatch, scoreString, 250 - (3 * scoreString.length()), 25);

            AssetLoader.font.draw(spriteBatch, scoreString, 250 - (3 * scoreString.length()), 25);
        }
        spriteBatch.end();


     /*   shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(skier.getColissionRect().getX(), skier.getColissionRect().getY(), skier.getColissionRect().getWidth(),skier.getColissionRect().getHeight());
        shapeRenderer.end();

        for(Obstacle obstacle : obstacleHandler.getObstacles()) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(obstacle.getColissionRect().getX(), obstacle.getColissionRect().getY(), obstacle.getColissionRect().getWidth(), obstacle.getColissionRect().getHeight());
            shapeRenderer.end();
        }
        */

    }
}
