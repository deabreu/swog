package lua

import scala.scalanative.interop.AutoReleasable

/**
 * High-level interface to a Lua interpreter instance.
 */
trait Lua extends AutoReleasable {
  /**
   * Loads the standard libraries and the scala utils into this instance.
   */
  def init(): Unit

  /**
   * Registers the speficied LuaModule (i.e. it can be accessed from lua via `scala.load()`)
   */
  def registerModule(module: LuaModule): Unit

  /**
   * Registers all specified LuaModules (i.e. they can be accessed from lua via 'scala.load()')
   * @param modules
   */
  def registerModules(modules: LuaModule*): Unit = modules.foreach(registerModule)

  /**
   * Executes the provided string as a chunk in this instance.
   * @param script
   */
  def execString(script: String): Unit

  def execFile(filename: String): Unit

  /**
   * Returns the value at the given position of the Lua stack.
   *
   * @param idx stack index (positive values: aboslute index; nbegative values: position from the top)
   * @return
   */
  def getValue(idx: Int): Any

  /**
   * Returns the value of the specified global variable, or None.
   *
   * @param name
   */
  def getGlobalValue(name: String): Option[Any]

  def table(idx: Int): LuaTable

}

object Lua {
  def apply(): Lua = LuaState()
}
