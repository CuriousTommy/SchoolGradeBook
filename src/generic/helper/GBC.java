package generic.helper;

import java.awt.GridBagConstraints;

public class GBC {
	static public void changeWeight(
		GridBagConstraints gbc,
		double weightx,
		double weighty
	) {
		gbc.weightx = weightx;
		gbc.weighty = weighty;
	}
	
	static public void changeGrid(
		GridBagConstraints gbc,
		int gridx,
		int gridy
	) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
	}
	
	static public void changeGridDimension(
		GridBagConstraints gbc,
		int gridwidth,
		int gridheight
	) {
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
	}
}
