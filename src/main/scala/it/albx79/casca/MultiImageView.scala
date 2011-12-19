/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.albx79.casca

import scala.swing._
import java.awt.image._

class MultiImageView extends Panel {
  private var _views : Seq[ImagePanel] = null
  def views_=(images : Seq[BufferedImage]) {
    _views = images.map(img => new ImagePanel { image = img })
    if (index >= _views.size)
      index = views.size-1
    repaint
  }
  def views = _views

  var index = 0
  def setIndex(v : Int) {
    index = v
    repaint
  }
  
  override def paintComponent(g : Graphics2D) = {
    if (views != null && views.size > 0) 
      g.drawImage(_views(index).image, 0, 0, null)
  }
}
