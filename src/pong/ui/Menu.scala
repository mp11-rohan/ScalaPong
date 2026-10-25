package pong.ui

import engine.GameBase
import engine.graphics.{Color, Point}
import pong.logic.*
import pong.logic.Constants.*

class Menu(game: GameBase) {

  private val TitleSize = 80
  private val TextSize = 30
  private val HintSize = 18

  private val CenterX = FieldWidth / 2
  private val TitleY = FieldHeight / 4
  private val ModeY = FieldHeight / 2 - 30
  private val SpeedY = FieldHeight / 2 + 15
  private val ChoiceHintY = FieldHeight / 2 + 60
  private val StartHintY = FieldHeight / 2 + 85
  private val ControlsHintY = FieldHeight * 3 / 4 + 30

  def draw(speed: Speed, mode: GameMode): Unit = {
    game.setBackground(Color.Black)
    game.setFillColor(Color.White)
    game.drawTextCentered("PONG", TitleSize, Point(CenterX, TitleY))
    game.drawTextCentered(s"< ${mode.label} >", TextSize, Point(CenterX, ModeY))
    game.drawTextCentered(s"< ${speed.label} >", TextSize, Point(CenterX, SpeedY))
    game.drawTextCentered("Up / Down: players    Left / Right: ball speed", HintSize, Point(CenterX, ChoiceHintY))
    game.drawTextCentered("Enter: start", HintSize, Point(CenterX, StartHintY))
    game.drawTextCentered("W / S and Up / Down: move paddles", HintSize, Point(CenterX, ControlsHintY))
  }
}