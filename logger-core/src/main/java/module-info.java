module logger4k.core {
    requires kotlin.stdlib;
    requires kotlin.reflect;
    exports com.github.openEdgn.logger4k;
    opens com.github.openEdgn.logger4k;
    exports com.github.openEdgn.logger4k.utils;
    opens com.github.openEdgn.logger4k.utils;

}
