package com.serli.dojo.gradle.safegit

import org.gradle.api.Plugin
import org.gradle.api.Project

const val taskGroup = "SafeGit Tasks"

class GitHookPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val installTask = project.tasks.register("installGitHooks", InstallGitHooksTask::class.java) {
            it.group = taskGroup
            it.description = "Install git hook scripts"
        }

        HookNames.names.forEach { hookName ->
            project.tasks.register(hookName) { task ->
                task.group = taskGroup
                task.description = "Task to depend on to enable $hookName hook"
            }
        }
    }

}
