module logger4k.core {
    requires java.base;
    requires kotlin.stdlib;
    requires kotlin.reflect;
    requires kotlin.stdlib.common;
    exports com.github.openEdgn.logger4k;
    exports com.github.openEdgn.logger4k.plugin;
    exports com.github.openEdgn.logger4k.format;
}