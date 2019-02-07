package com.serli.dojo.gradle.safegit

import org.gradle.api.Plugin
import org.gradle.api.Project

class GitHookPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val installTask = project.tasks.register("installGitHooks", InstallGitHooksTask::class.java)

        project.tasks.named("build").configure {
            it.dependsOn(installTask)
        }

        listOf(
                "applypatch-msg",
                "commit-msg",
                "post-applypatch",
                "post-checkout",
                "post-commit",
                "post-merge",
                "post-receive",
                "post-rewrite",
                "post-update",
                "pre-applypatch",
                "pre-auto-gc",
                "pre-commit",
                "pre-push",
                "pre-rebase",
                "pre-receive",
                "prepare-commit-msg",
                "push-to-checkout",
                "sendemail-validate",
                "update"
        ).forEach {
            project.tasks.create(it)
        }
    }

}
