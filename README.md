# Logger 4K

<p style="text-align: center">
<a href="https://github.com/OpenEdgn/Logger4K" target="_blank"><img alt="Jenkins" src="https://github.com/OpenEdgn/Logger4K/actions/workflows/release.yml/badge.svg?branch=master&color=green&style=flat-square"/></a>
<a href="LICENSE"><img alt="GitHub license" src="https://img.shields.io/github/license/OpenEdgn/Logger4K"></a>
<a href="#"><img alt="GitHub repo size" src="https://img.shields.io/github/repo-size/OpenEdgn/Logger4K"></a>
<a href="https://jitpack.io/#OpenEdgn/Logger4K" target="_blank"> <img alt="JitPack" src="https://img.shields.io/jitpack/v/github/OpenEdgn/Logger4K"></a>
</p>

CHINESE | [ENGLISH](README_EN.md) (Google Translate)

`Logger4K` 是一个轻量级`Kotlin JVM`日志框架

## 入门

本项目使用 `Gradle `构建, 覆盖 `Junit` 单元测试，如有发现漏洞或者意见可在项目`Issues` 下反馈,欢迎各位大佬提建议。

### 开始之前

> 在开始之前，你需要将模块引入到项目中，本项目使用 JitPack 作为依赖仓库，下面介绍了使用`Gradle` 和`Apache Maven` 的引入方法

#### Maven & Gradle

##### 1. 将JitPack存储库添加到您的构建文件中

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
        //其他仓库 
        maven { url 'https://jitpack.io' }
    }
}
```

##### 2. 添加依赖项

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
    implementation "com.github.OpenEdgn.Logger4K:${modName}:${version}"
}
```

> 其中，`${modName}` 代表 引用的模块名称，而 `${version}` 则代表引用的版本号，请按需引入。

其中，当前最新版本为：[![JitPack](https://img.shields.io/jitpack/v/github/OpenEdgn/Logger4K?label=version&style=flat-square)](https://jitpack.io/#OpenEdgn/Logger4K)

### 使用

``` kotlin

 logger.debug("DEBUG Message.") 
 //  输出 DEBUG 类型的日志 ( 注意，DEBUG未开启则不会输出日志)

 logger.info("INFO  Message.") 
 //  输出 INFO 类型的日志

 logger.warn("WARN  Message.") 
 //  输出 WARN 类型的日志

 logger.error("ERROR  Message.") 
 //  输出 ERROR 类型的日志

logger.debugOnly { 
    //此代码块仅在调试模式下运行
    info("INFO")
    warn("WARN")
    debug("DEBUG")
    error("ERROR")
}

```

更多使用方法请查看 `TEST` 下的 [PrintLogger.kt](./logger-console/src/test/kotlin/PrintLogger.kt)
和 [LoggerMainTestAll.kt](./logger-console/src/test/kotlin/LoggerMainTestAll.kt)  文件。

## LICENSE

此项目使用 MIT ，详情请查看 [License](./LICENSE) 文件. 
