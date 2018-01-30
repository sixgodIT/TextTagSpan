# TextTagSpan
在TextView中使用文本添加自定义标签

![image](https://github.com/sixgodIT/TextTagSpan/blob/master/Screenshot.png)

支持：
1. 可在文本任何位置添加文字标签
2. 标签可设置文字大小、文字颜色、边框颜色、边框宽度、边框圆角
3. 标签也可直接设置drawable背景
4. 边框可设置左右间距
5. 支持设置行间距后，每一行标签都居中显示。

### 使用
TextTagSpan构造方法指定宽高，可使用drawable作为背景

```
 TextTagSpan span = new TextTagSpan(this, dip2px(25), dip2px(15))
                .setRightMargin(dip2px(5))
                .setTextColor(Color.BLACK)
                .setTextSize(sp2px(11))
                .setBackground(R.drawable.tag_bacground);
```

也可直接设置背景边框颜色、边框宽度和圆角

```
TextTagSpan span = new TextTagSpan(this, dip2px(25), dip2px(15))
                .setLeftMargin(dip2px(5))
                .setTextColor(Color.RED)
                .setTextSize(sp2px(13))
                .setRadius(dip2px(3))
                .setStrokeWidth(dip2px(0.5f))
                .setStrokeColor(Color.RED);
```

结合**Truss**使用更加方便。Truss使用参考：https://gist.github.com/JakeWharton/11274467
