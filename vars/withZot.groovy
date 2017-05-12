import org.foo.Zot

def call(String message, Closure body) {
  def z = new Zot()
  z.myecho(message)
}
