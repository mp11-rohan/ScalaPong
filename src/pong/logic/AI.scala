package pong.logic

import pong.logic.Constants.*

object AI {

  private val DeadZone: Float = PaddleHeight / 6

  
  def direction(ball: Ball, paddle: Paddle): Int = {
    val targetY = if (ball.velocity.x > 0) ball.position.y + BallSize / 2 else FieldHeight / 2f
    val difference = targetY - (paddle.y + PaddleHeight / 2)
    if (difference > DeadZone) 1
    else if (difference < -DeadZone) -1
    else 0
  }
}