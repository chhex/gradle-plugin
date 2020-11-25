package ch.chesnb.test

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.tasks.Copy

class CopyAction implements Action<Copy>  {
    Project project

    private final ClassLoader loader = getClass().getClassLoader()

    CopyAction(Project project) {
        this.project = project
    }

    @Override
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
}
