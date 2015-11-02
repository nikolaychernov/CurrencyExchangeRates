package com.nikolaychernov.currencyexchangerates;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by Nikolay on 17.10.2015.
 */
public class Valute {

    @Attribute(name = "ID", required = false)
    public String id;

    @Element(name = "NumCode", required = false)
    public int numCode;

    @Element(name = "CharCode", required = false)
    public String charCode;

    @Element(name = "Nominal", required = false)
    public long nominal;

    @Element(name = "Name", required = false)
    public String name;

    @Element(name = "Value", required = false)
    public String value;
}