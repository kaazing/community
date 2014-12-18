Community
==========

Community provides a top level parent pom for other [Kaazing](http://kaazing.org) projects.  This pom binds the minimal set of plugins and [metadata](http://central.sonatype.org/pages/requirements.html#sufficient-metadata) to the maven build process to fullfil the [Maven Central release requirements](http://central.sonatype.org/pages/apache-maven.html). Additionally, it provides the plugin configuration for [jgitflow-maven-plugin](https://bitbucket.org/atlassian/jgit-flow/wiki/Home), which allows automated builds to follow the [gitflow branching model](http://nvie.com/posts/a-successful-git-branching-model/).

Projects inheriting community as a parent should add the following in order to release to maven central via automated builds

1. A [project name, description, and url](https://maven.apache.org/pom.html#What_is_the_POM) in the pom
2. [Javadoc and Sources](http://central.sonatype.org/pages/apache-maven.html#javadoc-and-sources-attachments) if the packaging is a jar.  
3. [SCM Information](http://central.sonatype.org/pages/requirements.html#scm-information).  In order for the scm information to to work with jgitflow-maven-plugin, scm should be configured as follows
```xml
    <scm>
        <connection>scm:git:git@github.com:kaazing/myProject.git</connection>
        <developerConnection>${project.scm.connection}</developerConnection>
        <url>git@github.com:kaazing/myProject</url>
    </scm>
```
where "myProject" is the name of the project to be released

Once released, projects will be deployed in the following repo which is periodically synced with Maven Central

```
    <repository>
        <id>sonatype-nexus-snapshots</id>
        <name>Sonatype Nexus Snapshots</name>
        <url>https://oss.sonatype.org/content/repositories/releases/</url>
    </repository>
```

Minimum requirements for building the project
---------
* Java Developer Kit (JDK) or Java Runtime Environment (JRE) Java 7 (version 1.7.0_21) or higher
* Maven 3.0.5

Steps for building this project
---------
