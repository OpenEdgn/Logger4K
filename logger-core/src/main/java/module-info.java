module logger4k.core {
    requires kotlin.stdlib;
    requires kotlin.reflect;
    exports com.github.openEdgn.logger4k;
    opens com.github.openEdgn.logger4k;
    exports com.github.openEdgn.logger4k.utils;
    opens com.github.openEdgn.logger4k.utils;
    exports com.github.openEdgn.logger4k.api;
    opens com.github.openEdgn.logger4k.api;
    uses com.github.openEdgn.logger4k.api.ILogApi;
    uses com.github.openEdgn.logger4k.api.ILoggerSearch;
    uses com.github.openEdgn.logger4k.utils.IClassNameFormat;
    uses com.github.openEdgn.logger4k.utils.IMessageFormat;
}
