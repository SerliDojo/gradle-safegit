package com.serli.dojo.gradle.safegit

object HookNames {
    val names = listOf(
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
    )
}
