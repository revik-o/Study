package com.company.proxy;

public class ProxyPage implements IPage {

    String url;
    Page page;

    public ProxyPage(String url) {
        this.url = url;
    }

    @Override
    public void show() {
        if (this.page == null) {
            this.page = new Page(this.url);
        }
        this.page.show();
    }
}
