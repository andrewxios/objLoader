package com.github.andrewxios.objLoader;

//java
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//lwjgl
import org.lwjgl.util.vector.Vector3f;

/**
 * author: andrewxios
 *
 */
public class objLoader 
{

	//format tested for blender v2.65
	final static String seperator = "/";
	final static String sVertex = "v ";
	final static String sNormal = "vn ";
	final static String sTexture = "vt ";
	final static String sFace = "f ";
	final static int startIndex = 1; //index after label


	public static Model loadObj(File filename) throws IOException, FileNotFoundException{

		BufferedReader file = new BufferedReader(new FileReader(filename));
		Model model = new Model();
		String line;
		boolean textureFlag = false;
		
		while((line = file.readLine()) != null ){
			if(line.startsWith(sVertex)){ //vertex

				model.vertices.add(new Vector3f(Float.valueOf(line.split(" ")[startIndex]), 
						Float.valueOf(line.split(" ")[startIndex + 1]),
						Float.valueOf(line.split(" ")[startIndex + 2]))); //adds vertex to list

			} else if(line.startsWith(sNormal)){ //normals

				model.normals.add(new Vector3f(Float.valueOf(line.split(" ")[startIndex]), 
						Float.valueOf(line.split(" ")[startIndex + 1]),
						Float.valueOf(line.split(" ")[startIndex + 2]))); //adds normal to list

			}else if(line.startsWith(sTexture)){
				
				if(!textureFlag)
					textureFlag = true;
				
				model.texcoords.add(new Vector3f(Float.valueOf(line.split(" ")[startIndex]), 
						Float.valueOf(line.split(" ")[startIndex + 1]),
						Float.valueOf(line.split(" ")[startIndex + 2]))); //adds texture coordinates to list
				
			} else if(line.startsWith(sFace)){ //faces
			
				if(textureFlag){
					Vector3f vert = new Vector3f(Float.valueOf(line.split(" ")[startIndex].split(seperator)[0]),
							Float.valueOf(line.split(" ")[startIndex + 1].split(seperator)[0]),
							Float.valueOf(line.split(" ")[startIndex + 2].split(seperator)[0]));

					Vector3f text = new Vector3f(Float.valueOf(line.split(" ")[startIndex].split(seperator)[1]),
							Float.valueOf(line.split(" ")[startIndex + 1].split(seperator)[1]),
							Float.valueOf(line.split(" ")[startIndex + 2].split(seperator)[1]));
					
					Vector3f norm = new Vector3f(Float.valueOf(line.split(" ")[startIndex].split(seperator)[2]),
							Float.valueOf(line.split(" ")[startIndex + 1].split(seperator)[2]),
							Float.valueOf(line.split(" ")[startIndex + 2].split(seperator)[2]));
					
					model.faces.add(new Model.Face(vert, norm, text));
				}
				else{
						
					Vector3f vert = new Vector3f(Float.valueOf(line.split(" ")[startIndex].split(seperator)[0]),
							Float.valueOf(line.split(" ")[startIndex + 1].split(seperator)[0]),
							Float.valueOf(line.split(" ")[startIndex + 2].split(seperator)[0]));

					Vector3f norm = new Vector3f(Float.valueOf(line.split(" ")[startIndex].split(seperator)[2]),
							Float.valueOf(line.split(" ")[startIndex + 1].split(seperator)[2]),
							Float.valueOf(line.split(" ")[startIndex + 2].split(seperator)[2]));

					model.faces.add(new Model.Face(vert, norm));
				}
				
			}
		}
		file.close();
		return model;
	}

}
