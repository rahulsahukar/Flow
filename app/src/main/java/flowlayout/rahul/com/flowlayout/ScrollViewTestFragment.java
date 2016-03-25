package flowlayout.rahul.com.flowlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.Set;
import java.util.zip.Inflater;

/**
 * Created by zhy on 15/9/10.
 */
public class ScrollViewTestFragment extends Fragment
{
    boolean edit = false;
    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView","Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView","Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView","Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView","Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView","Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView","Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android", "Weclome", "Button ImageView", "TextView", "Helloworld",
                    "Android", "Weclome Hello", "Button Text", "TextView"};

    private TagFlowLayout mFlowLayout;
    private TagAdapter<String> mAdapter ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_main_sc, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        mFlowLayout = (TagFlowLayout) view.findViewById(R.id.id_flowlayout);
        //mFlowLayout.setMaxSelectCount(3);

        mFlowLayout.setAdapter(mAdapter = new TagAdapter<String>(mVals) {

            @Override
            public View getView(FlowLayout parent, final int position, String s) {

                Log.i("Size", ""+mFlowLayout.getWidth());
//                if( position%2 == 0){
//                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                            15, LinearLayout.LayoutParams.WRAP_CONTENT);
//                    LinearLayout ll = new LinearLayout(getActivity());
//                    ll.setLayoutParams(params);
//                    return ll;
//                }

                LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.item,null);
                TextView textView = (TextView) view.findViewById(R.id.text);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edit = !edit;
                        mAdapter.notifyDataChanged();
                    }
                });

                ImageView delete = (ImageView) view.findViewById(R.id.delete);
                if(edit == false)
                    delete.setVisibility(View.GONE);
                else
                    delete.setVisibility(View.VISIBLE);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVals[position] = null;
                        mAdapter.notifyDataChanged();
                    }
                });

                if(mVals[position] == null){
                    return null;
                }
                textView.setText(s);
                return view;

                /*TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        mFlowLayout, false);
                tv.setText(s);
                tv.setTextSize(20);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                tv.setLayoutParams(params);

                FrameLayout fl = new FrameLayout(getActivity());
                fl.addView(tv);

                ImageView iv = new ImageView(getActivity());
                iv.setBackground(getActivity().getResources().getDrawable(R.drawable.delete));
                iv.setPadding(10, 10, 10, 10);

                FrameLayout.LayoutParams p = p = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);;
                p.gravity = Gravity.TOP|Gravity.RIGHT;
                iv.setLayoutParams(p);

                fl.addView(iv);


                return fl;*/
            }
        });

        //mAdapter.setSelectedList(1,3,5,7,8,9);
        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
        {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent)
            {
                //Toast.makeText(getActivity(), mVals[position], Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.GONE);
                return true;
            }
        });


        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                getActivity().setTitle("choose:" + selectPosSet.toString());
            }
        });
    }
}
