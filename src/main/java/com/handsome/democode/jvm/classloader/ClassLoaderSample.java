package com.handsome.democode.jvm.classloader;

import java.net.URL;

public class ClassLoaderSample {
    public static void main(String[] args) {

        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }
    }
}
