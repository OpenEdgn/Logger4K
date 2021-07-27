module logger4k.impl {
    requires kotlin.stdlib;
    requires logger4k.core;
    requires kotlin.reflect;
    exports logger4k.console;
    opens logger4k.console to logger4k.core;
}
