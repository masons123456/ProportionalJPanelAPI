package net.elemix.proportionaljpanel;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Storage for a Component, Corner, and Percentage of Window
 */
public class ProportionalComponent implements ComponentListener {

    private Component component;
    private double percentage;
    private Corner corner;

    private Dimension size;
    private int area;


    private ProportionalComponent() {
        area = 0;
        size = null;
    }

    /**
     * @param component
     * @param percentage - Percent of Panel to take
     * @param corner     - Position of Component in Panel
     */
    public ProportionalComponent(Component component, double percentage, Corner corner) {
        this.component = component;
        this.percentage = percentage;
        this.corner = corner;

        this.size = component.getSize();
        this.area = (int) Math.round(this.size.getHeight() * this.size.getWidth());

        this.component.addComponentListener(this);
    }

    /**
     * @param component
     * @param percentage - Percent of Panel to take
     */
    public ProportionalComponent(Component component, double percentage) {
        this(component, percentage, Corner.CENTER);
    }

    /**
     * @return Dimension originalSize - Size of Component when created
     */
    public Dimension getSize() {
        return this.size;
    }

    /**
     * @return Component component
     */
    public Component getComponent() {
        return this.component;
    }

    /**
     * @return double percentage - Percentage of Panel to hold
     */
    public double getPercentage() {
        return this.percentage;
    }

    /**
     * @return Corner corner - Position of Component in Panel
     */
    public Corner getCorner() {
        return this.corner;
    }

    /**
     * Used as a ratio for width:height
     *
     * @return int originalArea - Area of Component when created
     */
    public int getArea() {
        return this.area;
    }

    /**
     * Set Component
     *
     * @param c - Component
     */
    public void setComponent(Component c) {
        this.component = c;
    }

    /**
     * Set Percentage
     *
     * @param percentage - Percentage of Panel to hold
     */
    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    /**
     * Set Corner
     *
     * @param corner - Position of Component in Panel
     */
    public void setCorner(Corner corner) {
        this.corner = corner;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        if (e.getComponent() == this.getComponent()) {
            this.size = e.getComponent().getSize();
            this.area = (int) Math.round(this.size.getHeight() * this.size.getWidth());
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
