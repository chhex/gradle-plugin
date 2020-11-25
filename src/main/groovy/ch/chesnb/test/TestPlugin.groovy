package ch.chesnb.test

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.TaskContainer

class TestPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        TaskContainer tasks = project.getTasks();
        tasks.register("copySomeFiles", Copy.class,
                new CopyAction(project));
    }
}
