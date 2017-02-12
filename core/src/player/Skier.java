package player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;



/**
 * Created by marti on 27.11.2016.
 */

public class Skier {


    private final Animation skierCrashed;
    private Vector2 position;
    private float normalYpos;
    private float maxYPos;
    private int speed;
    private int height;
    private float rotation;
    private ESkierState state;
    private int skierHeight = 64;
    private int width = 64;

    private int maxSpeed = 800;
    private int normalSpeed = 600;

    // Animations
    private Animation skiStraight;
    private Animation skierCrashing;
    private Animation skierWaiting;

    public final Animation snowSplash;

    private Rectangle collisionRect;

    private float desiredXposition;
    private float desiredYposition;

    private float accelerationTime = 0.0f;
    private Blood blood = null;

    public Skier(Animation skiStraight,Animation snowSplash, Animation skierCrashing,Animation skierWaiting,Animation skierCrashed){
        state = ESkierState.WAITING;
        position = new Vector2(300,100);
        normalYpos = 100;
        maxYPos = 300;
        desiredXposition = position.x;
        desiredYposition = position.y;
        speed = 0;
        height = 0;
        rotation = 0.0f;
        collisionRect = new Rectangle();
        this.skiStraight = skiStraight;
        this.snowSplash = snowSplash;
        this.skierCrashing = skierCrashing;
        this.skierWaiting = skierWaiting;
        this.skierCrashed = skierCrashed;

        snowSplash.setFrameDuration(0);
        skiStraight.setFrameDuration(0);




    }

    public void update(float delta){

        if(height > 0){
            height = height -10;
            if(height <= 0){
                state = ESkierState.SKIING;
            }
        }

        if(state == ESkierState.WAITING){
            //DO NOTHING
        }else if(state == ESkierState.SKIING){
            snowSplash.setFrameDuration(50.0f/getSpeed());
            skiStraight.setFrameDuration(30.0f/getSpeed());
            if(speed < normalSpeed){
                speed = normalSpeed;
            }else if(speed > normalSpeed){
                speed = speed -1;
            }

            if(desiredYposition > normalYpos){
                desiredYposition = desiredYposition - 1f;
            }



            float yDifference = Float.valueOf(position.y) - desiredYposition;
            float absYDiff = Math.abs(yDifference);

            if (desiredYposition > position.y) {
                position.y = position.y + delta * absYDiff;
            } else if (desiredYposition < position.y) {
                position.y = position.y - delta * absYDiff;
            }


            float difference = Float.valueOf(position.x) - desiredXposition;
            float absDiff = Math.abs(difference);

            if (desiredXposition > position.x) {
                    position.x = position.x + delta * absDiff*5;
                } else if (desiredXposition < position.x) {
                    position.x = position.x - delta * absDiff*5;
                }

        }else if(state == ESkierState.CRASHING){
            if(speed > 0){
                speed = speed - Math.round(200*delta);
            }
            else if(speed < 0){
                speed = 0;
            }

            else if(speed == 0){
                state = ESkierState.CRASHED;
            }

            snowSplash.setFrameDuration(0);
        }

        if(blood != null){
            if(getSpeed() >0f) {
                blood.setStartYPos(blood.getEndYPos() - delta * getSpeed());
                blood.setEndYPos(getPosition().y);
            }
          //  obs.getPosition().x, obs.getPosition().y - (delta * skier.getSpeed()));
        }

        collisionRect.set(position.x+ 22,position.y + 40,20,20);


    }

    public int getSpeed(){
        return speed;
    }

    public Vector2 getPosition(){
        return position;
    }


    public void setDesiredXposition(float pos){
        desiredXposition = pos;
    }

    public void accelerate(){
        if(state == ESkierState.WAITING){
            state = ESkierState.SKIING;
            speed = 10;

        }else if(state == ESkierState.SKIING){

        if(speed < maxSpeed) {
            speed = speed + 100;
            if(desiredYposition < maxYPos){
                desiredYposition = maxYPos;
            }
        }
        }
    }


    public void jump(){
        height = height + speed;
        state = ESkierState.IN_AIR;
    }

    public ESkierState getState(){
        return state;
    }

    public int getSkierHeight(){
        return  skierHeight;
    }

    public int getWidth(){
        return width;
    }

    public Rectangle getColissionRect() {
        return collisionRect;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setState(ESkierState state) {
        this.state = state;
    }

    public Animation getSkierAnimation() {

        switch(state){
                case SKIING: return skiStraight;

                case WAITING: return  skierWaiting;

                case CRASHING: return skierCrashing;

                case CRASHED: return skierCrashed;


        }



        return this.skiStraight;
    }


    public void startBleeding(){
        blood = new Blood(this.position.x,this.position.y);
    }

    public void reset() {
        state = ESkierState.WAITING;
        position = new Vector2(300,100);
        normalYpos = 100;
        maxYPos = 300;
        desiredXposition = position.x;
        desiredYposition = position.y;
        speed = 0;
        height = 0;
        rotation = 0.0f;
        collisionRect = new Rectangle();
        snowSplash.setFrameDuration(0);
        skiStraight.setFrameDuration(0);
        blood = null;

    }

    public boolean isBleeding() {
        return blood != null;
    }

    public Blood getBlood() {
        return blood;
    }
}
