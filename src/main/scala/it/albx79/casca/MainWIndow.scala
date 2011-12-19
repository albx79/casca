/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.albx79.casca

import scala.swing._
import scala.swing.event._

import java.io._
import javax.imageio._
import java.awt.image._


object MainWindow extends SimpleSwingApplication {
  val cardList = new ListView[java.io.File] {
    selection.intervalMode = ListView.IntervalMode.Single
    renderer = ListView.Renderer(f => {
        val s = f.toString;
        s.substring(s.lastIndexOf("/")+1)
    })
  }
  //val allImages = new MultiImageView
  val thisImage = new ImagePanel 
  var originalImage : BufferedImage = null
  
  val imgSelector = new Slider {
    min = 0
    max = 20
    value = min
  }
  
  def top = new MainFrame {
    title = "CaSca"
    contents = new BorderPanel {
      add(new ScrollPane(cardList), BorderPanel.Position.West)
      add(thisImage, BorderPanel.Position.Center)
      add(imgSelector, BorderPanel.Position.North)
    }
  }
  
  listenTo(cardList.selection, imgSelector) 
  reactions += {
    case SelectionChanged(`cardList`) if cardList.selection.items.size > 0 => 
      val f = cardList.selection.items(0)
      print(f)
      //allImages.views = multiBlur(ImageIO read f, imgSelector.max)
      thisImage.imageFile = f
      originalImage = thisImage.image
      imgSelector.publish(new ValueChanged(imgSelector))
    case ValueChanged(`imgSelector`) => 
      //allImages.setIndex(imgSelector.value)
      thisImage.image = CV blur(imgSelector.value*2+1, originalImage)
  }

  def multiBlur(img: BufferedImage, times: Int) : Stream[BufferedImage] = {
    img #:: (if (times>0) multiBlur(CV blur img, times-1) else Stream.empty)
  }
}
