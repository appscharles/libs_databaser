# Generate SQL migrations with Gradle

This method generate migrations for all model, and changes in models after deploy project.

Add to project file `build.gradle`:

```
buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
     classpath 'com.devskiller.jpa2ddl:jpa2ddl-gradle-plugin:0.9.12'
  }
}
apply plugin: 'com.devskiller.jpa2ddl'
```

Create file in `gradle/MigrationGenerator.gradle` and fill content
from github this repository in localization `'gradle/MigrationGenerator.gradle'`.

Include file `gradle/MigrationGenerator.gradle` to `build.gradle` file by:
```
apply from: 'gradle/MigrationGenerator.gradle'
```

Configure properties in defined `migrationConfig` in `MigrationGenerator.gradle` file.

Generate migration by task 'generateMigration'.