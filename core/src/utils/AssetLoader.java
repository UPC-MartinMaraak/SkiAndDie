package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;


/**
 * Created by marti on 28.11.2016.
 */

public class AssetLoader {

    private static Texture texture;
    private static Texture skierTexture;

    public static TextureRegion snowBackground;

    private static int numberOfSkierFrames = 15;
    public static Animation skierAnimation;

    private static int numberOfSprayFrames = 16;
    public static Animation snowSpray;

    public static TextureRegion skierDown, skierLeft, skierRight;

    private static int numberOfTrees = 9;
    public static TextureRegion[] trees;

    public static BitmapFont font,shadow;
    public static Animation skierCrashing;
    public static Animation skierWaiting;
    public static Animation skierCrashed;
    private static int fontScale;

    public static void loadTextures(){

        texture = new Texture(Gdx.files.internal("skiAndDie.png"));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        skierTexture = new Texture(Gdx.files.internal("skier.png"));
        skierTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        snowBackground = new TextureRegion(texture,0,0,600,1200);
        snowBackground.flip(false,true);

        skierDown = new TextureRegion(texture,600,0,64,37);
        skierDown.flip(false,true);

        //Skier skiing
        TextureRegion[] skierTextures = new TextureRegion[numberOfSkierFrames];

        for(int i = 0;i<numberOfSkierFrames;i++){
            TextureRegion tmp = new TextureRegion(skierTexture,0,(64*i) +i*2,64,64);
            tmp.flip(false,true);
            skierTextures[i] = tmp;
        }


        skierAnimation = new Animation(0.07f,skierTextures);
        skierAnimation.setPlayMode(Animation.PlayMode.LOOP_RANDOM);

        // Skier waiting
        TextureRegion[] skierWaitTextures = new TextureRegion[numberOfSkierFrames];

        for(int i = 0;i<numberOfSkierFrames;i++){
            TextureRegion tmp = new TextureRegion(skierTexture,66,(64*i) +i*2,64,64);
            tmp.flip(false,true);
            skierWaitTextures[i] = tmp;
        }

        skierWaiting = new Animation(0.1f,skierWaitTextures);
        skierWaiting.setPlayMode(Animation.PlayMode.LOOP);

        //Skier Crashed
        TextureRegion[] skierCrashedTextures = new TextureRegion[numberOfSkierFrames];

        for(int i = 0;i<numberOfSkierFrames;i++){
            TextureRegion tmp = new TextureRegion(skierTexture,132,(64*i) +i*2,64,64);
            tmp.flip(false,true);
            skierCrashedTextures[i] = tmp;
        }


        skierCrashing = new Animation(0.1f,skierCrashedTextures);
        skierCrashing.setPlayMode(Animation.PlayMode.NORMAL);

        TextureRegion[] skierCrashingTextures = new TextureRegion[2];
        skierCrashingTextures[0] = skierCrashedTextures[13];
        skierCrashingTextures[1] = skierCrashedTextures[14];


        skierCrashed = new Animation(0.1f,skierCrashingTextures);

        //Spray animation
        TextureRegion[] sprayTextures = new TextureRegion[numberOfSprayFrames];

        for(int i = 0;i<numberOfSprayFrames;i++){
            TextureRegion tmp = new TextureRegion(skierTexture,198,(32*i) +i,32,32);
            tmp.flip(false,true);
            sprayTextures[i] = tmp;
        }

        snowSpray = new Animation(0.1f,sprayTextures);
        snowSpray.setPlayMode(Animation.PlayMode.LOOP_RANDOM);

        trees = new TextureRegion[numberOfTrees];

        for(int i = 0; i<numberOfTrees;i++) {
            TextureRegion tree = new TextureRegion(texture, 629, (64*i) +i, 64, 64);
            tree.flip(false, true);
            trees[i] = tree;
        }

        font = new BitmapFont(Gdx.files.internal("font.fnt"),true);

        shadow = new BitmapFont(Gdx.files.internal("shadow.fnt"),true);
        

    }

    public static void dispose(){
        texture.dispose();
        font.dispose();
    }



}
