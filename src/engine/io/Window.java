package engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

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

        window = GLFW.glfwCreateWindow(width, height, title, 0, 0);

        if(window == 0){
            System.err.println("Error, Window couldnt Initialize");
            return;
        }

        GLFWVidMode viedeoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window, viedeoMode.width() - width/2, (viedeoMode.height() - height/2));
        GLFW.glfwMakeContextCurrent(window);

        GLFW.glfwSetKeyCallback(window, input.getKeyboardCallback());
        GLFW.glfwSetCursorPosCallback(window, input.getMouseMovementCallback());
        GLFW.glfwSetMouseButtonCallback(window, input.getMouseButtonsCallback());

        GLFW.glfwShowWindow(window);
        GLFW.glfwSwapInterval(1);
        time = System.currentTimeMillis();
    }

    public void update(){
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
}
