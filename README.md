Example Gradle Plugin Project, which reproduces Problem with copying
resources from the Plugin to the target Build Directory.

Up to Gradle 6.4 the code in a Copy Action :

`@Override
    void execute(Copy copy) {
        if (getClass().getProtectionDomain().getCodeSource().getLocation().toExternalForm().endsWith("jar")) {
            copy.from(project.zipTree(getClass().getProtectionDomain().getCodeSource().getLocation().toExternalForm()).matching {
                include 'somefiles/**'
            })
            copy.into("${project.buildDir}")
        } else {
            copy.from(project.fileTree(loader.getResource("somefiles")))
            copy.into("${project.buildDir}/somefiles")
        }
    }
`