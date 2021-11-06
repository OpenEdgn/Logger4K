module logger4k.forward {
    requires kotlin.stdlib;
    requires logger4k.core;
    requires kotlin.reflect;
    requires java.logging;
    requires org.apache.logging.log4j;
    requires org.slf4j;
    exports logger4k.forward;
    opens logger4k.forward;
    provides com.github.openEdgn.logger4k.api.ILoggerSearch with logger4k.forward.ForwardSearch;
}
