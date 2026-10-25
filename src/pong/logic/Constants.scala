package pong.logic

object Constants {

  // Field
  val FieldWidth: Int = 800
  val FieldHeight: Int = 500

  // Paddle
  val PaddleWidth: Float = 10
  val PaddleHeight: Float = 75
  val PaddleSpeed: Float = 4 // pixels per frame
  val PaddleMargin: Float = 10 // distance from the screen edge
  val LeftPaddleX: Float = PaddleMargin
  val RightPaddleX: Float = FieldWidth - PaddleMargin - PaddleWidth
  val PaddleStartY: Float = (FieldHeight - PaddleHeight)/2

  // Ball
  val BallSize: Float = 10
  val BallStartX: Float = (FieldWidth - BallSize)/2
  val BallStartY: Float = (FieldHeight - BallSize)/2
  val MaxBounceAngle: Float = (math.Pi/3).toFloat

  // Timing and scoring
  val FramesPerSecond: Int = 60
  val WinningScore: Int = 5
  val PointPauseFrames: Int = FramesPerSecond // one second
}
