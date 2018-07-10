# Generate SQL migrations

Add to project file `build.gradle`. This dependency must be for tests
run from gradle tasks.

```
buildscript {
  repositories {
    mavenCentral()
    maven {
        url 'http://dl.bintray.com/appscharles/libs'
    }
  }
  dependencies {
    classpath "io.ebean:ebean-gradle-plugin:11.5.3"
    classpath 'com.appscharles.libs:databaser:1.+'
  }
}
apply plugin: 'io.ebean'
apply plugin 'com.appscharles.libs.databaser'
```

For IDE Intelij IDEA, add plugin `Ebean 11.x Enhancement`. This plugin
must be installed for tests to run from IDE Intelij IDEA. For activate plugin, check
`Ebean 11.x+ Enhancement` in menu `Build`.

Include file gradle to `build.gradle` be example: `apply from: 'gradle/MigrationGenerator.gradle'`.

Content `MigrationGenerator.gradle` file, exist in github repository in localization `'gradle/MigrationGenerator.gradle'`.

Configure properties in defined `databaserConfig` in `MigrationGenerator.gradle` file.