package com.serli.dojo.gradle.safegit

import org.gradle.api.Plugin
import org.gradle.api.Project

const val taskGroup = "SafeGit Tasks"

class GitHookPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("safegit", SafeGitExtension::class.java, project)

        // Register tasks before installGitHooks task to make sure they are available.
        HookNames.names.forEach { hookName ->
            project.tasks.register(hookName, GitHookTask::class.java) { task ->
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

        project.afterEvaluate {p ->
            if (extension.hookNames.get().isEmpty()) {
                val hookNames = p.tasks.withType(GitHookTask::class.java)
                        .filter { it.dependsOn.isNotEmpty() }
                        .map { it.name }

                extension.hookNames.set(hookNames)

                p.tasks.withType(InstallGitHooksTask::class.java).configureEach {
                    it.hookNames.set(hookNames)
                }
            }
        }
    }

}
