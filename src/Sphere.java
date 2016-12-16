/*
 * Sphere.java
 * Class for a 3D Sphere
 * 
 * Omayr Abdelgany
 * U54298732
 * 
 * History :
 * Nov 6, 2014 Created by Stan Sclaroff
 */

public class Sphere extends Shape {
	private Vector3D center;
	private float r;
	private int m, n;
	public Mesh3D mesh;
	
	private final float u_min = (float)-Math.PI/2;
	private final float u_max = (float)Math.PI/2;
	private final float v_min = (float)-Math.PI;
	private final float v_max = (float)Math.PI;
	
	
	public Sphere(float x, float y, float z, float r, int m, int n, Material mat) {
		this.center = new Vector3D(x, y, z);
		this.r = r;
		this.m = m;
		this.n = n;
		super.mat = mat;
		initMesh();
	}
	
	public void setCenter(float x, float y, float z) {
		this.center.x = x;
		this.center.y = y;
		this.center.z = z;
		fillMesh();
	}
	
	public void setRadius(float r) {
		this.r = r;
		fillMesh();
	}
	
	public void setM(int m) {
		this.m = m;
		initMesh();
	}
	
	public void setN(int n) {
		this.n = n;
		initMesh();
	}
	
	public Vector3D getCenter() {
		return this.center;
	}
	
	public float getRadius() {
		return this.r;
	}
	
	public int getM(){
		return this.m;
	}
	
	public int getN(){
		return this.n;
	}
	
	private void initMesh() {
		mesh = new Mesh3D(m,n);
		fillMesh();
	}
	
	private void fillMesh() {
		int i, j;
		float theta, phi;
		float d_phi = (u_max-u_min)/((float)n-1);
		float d_theta = (v_max-v_min)/((float)m-1);
		
		float c_theta, s_theta, c_phi, s_phi;
		
		for (i = 0, theta = v_min; i < m; ++i, theta += d_theta){
			c_theta = (float)Math.cos(theta);
			s_theta = (float)Math.sin(theta);
			
			for (j = 0, phi = u_min; j < n; ++j, phi += d_phi) {
				c_phi = (float)Math.cos(phi);
				s_phi = (float)Math.sin(phi);
				
				// Compute vertex
				mesh.v[i][j].x = center.x + r * c_phi * c_theta;
				mesh.v[i][j].y = center.y + r * c_phi * s_theta;
				mesh.v[i][j].z = center.z + r * s_phi;
				
				// Compute unit normal at vertex
				mesh.n[i][j].x = c_phi * c_theta;
				mesh.n[i][j].y = c_phi * s_theta;
				mesh.n[i][j].z = s_phi;
				
				mesh.n[i][j].normalize();
			}
		}
	}
}
