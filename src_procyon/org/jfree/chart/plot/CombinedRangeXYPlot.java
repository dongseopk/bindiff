package org.jfree.chart.plot;

import java.io.*;
import org.jfree.data.xy.*;
import org.jfree.chart.renderer.xy.*;
import org.jfree.chart.event.*;
import java.awt.*;
import org.jfree.ui.*;
import java.awt.geom.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.*;
import org.jfree.data.*;
import org.jfree.util.*;
import java.util.*;

public class CombinedRangeXYPlot extends XYPlot implements Serializable, Cloneable, PlotChangeListener, Zoomable, PublicCloneable
{
    private static final long serialVersionUID = -5177814085082031168L;
    private List subplots;
    private int totalWeight;
    private double gap;
    private transient Rectangle2D[] subplotAreas;
    
    public CombinedRangeXYPlot() {
        this(new NumberAxis());
    }
    
    public CombinedRangeXYPlot(final ValueAxis valueAxis) {
        super(null, null, valueAxis, null);
        this.totalWeight = 0;
        this.gap = 5.0;
        this.subplots = new ArrayList();
    }
    
    public String getPlotType() {
        return CombinedRangeXYPlot.localizationResources.getString("Combined_Range_XYPlot");
    }
    
    public double getGap() {
        return this.gap;
    }
    
    public void setGap(final double gap) {
        this.gap = gap;
    }
    
    public void add(final XYPlot xyPlot) {
        this.add(xyPlot, 1);
    }
    
    public void add(final XYPlot xyPlot, final int weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("The 'weight' must be positive.");
        }
        xyPlot.setParent(this);
        xyPlot.setWeight(weight);
        xyPlot.setInsets(new RectangleInsets(0.0, 0.0, 0.0, 0.0));
        xyPlot.setRangeAxis(null);
        xyPlot.addChangeListener(this);
        this.subplots.add(xyPlot);
        this.totalWeight += weight;
        this.configureRangeAxes();
        this.notifyListeners(new PlotChangeEvent(this));
    }
    
    public void remove(final XYPlot xyPlot) {
        if (xyPlot == null) {
            throw new IllegalArgumentException(" Null 'subplot' argument.");
        }
        int n = -1;
        for (int size = this.subplots.size(), n2 = 0; n == -1 && n2 < size; ++n2) {
            if (this.subplots.get(n2) == xyPlot) {
                n = n2;
            }
        }
        if (n != -1) {
            xyPlot.setParent(null);
            xyPlot.removeChangeListener(this);
            this.totalWeight -= xyPlot.getWeight();
            this.configureRangeAxes();
            this.notifyListeners(new PlotChangeEvent(this));
        }
    }
    
    public List getSubplots() {
        return Collections.unmodifiableList((List<?>)this.subplots);
    }
    
    protected AxisSpace calculateAxisSpace(final Graphics2D graphics2D, final Rectangle2D rectangle2D) {
        AxisSpace reserveSpace = new AxisSpace();
        final PlotOrientation orientation = this.getOrientation();
        final AxisSpace fixedRangeAxisSpace = this.getFixedRangeAxisSpace();
        if (fixedRangeAxisSpace != null) {
            if (orientation == PlotOrientation.VERTICAL) {
                reserveSpace.setLeft(fixedRangeAxisSpace.getLeft());
                reserveSpace.setRight(fixedRangeAxisSpace.getRight());
            }
            else if (orientation == PlotOrientation.HORIZONTAL) {
                reserveSpace.setTop(fixedRangeAxisSpace.getTop());
                reserveSpace.setBottom(fixedRangeAxisSpace.getBottom());
            }
        }
        else {
            final ValueAxis rangeAxis = this.getRangeAxis();
            final RectangleEdge resolveRangeAxisLocation = Plot.resolveRangeAxisLocation(this.getRangeAxisLocation(), orientation);
            if (rangeAxis != null) {
                reserveSpace = rangeAxis.reserveSpace(graphics2D, this, rectangle2D, resolveRangeAxisLocation, reserveSpace);
            }
        }
        final Rectangle2D shrink = reserveSpace.shrink(rectangle2D, null);
        final int size = this.subplots.size();
        this.subplotAreas = new Rectangle2D[size];
        double x = shrink.getX();
        double y = shrink.getY();
        double n = 0.0;
        if (orientation == PlotOrientation.VERTICAL) {
            n = shrink.getWidth() - this.gap * (size - 1);
        }
        else if (orientation == PlotOrientation.HORIZONTAL) {
            n = shrink.getHeight() - this.gap * (size - 1);
        }
        for (int i = 0; i < size; ++i) {
            final XYPlot xyPlot = this.subplots.get(i);
            if (orientation == PlotOrientation.VERTICAL) {
                final double n2 = n * xyPlot.getWeight() / this.totalWeight;
                this.subplotAreas[i] = new Rectangle2D.Double(x, y, n2, shrink.getHeight());
                x = x + n2 + this.gap;
            }
            else if (orientation == PlotOrientation.HORIZONTAL) {
                final double n3 = n * xyPlot.getWeight() / this.totalWeight;
                this.subplotAreas[i] = new Rectangle2D.Double(x, y, shrink.getWidth(), n3);
                y = y + n3 + this.gap;
            }
            reserveSpace.ensureAtLeast(xyPlot.calculateDomainAxisSpace(graphics2D, this.subplotAreas[i], null));
        }
        return reserveSpace;
    }
    
    public void draw(final Graphics2D graphics2D, final Rectangle2D plotArea, final Point2D point2D, PlotState plotState, final PlotRenderingInfo plotRenderingInfo) {
        if (plotRenderingInfo != null) {
            plotRenderingInfo.setPlotArea(plotArea);
        }
        this.getInsets().trim(plotArea);
        final AxisSpace calculateAxisSpace = this.calculateAxisSpace(graphics2D, plotArea);
        final Rectangle2D shrink = calculateAxisSpace.shrink(plotArea, null);
        this.setFixedDomainAxisSpaceForSubplots(calculateAxisSpace);
        final ValueAxis rangeAxis = this.getRangeAxis();
        final RectangleEdge rangeAxisEdge = this.getRangeAxisEdge();
        final AxisState draw = rangeAxis.draw(graphics2D, RectangleEdge.coordinate(shrink, rangeAxisEdge), plotArea, shrink, rangeAxisEdge, plotRenderingInfo);
        if (plotState == null) {
            plotState = new PlotState();
        }
        plotState.getSharedAxisStates().put(rangeAxis, draw);
        for (int i = 0; i < this.subplots.size(); ++i) {
            final XYPlot xyPlot = this.subplots.get(i);
            PlotRenderingInfo plotRenderingInfo2 = null;
            if (plotRenderingInfo != null) {
                plotRenderingInfo2 = new PlotRenderingInfo(plotRenderingInfo.getOwner());
                plotRenderingInfo.addSubplotInfo(plotRenderingInfo2);
            }
            xyPlot.draw(graphics2D, this.subplotAreas[i], point2D, plotState, plotRenderingInfo2);
        }
        if (plotRenderingInfo != null) {
            plotRenderingInfo.setDataArea(shrink);
        }
    }
    
    public LegendItemCollection getLegendItems() {
        LegendItemCollection fixedLegendItems = this.getFixedLegendItems();
        if (fixedLegendItems == null) {
            fixedLegendItems = new LegendItemCollection();
            if (this.subplots != null) {
                final Iterator<XYPlot> iterator = this.subplots.iterator();
                while (iterator.hasNext()) {
                    fixedLegendItems.addAll(iterator.next().getLegendItems());
                }
            }
        }
        return fixedLegendItems;
    }
    
    public void zoomDomainAxes(final double n, final PlotRenderingInfo plotRenderingInfo, final Point2D point2D) {
        final XYPlot subplot = this.findSubplot(plotRenderingInfo, point2D);
        if (subplot != null) {
            subplot.zoomDomainAxes(n, plotRenderingInfo, point2D);
        }
    }
    
    public void zoomDomainAxes(final double n, final double n2, final PlotRenderingInfo plotRenderingInfo, final Point2D point2D) {
        final XYPlot subplot = this.findSubplot(plotRenderingInfo, point2D);
        if (subplot != null) {
            subplot.zoomDomainAxes(n, n2, plotRenderingInfo, point2D);
        }
    }
    
    public XYPlot findSubplot(final PlotRenderingInfo plotRenderingInfo, final Point2D point2D) {
        XYPlot xyPlot = null;
        final int subplotIndex = plotRenderingInfo.getSubplotIndex(point2D);
        if (subplotIndex >= 0) {
            xyPlot = (XYPlot)this.subplots.get(subplotIndex);
        }
        return xyPlot;
    }
    
    public void setRenderer(final XYItemRenderer xyItemRenderer) {
        super.setRenderer(xyItemRenderer);
        final Iterator<XYPlot> iterator = this.subplots.iterator();
        while (iterator.hasNext()) {
            iterator.next().setRenderer(xyItemRenderer);
        }
    }
    
    public void setOrientation(final PlotOrientation plotOrientation) {
        super.setOrientation(plotOrientation);
        final Iterator<XYPlot> iterator = this.subplots.iterator();
        while (iterator.hasNext()) {
            iterator.next().setOrientation(plotOrientation);
        }
    }
    
    public Range getDataRange(final ValueAxis valueAxis) {
        Range combine = null;
        if (this.subplots != null) {
            final Iterator<XYPlot> iterator = this.subplots.iterator();
            while (iterator.hasNext()) {
                combine = Range.combine(combine, iterator.next().getDataRange(valueAxis));
            }
        }
        return combine;
    }
    
    protected void setFixedDomainAxisSpaceForSubplots(final AxisSpace fixedDomainAxisSpace) {
        final Iterator<XYPlot> iterator = this.subplots.iterator();
        while (iterator.hasNext()) {
            iterator.next().setFixedDomainAxisSpace(fixedDomainAxisSpace);
        }
    }
    
    public void handleClick(final int n, final int n2, final PlotRenderingInfo plotRenderingInfo) {
        if (plotRenderingInfo.getDataArea().contains(n, n2)) {
            for (int i = 0; i < this.subplots.size(); ++i) {
                ((XYPlot)this.subplots.get(i)).handleClick(n, n2, plotRenderingInfo.getSubplotInfo(i));
            }
        }
    }
    
    public void plotChanged(final PlotChangeEvent plotChangeEvent) {
        this.notifyListeners(plotChangeEvent);
    }
    
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CombinedRangeXYPlot)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final CombinedRangeXYPlot combinedRangeXYPlot = (CombinedRangeXYPlot)o;
        return ObjectUtilities.equal(this.subplots, combinedRangeXYPlot.subplots) && this.totalWeight == combinedRangeXYPlot.totalWeight && this.gap == combinedRangeXYPlot.gap;
    }
    
    public Object clone() {
        final CombinedRangeXYPlot parent = (CombinedRangeXYPlot)super.clone();
        parent.subplots = (List)ObjectUtilities.deepClone(this.subplots);
        final Iterator iterator = parent.subplots.iterator();
        while (iterator.hasNext()) {
            iterator.next().setParent(parent);
        }
        final ValueAxis rangeAxis = parent.getRangeAxis();
        if (rangeAxis != null) {
            rangeAxis.configure();
        }
        return parent;
    }
}
