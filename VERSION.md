# 版本更新日志

## 关于版本控制

如果此项目发布了新的版本 ,版本号为 [X.Y.Z], 则

- X：大版本更新，可能包含破坏性方法（如删除过时方法），从低版本升级时需谨慎
- Y：功能更新，不破坏现有方法和字段
- Z：漏洞修复更新，建议随时跟进

## 版本日志

### 2.0.0

重构的 Logger4K 2.0 来了 ✨

注意：此版本为 2.0 初始版本，想从`1.x.x` 迁移请查看 [迁移教程](./docs/update-1.x-2.x.md)

- 移动模块 `logger-slf4j` 和 `slf4j-over-logger4k`到
  [Logger4KSupport](https://github.com/OpenEdgn/Logger4KSupport)

- 重构 `logger-core` 模块，现已内嵌一个简单的日志输出实现
- `logger-console` 模块啥也没变

### 1.7.0

- 升级 `slf4j` 到 1.7.31
- 升级 `kotlin` 到 1.5.21
- 升级 `gradle` 到 6.8.3
- 优化模块 `logger-slf4j` 逻辑，现在引入此模块后不会影响项目原 `slf4j` 版本了

### 1.6.0

- 优化 `logger-console` 日志输出样式
- 允许 `logger-console` 在运行时配置

### 1.5.1

- 优化 `logger-console` 日志输出样式

### 1.5.0

**注意**: 模块`Core` 已更名为 `logger-core`

### 1.4.0

**注意**：存在部分兼容性问题，须在`module-info.java` 下更改模块命名。

- 统一化 `module-info` 命名方式
- `logger-console` 下启用异步日志
- 修复在某些情况下找不到日志实现的问题

### 1.3.2

- 修复在某些情况下找不到日志实现的问题
- 新增查询日志实现的方案

### 1.3.1

- 修复在某些情况下找不到日志实现的问题

### 1.3.0

- 添加 Logger4K 到 SLF4J 的代理 (`logger-slf4j`)
- 优化各大模块下类名称格式化方案

### 1.2.1

- 优化 `logger-console` 下显示逻辑

### 1.2.0

- 添加 SLF4J 到 Logger4K 的代理 (`slf4j-over-logger4k`)
- 优化 `logger-console` 下类名称格式化方案

### 1.1.0

- 修复模块化问题

### 1.0.4

- 修复无效的依赖关系

### 1.0.3

- 去除 `Logger.xxx("message, {}",data)` 中数据自动格式化

### 1.0.2

- 更新 `Kotlin` 到 `1.4.10`
- 优化项目目录结构

### 1.0.1

第一个稳定发行版

