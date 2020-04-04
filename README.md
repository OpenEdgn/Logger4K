# LOGGER 4K (BETA)

LOGGER4K 是一个现代化的轻量级Kotlin日志框架，100% 使用 `Kotlin`

![](https://img.shields.io/badge/LICENSE-MIT-green.svg) ![](https://img.shields.io/badge/CODE-Kotlin-green.svg) [![](https://jitpack.io/v/OPEN-EDGN/Logger4K.svg)](https://jitpack.io/#OPEN-EDGN/Logger4K)

## 入门

本项目使用 `Gradle `构建,

如有发现漏洞或者意见可在项目`Issues` 下反馈.

### 开始之前

> 在开始之前，你需要将模块引入到项目中，下面介绍了`Gradle` 和`Apache Maven` 的引入方法

#### Maven & Gradle

1. 将JitPack存储库添加到您的构建文件中

Maven:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Gradle

```groovy
allprojects {
	repositories {
        //其他仓库 
		maven { url 'https://jitpack.io' }
	}
}
```

2. 添加依赖项

Maven

```xml
<dependency>
    <groupId>com.github.OPEN-EDGN.ETOOL4K</groupId>
    <artifactId>${modName}</artifactId>
    <version>${version}</version>
</dependency>
```

Gradle

```groovy
dependencies {
    implementation 'com.github.OPEN-EDGN.ETOOL4K:${modName}:${version}'
}
```

> 其中，`${modName}` 代表 引用的模块名称，而 `${version}` 则代表引用的版本号，请按需引入。


## 使用

关于各个子模块的引用请查看其 `README` 文件

1. [Logger4k Project](/logger4k/README.md)
2. [Security4k Project](/security4k/README.md)
3. [Tools4k Project](/tools4k/README.md)


## LICENSE

```text
Copyright (c) 2013, Aldo Cortesi. All rights reserved.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```

