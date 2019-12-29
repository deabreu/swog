package scala.scalanative.cobj

import de.surfice.smacrotools.BlackboxMacroTools

import scala.reflect.macros.blackbox
import scala.scalanative.unsafe.{Ptr, Zone}
import scala.language.experimental.macros

final class ResultPtr[T](val ptr: Ptr[T]) extends CObject {
  @inline def __ptr: Ptr[Byte] = ptr.asInstanceOf[Ptr[Byte]]
  @inline def isDefined: Boolean = ??? //!ptr != null
  @inline def isEmpty: Boolean = !isDefined

  @inline def value: T = ???
  @inline def wrappedValue(implicit wrapper: CObjectWrapper[T]): T = ???
}
object ResultPtr {
  def alloc[T](implicit zone: Zone): ResultPtr[T] = macro Macros.allocImpl[T]

  private class Macros(val c: blackbox.Context) extends BlackboxMacroTools {
    import c.universe._

    def allocImpl[T: WeakTypeTag](zone: Tree) = {
      val tpe = c.weakTypeOf[T]
      q"""new scalanative.cobj.ResultPtr(scalanative.unsafe.alloc[$tpe])"""
    }
  }
}
