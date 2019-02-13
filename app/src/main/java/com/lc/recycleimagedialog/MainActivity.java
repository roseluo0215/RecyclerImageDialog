package com.lc.recycleimagedialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : lc
 *         date : 19/2/13
 */
public class MainActivity extends AppCompatActivity {

  private ShowRecyclerImageDialog mShowImageDialog;
  private List<String> list = new ArrayList<>(3);

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    String url1 =
        "https://img5.duitang.com//uploads//item//201306//14//20130614185903_raNR3.thumb.jpeg";
    String url2 =
        "https://img5.duitang.com//uploads//item//201112//11//20111211191621_HU4Bj.thumb.jpg";
    String url =
        "https://img4.duitang.com//uploads//blog//201311//04//20131104193715_NCexN.thumb.jpeg";
    list.add(url);
    list.add(url1);
    list.add(url2);


    findViewById(R.id.btn_show).setOnClickListener(v -> {

      if (mShowImageDialog == null) {
        mShowImageDialog = new ShowRecyclerImageDialog(v.getContext());
      }
      mShowImageDialog.setImageUrlList(list);
      mShowImageDialog.show();
    });
  }
}
