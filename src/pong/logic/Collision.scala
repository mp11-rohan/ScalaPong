package pong.logic

import engine.graphics.{Point, Rectangle}
import pong.logic.Constants.*

object Collision {

  def bounceOffWalls(ball: Ball): Ball = {
    val hitsTop = ball.position.y < 0 && ball.velocity.y < 0
    val hitsBottom = ball.position.y + BallSize > FieldHeight && ball.velocity.y > 0
    if (hitsTop || hitsBottom)
      ball.copy(velocity = Point(ball.velocity.x, -ball.velocity.y))
    else ball
  }

  def bounceOffPaddle(ball: Ball, paddle: Paddle): Ball = if(movingTowards(ball, paddle) && overlaps(paddleRectangle(paddle), ballRectangle(ball))) ball.copy(velocity = bounceVelocity(ball, paddle)) else ball 

  private def ballRectangle(ball: Ball): Rectangle = Rectangle(ball.position, BallSize, BallSize)

  private def paddleRectangle(paddle: Paddle): Rectangle = Rectangle(Point(paddle.x, paddle.y), PaddleWidth, PaddleHeight)

  private def overlaps(paddle: Rectangle, ball: Rectangle): Boolean = {
    val overlapsTop = paddle.top < ball.bottom
    val overlapsBottom = paddle.bottom > ball.top
    val overlapsLeft = paddle.left < ball.right
    val overlapsRight = paddle.right > ball.left
    overlapsRight && overlapsLeft && overlapsBottom && overlapsTop
  }

  // +1 if the ball should leave to the right (left paddle), -1 to the left
  private def awayDirection(paddle: Paddle): Int = {
    if(paddle.x < FieldWidth/2) 1
    else -1
  } 

  private def movingTowards(ball: Ball, paddle: Paddle): Boolean = ball.velocity.x * awayDirection(paddle = paddle) < 0

  // -1 = hit the top edge of the paddle, 0 = center, +1 = bottom edge
  private def hitOffset(ball: Ball, paddle: Paddle): Float = {
    val ballCenterY = ball.position.y + BallSize / 2
    val paddleCenterY = paddle.y + PaddleHeight / 2
    ((ballCenterY - paddleCenterY) / (PaddleHeight / 2)).max(-1).min(1)
  }

  private def bounceVelocity(ball: Ball, paddle: Paddle): Point = {
    val speed = math.hypot(ball.velocity.x, ball.velocity.y).toFloat
    val angle = hitOffset(ball, paddle) * MaxBounceAngle
    Point(
      awayDirection(paddle) * speed * math.cos(angle).toFloat,
      speed * math.sin(angle).toFloat
    )
  }
}
