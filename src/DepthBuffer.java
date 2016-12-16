import java.awt.image.BufferedImage;
import java.util.Arrays;
public class DepthBuffer {
	
	public float[][] depth_buffer;
	
	public DepthBuffer(int width, int height, BufferedImage buff) {
		depth_buffer = new float[width][height];
		for (float[] row : depth_buffer) {
			Arrays.fill(row, -999.0f);
		}
		/*
		buff = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
		// TODO: Make sure clipping happens before this!!
		for (Mesh3D object : objects) {
			for (int i = 0; i < object.rows; ++i) {
				for (int j = 0; j < object.cols; ++j) {
					// TODO: Might need to do this over each triangle, not each triangle vertex
					if (object.v[i][j].z < depthBuff[i][j]) {
						// compute illumination eqn for this triangle
						depthBuff[i][j] = object.v[i][j].z;
						// buff = color at (x,y) on triangle;
					}
				}
			}
		}*/
	}

}
