package com.company.flyweight;

import java.util.HashMap;

public class FlyweightDomainName {

    HashMap<String, DomainName> map = new HashMap<>();

    public DomainName getDomainNameByName(String s) {
        DomainName domainName = map.get(s);
        if (domainName == null) {
            domainName = new DomainName(s);
            map.put(domainName.name, domainName);
            System.out.println("add new object");
        }
        return domainName;
    }

}
