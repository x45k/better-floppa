
// For those who want the bleeding edge
buildscript {
    repositories {
        jcenter()
        maven {
            name = "forge"
            url = "http://files.minecraftforge.net/maven"
        }
    }
    dependencies {
        classpath 'net.minecraftforge.gradle:ForgeGradle:2.1-SNAPSHOT';
    }
}
apply plugin: 'net.minecraftforge.gradle.forge'

/*
// for people who want stable - not yet functional for MC 1.8.8 - we require the forgegradle 2.1 snapshot
plugins {
    id "net.minecraftforge.gradle.forge" version "2.0.2"
}
*/
version = "1.0"
group= "com.x45k.weedclient" // http://maven.apache.org/guides/mini/guide-naming-conventions.html
archivesBaseName = "weedclient"

minecraft {
    version = "1.8.9-11.15.1.2318-1.8.9"
    runDir = "run"

    // the mappings can be changed at any time, and must be in the following format.
    // snapshot_YYYYMMDD   snapshot are built nightly.
    // stable_#            stables are built at the discretion of the MCP team.
    // Use non-default mappings at your own risk. they may not allways work.
    // simply re-run your setup task after changing the mappings to update your workspace.
    mappings = "stable_20"
    // makeObfSourceJar = false // an Srg named sources jar is made by default. uncomment this to disable.
}

dependencies {
    // you may put jars on which you depend on in ./libs
    // or you may define them like so..
    //compile "some.group:artifact:version:classifier"
    //compile "some.group:artifact:version"

    // real examples
    //compile 'com.mod-buildcraft:buildcraft:6.0.8:dev'  // adds buildcraft to the dev env
    //compile 'com.googlecode.efficient-java-matrix-library:ejml:0.24' // adds ejml to the dev env

    // the 'provided' configuration is for optional dependencies that exist at compile-time but might not at runtime.
    //provided 'com.mod-buildcraft:buildcraft:6.0.8:dev'

    // the deobf configurations:  'deobfCompile' and 'deobfProvided' are the same as the normal compile and provided,
    // except that these dependencies get remapped to your current MCP mappings
    //deobfCompile 'com.mod-buildcraft:buildcraft:6.0.8:dev'
    //deobfProvided 'com.mod-buildcraft:buildcraft:6.0.8:dev'

    // for more info...
    // http://www.gradle.org/docs/current/userguide/artifact_dependencies_tutorial.html
    // http://www.gradle.org/docs/current/userguide/dependency_management.html

}

processResources
{
    // this will ensure that this task is redone when the versions change.
    inputs.property "version", project.version
    inputs.property "mcversion", project.minecraft.version

    // replace stuff in mcmod.info, nothing else
    from(sourceSets.main.resources.srcDirs) {
        include 'mcmod.info'

        // replace version and mcversion
        expand 'version':project.version, 'mcversion':project.minecraft.version
    }

    // copy everything else, thats not the mcmod.info
    from(sourceSets.main.resources.srcDirs) {
        exclude 'mcmod.info'
    }
}
