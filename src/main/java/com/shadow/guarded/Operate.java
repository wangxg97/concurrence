package com.shadow.guarded;

import java.util.concurrent.TimeUnit;

public class Operate {

    public static  String dbOprate() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        return "result";
    }
}
