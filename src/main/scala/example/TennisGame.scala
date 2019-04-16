package example

trait Point {
  def value:Int
}
case class Love(value:Int = 0) extends Point
case class Fifteen(value:Int = 1) extends Point
case class Thirty(value:Int = 2) extends Point
case class Forty(value:Int = 3) extends Point

trait Score
object Deuce extends Score
case class Advantage(player:String) extends Score
object Replay extends Score

case class TotalScores(leftTotalPoint: Int, rightTotalPoint: Int)

case class Play(leftPlayerPoint: Point, rightPlayerPoint: Point)

object Game {
  def score(plays:Seq[Play]): Either[String, Score] = plays match {
    case p:Seq[Play] if(p.size < 3) => Left("Incomplete game!")
    case p:Seq[Play] if(p.size == 3) => {
     val leftTotal = plays.map(play => play.leftPlayerPoint.value).sum
     val rightTotal = plays.map(play => play.rightPlayerPoint.value).sum
      CompleteGame.score(TotalScores(leftTotal, rightTotal))
    }
    case _ => Left("Invalid game!")
  }
}

object CompleteGame {
  def score(totalScores:TotalScores): Either[String, Score] = totalScores match {
    case TotalScores(left, right) if(left < Thirty().value && right < Thirty().value) => Right(Replay)
    case TotalScores(left, right) if(left == Thirty().value && right == Thirty().value) => Right(Deuce)
    case TotalScores(left, right) if(left == Forty().value && right == Forty().value) => Right(Deuce)
    case _ => ???
  }
}


