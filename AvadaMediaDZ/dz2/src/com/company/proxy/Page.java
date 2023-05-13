package com.company.proxy;

public class Page implements IPage{

    String url;

    public Page(String url) {
        this.url = url;
        System.out.println(this.url + " page is downloaded");
    }

    @Override
    public void show() {
        System.out.println(this.url + " page is shown");
    }
}
