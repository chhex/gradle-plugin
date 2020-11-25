Example Gradle Plugin Project, which reproduces Problem with copying
resources from the Plugin to the target Build Directory.

Up to Gradle 6.4 the code in the [Copy Action ](src/main/groovy/ch/chesnb/test/CopyAction.groovy) works, with Gradle 6.5.1 not anymore.

Reason: The class files of the plugin are packed into main.jar, but without the resources.