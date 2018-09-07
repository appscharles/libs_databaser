# Generate SQL migrations with Gradle

This method generate migrations for all model, and changes in models after deploy project.

Add to project file `build.gradle`:

```
repositories {
    maven {
        url 'http://dl.bintray.com/appscharles/libs'
    }
}
dependencies {
    compile group: 'com.appscharles.libs', name: 'dialoger', version: '1.0.0.0-dev5'
}
```

Create file in `gradle/MigrationGenerator.gradle` and fill content
from github this repository in localization `'gradle/MigrationGenerator.gradle'`.

Include file `gradle/MigrationGenerator.gradle` to `build.gradle` file by:
```
apply from: 'gradle/MigrationGenerator.gradle'
```

Configure properties in defined `migrationConfig` in `MigrationGenerator.gradle` file.

Generate migration by task 'generateMigration'.