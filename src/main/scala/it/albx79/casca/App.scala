package it.albx79.casca

import swing._                                                                

import uk.co.flamingpenguin.jewel.cli._

import javax.imageio._
import javax.swing._

import java.io._
//import java.awt.image._

trait CliConfig {
  @Option(longName=Array("training")) 
  def getTrainingDir : File
  
  @Option(helpRequest=true)
  def getHelp : Boolean;
}

/**
 * @author ${user.name}
 */
object App {
    
  def getConf = conf
  private var conf: CliConfig = null
    
  
  def main(args : Array[String]) {
    try {
      conf = CliFactory.parseArguments(classOf[CliConfig], args:_*)
      assert(conf.getTrainingDir != null && conf.getTrainingDir.isDirectory)
    } catch {
      case e: Exception => println(e getMessage)      
    }    

    MainWindow.cardList.listData = conf.getTrainingDir.listFiles.filter(_.getName endsWith ".jpg")
    MainWindow.top.pack
    MainWindow.top.visible = true
  }
}
 