package engine.graphics;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.IOException;

/**
 * This class loads the Textures from the textures Folder.
 * With the path, texture, width and height of the Texture and the TextureID
 *
 * @author: Ramajana Skopljak
 * @version: 1.0
 */

public class Material {
    private String path;
    private Texture myTexture;
    private float width, height;
    private int myTextureID;

    /**
     * When called outside this class, we just need to set the path like "/textures/myTexture.png"
     * @param path
     */
    public Material(String path) {
        this.path = path;
    }

    //Checks if the png/jpg exitst and shows a error message, if it doesnt
    public void create() {
        try {
            myTexture = TextureLoader.getTexture(path.split("[.]")[1], Material.class.getResourceAsStream(path), GL11.GL_NEAREST);
            width = myTexture.getWidth();
            height = myTexture.getHeight();
            myTextureID = myTexture.getTextureID();
        } catch (IOException e) {
            System.err.println("Can't find texture at " + path);
        }
    }

    //Always needs to be destroyed later on
    public void destroy() {
        GL13.glDeleteTextures(myTextureID);
    }

    public int getMyTextureID() {
        return myTextureID;
    }
}