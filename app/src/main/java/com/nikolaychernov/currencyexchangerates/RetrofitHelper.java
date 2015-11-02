package com.nikolaychernov.currencyexchangerates;

import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import retrofit.SimpleXmlConverterFactory;

/**
 * Created by Nikolay on 17.10.2015.
 */
public class RetrofitHelper {

    public CentrobankService newCentrobankService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CentrobankService.ENDPOINT)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(CentrobankService.class);
    }

}
