package com.shop.util;

import java.util.Random;

public class CodeGenerator {

    public static String generate(){
       return String.format("%04d", new Random().nextInt(10000));
   }
}
