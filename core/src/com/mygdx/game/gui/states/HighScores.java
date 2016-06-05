package com.mygdx.game.gui.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.logic.Game;
import com.mygdx.game.logic.GameData;
import com.mygdx.game.logic.Save;

/**
 * Created by Madnar on 04/06/2016.
 */
public class HighScores extends State  {

    private long[] highScores;
    private String[] names;
    private BitmapFont font;
    private SoundManager  soundManager;
    private Texture background;

    public HighScores(GameStateManager manager, SoundManager soundManager) {
        super(manager);

        this.soundManager = soundManager;


        background = new Texture("MenuBackgroundTitle.png");

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
                Gdx.files.internal("chicagoexpress.ttf")
        );
        FreeTypeFontGenerator.FreeTypeFontParameter config = new FreeTypeFontGenerator.FreeTypeFontParameter();
        config.size = 30;
        config.magFilter = Texture.TextureFilter.Nearest;
        config.minFilter = Texture.TextureFilter.Nearest;
        config.color = Color.GRAY;
        font = gen.generateFont(config);


        GameData teste = new GameData();
        teste.init();
        teste.addHighScore(1000, "Maria");
        Save.gd = teste;

          Save.save();

          Save.load();

        teste.addHighScore(100, "Maria");
        Save.save();

        teste.addHighScore(100, "Maria");
        teste.sortHighScores();
        Save.gd = teste;

         Save.load();
        highScores = Save.gd.getHighScores();
        names = Save.gd.getNames();

    }


    @Override
    public void handleInput() {
        if(Gdx.input.isTouched())
        {
            soundManager.PlayClick();
            manager.set(new Menu(manager,soundManager));
            dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){

        }
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.begin();
        batch.draw(background, 0, 0);
        float GameWidth = Gdx.app.getGraphics().getWidth()/2;

        GlyphLayout glyphLayout = new GlyphLayout();

        String s;
        float w;



        s = "High Scores";

        glyphLayout.setText(font,s);

        w = glyphLayout.width;
        font.draw(batch, s, (GameWidth - w/2) / 2, 240);

        for(int i = 0; i < highScores.length; i++) {
            s = String.format(
                    "%2d. %7s %s",
                    i + 1,
                    highScores[i],
                    names[i]
            );
            glyphLayout.setText(font,s);
            w = glyphLayout.width;
            font.draw(batch, s, (GameWidth - w/2) / 2, 200 - 20 * i);
        }
        batch.end();
    }

    @Override
    public void dispose() {

    }
}
