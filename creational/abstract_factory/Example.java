package creational.abstract_factory;

// Step 1: Define Abstract Products

// Abstract Product A
interface Button {
    void render();
}

// Abstract Product B
interface Checkbox {
    void render();
}

// Step 2: Create Concrete Products

// Concrete Product A1
class WindowsButton implements Button {
    public void render() {
        System.out.println("Rendering Windows Button");
    }
}

// Concrete Product A2
class MacButton implements Button {
    public void render() {
        System.out.println("Rendering Mac Button");
    }
}

// Concrete Product B1
class WindowsCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering Windows Checkbox");
    }
}

// Concrete Product B2
class MacCheckbox implements Checkbox {
    public void render() {
        System.out.println("Rendering Mac Checkbox");
    }
}

// Step 3: Define Abstract Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Step 4: Implement Concrete Factories

class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }
    
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }
    
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// Step 5: Client Code

class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void render() {
        button.render();
        checkbox.render();
    }
}


public class Example {
    public static void main(String[] args) {
        GUIFactory factory;

        // Simulate user choosing OS
        // String os = "Windows";  // Change to "Mac" for macOS
        String os = "Mac";

        if (os.equalsIgnoreCase("Windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacFactory();
        }

        Application app = new Application(factory);
        app.render();
    }
}