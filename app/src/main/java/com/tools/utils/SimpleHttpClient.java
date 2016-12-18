package com.tools.utils;

/**
 * Created by Administrator on 2016/12/16 0016.
 */

public class SimpleHttpClient {
    private SimpleHttpClient() {
    }
    public Builder newBuilder(){
        return new Builder();
    }
    public static class Builder{
        public Builder get(){
            return this;
        }
        public SimpleHttpClient build(){
            return new SimpleHttpClient();
        }
    }
}
