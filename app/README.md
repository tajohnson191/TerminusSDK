To publish the code for local testing in Terminus:

update the version in build.gradle
./gradlew assembleRelease
./gradlew publishReleasePublicationToMavenLocal

Then, in rminus, change the version for the SDK to match and ensure that add mavenLocal() is added to build.gradle (Project: Terminus)