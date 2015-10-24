package eu.mysmartline.models;

import eu.mysmartline.entities.Line;

public class LineModel {
	private Line line;
	private Long activationItem;
	
	public Line getLine() {
		return line;
	}
	public void setLine(Line line) {
		this.line = line;
	}
	public Long getActivationItem() {
		return activationItem;
	}
	public void setActivationItem(Long activationItem) {
		this.activationItem = activationItem;
	}
	
}
