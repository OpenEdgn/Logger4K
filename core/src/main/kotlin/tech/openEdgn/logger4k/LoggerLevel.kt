package tech.openEdgn.logger4k;
/**
 * # 日志类型划分
 *
 * 1. DEBUG: 调试
 * 2. INFO: 标准
 * 3. WARN: 警告
 * 4. ERROR: 错误
 * @constructor 数字等级
 */
enum class LoggerLevel(val levelInt: Int) {
    DEBUG(0),
    INFO(1),
    WARN(2),
    ERROR(3);
}
