Community
==========
[![Build Status][build-status-image]][build-status]

[build-status-image]: https://travis-ci.org/kaazing/community.svg?branch=develop
[build-status]: https://travis-ci.org/kaazing/community

Community provides a parent POM for other [Kaazing](http://kaazing.org) projects.  This POM binds the minimal set of plugins and [metadata](http://central.sonatype.org/pages/requirements.html#sufficient-metadata) to the Maven build process to fullfil the [Maven Central release requirements](http://central.sonatype.org/pages/apache-maven.html).

Projects inheriting community as a parent should add the following in order to release to maven central via automated builds

1. POM [name, description, and url](https://maven.apache.org/pom.html#What_is_the_POM)
2. POM [SCM information](http://maven.apache.org/pom.html#SCM)

Once released, projects will be deployed in the following Sonatype repository, which is periodically synced with Maven Central.

```xml
    <repository>
        <id>sonatype-nexus-releases</id>
        <name>Sonatype Nexus Releases</name>
        <url>https://oss.sonatype.org/content/repositories/releases/</url>
    </repository>
```
