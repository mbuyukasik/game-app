package com.mbuyukasik.game.app.service.rank;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mbuyukasik.game.app.GameAppTest;
import com.mbuyukasik.game.app.model.Player;

/**
 * RankServiceTest - Test class of RankService
 * 
 * @author TTMBUYUKASIK
 *
 */
public class RankServiceTest extends GameAppTest {

	@Autowired
	private RankService rankService;

	@Test
	public void calculateProbabilityTest() {
		// Precalculated value 1
		float expectedValue = 0.38686317f;
		float genaratedValue = rankService.calculateProbability(100, 20);

		assertEquals(expectedValue, genaratedValue, String.format(
				"Probability calculation is incorrent. genarated: %f, expected: %f", genaratedValue, expectedValue));

		// Precalculated value 2
		expectedValue = 0.7597469f;
		genaratedValue = rankService.calculateProbability(0, 200);

		assertEquals(expectedValue, genaratedValue, String.format(
				"Probability calculation is incorrent. genarated: %f, expected: %f", genaratedValue, expectedValue));

	}

	@Test
	public void calculateEloRatingTest() {

		float winnerPlayerRank = 10;
		float looserPlayerRank = 1;

		Player winnerPlayer = new Player(1l, "Defne");
		winnerPlayer.setRank(winnerPlayerRank);

		Player looserPlayer = new Player(1l, "Mehmet");
		looserPlayer.setRank(looserPlayerRank);

		// Precalculated values for ranks 10 and 1
		float expectedRankWinner = 20;
		float expectedRankLooser = -9;

		this.rankService.calculateEloRating(winnerPlayer, looserPlayer);

		assertEquals(expectedRankWinner, winnerPlayer.getRank(),
				String.format("Rank calculation is incorrent for winner. genarated: %f, expected: %f",
						winnerPlayer.getRank(), expectedRankWinner));

		assertEquals(expectedRankLooser, looserPlayer.getRank(),
				String.format("Rank calculation is incorrent for looser. genarated: %f, expected: %f",
						looserPlayer.getRank(), expectedRankLooser));
		
	}

}
