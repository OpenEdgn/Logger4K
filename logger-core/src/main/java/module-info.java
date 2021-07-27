module logger4k.core {
    requires java.base;
    requires kotlin.stdlib;
    requires kotlin.reflect;
    exports com.github.openEdgn.logger4k;
    opens com.github.openEdgn.logger4k;
    exports com.github.openEdgn.logger4k.plugin;
    opens com.github.openEdgn.logger4k.plugin;
    exports com.github.openEdgn.logger4k.utils.format.classes;
    opens com.github.openEdgn.logger4k.utils.format.classes;
    exports com.github.openEdgn.logger4k.utils.format.message;
    opens com.github.openEdgn.logger4k.utils.format.message;
    exports com.github.openEdgn.logger4k.utils.format.datas.impl;
    opens com.github.openEdgn.logger4k.utils.format.datas.impl;
    exports com.github.openEdgn.logger4k.utils.format.datas;
    opens com.github.openEdgn.logger4k.utils.format.datas;
    exports com.github.openEdgn.logger4k.utils;
    opens com.github.openEdgn.logger4k.utils;

}
