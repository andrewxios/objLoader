package com.github.andrewxios.objLoader;

//java
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;
/**
 * author: andrewxios
 *
 */
public class Model {
	
	//nested class
	static class Face {
		//Indices for vertices and normals 
		private Vector3f vertIn;
		private Vector3f normIn;

		Face(Vector3f vertIn, Vector3f normIn){
			this.vertIn = vertIn;
			this.normIn = normIn;
		}
		
	}
	public List<Vector3f> vertices = new ArrayList<Vector3f>();
	public List<Vector3f> normals = new ArrayList<Vector3f>();
	public List<Face> faces = new ArrayList<Face>();
	
	
}