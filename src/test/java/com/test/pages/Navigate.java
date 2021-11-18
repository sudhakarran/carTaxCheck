package com.test.pages;

import com.test.configs.ConfigFileReader;

public class Navigate extends UtilPage{

    private static Navigate navigate;

    public static Navigate getInstance() {
        return (navigate == null) ? new Navigate() : navigate;
    }

    public void toTaxCarCheckSite() {
        driver.get(ConfigFileReader.getInstance().getApplicationUrl());
    }

}
