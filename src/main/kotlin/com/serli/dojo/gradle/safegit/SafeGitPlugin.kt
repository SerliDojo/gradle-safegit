package com.serli.dojo.gradle.safegit

import org.gradle.api.Plugin
import org.gradle.api.Project

const val taskGroup = "SafeGit Tasks"

class GitHookPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("safegit", SafeGitExtension::class.java, project)

        // Register tasks before installGitHooks task to make sure they are available.
        extension.hookNames.get().forEach { hookName ->
            project.tasks.register(hookName) { task ->
                task.group = taskGroup
                task.description = "Task to depend on to enable $hookName hook"
            }
        }

        project.tasks.register("installGitHooks", InstallGitHooksTask::class.java) {
            it.group = taskGroup
            it.description = "Install git hook scripts"

            it.hookNames.set(extension.hookNames)
            it.scriptContent.set(extension.script)
        }
    }

}
