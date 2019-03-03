# gradle-safegit
Plugin Gradle pour gérer les git hooks

## Usage

### Releases

Release are available on https://plugins.gradle.org/plugin/com.serli.dojo.gradle-safegit

```groovy
plugins {
  id "com.serli.dojo.gradle-safegit" version "1.2.1"
}
```

### Snapshots

The plugin is available from jitpack : 
https://jitpack.io/#SerliDojo/gradle-safegit

In your build.gradle : 
```groovy
buildscript {
    repositories {
        maven { url 'https://jitpack.io' }
    }
    dependencies {
        classpath('com.github.SerliDojo:gradle-safegit:master-SNAPSHOT')
    }
}

apply plugin: 'com.serli.dojo.gradle-safegit'
```

### Tasks

The plugin add a task to install the git hook in `.git/hooks` : `installGitHooks`.

It also add a number of tasks named after git hooks. Those tasks are used to hook gradle tasks (for example `build`) to a git hook (for example `pre-commit`.

To see all tasks, you can execute :

```sh
$ gradlew tasks --group="SafeGit Tasks"

SafeGit Tasks tasks
-------------------
installGitHooks - Install git hook scripts

applypatch-msg - Task to depend on to enable applypatch-msg hook
commit-msg - Task to depend on to enable commit-msg hook
post-applypatch - Task to depend on to enable post-applypatch hook
post-checkout - Task to depend on to enable post-checkout hook
post-commit - Task to depend on to enable post-commit hook
post-merge - Task to depend on to enable post-merge hook
post-receive - Task to depend on to enable post-receive hook
post-rewrite - Task to depend on to enable post-rewrite hook
post-update - Task to depend on to enable post-update hook
pre-applypatch - Task to depend on to enable pre-applypatch hook
pre-auto-gc - Task to depend on to enable pre-auto-gc hook
pre-commit - Task to depend on to enable pre-commit hook
pre-push - Task to depend on to enable pre-push hook
pre-rebase - Task to depend on to enable pre-rebase hook
pre-receive - Task to depend on to enable pre-receive hook
prepare-commit-msg - Task to depend on to enable prepare-commit-msg hook
push-to-checkout - Task to depend on to enable push-to-checkout hook
sendemail-validate - Task to depend on to enable sendemail-validate hook
update - Task to depend on to enable update hook
```

### Task configuration

To configure the git hooks, you need to configure gradle tasks.
By default, the plugin will only create hooks for tasks that depends on other tasks : 

```groovy
// Will create a git hook "pre-commit".
// When it is invoked, it trigger a build.
tasks.named("pre-commit").configure {
    it.dependsOn("build")
}
```

### Plugin configuration

The plugin expose an extension to configure its behavior.

The following is the default configuration : 

```groovy
safegit {
  cleanHooks = true // clean previous git hook scripts in .git/hooks when a change is detected
  // simple script that invoke a gradle task matching the hook name
  script = '''#!/bin/sh

hookName=`basename "\$0"`
gitParams="\$*"

./gradlew $hookName -Pparams="$gitParams" --console=plain
'''
  hookNames = emptyList() // when the list is empty (which is the default), the plugin will select every tasks that are depended on as git hooks
}
```

## Report an issue

This plugin is new, things might not work as expected in every situation, features might be missing.

If you see anything wrong or want to propose a feature : https://github.com/SerliDojo/gradle-safegit/issues/new
