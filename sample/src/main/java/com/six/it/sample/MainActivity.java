package com.six.it.sample;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.widget.TextView;

import com.sixit.textspan.TextTagSpan;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView startTagTv = (TextView) findViewById(R.id.start_tag);
        TextView endTagTv = (TextView) findViewById(R.id.end_tag);
        TextView multiTagTv = (TextView) findViewById(R.id.multi_tag);
        setStartTag(startTagTv);
        setEndTag(endTagTv);
        setMultiTag(multiTagTv);
    }


    void setStartTag(TextView tagView) {

        Truss truss = new Truss();
        TextTagSpan span = new TextTagSpan(this, dip2px(25), dip2px(15))
                .setRightMargin(dip2px(5))
                .setTextColor(Color.BLACK)
                .setTextSize(sp2px(11))
                .setBackground(R.drawable.tag_bacground);
        truss.pushSpan(span)
                .append("tag")
                .popSpan()
                .append("this is start tag，this is start tag，this is start tag");
        tagView.setText(truss.build());
    }


    void setEndTag(TextView tagView) {

        Truss truss = new Truss();
        TextTagSpan span = new TextTagSpan(this, dip2px(25), dip2px(15))
                .setLeftMargin(dip2px(5))
                .setTextColor(Color.RED)
                .setTextSize(sp2px(13))
                .setRadius(dip2px(3))
                .setStrokeWidth(dip2px(0.5f))
                .setStrokeColor(Color.RED);

        truss.append("this is end tag，this is end tag，this is end tag，this is end tag")
                .pushSpan(span)
                .append("tag")
                .popSpan();
        tagView.setText(truss.build());
    }

    void setMultiTag(TextView tagView) {

        Truss truss = new Truss();
        TextTagSpan span = new TextTagSpan(this, dip2px(25), dip2px(15))
                .setLeftMargin(dip2px(5))
                .setRightMargin(dip2px(5))
                .setTextColor(Color.GRAY)
                .setTextSize(sp2px(11))
                .setRadius(dip2px(3))
                .setStrokeWidth(dip2px(2f))
                .setStrokeColor(Color.BLUE);

        truss.append("this is multi tag，this is multi tag，this is multi tag，this is multi tag，this is multi tag，this is multi tag")
                .pushSpan(span)
                .append("tag")
                .popSpan()
                .append("this is multi tag，this is multi tag，this is multi tag");
        tagView.setText(truss.build());
    }

    public static int dip2px(float dipValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, Resources.getSystem().getDisplayMetrics());
    }

    public static int sp2px(float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, Resources.getSystem().getDisplayMetrics());
    }

}
