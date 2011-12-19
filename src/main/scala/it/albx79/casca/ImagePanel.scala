/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.albx79.casca

import swing._
import java.io._
import javax.imageio._
import java.awt.image._

class ImagePanel extends Panel                                                
{                                                                             
  private var _imageFile : File = null                                                 
  private var bufferedImage:BufferedImage = null                              

  def image : BufferedImage = bufferedImage  
  def image_=(value: BufferedImage) {
    bufferedImage = value
    preferredSize = new Dimension(bufferedImage getWidth, bufferedImage getHeight)
    size = preferredSize
    repaint
  }

  def imageFile = _imageFile
  def imageFile_=(value : File)                                               
  {                                                                           
    _imageFile = value                                                        
    image = ImageIO read value
  }                                                                           

  override def paintComponent(g:Graphics2D) = {
    if (null != bufferedImage) g.drawImage(bufferedImage, 0, 0, null)         
  }                                                                           
}

object ImagePanel                                                             
{                                                                             
  def apply() = new ImagePanel()                                              
}
