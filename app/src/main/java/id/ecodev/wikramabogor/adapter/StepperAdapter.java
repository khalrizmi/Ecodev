package id.ecodev.wikramabogor.adapter;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;


public class StepperAdapter extends AbstractFragmentStepAdapter {

    String[] titles;
    Step[]   steps;

    public StepperAdapter(@NonNull FragmentManager fm, @NonNull Context context, String[] titles, Step[] steps) {
        super(fm, context);
        this.titles = titles;
        this.steps = steps;
    }


    @Override
    public Step createStep(int position) {
        return steps[position];
    }

    @Override
    public int getCount() {
        return steps.length;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        return new StepViewModel.Builder(context)
                .setTitle(titles[position])
                .create();
    }
}
