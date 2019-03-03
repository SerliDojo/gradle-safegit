package com.serli.dojo.gradle.safegit

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.Task
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.*
import java.io.File

open class GitHookTask : DefaultTask() {

    @TaskAction
    fun exec() {
        // nothing to do here.
    }
}
