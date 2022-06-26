package com.tyler.book_monitor.ui.content;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tyler.book_monitor.R;
import com.tyler.book_monitor.adapters.ChapterAdapter;
import com.tyler.book_monitor.adapters.ChapterChoiceAdapter;
import com.tyler.book_monitor.data.models.Chapter;
import com.tyler.book_monitor.helpers.IChapterClick;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ChapterChoicesFragment extends DialogFragment {

//    private RecyclerView rvChapterChoices;
    private ListView lvChapterChoices;

    private List<Chapter> mChapterChoices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

            Toast.makeText(getActivity(), "You selected " + chapter.getTitle(), Toast.LENGTH_SHORT).show();

            dismiss();
        });
    }
}