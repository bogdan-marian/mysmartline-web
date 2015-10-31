package eu.mysmartline.models;

import eu.mysmartline.entities.Line;

public class LineModel {
	private Line line;
	private String activationItem;
	//generated items
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public String getActivationItem() {
		return activationItem;
	}
	public void setActivationItem(String activationItem) {
		this.activationItem = activationItem;
	}
	
}
