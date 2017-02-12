package gameworld;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by marti on 01.12.2016.
 */

public class Background {

    private final TextureRegion texture;
    private Vector2 position;

    public Background(TextureRegion background,int y) {
        this.texture = background;
        position = new Vector2(0,y);
    }

    public void setY(float y){
        position.y = y;
    }

    public TextureRegion getTexture() {
        return texture;
    }

    public Vector2 getPosition() {
        return position;
    }
}
