package com.mbuyukasik.game.app.service.rank;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mbuyukasik.game.app.model.Player;

/**
 * RankService is used to calculate rank of players
 * Elo algorithm is used for calculations
 * 
 * @author: mehmet buyukasik
 * @version 1.0
 */
@Service
public class RankService {

	@Value("${ELO_RATING_CONST_K}")
	private float eloRatingConstK;

	public float calculateProbability(float winnerRank, float looserRank) {
		return 1.0f * 1.0f / (1 + 1.0f * (float) (Math.pow(10, 1.0f * (winnerRank - looserRank) / 400)));
	}

	public void calculateEloRating(Player winnerPlayer, Player looserPlayer) {

		// Calculate the winning probability of winner player
		float probLooser = calculateProbability(winnerPlayer.getRank(), looserPlayer.getRank());
		// Calculate the winning probability of looser player
		float probWinner = calculateProbability(looserPlayer.getRank(), winnerPlayer.getRank());

		float newRankWinner = winnerPlayer.getRank() + eloRatingConstK * (1 - probWinner);
		float newRankLooser = looserPlayer.getRank() + eloRatingConstK * (0 - probLooser);

		winnerPlayer.setRank(Math.round(newRankWinner));
		looserPlayer.setRank(Math.round(newRankLooser));
	}

}
