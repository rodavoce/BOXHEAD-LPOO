package com.mygdx.game.gui.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Vector;

public class HUD {
    //botões
    private ImageButton aButton;
    private ImageButton bButton;
    private ImageButton shootButton;
    private ImageButton playButton;
    private ImageButton soundButton;
    private ImageButton backButton;
    private TextureAtlas ButtonsPack;
    private Skin skin;
    private ImageButton.ImageButtonStyle style;
    private ImageButton.ImageButtonStyle play;
    private ImageButton.ImageButtonStyle pause;
    private ImageButton.ImageButtonStyle sound;
    private ImageButton.ImageButtonStyle mute;

    private Label ammoLabel;
    private Label ammoMsg;
    private int ammo;

    private Viewport viewp;
    private Stage stage;

    //touchpad
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Drawable touchBackground;
    private Drawable touchKnob;

    private float width;
    private float height;

    public HUD(){
        width = Gdx.app.getGraphics().getWidth();
        height =Gdx.app.getGraphics().getHeight();

        viewp = new FillViewport(width,height);
        stage = new Stage(viewp);
        stage.clear();
        Gdx.input.setInputProcessor(stage);

        ButtonsPack = new TextureAtlas("PlayButtons.atlas");        //pack de botões
        skin = new Skin();                                      //skin de botão
        skin.addRegions(ButtonsPack);
        style = new ImageButton.ImageButtonStyle();

        touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        touchBackground = skin.getDrawable("Joystick");
        touchKnob = skin.getDrawable("Joystickp");
        //Apply the Drawables to the TouchPad Style
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;
        //Create new TouchPad with the created style
        touchpad = new Touchpad(10, touchpadStyle);
        //setBounds(x,y,width,height)
        touchpad.setBounds(30, 30,height/3, height/3);
        stage.addActor(touchpad);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Shoot");
        style.down = skin.getDrawable("Shootp");
        shootButton = new ImageButton(style);
        shootButton.setWidth(height/5);
        shootButton.setHeight(height / 5);
        shootButton.setPosition(viewp.getScreenWidth() - shootButton.getWidth() - 15, 15);
        stage.addActor(shootButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("A");
        style.down = skin.getDrawable("Ap");
        aButton = new ImageButton(style);
        aButton.setWidth(height/5);
        aButton.setHeight(height / 5);
        aButton.setPosition(viewp.getScreenWidth() - aButton.getWidth() * 2 - 30, 15);
        stage.addActor(aButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("B");
        style.down = skin.getDrawable("Bp");
        bButton = new ImageButton(style);
        bButton.setWidth(height/5);
        bButton.setHeight(height / 5);
        bButton.setPosition(viewp.getScreenWidth() - bButton.getWidth() - 15, bButton.getHeight() + 30);
        stage.addActor(bButton);

        play = new ImageButton.ImageButtonStyle();
        pause = new ImageButton.ImageButtonStyle();
        play.up = skin.getDrawable("Play");
        pause.up = skin.getDrawable("Pause");
        playButton = new ImageButton(play);
        playButton.setWidth(height/8);
        playButton.setHeight(height / 8);
        playButton.setPosition(viewp.getScreenWidth() / 2 - playButton.getWidth() / 2, viewp.getScreenHeight() - playButton.getHeight() * 4 / 3);
        stage.addActor(playButton);

        sound = new ImageButton.ImageButtonStyle();
        sound.up = skin.getDrawable("Sound");
        mute = new ImageButton.ImageButtonStyle();
        mute.up = skin.getDrawable("Mute");
        soundButton = new ImageButton(sound);
        soundButton.setWidth(height/8);
        soundButton.setHeight(height / 8);
        soundButton.setPosition(soundButton.getWidth() * 2 / 3, viewp.getScreenHeight() - soundButton.getHeight() * 4 / 3);
        stage.addActor(soundButton);

        style = new ImageButton.ImageButtonStyle();
        style.up = skin.getDrawable("Back");
        backButton = new ImageButton(style);
        backButton.setWidth(height/8);
        backButton.setHeight(height / 8);
        backButton.setPosition(viewp.getScreenWidth() - backButton.getWidth()*5/3, viewp.getScreenHeight() - backButton.getHeight()* 4/3);
        stage.addActor(backButton);

        ammoMsg =new Label("Ammo", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        ammoMsg.setFontScale(3);
        ammoMsg.setPosition(viewp.getScreenWidth()/2 - ammoMsg.getWidth()/2, viewp.getScreenHeight()*3/20);
        stage.addActor(ammoMsg);

        ammoLabel =new Label(String.format("%06d", ammo), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        ammoLabel.setFontScale(3);
        ammoLabel.setPosition(viewp.getScreenWidth()/2 - ammoLabel.getWidth()/2, viewp.getScreenHeight()/20);
        stage.addActor(ammoLabel);
    }

    public ImageButton getaButton(){
        return aButton;
    }

    public ImageButton getbButton(){
        return bButton;
    }

    public ImageButton getShootButton(){
        return shootButton;
    }

    public ImageButton getPlayButton(){
        return playButton;
    }

    public ImageButton getSoundButton(){
        return soundButton;
    }

    public ImageButton getBackButton(){
        return backButton;
    }

    public Vector2 getTouchpadInput(){
        return new Vector2(touchpad.getKnobPercentX(),touchpad.getKnobPercentY());
    }

    public void draw(){
        stage.draw();
    }

    public void dispose(){
        skin.dispose();
        ButtonsPack.dispose();
        stage.dispose();
    }

    public void setPause(){
        playButton.setStyle(pause);
    }

    public void setPlay(){
        playButton.setStyle(play);
    }

    public void setSound(){soundButton.setStyle(sound);}

    public void setMute(){soundButton.setStyle(mute);}

    public void setAmmo(int ammo)
    {
        this.ammo = ammo;
        ammoLabel.setText(String.format("%06d", ammo));
    }
}
