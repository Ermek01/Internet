package com.geektech.internet.ui.main;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.internet.R;
import com.geektech.internet.data.pojo.CurrentWeather;

public class ForecastViewHolder extends RecyclerView.ViewHolder {

    TextView tvMin, tvMax;

    public ForecastViewHolder(@NonNull View itemView) {
        super(itemView);
        tvMin = itemView.findViewById(R.id.tvWeatherMin);
        tvMax = itemView.findViewById(R.id.tvWeatherMax);
    }

    public void bind(CurrentWeather currentWeather){
        tvMax.setText(currentWeather.getMain().getTempMax().toString());
        tvMin.setText(currentWeather.getMain().getTempMin().toString());
    }
}
