package example

import org.scalatest._


class TennisSpec extends FlatSpec with Matchers {

  /*

    1. A game is won by the first player to have won at least
    four points in total and at least two points more than the opponent.

    2. The running score of each game is described in a manner
    peculiar to tennis: scores from zero to three points are
    described as “love”, “fifteen”, “thirty”, and “forty” respectively.

    3. If at least three points have been scored by each player,
    and the scores are equal, the score is “deuce”.

    4. If at least three points have been scored by each
    side and a player has one more point than his opponent,
    the score of the game is “advantage” for the player in the lead.

    Assumptions
    - Game -> A set of 3 Plays
    - To win! Player -> 40 points and 2 points over opponent
    - Player Point -> 0 “love”, 1 “15”,  2 “30”, and 3 “40”
    - Point >= 30 and Equal then -> “deuce”
    - Point >= 30 and (P1  1 point more than P2) -> P1 “advantage”
    - both Players points < 30 -> "replay"
   */

  "When game has no plays" should "Not complete yet" in {
    Game.score(Seq()) shouldEqual Left("Incomplete game!")
  }

  "When game has 1 play" should "Not complete yet" in {
    Game.score(Seq(Play(Love(), Love()))) shouldEqual Left("Incomplete game!")
  }

  "When game has 3 plays" should "Complete with Score" in {
    val completePlaySet = Seq(Play(Love(), Love()), Play(Love(), Love()), Play(Love(), Love()))
    Game.score(completePlaySet) shouldEqual Right(Replay)
  }

  "Both players have 30" should "game is deuce" in {
    val completePlaySet = Seq(Play(Thirty(), Thirty()), Play(Love(), Love()), Play(Love(), Love()))
    Game.score(completePlaySet) shouldEqual Right(Deuce)
  }

  "Both players have 30 across two pays" should "game is deuce" in {
    val completePlaySet = Seq(Play(Fifteen(), Fifteen()), Play(Fifteen(), Fifteen()), Play(Love(), Love()))
    Game.score(completePlaySet) shouldEqual Right(Deuce)
  }

  "Both players have 40" should "game is deuce" in {
    val completePlaySet = Seq(Play(Forty(), Forty()), Play(Love(), Love()), Play(Love(), Love()))
    Game.score(completePlaySet) shouldEqual Right(Deuce)
  }

}