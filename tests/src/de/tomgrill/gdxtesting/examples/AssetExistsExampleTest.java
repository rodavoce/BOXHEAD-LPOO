/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package de.tomgrill.gdxtesting.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.logic.GameData;
import com.mygdx.game.logic.Save;
import com.mygdx.game.logic.sprites.Gun;

import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class AssetExistsExampleTest {

    @Test
    public void testSave()
    {
        GameData test = new GameData();
        test.init();

        Save temp = new Save();
        temp.init();
        temp.load();

        long scores [] = temp.gd.getHighScores();
        String names[] = temp.gd.getNames();

        for(int i = 0; i < 5 ; i++)
        {
            assertEquals(0,scores[i]);
            assertEquals("----",names[i]);
        }

        temp.gd.addHighScore(10,"20/20/20");
        temp.gd.sortHighScores();

        temp.save();

        temp.load();

        scores = temp.gd.getHighScores();
        names  = temp.gd.getNames();

        assertEquals(10,scores[0]);
        assertEquals("20/20/20",names[0]);
    }


    @Test
    public void testGun()
    {
      //  Gun test = new Gun(1);
    //    assertEquals(1,test.getDurability());
     //   assertEquals(true,test.use());
     //   assertEquals(false,test.use());


    }

}
