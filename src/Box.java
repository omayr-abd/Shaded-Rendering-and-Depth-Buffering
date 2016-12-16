/*
 * Box.java
 * 
 * Class for a 3D box
 * 
 * Omayr Abdelgany
 * U54298732
 * 
 */
public class Box extends Shape{
	private Vector3D center, vec, u_vec, v_vec;
	private float r;
	private int m, n;
	public Mesh3D[] all_meshes;
	
	private float u_max, u_min, v_max, v_min;
	
	public Box(float x, float y, float z, float r, int m, int n, Material mat) {
		this.center = new Vector3D(x, y, z);
		this.r = r;
		this.u_vec = new Vector3D(0, -1*this.r, 0);
		this.v_vec = new Vector3D(0, 0, 1*this.r);
		this.m = m;
		this.n = n;
		this.u_min = -1;
		this.u_max = 1;
		this.v_min = -1;
		this.v_max = 1;
		super.mat = mat;
		this.vec = center.plus(new Vector3D(-this.r, 0, 0));
		initMesh();
	}
	
	public void setCenter(float x, float y, float z) {
		this.center.x = x;
		this.center.y = y;
		this.center.z = z;
		fillMesh();
	}
	
	public void setRadiusX(float r) {
		this.r = r;
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
		all_meshes = new Mesh3D[6];
		fillMesh();
	}
	
	private void fillMesh() {
		int i, j;
		float theta, phi;
		float d_phi = (u_max-u_min)/((float)n-1);
		float d_theta = (v_max-v_min)/((float)m-1);
		
		for (int side = 0; side < 6; ++side) {
			all_meshes[side] = new Mesh3D(m,n);
			if(side == 1) {
				vec = center.plus(new Vector3D(this.r, 0, 0));
				u_vec = u_vec.scale(-1); // negative for normal
			} else if (side == 2) {
				vec = center.plus(new Vector3D(0, -this.r, 0));
				u_vec = new Vector3D(1*r, 0, 0);
				v_vec = new Vector3D(0, 0, 1*r);
			} else if (side == 3) {
				vec = center.plus(new Vector3D(0, this.r, 0));
				// Swap for normals
				Vector3D temp = u_vec;
				u_vec = v_vec;
				v_vec = temp; 
			} else if (side == 4) {
				vec = center.plus(new Vector3D(0, 0, -this.r));
				u_vec = new Vector3D(-1*r, 0, 0);
				v_vec = new Vector3D(0, 1*r, 0);
			} else if (side == 5) {
				vec = center.plus(new Vector3D(0, 0, this.r));
				u_vec = u_vec.scale(-1); // negative for normal
			}
			
			for (i = 0, theta = v_min; i < m; ++i, theta += d_theta) {
				for (j = 0, phi = u_min; j < n; ++j, phi += d_phi) {
					
					// Compute vertex
					all_meshes[side].v[i][j] = vec.plus(u_vec.scale(theta).plus(v_vec.scale(phi)));
					
					// Computer normals
					all_meshes[side].n[i][j] = u_vec.crossProduct(v_vec);
					all_meshes[side].n[i][j].normalize();
				}
			}
		}
	}
}
