package eu.silvere;

import java.util.HashMap;
import java.util.Map;

import android.graphics.drawable.Drawable;

public class BeerBottle {

	private String name;
	private Drawable image;
	private String description;
	// the volume is the key (we are sure it's unique), the frequency is the
	// value
	private Map<Float, Float> frequencies;

	public BeerBottle(String name) {
		this.name = name;
		frequencies = new HashMap<Float, Float>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Drawable getImage() {
		return image;
	}

	public void setImage(Drawable image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<Float, Float> getFrequencies() {
		return frequencies;
	}

	public void setFrequencies(Map<Float, Float> frequencies) {
		this.frequencies = frequencies;
	}

	public void addFrequency(Float volume, Float frequency) {
		this.frequencies.put(volume, frequency);
	}
}
