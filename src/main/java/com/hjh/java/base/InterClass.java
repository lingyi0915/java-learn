package com.hjh.java.base;

import com.sun.xml.internal.ws.util.StringUtils;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Stack;

/**
 * @author hjh
 * @date 2019/12/25
 */
public interface InterClass {
    default void print() {

    }

    static void ptt() {
        System.out.println("InterClass.static");
    };

    void pt();

}

class ImplClass implements InterClass {
    @Override
    public void pt() {

    }

    static void ptt() {
        System.out.println("ImplClass.static");
    }

  public static void main(String[] args) throws IOException {
      InterClass impl = new ImplClass();

      ImplClass impl2 = new ImplClass();

      InterClass.ptt();
      ImplClass.ptt();

      InputStream input = new ByteArrayInputStream(new byte[100]);
      Reader reader = new StringReader("");
//      Files.size(Paths.get(URI.create("")));

      StringBuilder builder = new StringBuilder("aaabbbccc");
  }
}
