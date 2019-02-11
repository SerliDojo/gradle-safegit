package com.serli.dojo.gradle.safegit

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

open class InstallGitHooksTask : DefaultTask() {

    @Input
    val hookNames = HookNames.names

    @Internal
    val dependedOnTasks = hookNames.map { name -> project.tasks.named(name) }
            .filter{ task -> !task.get().dependsOn.isEmpty() }

    @OutputDirectory
    val hookDir: File = project.file("${project.projectDir}/.git/hooks")

    @TaskAction
    fun install() {
        val gitDir = project.file("${project.projectDir}/.git")
        if (!gitDir.exists()) {
            throw GradleException("No git directory found at ${gitDir.absolutePath}.")
        }

        if (!hookDir.exists()) {
            project.logger.info("No hook directory found at ${hookDir.absolutePath}. A directory will be created ...")
            hookDir.mkdir()
        }

        val hooks = dependedOnTasks
                .map { task -> task.name }
                .map { File(hookDir, it) }

        val stream = this.javaClass.classLoader.getResourceAsStream("script-hook")
        val script = stream.bufferedReader().use { it.readText() }

        hooks.forEach {
            it.writeText(script)
        }

    }
}
