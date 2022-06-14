package com.handsome.democode.netty.msgpack;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessagePackDemo {
    public static void main(String[] args) throws IOException {
        ArrayList<String> src = new ArrayList<>(Arrays.asList("msgpack", "kumofs", "viver"));
        MessagePack messagePack = new MessagePack();
        byte[] write = messagePack.write(src);
        System.out.println(write.length);
        List<String> read = messagePack.read(write, Templates.tList(Templates.TString));
        System.out.println(read);
    }
}
