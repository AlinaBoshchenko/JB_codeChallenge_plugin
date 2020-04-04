# Clock widget plugin for Intellij IDEA 

Plugin is aimed to display the current time in the right bottom of the IDE window. 

### Building
git clone https://github.com/AlinaBoshchenko/JB_codeChallenge_plugin.git

cd JB_codeChallenge_plugin

**./gradlew runIde** — run a development IDE with the plugin installed.

**./gradlew buildPlugin** — build plugin and create an archive at build/distributions 

You can install plugin into IntelliJ IDEA using *File -> Settings -> Plugins -> Install plugin from disk.*

I decided to add test for checking test time changes. 

**./gradlew test**  —   run tests.
