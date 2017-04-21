logParserRules = '''
error /:\\s+error:/
warning /:\\s+warning:/
'''

 def withArguments(body) {
         def config = [:]
         body.resolveStrategy = Closure.DELEGATE_FIRST
         body.delegate = config
         body()

         assert config.srcdir && config.doxydir && config.outdir

         sh 'rm -f -r doxygen'
         sh 'mkdir -p doxygen/cfg'
         sh "cp $config.doxydir/* doxygen/cfg"

         def template = readFile("$config.doxydir/doxyfile.conf")

         def cfg = template.replaceFirst(/(?m)^INPUT\s*=.*/, "INPUT=$config.srcdir").
                         replaceFirst(/(?m)^OUTPUT_DIRECTORY\s*=.*/, 'OUTPUT_DIRECTORY=doxygen').
                         replaceFirst(/(?m)^LAYOUT_FILE\s*=.*/, 'LAYOUT_FILE=doxygen/cfg/doxygenlayout.xml')

         writeFile(file: 'doxygen/cfg/doxyfile.conf', text: cfg)

         try {
                 docker.image("nya-docker-registry.its.umu.se/build-doxygen:0.0.1").inside {
                   sh "doxygen doxygen/cfg/doxyfile.conf"
                 }

                 writeFile(file: 'jenkins-rule-logparser', text: logParserRules)
         }
         finally {
                 step([$class: 'LogParserPublisher', failBuildOnError: true,  unstableOnWarning: true, useProjectRule: true, projectRulePath: 'jenkins-rule-logparser'])
         }

         sh "cp -r doxygen/html $config.outdir"
 }
