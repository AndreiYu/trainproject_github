package by.poezdrw.util;


import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.testng.Reporter;

import java.io.Serializable;

// class name may not match the @Plugin name.
@Plugin(name="ReportLoggerAppender", category="Core", elementType="appender", printObject=true)
public final class ReportLoggerAppender extends AbstractAppender {


    protected ReportLoggerAppender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions) {
        super(name, filter, layout, ignoreExceptions);
    }

    @Override
    public void append(LogEvent logEvent) {
        byte [] message = this.getLayout().toByteArray(logEvent);
        Reporter.log(new String(message)); //put logs in ReportNG
    }

    // Custom appender needs to declare a factory method
    // annotated with `@PluginFactory`. Log4j will parse the configuration
    // and call this factory method to construct an appender instance with
    // the configured attributes.
    @PluginFactory
    public static ReportLoggerAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter){
        if (name == null) {
            LOGGER.error("No name provided for ReportLoggerAppender");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new ReportLoggerAppender(name, filter, layout, true);
    }

}
