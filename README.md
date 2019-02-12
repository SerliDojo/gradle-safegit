# gradle-safegit
Plugin Gradle pour gérer les git hooks

## Usage

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

## Report an issue

If you see anything wrong or want to propose a feature : https://github.com/SerliDojo/gradle-safegit/issues/new