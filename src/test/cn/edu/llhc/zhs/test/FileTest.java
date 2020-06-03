package cn.edu.llhc.zhs.test;

import org.junit.Test;

public class FileTest {
    @Test
    public void showPath(){
        String path = System.getProperty("user.dir");
        path += "\\src\\main\\webapp\\img\\books\\";
        System.out.println(path);
    }
}
