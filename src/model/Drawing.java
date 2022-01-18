package model;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Drawing extends JPanel {

    private static final int MUSIC_LINES_SPACE = 30;

	private List<Oval> ovals;
	private int playLineColumn;


	public Drawing() {
		super();
		ovals = new ArrayList<Oval>();
		setBackground(Color.white);
	}

	// getters
    public List<Oval> getShapes() { return this.ovals; }
    public int getPlayLineColumn() { return this.playLineColumn; }

    // setters
	public void setPlayLineColumn(int plc) { playLineColumn = plc; }

    // EFFECTS: return true if the given Shape s is contained in Drawing
    public boolean containsShape(Oval s) {
		return ovals.contains(s);
	}

    // EFFECTS: paints grid, playback line, and all figures in drawing
	//          Note to students: calls to repaint gets here via the Java graphics framework
    @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawHorizontalNotesLines(g);
		for (Oval oval : ovals) {
            oval.draw(g);
        }
	}

	// EFFECTS: draws grid with lines GRIDSPACE apart, and draws red line at its current position
    private void drawHorizontalNotesLines(Graphics g) {
        Color save = g.getColor();
        g.setColor(new Color(227,227,227));
        for (int y = MUSIC_LINES_SPACE; y < getHeight(); y += MUSIC_LINES_SPACE) {
            g.drawLine(0, y, getWidth(), y);
        }
        if (playLineColumn > 0 && playLineColumn < getWidth()) {
            g.setColor(Color.RED);
            g.drawLine(playLineColumn, 0, playLineColumn, getHeight());
        }
        g.setColor(save);
    }

    // MODIFIES: this
    // EFFECTS:  adds the given shape to the drawing
	public void addShape(Oval oval) {
		ovals.add(oval);
	}


    // MODIFIES: this
    // EFFECTS:  removes shape from the drawing
	public void removeShape(Oval oval) {
		ovals.remove(oval);
		repaint();
	}

	// EFFECTS: returns the Shape at a given Point in Drawing, if any
	public Oval getShapesAtPoint(Point point) {
		for (Oval oval : ovals) {
			if (oval.contains(point))
				return oval;
		}
		return null;
	}

	// EFFECTS: returns all Shapes at given column corresponding to an x-coordinate
	public List<Oval> getShapesAtColumn(int x) {
	    List<Oval> shapesAtColumn = new ArrayList<Oval>();
		for (Oval oval : ovals) {
			if (oval.containsX(x))
				shapesAtColumn.add(oval);
		}
		return shapesAtColumn;
	}
}
