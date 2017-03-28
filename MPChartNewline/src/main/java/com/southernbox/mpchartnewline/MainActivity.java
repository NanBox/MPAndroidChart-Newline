package com.southernbox.mpchartnewline;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SouthernBox on 2017/3/27 0027.
 * 主页面
 */

public class MainActivity extends AppCompatActivity {

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initChartView();
        initData();
    }

    /**
     * 初始化图表界面视图
     */
    private void initChartView() {
        mChart = (LineChart) findViewById(R.id.chart);

        mChart.setScaleEnabled(true);//启用/禁用缩放图表上的两个轴
        mChart.setScaleEnabled(false);// 是否可以缩放
        mChart.setPinchZoom(false);// 集双指缩放
        mChart.setViewPortOffsets(0, 0, 0, dip2px(this, 50));
        mChart.setDragOffsetX(30);//增加X轴最左边与Y轴的距离
        mChart.setBackgroundColor(ContextCompat.getColor(this, R.color.chart_toolbar_bg));//设置背景颜色
        mChart.setDrawGridBackground(true);
        mChart.setGridBackgroundColor(ContextCompat.getColor(this, R.color.chart_bg));//设置表格颜色
        mChart.getAxisRight().setEnabled(false);
        mChart.setDescription(null);//设置一个描述的文本出现在右下角的图
        mChart.getLegend().setEnabled(false);//隐藏图例

        //设置X轴
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置X轴的位置
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);//不显示X坐标轴上的线
        xAxis.setYOffset(10f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                int year = Integer.valueOf(String.valueOf((int) value).substring(0, 4));
                int month = Integer.valueOf(String.valueOf((int) value).substring(4));
                return month + "月 " + year;
            }
        });

        //设置Y轴
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setGridColor(Color.parseColor("#313131"));
        leftAxis.setGridLineWidth(1f);
        leftAxis.setTextSize(10);
        leftAxis.setDrawAxisLine(false);//不显示Y坐标轴上的线
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);//设置Y坐标显示在右边
        leftAxis.setYOffset(-7f);//Y坐标显示向上偏移

    }

    private void initData() {
        //x轴数据
        List<Float> xData = new ArrayList<>();
        xData.add(201701f);
        xData.add(201702f);
        xData.add(201703f);
        xData.add(201704f);
        xData.add(201705f);
        xData.add(201706f);
        xData.add(201707f);

        //统计曲线
        List<LineDataSet> lineDataSets = new ArrayList<>();
        int maxNum = 0;//最大值
        for (int i = 0; i < 10; i++) {
            //y轴数据
            List<Integer> numList = new ArrayList<>();
            numList.add(2);
            numList.add(1);
            numList.add(4);
            numList.add(3);
            numList.add(6);
            numList.add(5);
            numList.add(3);
            ArrayList<Entry> values = new ArrayList<>();
            for (int j = 0; j < numList.size(); j++) {
                values.add(new Entry(xData.get(j), numList.get(j)));
                if (numList.get(j) > maxNum) {
                    maxNum = numList.get(j);
                }
            }
            LineDataSet lineDataSet = new LineDataSet(values, "");
            lineDataSet.setLineWidth(2f);
            lineDataSet.setDrawCircleHole(false);
            lineDataSet.setDrawCircles(false);
            lineDataSet.setDrawValues(false);//启用/禁用绘制所有DataSets数据对象包含的数据的值文本
            lineDataSet.setDrawFilled(false);
            lineDataSet.setDrawHorizontalHighlightIndicator(false);
            lineDataSet.setHighlightBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.statistic_highlight));

            lineDataSets.add(lineDataSet);
        }

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        if (lineDataSets.size() > 0) {
            for (LineDataSet lineDataSet : lineDataSets) {
                dataSets.add(lineDataSet);
            }
        }

        LineData data = new LineData(dataSets);
        mChart.setData(data);
        mChart.invalidate();
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
