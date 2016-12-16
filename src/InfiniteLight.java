/*
 * InfiniteLight.java
 * 
 * Type of lighting model that models a light source infinitely far away; like the sun
 * 
 * Omayr Abdelgany
 * U54298732
 * 
 * History :
 * Nov 6, 2014 Created by Stan Sclaroff
 */
public class InfiniteLight extends Light {
	public Vector3D direction;
	public ColorType color;
	
	public InfiniteLight(ColorType color, Vector3D direction) {
		this.color = new ColorType(color);
		this.direction = new Vector3D(direction);
		super.light_on = true;
	}
	
	public ColorType applyLight(Material mat, Vector3D v, Vector3D n, Vector3D p) {
		ColorType res = new ColorType();
		
		double dot = direction.dotProduct(n);
		
		if (dot > 0.0) {
			if (mat.isDiffuse()) {
				res.r += (float)(dot * mat.get_k_diff().r * color.r);
				res.g += (float)(dot * mat.get_k_diff().g * color.g);
				res.b += (float)(dot * mat.get_k_diff().b * color.b);
				
			}
			
			if (mat.isSpecular()) {
				Vector3D r = direction.reflect(n);
				dot = r.dotProduct(v);
				if (dot > 0.0) {
					res.r += (float)Math.pow((dot * mat.get_k_spec().r * color.r), mat.get_specular_exp());
					res.g += (float)Math.pow((dot * mat.get_k_spec().g * color.g), mat.get_specular_exp());
					res.b += (float)Math.pow((dot * mat.get_k_spec().b * color.b), mat.get_specular_exp());
				}
			}
			
			// Clamp
			res.r = (float)Math.min(1.0, res.r);
			res.g = (float)Math.min(1.0, res.g);
			res.b = (float)Math.min(1.0, res.b);
		}
		return(res);
	}
	
	public void toggleLight() {
		light_on = !light_on;
	}
}
