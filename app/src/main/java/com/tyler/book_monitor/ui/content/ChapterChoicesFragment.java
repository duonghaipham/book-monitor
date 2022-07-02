package com.tyler.book_monitor.ui.content;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.DialogFragment;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.adapters.ChapterChoiceAdapter;
import com.tyler.book_monitor.data.models.Chapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ChapterChoicesFragment extends DialogFragment {

    public interface IChapterChoice {
        void onDataPass(int chapterIndex);
    }

    private IChapterChoice mChapterChoice;

    private ListView lvChapterChoices;

    private List<Chapter> mChapterChoices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ArrayList<String> chapterChoices = getArguments().getStringArrayList("chapterChoices");
        mChapterChoices = new Vector<>();

        for (String chapter : chapterChoices) {
            mChapterChoices.add(new Chapter(chapter, "This is chapter " + chapter));
        }

        return inflater.inflate(R.layout.fragment_chapter_choices, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvChapterChoices = view.findViewById(R.id.lv_chapter_choices);

        ChapterChoiceAdapter chapterChoiceAdapter = new ChapterChoiceAdapter(getActivity(), mChapterChoices);
        lvChapterChoices.setAdapter(chapterChoiceAdapter);

        lvChapterChoices.setOnItemClickListener((parent, view1, position, id) -> {
            Chapter chapter = mChapterChoices.get(position);

            mChapterChoice.onDataPass(position);

            dismiss();
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mChapterChoice = (IChapterChoice) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement IChapterChoice");
        }
    }
}