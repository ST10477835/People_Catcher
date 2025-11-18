# 🧑‍🤝‍🧑 People Catcher Game

A simple, classic-style arcade game built with **Java Swing**. Control the player at the bottom of the screen to **catch the falling people** (boxes) and prevent them from reaching the ground to keep your health up! The game difficulty increases as your score gets higher.

---

## 📸 Game Screenshot

Here's a look at the **People Catcher** game in action:

![Alt Text](image-url-or-path)

---

## 🚀 Getting Started

### Prerequisites

* **Java Development Kit (JDK) 8 or newer** must be installed on your system.
* An Integrated Development Environment (IDE) like **NetBeans, Eclipse, or IntelliJ IDEA** is recommended for easy setup.

### Setup

1.  **Code Placement:** Save the provided Java code as `Box_catcher.java` inside a package named `box_catcher`.
2.  **Add Assets:**
    This game relies on several image files. For the game to run correctly, you must place the following image files in the **same directory** as your compiled classes (usually the package folder, e.g., `box_catcher`):
    * `background.jpg`
    * `king.png` (for the **Player/Catcher**)
    * `box1.png`, `box2.png`, `box3.png` (These are the images for the **Falling People**)
    * `HeartFull.png`, `HeartEmpty.png` (for the health display)

3.  **Run the Game:**
    Compile and run the `Box_catcher.java` file containing the `main` method.

---

## 🎮 How to Play

### Controls

Use the keyboard keys to move the catcher horizontally across the bottom of the screen.

| Key | Action |
| :---: | :---: |
| **A** | Move **Left** |
| **D** | Move **Right** |

### Game Objective

* **Rescue the People:** Move your player (`king.png`) to intercept the falling people before they hit the bottom edge of the screen.
* **Scoring:** Every person caught increases your score by **1**.
* **Difficulty:** The falling speed of the people increases every **10 points**.
* **Health:** You start with **3 hearts**. Missing a person (allowing them to reach the bottom) removes one heart.
* **Game Over:** The game ends when all 3 hearts are lost.

---

## 🛠️ Code Structure

The core functionality of the game is implemented in these classes:

* **`Box_catcher`**: The entry point for the application.
* **`GameFrame`**: The main `JFrame` window, handling the **game loop**, keyboard input (`KeyAdapter`), collision detection, and score/health logic.
* **`GamePanel`**: A `JComponent` responsible for all **rendering** (drawing the background, player, falling people, score, and health bar).
* **`Player`**: Represents the **Catcher** character.
* **`Box`**: Represents the **Falling People** objects.
