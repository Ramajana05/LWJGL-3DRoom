import engine.io.Input;
import engine.io.Window;
import org.lwjgl.glfw.GLFW;

public class Main implements Runnable {
    public Thread game;
    public Window window;
    public final int WIDTH = 1280, HEIGHT = 760;

    public void start(){
        game = new Thread(this, "game");
        game.start();
    }

    public void init(){
        window = new Window(WIDTH, HEIGHT, "Game");
        window.setBackgroundColor();
        window.setFullScreen(true);
        window.create();
    }


    @Override
    public void run() {
        init();
        while(!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE )){
            update();
            render();
            if(Input.isKeyDown(GLFW.GLFW_KEY_F11)) {
                window.setFullScreen(!window.isFullScreen());
            }

            if(Input.isKeyDown(GLFW.GLFW_KEY_F12)){
                window.isFullScreen();
            }
        }
        window.destroy();
    }

    public void update(){
        window.update();
        if(Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)){
            System.out.println("X: " + Input.getMouseX() + ", " + Input.getMouseY());
        }
    }

    public void render(){
        window.swapBuffers();
    }

    public static void main(String[] args) {
        new Main().start();
    }

}
