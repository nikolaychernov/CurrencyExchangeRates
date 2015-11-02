package com.nikolaychernov.currencyexchangerates;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by Nikolay on 17.10.2015.
 */
@Root(name = "ValCurs", strict = false)
public class ValCurs {

    @Attribute(name = "Date", required = false)
    public String date;

    @Attribute(name = "name", required = false)
    public String name;

    /*@Element(name = "Valute", required = false)
    public Valute valute;*/

    @ElementList(entry = "Valute", inline = true)
    public List<Valute> valutes;

    //@Root(name = "Valute", strict = false)
    /*public static class Valute {

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
    }*/

}