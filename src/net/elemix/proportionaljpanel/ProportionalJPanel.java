package net.elemix.proportionaljpanel;


import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;

public class ProportionalJPanel extends JPanel {

    private HashMap<Component, ProportionalComponent> components;
    private final double resolution;

    /**
     * A JPanel that draws components based on panel percentage and relative position
     *
     * @param frame - JFrame of Panel
     */
    public ProportionalJPanel(JFrame frame) {
        this.components = new HashMap<>();
        Toolkit tk = this.getToolkit();
        this.resolution = tk.getScreenSize().getWidth() / tk.getScreenSize().getHeight();
        frame.setResizable(false);

        this.setLayout(null);
    }

    /**
     * Add a component to the Panel
     *
     * @param component - Component to add
     * @param percent   - Percent of JPanel to designate to the Component
     * @param corner    - Relative position of Component to draw in
     */
    public void addComponent(Component component, double percent, Corner corner) {
        this.addComponent(new ProportionalComponent(component, percent, corner));
    }

    /**
     * Add a ProportionalComponent to the Panel
     *
     * @param pc - ProportionalComponent
     */
    public void addComponent(ProportionalComponent pc) {
        this.components.put(pc.getComponent(), pc);
        this.add(pc.getComponent());
        try {
            this.sizePanel();
        } catch (PanelCanNotBeSizedException e) {
            e.printStackTrace();
        }
        this.sizeComponent(pc);
    }

    /**
     * Remove a Component from the JPanel
     *
     * @param component - Component
     */
    public void removeComponent(Component component) {
        this.components.remove(component);
        this.remove(component);
    }

    /**
     * Remove a ProportionalComponent from the JPanel
     *
     * @param pc - ProportionalComponent
     */
    public void removeComponent(ProportionalComponent pc) {
        this.removeComponent(pc.getComponent());
    }

    private void sizePanel() throws PanelCanNotBeSizedException {
        if (this.getHeight() != 0 && this.getWidth() != 0) {
            return;
        }
        if (!this.components.isEmpty()) {
            for (ProportionalComponent pc : this.components.values()) {
                if (pc.getComponent().getHeight() > 0 && pc.getComponent().getWidth() > 0) {
                    int origArea = pc.getComponent().getHeight() * pc.getComponent().getWidth();
                    double percentage = pc.getPercentage();
                    double panelArea = Math.round(origArea * (1D / percentage));
                    int height = (int) Math.round(Math.sqrt(panelArea / this.resolution));
                    int width = (int) Math.round(panelArea / height);
                    this.setSize(width, height);
                    return;
                }
            }
            throw new PanelCanNotBeSizedException("All Components are sizeless and Panel size is never defined");
        }
    }

    /**
     * Set the Size and Location of a Component
     *
     * @param pc - ProportionalComponent
     */
    public void sizeComponent(ProportionalComponent pc) {
        Location loc = this.getLocation(pc.getCorner());
        Dimension size = this.getSize(pc);

        int x = (int) Math.round(loc.getX() - (size.getWidth() / 2));
        int y = (int) Math.round(loc.getY() - (size.getHeight() / 2));
        x = x < 0 ? 0 : x;
        y = y < 0 ? 0 : y;
        x = x + size.getWidth() > this.getWidth() ? (int) (this.getWidth() - size.getWidth()) : x;
        y = y + size.getHeight() > this.getHeight() ? (int) (this.getHeight() - size.getHeight()) : y;

        pc.getComponent().setLocation(x, y);
        pc.getComponent().setSize(size);
    }

    /**
     * Get the Location of Relative Position(Corner) within the JPanel
     *
     * @param c - Corner to find postion of
     * @return Location of Corner c
     */
    public Location getLocation(Corner c) {
        double width = this.getWidth();
        double height = this.getHeight();
        int x = this.getX();
        int y = this.getY();
        return new Location((int) Math.round(c.getX() * width + x), (int) Math.round(c.getY() * height + y));
    }

    /**
     * Get the size of Component based on percentage
     *
     * @param cp ProportionalComponent to get size of
     * @return Dimensions of Component relative to JPanel size
     */
    public Dimension getSize(ProportionalComponent cp) {
        Component c = cp.getComponent();
        double percentage = cp.getPercentage();

        if (cp.getSize().getHeight() != 0 && cp.getSize().getWidth() != 0) {
            int desiredArea = (int) Math.round(this.getSize().getHeight() * this.getSize().getWidth() * percentage);
            int multiplier = (int) Math.round(Math.sqrt(desiredArea / cp.getArea()));
            return new Dimension(c.getWidth() * multiplier, c.getHeight() * multiplier);
        } else {
            return new Dimension((int) (this.getWidth() * Math.sqrt(percentage)), (int) (this.getHeight() * Math.sqrt(percentage)));
        }
    }

    /**
     * @return Collection<ProportionalComponent> components - Collection of ProportionalComponents stored in JPanel
     */
    public Collection<ProportionalComponent> getProportionalComponents() {
        return this.components.values();
    }
}
