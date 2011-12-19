/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.albx79.casca

import java.awt.image._
import java.awt.geom._

object CV {
  def getFilter(width : Int) = {
    val size = width*width
    Array.fill(size)(1f/size)
  }
  
  def getCircularKernel(diameter : Int) = {
    val radius = diameter / 2
    val sqr = diameter * diameter
    var area = 0
    val circle = Array.tabulate(sqr)(n => {
        val x = (n-radius)/radius
        val y = (n-radius)%radius
        if (x*x+y*y < radius) {
          area += 1
          1f
        }
        else
          0f
      })
    circle.map(_/area)
  }
    
  def blur(width : Int, input : BufferedImage) = {
    val tmp = new BufferedImage(input.getWidth, input.getHeight, BufferedImage.TYPE_INT_RGB)
    tmp.createGraphics.drawImage(input, new AffineTransform, null)    
    if (width > 0) {
      val blurOp = new ConvolveOp(new Kernel(width, width, getCircularKernel(width)), ConvolveOp.EDGE_ZERO_FILL, null)
      blurOp.filter(tmp, null.asInstanceOf[BufferedImage])
    } else {
      tmp    
    }
  }

  def blur(input : BufferedImage) : BufferedImage = blur(3, input)  
}
