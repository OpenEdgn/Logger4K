# LOGGER 4K

<p style="text-align: center">
<a href="https://travis-ci.com/OpenEdgn/Logger4K" target="_blank"><img alt="Jenkins" src="https://img.shields.io/travis/OpenEdgn/Logger4K?branch=master&color=green&style=flat-square"/></a>
<a href="LICENSE"><img alt="GitHub license" src="https://img.shields.io/github/license/OpenEdgn/Logger4K?color=green&style=flat-square"></a>
<a href="#"><img alt="GitHub repo size" src="https://img.shields.io/github/repo-size/OpenEdgn/Logger4K?color=green&style=flat-square"></a>
<a href="https://jitpack.io/#OpenEdgn/Logger4K" target="_blank"> <img alt="JitPack" src="https://img.shields.io/jitpack/v/github/OpenEdgn/Logger4K?color=green&style=flat-square"></a>
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

##### 2. Add dependencies

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

### Usage

Basic usage of the Logger API:

``` kotlin
val logger = getLogger () // Create a Logger object


 logger.debug ( " DEBUG Message. " ) 
  //   Output DEBUG type log (note that the log will not be output if DEBUG is not enabled)

 logger.info ( " INFO Message. " ) 
  //   Output INFO type log

 logger.warn (" WARN Message. ") 
  //   Output WARN type log

 logger.error("ERROR  Message.") 
  //   Output log of ERROR type

logger.debugOnly { 
    // This code block only runs in debug mode
    info("INFO")
    warn("WARN")
    debug("DEBUG")
    error("ERROR")
}

```

Please see [PrintLogger.kt](./logger-console/src/test/kotlin/logger4k/impl/console/PrintLogger.kt)
and [LoggerMainTestAll.kt](./logger-console/src/test/kotlin/logger4k/impl/console/LoggerMainTestAll.kt)  under `TEST` for more usage
methods.

## LICENSE

Warn: This project uses [SLF4J](https://github.com/qos-ch/slf4j) source code.

SEE [LICENSE FILE](./LICENSE) AND  [SLF4J LICENSE](https://github.com/qos-ch/slf4j/blob/master/LICENSE.txt)
