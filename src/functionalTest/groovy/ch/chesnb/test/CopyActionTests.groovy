package ch.chesnb.test

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
import static org.gradle.testkit.runner.TaskOutcome.*

class CopyActionTests extends Specification {

    @Rule TemporaryFolder testProjectDir = new TemporaryFolder()
    File settingsFile
    File buildFile

    def setup() {
        settingsFile = testProjectDir.newFile('settings.gradle')
        buildFile = testProjectDir.newFile('build.gradle')
    }

    def "Test copy some files from Plugin"() {
        given:
        settingsFile << "rootProject.name = 'test'"
        buildFile << """
             plugins {
                  id 'ch.chesnb.test'
            }
        """

        when:
        def result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withArguments('copySomeFiles')
                .withPluginClasspath()
                .build()

        then:
        result.task(":copySomeFiles").outcome == SUCCESS
        def packagingTarget = new File(testProjectDir.getRoot(),"build/somefiles")
        def cntTarget = 0
        packagingTarget.traverse {
            cntTarget++
        }
        cntTarget == 1
    }
}
