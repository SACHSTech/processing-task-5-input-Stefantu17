import processing.core.PApplet;

public class Sketch extends PApplet {

/**
  Global Variables
*/

// Inverse for X
public float inverseX;

// Circle x and y starting values
public double circleX = 0;
public double circleY = 250;

// Circle size values
public float circleWidth = 50;
public float circleHeight = 50;

// Speed of the moving circle
public double speed = 5;

// Situation conditionals
public boolean sun = true;
public boolean moon = false;

// Background fade inital values
public float transparency = 1;
public int day = color(51, 198, 255);
public int night = color(15, 3, 36);
public int starColor = color(255, 255, 255);
public float amt = 0;

// Ground colours
public int grass1 = color(85, 234, 19);
public int ground1 = color(116, 91, 14);
public int grass2 = color(6, 81, 23);
public int ground2 = color(48, 37, 4);

// Counter 
public boolean showCounter = false;
public int count = 0;
public char lastKey;

// Time
public boolean showTime = false;
public String currentTime;

  public void settings() {
    size(800, 300);
    smooth();
  }

  public void setup() {
    background(0);
  }

  public void counter() {
    if (showCounter == true) {
      fill(0, 0, 0);
      textSize(15);
      text("Current rotations: " + count, 5, 280);
    }
  }

  public void time() {
    if (count % 2 == 0) {
      currentTime = "Day";
    }
    else if (count % 2 != 0) {
      currentTime = "Night";
    }
    if (showTime == true) {
      fill(0, 0, 0);
      textSize(15);
      text(currentTime, 750, 280);
    }
  }

  public void draw() {

    lastKey = key;

    if (sun == true) {

      // Backgound
      background(lerpColor(night, day, amt));

      // Parabola-arch circle movement
      fill(246, 255, 51);
      ellipse(inverseX, (float)circleY, circleWidth, circleHeight);

      if (mouseY > 150) {
        circleX += mouseX / 80;
        circleY = (0.0009 * (Math.pow(circleX - width/2, 2))) + 100;
        inverseX = width - (float) circleX;
      }
      else if (mouseY < 150) {
        circleX -= mouseX / 80;
        circleY = (0.0009 * (Math.pow(circleX - width/2, 2))) + 100;
        inverseX = width - (float) circleX;
      }

      // Ground
      fill(lerpColor(grass2, grass1, amt));
      rect(0, 250, width, height - 40);
      fill(lerpColor(ground2, ground1, amt));
      rect(0, 260, width, height);
      
      // Increase amount 
      amt += 0.03;

      // Reset values
      if (circleY >= 300) {
        moon = true;
        sun = false;
        circleX = 0;
        circleY = 250;
        amt = 0;
        count += 1;
      }
    }

    else if (moon == true) {

      // Background
      background(lerpColor(day, night, amt));

      // Parabola-arch circle movement
      fill(255, 255, 255);
      ellipse(inverseX, (float)circleY, circleWidth, circleHeight);
      if (mouseY > 150) {
        circleX += mouseX / 80;
        circleY = (0.0009 * (Math.pow(circleX - width/2, 2))) + 100;
        inverseX = width - (float) circleX;
      }
      else if (mouseY < 150) {
        circleX -= mouseX / 80;
        circleY = (0.0009 * (Math.pow(circleX - width/2, 2))) + 100;
        inverseX = width - (float) circleX;
      }

      // Ground
      fill(lerpColor(grass1, grass2, amt));
      rect(0, 250, width, height - 40);
      fill(lerpColor(ground1, ground2, amt));
      rect(0, 260, width, height);

      // Increase amount
      amt += 0.03;

      // Reset values
      if (circleY >= 300) {
        sun = true;
        moon = false;
        circleX = 0;
        circleY = 250;
        amt = 0;
        count += 1;
      }
    }
  counter();
  time();
  }

  public void keyPressed() {
    if (lastKey == 'z') {
      showCounter = true;
    }
    else if (lastKey == 'x') {
      showCounter = false;
    }
    if (keyCode == SHIFT) {
      showTime = true;
    }
    else if (keyCode == ALT) {
      showTime = false;
    }
  }
  public void mousePressed() {
    fill(0, 0, 0);
    textSize(15);
    if (mouseY > 150) {
      text("You are going left", 350, 280);
    }
    else if (mouseY< 150) {
      text("You are going right", 350, 280);
    }
  }
}