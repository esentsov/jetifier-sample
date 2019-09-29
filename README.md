# Jetifier Sample
An example of using jetifier plugin to rewrite package names.
The [jetifier](https://developer.android.com/studio/command-line/jetifier) is a powerful tool
to repackage `aar` and `jar` dependencies.

The project uses [jetifier gradle plugin](https://android.googlesource.com/platform/frameworks/support/+/support-library-27.1.0/jetifier/jetifier/gradle-plugin/)
source code which is placed under `buildSrc` with `build.gradle.kts` rewritten,
because the original gradle plugin is not published as an artifact.

The jetifier config is stored in `app/jetifier.config.json`. Here it rewrites all `androidx.*` packages
to `myandroidx.*`.
The plugin is applied in `app` `build.gradle.kts` file:
`apply<JetifierPlugin>()` and configured with the above config file:
```
val jetifier = the<JetifierExtension>().apply {
    setConfigFile(file("jetifier.config.json").path)
}
```

Then in `dependencies` block it's applied:
```
implementation(jetifier.process("androidx.constraintlayout:constraintlayout:1.1.3"))
implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta2")
```

As a result it's possible to use both `constraintlayout:1.1.3` referring it as `myandroidx.*`
and `constraintlayout:2.0.0-beta2` in the project.
E.g. `activity_main.xml` uses both versions at the same time.

# Why

This sample was created to demonstrate how to use both `constraintlayout:1.1.3` and
`constraintlayout:2.0.0-beta2` in the project. This might be useful for people, who
switched to `2.0.0-beta` in production and started to use `MotionLayout`.
Currently `constraintlayout:2.0.0-beta2` has a few opened bugs, so to avoid them they can still use
`ConstraintLayout` from `1.1.3` version having `MotionLayout` from `2.0.0-beta` in the project.

The project might be useful also as a general example of using jetifier android plugin to rewrite package names.
