package gameworld;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.css.Rect;

import player.Skier;

/**
 * Created by marti on 01.12.2016.
 */

public class Obstacle {

    private final float collisionOffsetX;
    private final float collisionOffsetY;
    private EObstacleType type;
    private Vector2 position;
    private TextureRegion texture;
    private Rectangle collisionRect;
    private float width;
    private float height;

    public Obstacle(EObstacleType type, Vector2 position, TextureRegion texture) {
        this.type = type;
        this.position = position;
        this.texture = texture;

        width = texture.getRegionWidth()*2;
        height = texture.getRegionHeight()*2;

        collisionOffsetX = width/2 -4;
        collisionOffsetY = height-13;

        collisionRect = new Rectangle();
    }

    public EObstacleType getType() {
        return type;
    }

    public void setType(EObstacleType type) {
        this.type = type;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        collisionRect.set(position.x+ collisionOffsetX,position.y + collisionOffsetY,10,10);
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }



    public float getWidth() {

        return this.width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getColissionRect() {
        return this.collisionRect;
    }

    public boolean hasCollided(Skier skier) {
      //  if(getPosition().y >= skier.getPosition().y){
            return Intersector.overlaps(getColissionRect(),skier.getColissionRect());

        //}
        //return false;
    }
}
