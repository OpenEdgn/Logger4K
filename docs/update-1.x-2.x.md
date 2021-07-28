# 1.x - 2.x 迁移

> 此文档记录 1.x 到 2.x 迁移方案

## 变更日志

1. 在 2.x 中移除了 `logger4k` 到其他日志框架转发模块 。 [\(原因\)](#移除转发模块)
1. 在 2.x 中移除了 `slf4j` 到 `logger4k` 的日志转发 。[\(原因\)](#移除转发模块)

## 移除转发模块

将转发模块移动至 [Logger4KSupport](https://github.com/OpenEdgn/Logger4KSupport) ,与主线脱离

