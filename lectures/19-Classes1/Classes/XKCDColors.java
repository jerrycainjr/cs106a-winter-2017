/*
 * File: XKCDColors.java
 * ========================================================
 * A program to plot colors from the xkcd colors file.
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.*;
import acm.program.*;
import acm.graphics.*;
import acm.util.*;

public class XKCDColors extends GraphicsProgram {
	/* The number of columns to use in the text box. */
	private static final int NUM_COLUMNS = 16;
	
	/* The name of the data file holding all of the named RGB triplets. */
	private static final String COLORS_FILE = "xkcd-colors.txt";
	
	/* Text field where the user can input a color. */
	private JTextField colorInput = new JTextField(NUM_COLUMNS);
	
	/* The data for our program */
	private HashMap<String, ArrayList<Color>> colorMap;
	
	/* Sets up interactors. */
	public void init() {	
		colorMap = loadXKCDColors();
		
		JLabel colorName = new JLabel("Color: ");
		add(colorName, SOUTH);
		add(colorInput, SOUTH);
		
		JButton graphButton = new JButton("Graph");
		add(graphButton, SOUTH);
		
		JButton clearButton = new JButton("Clear");
		add(clearButton, SOUTH);
		
		addActionListeners();
	}
	
	public void run() {
		clear();
	}
	
	/**
	 * Called each time a button is clicked.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear")) {
			clear();
		} else if (e.getActionCommand().equals("Graph")) {
			plotColorName();
		}
	}

	/**
	 * Plot the color whose name is in the colorInput textfield.
	 */
	private void plotColorName() {
		String colorName = colorInput.getText().toLowerCase();
		if (colorMap.containsKey(colorName)) {
			ArrayList<Color> allOfColor = colorMap.get(colorName);
			for (Color c: allOfColor) {
				plotColor(c);
			}
		}
	}
	
	
	
	/** Everything below here is beyond the scope of class. **/
	
	
	/**
	 * Loads in a table of all of the names of the color data points from
	 * the xkcd color naming set.
	 * 
	 * @return A map from the color names to the list of RGB triplets with
	 *         that name.
	 */
	private HashMap<String, ArrayList<Color>> loadXKCDColors() {
		try {
			/* Open the file for reading. */
			BufferedReader br = new BufferedReader(new FileReader(COLORS_FILE));
			
			/* Construct the HashMap that we will provide back as a result. */
			HashMap<String, ArrayList<Color>> result = new HashMap<String, ArrayList<Color>>();
			
			while (true) {
				/* Read the next entry:
				 * 1. The name of the color.
				 * 2. Its red component.
				 * 3. Its green component.
				 * 4. Its blue component.
				 */
				String colorName = br.readLine();
				String r = br.readLine();
				String g = br.readLine();
				String b = br.readLine();
				
				/* If we ran out of data, we're done. */
				if (b == null) break;
				
				/* Construct a Color object from this data. */
				Color color = new Color(Integer.parseInt(r),
				                        Integer.parseInt(g),
				                        Integer.parseInt(b));
				
				/* Ensure that there's an ArrayList waiting for us. */
				if (!result.containsKey(colorName)) {
					result.put(colorName, new ArrayList<Color>());
				}
				
				/* Add this color data to the color list. */
				result.get(colorName).add(color);
			}
			
			br.close();
			return result;			
		} catch (IOException e) {
			throw new ErrorException(e);
		}
	}
	
	/**
	 * Clears the display of all points.
	 */
	private void clear() {
		removeAll();
		addCircle();
	}	
	
	/* A constant detailing what fraction of the maximum possible radius should be
	 * used.
	 */
	private static final double RADIUS_MODIFIER = 0.95;
	
	/**
	 * Given a color, plots that triplet based on its hue and saturation.
	 * 
	 * @param color The color to plot.
	 */
	private void plotColor(Color color) {
		/* Convert the color to an HSB triplet of hue (color), saturation
		 * (intensity), and brightness.
		 */
		float[] components = Color.RGBtoHSB(color.getRed(),
				                            color.getGreen(),
				                            color.getBlue(),
				                            null);
		
		/* The radius is given by the intensity of the color, which is in slot 1. */
		double radius = getRadius() * components[1];
		
		/* The angle is given by the hue of the color, which is in slot 0. */
		double theta = components[0] * Math.PI * 2.0;
		
		/* Determine the x and y coordinates. */
		double x = getWidth() / 2.0  + radius * Math.cos(theta);
		double y = getHeight() / 2.0 - radius * Math.sin(theta);
		
		/* Plot a pixel there. */
		GRect pt = new GRect(x, y, 1, 1);
		pt.setFilled(true);
		pt.setColor(color);
		add(pt);
	}
	

	/**
	 * Returns the radius of the outermost circle, which is just slightly less than
	 * half the width of the canvas.
	 * @return
	 */
	private double getRadius() {
		return Math.min(getWidth(), getHeight()) / 2.0 * RADIUS_MODIFIER;
	}

	/**
	 * Draws the outline of the full circle.
	 */
	private void addCircle() {
		double r = getRadius();
		add(new GOval(getWidth() / 2.0 - r, getHeight() / 2.0 - r, 2 * r, 2 * r));
	}
	
	/**
	 * Loads in a table of all of the names of the color data points from
	 * the xkcd color naming set.
	 * 
	 * @return A map from the color names to the list of RGB triplets with
	 *         that name.
	 */
	private HashMap<String, ArrayList<int[]>> loadColorsFile() {
		try {
			/* Open the file for reading. */
			BufferedReader br = new BufferedReader(new FileReader(COLORS_FILE));
			
			/* Construct the HashMap that we will provide back as a result. */
			HashMap<String, ArrayList<int[]>> result = new HashMap<String, ArrayList<int[]>>();
			
			while (true) {
				/* Read the next entry:
				 * 1. The name of the color.
				 * 2. Its red component.
				 * 3. Its green component.
				 * 4. Its blue component.
				 */
				String colorName = br.readLine();
				String r = br.readLine();
				String g = br.readLine();
				String b = br.readLine();
				
				/* If we ran out of data, we're done. */
				if (b == null) break;
				
				/* Construct an array of the colors. */
				int[] color = new int[3];
				color[0] = Integer.parseInt(r);
				color[1] = Integer.parseInt(g);
				color[2] = Integer.parseInt(b);
				
				/* Ensure that there's an ArrayList waiting for us. */
				if (!result.containsKey(colorName)) {
					result.put(colorName, new ArrayList<int[]>());
				}
				
				/* Add this color data to the color list. */
				result.get(colorName).add(color);
			}
			
			br.close();
			return result;			
		} catch (IOException e) {
			throw new ErrorException(e);
		}
	}
}