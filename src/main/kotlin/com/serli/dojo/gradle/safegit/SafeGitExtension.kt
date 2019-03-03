package com.serli.dojo.gradle.safegit

import org.gradle.api.Project
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

open class SafeGitExtension(project: Project) {
    val script: Property<String> = project.objects.property(String::class.java)
            .also {
                val stream = this.javaClass.classLoader.getResourceAsStream("script-hook")
                val scriptContent = stream.bufferedReader().use { it.readText() }
                it.set(scriptContent)
            }

    val hookNames: ListProperty<String> = project.objects.listProperty(String::class.java)
            .also { it.set(emptyList()) }
}
