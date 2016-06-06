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

import com.mygdx.game.logic.GameData;
import com.mygdx.game.logic.Save;
import com.mygdx.game.logic.sprites.Gun;
import com.mygdx.game.logic.sprites.Weapon;

import junit.framework.Assert;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnitTestExample {

	//GAMEDATA TEST

	@Test
	public void testGameData()
	{
		GameData test = new GameData();
		test.init();

		long scores [] = test.getHighScores();
		String names[] = test.getNames();

		for(int i = 0; i < 5 ; i++)
		{
			assertEquals(0,scores[i]);
			assertEquals("----",names[i]);
		}

		test.addHighScore(10,"20/10/10");
		test.sortHighScores();

		scores=test.getHighScores();
		names =test.getNames();
		assertEquals(10,scores[0]);
		assertEquals("20/10/10",names[0]);

		test.addHighScore(20,"20/10/10");
		test.addHighScore(30,"20/10/10");
		test.addHighScore(40,"20/10/10");
		test.addHighScore(50,"20/10/10");

		assertEquals(test.isHighScore(5),false);
		assertEquals(test.isHighScore(100),true);

	}




}
