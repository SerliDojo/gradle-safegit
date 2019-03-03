package com.serli.dojo.gradle.safegit

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Task
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.*
import java.io.File

open class InstallGitHooksTask : DefaultTask() {

    @Input
    val hookNames: ListProperty<String> = project.objects.listProperty(String::class.java)

    @Input
    val scriptContent: Property<String> = project.objects.property(String::class.java)

    @OutputDirectory
    val hookDir: File = project.file("${project.projectDir}/.git/hooks")

    @TaskAction
    fun install() {
        project.logger.info("HookNames : ${hookNames.getOrElse(emptyList())}")

        val gitDir = project.file("${project.projectDir}/.git")
        if (!gitDir.exists()) {
            throw GradleException("No git directory found at ${gitDir.absolutePath}.")
        }

        if (!hookDir.exists()) {
            project.logger.info("No hook directory found at ${hookDir.absolutePath}. A directory will be created ...")
            hookDir.mkdir()
        }

        val hooks = hookNames.getOrElse(emptyList())
                .map { File(hookDir, it) }

        val script = scriptContent.get()

        hooks.forEach {
            it.writeText(script)
        }

    }
}
