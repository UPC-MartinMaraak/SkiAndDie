package player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by marti on 09.02.2017.
 */

public class Blood {

    private Color bloodColor;
    private ArrayList<Texture> bloodTrack;

    private int width = 30;
    private float xPos;
    private float startYPos;
    private float endYPos;

    public Blood(float xPos,float startYPos){
        System.out.println("Created blood");
        bloodTrack = new ArrayList<Texture>();
        this.xPos = xPos;
        this.startYPos = startYPos;
        setEndYPos(startYPos);
    }

    public void setEndYPos(float y){
        float diff = endYPos - startYPos;
       // System.out.println("Set new Y end pos  = " +y);
        if(diff > 1) {
            for (int i = 0; i < diff; i++) {
                bloodTrack.add(createRandomBlood());
            }

            endYPos = y;
        }
    }

    private Texture createRandomBlood() {

        Pixmap pixmap = new Pixmap(width, 1, Pixmap.Format.RGBA8888);
        //pixmap.setColor(0x0000FF00);// clear background, Alpha == 0
        //pixmap.fill();
        pixmap.setBlending(Pixmap.Blending.None);

        for(int i=0;i<pixmap.getWidth();i++){
            Random rand = new Random();
            float redInt = 1.0f -rand.nextFloat();
            int alpha = 255-rand.nextInt(100);

            boolean shouldDrawBlood = shouldDrawBlood(i);

            if(shouldDrawBlood) {
                pixmap.drawPixel(i, 0, Color.rgba8888(redInt, 0, 0, 1.0f));
            }
        }
        Texture result = new Texture(pixmap);
        pixmap.dispose();
        return result;
    }

    private boolean shouldDrawBlood(int i) {

        Random r = new Random();
        int should = r.nextInt(10);

        if(i>22 || i<8){
            if(should>8){
                return true;
            }
        }else if(i >20 || i<10){
            if(should>4){
                return true;
            }
        }else{
            if(should>2){
                return true;
            }
        }

        return false;

    }

    public ArrayList<Texture> getBloodTrack() {
        return bloodTrack;
    }

    public void setStartYPos(float y){
        startYPos = y;
    }

    public float getStartYPos() {
        return startYPos;
    }

    public float getEndYPos() {
        return endYPos;
    }
}
