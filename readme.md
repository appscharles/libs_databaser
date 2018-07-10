# Generate SQL migrations with Gradle

This method generate migrations for all model, and changes in models after deploy project.
Migration are generate by library Ebean.

Add to project file `build.gradle`:

```
buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath "io.ebean:ebean-gradle-plugin:11.5.3"
  }
}
apply plugin: 'io.ebean'
```

Add repository to dependencies in `build.gradle`:
```
repositories {
    maven {
        url 'http://dl.bintray.com/appscharles/libs'
    }
 }
```

Add dependency in `build.gradle`:
```
compile group: 'com.appscharles.libs', name: 'databaser', version: '1.+'
```

Create file in `gradle/MigrationGenerator.gradle` and fill content
from github this repository in localization `'gradle/MigrationGenerator.gradle'`.

Include file `gradle/MigrationGenerator.gradle` to `build.gradle` file by:
```
apply from: 'gradle/MigrationGenerator.gradle'
```

Configure properties in defined `databaserConfig` in `MigrationGenerator.gradle` file.

Generate migration by task 'generateMigration'.

# Test this library
For IDE Intelij IDEA, add plugin `Ebean 11.x Enhancement`. This plugin
must be installed for tests to run from IDE Intelij IDEA. For activate plugin, check
`Ebean 11.x+ Enhancement` in menu `Build`.