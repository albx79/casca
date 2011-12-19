/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package it.albx79.test.cv

import org.junit._
import Assert._

import it.albx79.casca._

@Test
class BlurTest {
  /**
   * Make sure that the kernels used for Gaussian blur have unitary sum.
   */
  @Test
  def testAllKernelSum1 = {
    Array(CV.getFilter(_), CV.getCircularKernel(_)) foreach testKernelSum1(_)
  }
  
  def testKernelSum1(krnl : Int => Array[Float]) = {
    List(2, 3, 4, 5, 10, 20, 50, 100) foreach { width =>
      val kernel = krnl(width)
      val sum = kernel.reduceLeft(_ + _)
      assertTrue(sum<1.01)
      assertTrue(sum>0.99)
    }
  }
}
