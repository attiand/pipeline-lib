def call(String node, Closure body) {
	node(node) {
		body()
    }
 }
