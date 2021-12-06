package engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.sql.Array;

public class Input {

    private static GLFWKeyCallback keyboard;
    private static GLFWCursorPosCallback mouseMovement;
    private static GLFWMouseButtonCallback mouseButtons;

    private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX, mouseY;

    public Input(){
        keyboard = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                keys[key] = (action != GLFW.GLFW_RELEASE);
            }
        };

        mouseMovement = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };

        mouseButtons = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                buttons[button] = (action != GLFW.GLFW_RELEASE);
            }
        };
    }

    public static boolean isKeyDown(int key){
        return keys[key];
    }

    public static boolean isButtonDown(int button){
        return buttons[button];
    }


    // clears all the Callbacks
    public static void destroy(){
        keyboard.free();
        mouseMovement.free();
        mouseButtons.free();
    }

    public GLFWKeyCallback getKeyboardCallback() {
        return keyboard;
    }

    public void setKeyboardCallback(GLFWKeyCallback keyboard) {
        this.keyboard = keyboard;
    }

    public GLFWCursorPosCallback getMouseMovementCallback() {
        return mouseMovement;
    }

    public void setMouseMovementCallback(GLFWCursorPosCallback mouseMovement) {
        this.mouseMovement = mouseMovement;
    }

    public GLFWMouseButtonCallback getMouseButtonsCallback() {
        return mouseButtons;
    }

    public void setMouseButtonsCallback(GLFWMouseButtonCallback mouseButtons) {
        this.mouseButtons = mouseButtons;
    }

    public boolean[] getKeys() {
        return keys;
    }

    public void setKeys(boolean[] keys) {
        this.keys = keys;
    }

    public boolean[] getButtonsCallback() {
        return buttons;
    }

    public void setButtonsCallback(boolean[] buttons) {
        this.buttons = buttons;
    }

    public static double getMouseX() {
        return mouseX;
    }

    public void setMouseX(double mouseX) {
        this.mouseX = mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    public void setMouseY(double mouseY) {
        this.mouseY = mouseY;
    }
}
