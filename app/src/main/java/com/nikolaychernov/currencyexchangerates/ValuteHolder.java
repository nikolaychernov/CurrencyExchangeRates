package com.nikolaychernov.currencyexchangerates;

import android.view.View;
import android.widget.TextView;

import uk.co.ribot.easyadapter.ItemViewHolder;
import uk.co.ribot.easyadapter.PositionInfo;
import uk.co.ribot.easyadapter.annotations.LayoutId;
import uk.co.ribot.easyadapter.annotations.ViewId;

/**
 * Created by Nikolay on 17.10.2015.
 */
@LayoutId(R.layout.valute_list_item)
public class ValuteHolder extends ItemViewHolder<Valute> {

    @ViewId(R.id.charCode)
    TextView mCharCodeText;

    @ViewId(R.id.name)
    TextView mNameText;

    @ViewId(R.id.value)
    TextView mValueText;

    public ValuteHolder(View view) {
        super(view);
    }

    @Override
    public void onSetValues(Valute valute, PositionInfo positionInfo) {
        mNameText.setText(valute.nominal + " " + valute.name);
        mCharCodeText.setText(valute.charCode);
        mValueText.setText(valute.value + " \u20BD");
    }
}