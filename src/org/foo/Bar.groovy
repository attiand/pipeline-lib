package org.foo;

enum Bar {
  ONE('one'),
  TWO('two')

  private final String label;

  Bar(String label) {
  		this.label = label
  	}

  String getLabel() {
  		label
  	}
}
