package com.nikolaychernov.currencyexchangerates;

import retrofit.http.GET;
import rx.Observable;

/**
 * Created by Nikolay on 17.10.2015.
 */
public interface CentrobankService {

    String ENDPOINT = "http://cbr.ru/scripts/";

    @GET("XML_daily.asp")
    Observable<ValCurs> getValute();

}