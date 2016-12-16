/*
 * Material.java
 * 
 * Class for a surface material containing ambient, diffuse and specular terms
 * 
 * Omayr Abdelgany
 * U54298732
 * 
 * History :
 * Nov 6, 2014 Created by Stan Sclaroff
 */
public class Material {
	private ColorType k_amb, k_spec, k_diff;
	private int specular_exp;
	public boolean specular, ambient, diffuse;
	
	public Material(ColorType k_amb, ColorType k_diff, ColorType k_spec, int specular_exp) {
		this.k_amb = new ColorType(k_amb);
		this.k_spec = new ColorType(k_spec);
		this.k_diff = new ColorType(k_diff);
		this.specular_exp = specular_exp;
		
		specular = (specular_exp>0 && (k_spec.r > 0.0 || k_spec.g > 0.0 || k_spec.b > 0.0));
		diffuse = (k_diff.r > 0.0 || k_diff.g > 0.0 || k_diff.b > 0.0);
		ambient = (k_amb.r > 0.0 || k_amb.g > 0.0 || k_amb.b > 0.0);
	}
	
	public ColorType get_k_amb() {
		return this.k_amb;
	}
	
	public ColorType get_k_diff() {
		return this.k_diff;
	}
	
	public ColorType get_k_spec() {
		return this.k_spec;
	}
	
	public int get_specular_exp() {
		return this.specular_exp;
	}
	
	public boolean isDiffuse() {
		return diffuse;
	}
	
	public boolean isAmbient() {
		return ambient;
	}
	
	public boolean isSpecular() {
		return specular;
	}
}
