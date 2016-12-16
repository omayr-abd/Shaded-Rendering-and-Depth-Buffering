/*
 * AmbientLight.java
 * 
 * Models an ambient light source to illuminate from all directions
 * 
 * Omayr Abdelgany
 * U54298732
 * 
 */
public class AmbientLight extends Light {

	public Vector3D direction;
	public ColorType color;
	
	public AmbientLight(ColorType color, Vector3D direction) {
		this.color = color;
		this.direction = direction;
		super.light_on = true;
	}
	
	public ColorType applyLight(Material mat, Vector3D v, Vector3D n, Vector3D p) {
		ColorType res = new ColorType();
		
		if (mat.isAmbient()) {
			res.r = (float) (color.r * mat.get_k_amb().r);
			res.g = (float) (color.g * mat.get_k_amb().g);
			res.b = (float) (color.b * mat.get_k_amb().b);
		}

		// Clamp
		res.r = (float) Math.min(1.0, res.r);
		res.g = (float) Math.min(1.0, res.g);
		res.b = (float) Math.min(1.0, res.b);
		return(res);
	}
	
	public void toggleLight() {
		light_on = !light_on;
	}
}
