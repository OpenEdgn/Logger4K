module logger4k.impl {
    requires kotlin.stdlib;
    requires logger4k.core;
    requires kotlin.reflect;
    exports logger4k.console;
    opens logger4k.console;
    provides com.github.openEdgn.logger4k.api.ILogApi with logger4k.console.ConsoleLogApi;
}
