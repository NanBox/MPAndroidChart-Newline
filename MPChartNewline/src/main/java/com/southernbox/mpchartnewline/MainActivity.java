package com.southernbox.mpchartnewline;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

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

public class MainActivity extends Activity {

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
        //启用/禁用缩放图表上的两个轴
        mChart.setScaleEnabled(true);
        //是否可以缩放
        mChart.setScaleEnabled(false);
        //集双指缩放
        mChart.setPinchZoom(false);
        mChart.setViewPortOffsets(0, 0, 0, 130);
        //增加X轴最左边与Y轴的距离
        mChart.setDragOffsetX(30);
        //设置背景颜色
        mChart.setBackgroundColor(getResources().getColor(R.color.chart_toolbar_bg));
        mChart.setDrawGridBackground(true);
        //设置表格颜色
        mChart.setGridBackgroundColor(getResources().getColor(R.color.chart_bg));
        mChart.getAxisRight().setEnabled(false);
        //设置一个描述的文本出现在右下角的图
        mChart.setDescription(null);
        //隐藏图例
        mChart.getLegend().setEnabled(false);

        //设置X轴
        XAxis xAxis = mChart.getXAxis();
        //设置X轴的位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        //不显示X坐标轴上的线
        xAxis.setDrawAxisLine(false);
        xAxis.setYOffset(10f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            String[] values = {"1月\n2018", "2月\n2018", "3月\n2018",
                    "4月\n2018", "5月\n2018", "6月\n2018", "7月\n2018"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return values[(int) value];
            }
        });
        //设置Y轴
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setGridColor(Color.parseColor("#313131"));
        leftAxis.setGridLineWidth(1f);
        leftAxis.setTextSize(10);
        //不显示Y坐标轴上的线
        leftAxis.setDrawAxisLine(false);
        //设置Y坐标显示在右边
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        //Y坐标显示向上偏移
        leftAxis.setYOffset(-7f);
    }

    private void initData() {
        // 添加数据
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(0, 2));
        values.add(new Entry(1, 7));
        values.add(new Entry(2, 4));
        values.add(new Entry(3, 3));
        values.add(new Entry(4, 5));
        values.add(new Entry(5, 8));
        values.add(new Entry(6, 11));
        // 添加折线
        List<ILineDataSet> dataSets = new ArrayList<>();
        LineDataSet lineDataSet = new LineDataSet(values, "");
        lineDataSet.setLineWidth(2f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawFilled(false);
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setHighlightBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.statistic_highlight));
        dataSets.add(lineDataSet);
        // 绘制折线
        LineData data = new LineData(dataSets);
        mChart.setData(data);
        mChart.invalidate();
    }
}
