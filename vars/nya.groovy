class nya implements Serializable {
    private Docker docker
    def setDocker(value) {
        docker = value
    }
    def getDocker() {
        docker
    }
}

class Docker implements Serializable {
    private String registry
    def setRegistry(value) {
        registry = value
    }
    def getRegistry() {
        registry
    }
}

class Registry implements Serializable {
    private String link
    def setLink(value) {
        link = value
    }
    def getLink() {
        link
    }
}
