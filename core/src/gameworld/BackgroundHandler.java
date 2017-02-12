package gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

import player.Skier;

/**
 * Created by marti on 01.12.2016.
 */

public class BackgroundHandler {

    private final Skier skier;
    private final ArrayList<Background> backgrounds;
    private final ArrayList<Background> backgroundsToRemove = new ArrayList<Background>();
    private final int screenHeight;
    private TextureRegion background;


    public BackgroundHandler(TextureRegion background, int screenHeight, Skier skier){
        int backgroundHeight = background.getRegionHeight();
        backgrounds = new ArrayList<Background>();
        this.skier = skier;
        int y = 0;
        this.screenHeight = screenHeight;

        if(screenHeight< backgroundHeight){
        while(screenHeight>0){
            backgrounds.add(new Background(background,y));
            y = y + backgroundHeight;
            screenHeight = screenHeight-backgroundHeight;
            Gdx.app.log(this.getClass().getName(),"Added Background with y coordinate " + y );
        }
        }else{
            backgrounds.add(new Background(background,0));
            backgrounds.add(new Background(background,background.getRegionHeight()));
        }

    }

    public void update(float delta){
        float speed = skier.getSpeed();

        int bottomPosition = 0;

        Background backgroundToRemove = null;
        boolean addBackground = false;
        float nextY = 0;
        for(Background bg : backgrounds){
            float yPos = bg.getPosition().y;
            if(yPos+bg.getTexture().getRegionHeight() > nextY){
                nextY = yPos+bg.getTexture().getRegionHeight();
            }
            bg.setY(bg.getPosition().y - delta*speed);
            if(yPos+bg.getTexture().getRegionHeight() <0){
                backgroundToRemove = bg;
                addBackground = true;
            }
        }
        if(addBackground) {
            backgroundToRemove.setY(nextY-delta*speed);
        }

    }

    public ArrayList<Background> getBackgrounds() {
        return backgrounds;
    }
}
