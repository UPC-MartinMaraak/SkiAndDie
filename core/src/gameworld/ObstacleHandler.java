package gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

import player.ESkierState;
import player.Skier;
import utils.AssetLoader;

/**
 * Created by marti on 01.12.2016.
 */

public class ObstacleHandler {


    ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    private ArrayList<Obstacle> obstaclesToRemove = new ArrayList<Obstacle>();

    private Random random;
    private int bottomOfScreen = 810;
    private Skier skier;
    private int numberOfObstacles = 5;
    private float timeSinceLastObstacle = 5;


    public ObstacleHandler(Skier skier, int numberOfObstacles) {
        this.skier = skier;
        this.numberOfObstacles = numberOfObstacles;
        random = new Random();
        for(int i = 0; i<numberOfObstacles;i++){
            Obstacle obstacle = createObstacle(random.nextInt(700)+100);
            if(!obstacleIsTooClose(obstacle)) {
                obstacles.add(obstacle);
            }
        }
    }

    private boolean obstacleIsTooClose(Obstacle obstacle) {

        for(Obstacle existingObst : getObstacles()){
            if (Math.abs(existingObst.getPosition().x -obstacle.getPosition().x) < 20){
                return true;
            }else  if (Math.abs(existingObst.getPosition().y -obstacle.getPosition().y) < 20){
                return true;
            }
        }

        return false;

    }

    public void update(float delta){
        timeSinceLastObstacle = timeSinceLastObstacle + delta;
        if(obstacles.size() < numberOfObstacles && timeSinceLastObstacle > 0.2 & skier.getSpeed() > 0){
            obstacles.add(createObstacle(810));
            timeSinceLastObstacle = 0;
        }

        obstaclesToRemove.clear();
        for(Obstacle obs : obstacles){
            if(obs.getPosition().y < -64){
                obstaclesToRemove.add(obs);
            }else {
                obs.setPosition(new Vector2(obs.getPosition().x, obs.getPosition().y - (delta * skier.getSpeed())));
            }
        }
        obstacles.removeAll(obstaclesToRemove);
    }

    private Obstacle createObstacle(int y) {
        random = new Random();
        int posX = random.nextInt(560) +20;
      //  Gdx.app.log("ObstacleHandler","Created new obstacle");

        random = new Random();
        int obstacleIndex = random.nextInt(AssetLoader.trees.length-1);

        return new Obstacle(EObstacleType.Tree,new Vector2(posX,y), AssetLoader.trees[obstacleIndex]);
    }


    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public boolean hasCollided(Skier skier) {
        boolean result = false;

        for(Obstacle obstacle : obstacles){
            if(obstacle.hasCollided(skier)){
                if(skier.getState().equals(ESkierState.SKIING)){
                    Gdx.input.vibrate(1500);
                    skier.startBleeding();
                }
                return true;
            }
        }


        return false;
    }

    public void reset() {
        obstacles.clear();
        random = new Random();
        for(int i = 0; i<numberOfObstacles;i++){
            Obstacle obstacle = createObstacle(random.nextInt(700)+100);
            if(!obstacleIsTooClose(obstacle)) {
                obstacles.add(obstacle);
            }
        }
    }
}
