package pong.logic

import engine.graphics.Point
import pong.logic.Constants.BallStartX
import pong.logic.Constants.BallStartY
import pong.logic.Constants.BallStartSpeed

case class Ball(position: Point, velocity: Point) {

  def move(): Ball = copy(position = this.position + this.velocity, velocity = this.velocity)
}

object Ball {

  def initial: Ball = Ball(position = Point(BallStartX, BallStartY), velocity = Point(BallStartSpeed, 0))
}