package com.designMode.chain.responsibility;

public interface Filter {
    boolean doFilter(Request req, Response res, FilterChain chain);
}
