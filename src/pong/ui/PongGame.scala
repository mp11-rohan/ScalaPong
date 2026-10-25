package pong.ui

import java.awt.event.KeyEvent.{VK_DOWN, VK_ENTER, VK_LEFT, VK_RIGHT, VK_SPACE, VK_UP}
import engine.GameBase
import pong.logic.*
import pong.logic.Constants.*
import processing.event.KeyEvent

class PongGame extends GameBase {

  private var state: GameState = GameState.initial
  private val input = new Input
  private val renderer: Renderer = new Renderer(this)
  private val updateTimer = new UpdateTimer(FramesPerSecond)

  override def draw(): Unit = {
    updateState()
    renderer.draw(state)
  }

  override def keyPressed(event: KeyEvent): Unit = {
    input.keyPressed(event)
    state.phase match {
      case MainMenu => handleMenuKey(event.getKeyCode)
      case GameOver => if (event.getKeyCode == VK_SPACE) state = GameLogic.backToMenu(state)
      case Playing  => ()
    }
  }
  
  private def handleMenuKey(keyCode: Int): Unit = keyCode match {
    case VK_LEFT  => state = GameLogic.changeSpeed(state, -1)
    case VK_RIGHT => state = GameLogic.changeSpeed(state, 1)
    case VK_ENTER => state = GameLogic.startGame(state)
    case VK_UP | VK_DOWN    => state = GameLogic.changeMode(state)
    case _        => ()
  }
  override def keyReleased(event: KeyEvent): Unit = input.keyReleased(event = event)

  override def settings(): Unit = {
    pixelDensity(displayDensity())
    size(FieldWidth, FieldHeight)
  }

  override def setup(): Unit = {
    text("", 0, 0)
    updateTimer.init()
  }

  private def updateState(): Unit = {
    if(updateTimer.timeForNextFrame()) {
      state = GameLogic.step(state = state, controls = input.controls)
      updateTimer.advanceFrame()
    }
  }


}
