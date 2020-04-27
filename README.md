# LOGGER 4K

<p style="text-align: center">
<a href="https://jenkins.open-edgn.tech/job/Logger4K/" target="_blank"><img alt="Jenkins" src="https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fjenkins.open-edgn.tech%2Fjob%2FLogger4K%2F&style=flat-square"/></a>
<a href="LICENSE"><img alt="GitHub license" src="https://img.shields.io/github/license/OpenEdgn/Logger4K?color=green&style=flat-square"></a>
<a href="#"><img alt="GitHub repo size" src="https://img.shields.io/github/repo-size/OpenEdgn/Logger4K?color=green&style=flat-square"></a>
<a href="https://jitpack.io/#OpenEdgn/Logger4K" target="_blank"> <img alt="JitPack" src="https://img.shields.io/jitpack/v/github/OpenEdgn/Logger4K?color=green&style=flat-square"></a>
<a href="https://jenkins.open-edgn.tech/job/Logger4K/" target="_blank"> <img alt="JitPack" src="https://img.shields.io/badge/Jenkins-Nightly-green?color=red&style=flat-square"></a>
</p>

[CHINESE](README_CN.md) | ENGLISH (Google Translate)


`LOGGER4K` Is a lightweight `Kotlin`logging framework.

## Getting Started

This project used `Gradle ` to build coverage `Junit` test.

If you find a bug or comments, you can feedback under `Issues`.


### Add this to the project


> Before you start, you need to introduce the module into the project. The introduction of `Apache Maven` and `Gradle` is introduced below.

#### Maven & Gradle

##### 1. Add it to the project:


**Maven**

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

**Gradle**

```groovy
allprojects {
	repositories {
        // ...
	    maven { url 'https://jitpack.io' }
	}
}
```

#####  2. Add dependencies

**Maven** 

```xml
<dependency>
    <groupId>com.github.OpenEdgn.Logger4K</groupId>
    <artifactId>${modName}</artifactId>
    <version>${version}</version>
</dependency>
```

**Gradle**

```groovy
dependencies {
    implementation 'com.github.OpenEdgn.Logger4K:${modName}:${version}'
}
```

> `$ {modName}` represents the module name, and `$ {version}` represents the referenced version number.

version：[![JitPack](https://img.shields.io/jitpack/v/github/OpenEdgn/Logger4K?label=version&style=flat-square)](https://jitpack.io/#OpenEdgn/Logger4K)

| Module enum |     Module Name     |                               Introduction                               |
| :------: | :----------: | :--------------------------------------------------------------: |
|  `core`  | Core module | The core module has all functions and outputs logs in `System.out` and` System.err`. |
| `extras` | IO module |     Lightweight persistent log module, which depends on `core` and supports saving logs to a file.    |

 **WARN**：In the module `extras`, the` DEBUG` level log will not be saved to a file!

###  Usage

Basic usage of the Logger API:

``` kotlin
val logger = getLogger () // Create a Logger object

LoggerConfig .enableDebug () 
 // Turn on DEBUG mode (1)
enableDebug ()
// Turn on DEBUG mode (2)
disableDebug ()
// Turn off DEBUG mode (1) 
LoggerConfig .enableDebug ()
 // Turn off DEBUG mode (2)

 logger.debug ( " DEBUG Message. " ) 
  //   Output DEBUG type log (note that the log will not be output if DEBUG is not enabled)

 logger.info ( " INFO Message. " ) 
  //   Output INFO type log

 logger.warn ( " WARN Message. " ) 
  //   Output WARN type log

 logger.error ( " ERROR Message. " ) 
  //   Output log of ERROR type

logger.debugOnly { 
    // This code block only runs 
    info ( " INFO " ) in debug mode
    warn ( " WARN " )
    debug ( " DEBUG " )
    error ( " ERROR " )
}

```
Please see [LoggerTest](./core/src/test/kotlin/com/github/openEdgn/logger4k/LoggerTest.kt) under `TEST` for more usage methods.
## LICENSE

SEE [LICENSE FILE](./LICENSE)
