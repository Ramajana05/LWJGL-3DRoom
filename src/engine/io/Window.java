package engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.sql.Array;

/**
 * Builds the Window for our Application
 */

public class Window {

    private int width, height;
    private String title;
    private long window;
    public int frames;
    public static long time;

    public Input input;

    private float backgroundR, backgroundG, backgroundB;

    private GLFWWindowSizeCallback sizeCallback;
    private boolean isResized, isFullScreen;

    public Window(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create(){
        if(!GLFW.glfwInit()){
                System.err.println("Error, couldnt Initialize");
                return;
        }

        input = new Input();

        window = GLFW.glfwCreateWindow(width, height, title, isFullScreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);

        if(window == 0){
            System.err.println("Error, Window couldnt Initialize");
            return;
        }

        GLFWVidMode viedeoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, (viedeoMode.width() - width/2), (viedeoMode.height() - height)/2);

        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        createCallbacks();

        GLFW.glfwShowWindow(window);
        GLFW.glfwSwapInterval(1);
        time = System.currentTimeMillis();
    }

    private void createCallbacks(){
        sizeCallback = new GLFWWindowSizeCallback() {
            @Override
            public void invoke(long window, int w, int h) {
                width = w;
                height = h;
                isResized = true;
            }
        };
        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMovementCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());
        GLFW.glfwSetWindowSizeCallback(window, sizeCallback);
    }

    public void update(){
        if(isResized){
            GL11.glViewport(0, 0, width, height);
            isResized = false;
        }

        GL11.glClearColor(backgroundR, backgroundG, backgroundB, 1.0f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GLFW.glfwPollEvents();

        //FPS counter
        frames++;
        if(System.currentTimeMillis() > time + 1000){
            GLFW.glfwSetWindowTitle(window, title + " FPS: " + frames);
            time = System.currentTimeMillis();
            frames = 0;
        }
    }

    public void swapBuffers(){
        GLFW.glfwSwapBuffers(window);
    }

    public boolean shouldClose(){
        return GLFW.glfwWindowShouldClose(window);
    }

    public void destroy(){
        Input.destroy();
        GLFW.glfwWindowShouldClose(window);
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }

    public void setBackgroundColor(){
        backgroundR = 0f;
        backgroundB = 1f;
        backgroundG = 1f;
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
        isResized = true;

        // TODO: 2021-12-06 fix FullScreen Mode
        //if(!isFullScreen){
            //GLFWVidMode viedeoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
            //GLFW.glfwSetWindowPos(window, (viedeoMode.width() - width/2), (viedeoMode.height() - height)/2);
        //}
    }
}
