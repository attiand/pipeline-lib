package org.foo;

class Context {
  static def foo(script) {
    println '----'
    println script.MY_ATTR
    println '----'
    return '..' + script.MY_ATTR + '..'
  }
}
