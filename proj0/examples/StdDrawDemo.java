/* 
 *
 * Make sure to see the more detailed description of
 * StdDraw at: http://introcs.cs.princeton.edu/java/15inout/ 
 * 
 * The link above also provides additional examples like BouncingBall.java
 *
 * Or you can see the full documentation at:
 *   http://introcs.cs.princeton.edu/java/15inout/javadoc/StdDraw.html
 */

public class StdDrawDemo {
	public static String imageToDraw = "advice.png";

	/* Draws three copies of the image in a rectangular pattern. */
	public static void drawThree() {
		/** Sets up the universe so it goes from 
		  * -100, -100 up to 100, 100 */
		StdDraw.setScale(-100, 100);

		/* Clears the drawing window. */
		StdDraw.clear();

		/* Stamps three copies of advice.png in a triangular pattern. */
		StdDraw.picture(0, 75, imageToDraw);
		StdDraw.picture(-75, -75, imageToDraw);
		StdDraw.picture(75, -75, imageToDraw);

		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		StdDraw.show();
		StdDraw.pause(2000);		
	}

	/* Draws random copies of the image, clearing in between
	 * drawings. */
	public static void drawRandom() {
		int waitTimeMilliseconds = 100;

		/* Stamp 100 additional pictures in random locations,
		 * each one coming slightly faster than the one before. */
		int count = 0;
		while (count < 200) {
			/* picks random x and y between -90 and 90 */
			double x = StdRandom.uniform(-90, 90);
			double y = StdRandom.uniform(-90, 90);

			/* Clears the screen. */
			StdDraw.clear();
			StdDraw.picture(x, y, imageToDraw);
			StdDraw.show();
			StdDraw.pause(waitTimeMilliseconds);

			/* Reduce wait time for each thing drawn, but
			 * never wait less than 10 milliseconds. */
			waitTimeMilliseconds = waitTimeMilliseconds - 1;
			if (waitTimeMilliseconds < 1) {
				waitTimeMilliseconds = 10;
			}

			count += 1; 
		}
	}

	/** Stick a copy of the image in the dead center of the image,
	  * which is position (0, 0). Slowly zoom in on the image, 
	  * then zoom back out (but faster than we zoomed in). */
	public static void drawZoom() {
		/** Enables double buffering.
		  * An animation technique where all drawing takes place on the offscreen canvas.
		  * Only when you call show() does your drawing get copied from the
		  * offscreen canvas to the onscreen canvas, where it is displayed
		  * in the standard drawing window. You don't have to understand this
		  * for CS61B. Just know that if you don't call this function, any attempt
		  * at smooth animation will look bad and flickery (remove it and see 
		  * what happens!). */
		StdDraw.enableDoubleBuffering();


		double size = 100;
		while (size < 500) {
			StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw, size, size);
			StdDraw.show();
			StdDraw.pause(10);
			size += 1;
		}

		while (size > 1) {
			StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw, size, size);
			StdDraw.show();
			StdDraw.pause(1);
			size -= 1;
		}
	}

	public static void main(String[] args) {
		
		drawThree();
		drawRandom();
		drawZoom();
	}
} 