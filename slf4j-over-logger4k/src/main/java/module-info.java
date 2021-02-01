module logger4k.proxy.fromslf4j {
    requires kotlin.stdlib;
    requires logger4k.core;
    requires kotlin.reflect;
    requires org.slf4j;
    opens org.slf4j.impl;
    exports org.slf4j.impl;
}