module logger4k.console {
    requires kotlin.stdlib;
    requires logger4k.core;
    requires kotlin.reflect;
    requires org.slf4j;
    exports logger4k.impl.slf4j;
    opens logger4k.impl.slf4j;
}