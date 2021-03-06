package test.cxxlib

import cxxlib.CxxVector
import de.surfice.smacrotools.debug
import utest._

import scala.scalanative.cobj.CObject
import scala.scalanative.cxx.{Cxx, constructor, include}
import scala.scalanative.unsafe.extern

@Cxx
@debug
class CxxIntVector extends CxxVector[Int]
object CxxIntVector{
  @constructor
  def apply(): CxxIntVector = extern
}

object CxxVectorTest extends TestSuite {
  val tests = Tests {
    'basicOps-{
      val v = CxxIntVector()
      v.push_back(1)
      v.push_back(2)
      v.push_back(3)
      v.size ==> 3
      v(0) ==> 1
      v(1) ==> 2
      v(2) ==> 3
      v(0) = 42
      v(0) ==> 42
    }
  }
}
