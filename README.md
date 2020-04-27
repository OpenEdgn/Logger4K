# LOGGER 4K

`LOGGER4K` 是一个轻量级`Kotlin`日志框架

<p style="text-align: center">
<a href="LICENSE"><img alt="GitHub license" src="https://img.shields.io/github/license/OpenEdgn/Logger4K?color=green&style=flat-square"></a>
<img alt="GitHub repo size" src="https://img.shields.io/github/repo-size/OpenEdgn/Logger4K?color=green&style=flat-square">
<img alt="JitPack" src="https://img.shields.io/jitpack/v/github/OpenEdgn/Logger4K?color=green&style=flat-square">
</p>


## 入门

本项目使用 `Gradle `构建, 覆盖 `Junit` 单元测试

如有发现漏洞或者意见可在项目`Issues` 下反馈.

### 开始之前

> 在开始之前，你需要将模块引入到项目中，下面介绍了`Gradle` 和`Apache Maven` 的引入方法

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

#####  2. 添加依赖项

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

> 其中，`${modName}` 代表 引用的模块名称，而 `${version}` 则代表引用的版本号，请按需引入。

当前版本：![JitPack](https://img.shields.io/jitpack/v/github/OpenEdgn/Logger4K?label=version&style=flat-square)

| 模块代号 |     名称     |                               用途                               |
| :------: | :----------: | :--------------------------------------------------------------: |
|  `core`  | 日志核心模块 | 核心模块，具有所有功能 ，在`System.out` 和 `System.err` 输出日志 |
| `extras` | 文件日志模块 |     轻量级持久日志模块，依赖于 `core`,支持将日志保存到文件下     |

 **注意**：在`extras`中，不会保存`DEBUG`级别日志到文件！

###  在项目中使用

> 所有模块均开箱即用

``` kotlin
val logger = getLogger() //创建一个Logger 对象

LoggerConfig.enableDebug() 
//开启DEBUG 模式 (1)
enableDebug()
//开启DEBUG 模式 (2)
disableDebug()
// 关闭DEBUG 模式 (1)
LoggerConfig.enableDebug()
// 关闭DEBUG 模式 (2)

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
更多使用方法请查看 `TEST` 下的 [LoggerTest](./core/src/test/kotlin/com/github/openEdgn/logger4k/LoggerTest.kt) 文件。

## LICENSE

请转到 [LICENSE FILE](./LICENSE)
