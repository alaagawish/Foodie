package eg.gov.iti.jets.foodie.home.view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import eg.gov.iti.jets.foodie.home.view.Slider;

import java.util.ArrayList;
import java.util.List;

import eg.gov.iti.jets.foodie.R;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {

    private List<Slider> sliderList;

    public SliderAdapter(ArrayList<Slider> sliderList) {
        this.sliderList = sliderList;
    }

    public void updateArrayList(List<Slider> sliderList) {
        this.sliderList = sliderList;
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null, false);
        return new SliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        Slider sliderItem = sliderList.get(position);

        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImgUrl())
                .fitCenter()
                .into(viewHolder.imageViewBackground);

    }

    @Override
    public int getCount() {
        if (sliderList == null)
            return 0;
        return sliderList.size();
    }

    public static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        private ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imgImageView);
        }
    }
}
