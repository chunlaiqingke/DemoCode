package com.handsome.democode.jvm.classloader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PersonClassloader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        PersonClassloader mcl = new PersonClassloader();
        Class<?> clazz = Class.forName("Person", true, mcl);
        Class<?> clazz2 = Class.forName("Person", true, mcl);
        Object obj = clazz.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }

    /**
     * 使用双亲委派，重写这个方法
     * @param name
     *         The <a href="#name">binary name</a> of the class
     *
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(new URI("file:////Users/zhaojun/Desktop/tmp/Person.class")));
            return this.defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
