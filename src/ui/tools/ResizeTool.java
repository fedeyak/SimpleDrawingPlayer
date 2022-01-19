package ui.tools;

import model.Shape;
import ui.DrawingEditor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class ResizeTool extends Tool {

	private Shape ovalToResize;

    // EFFECTS: creates a new ResizeTool with the given editor and parent. Sets shapeToResize to null
	public ResizeTool(DrawingEditor editor, JComponent parent) {
		super(editor, parent);
		ovalToResize = null;
	}

    // MODIFIES: this
    // EFFECTS: creates new button and adds to parent
	@Override
	protected void createButton(JComponent parent) {
		button = new JButton("Resize");
		addToParent(parent);
	}

    // MODIFIES: this
    // EFFECTS: associate button with new ClickHandler
	@Override
	protected void addListener() {
		button.addActionListener(new ResizeToolClickHandler());
	}

    // MODIFIES: this
	// EFFECTS:  Sets the shape at the current mouse position as the shape to resize,
	//           selects the shape and plays it
	@Override
	public void mousePressedInDrawingArea(MouseEvent e) {
		ovalToResize = editor.getShapeInDrawing(e.getPoint());
		if (ovalToResize != null) {
			ovalToResize.selectAndPlay();
		}
	}

    // MODIFIES: this
    // EFFECTS: deselects the resized shape, sets the shape to resize to null
	@Override
	public void mouseReleasedInDrawingArea(MouseEvent e) {
		if (ovalToResize != null) {
			ovalToResize.unselectAndStopPlaying();
			ovalToResize = null;
		}
    }

    // MODIFIES: this
    // EFFECTS: resizes shapeToResize to drag release point
	@Override
	public void mouseDraggedInDrawingArea(MouseEvent e) {
		if (ovalToResize != null) {
			ovalToResize.setBounds(e.getPoint());
		}
	}

	private class ResizeToolClickHandler implements ActionListener {

		// EFFECTS: sets active tool to the resize tool
		//          called by the framework when the tool is clicked
		@Override
		public void actionPerformed(ActionEvent e) {
			editor.setActiveTool(ResizeTool.this);
		}
	}
}
