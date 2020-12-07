module logger4k.console {
    requires kotlin.stdlib;
    requires logger4k.core;
    requires kotlin.reflect;
    exports logger4k.console;
    opens logger4k.console;
}