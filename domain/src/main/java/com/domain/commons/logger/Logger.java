package com.domain.commons.logger;

public interface Logger {

    void tag(String tag);

    void d(String message, Object... objects);
    void d(Throwable t);
    void d(Throwable t, String message, Object... objects);

    void i(String message, Object... objects);
    void i(Throwable t);
    void i(Throwable t, String message, Object... objects);

    void e(String message, Object... objects);
    void e(Throwable t);
    void e(Throwable t, String message, Object... objects);
}
