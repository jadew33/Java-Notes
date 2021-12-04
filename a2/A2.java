package a2;

import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

public class A2 {
	/**
	 * The original image
	 */
	private static Picture orig;

	/**
	 * The image viewer class
	 */
	private static A2Viewer viewer;

	/**
	 * Returns a 300x200 image containing the Queen's flag (without the crown).
	 * 
	 * @return an image containing the Queen's flag
	 */
	public static Picture flag() {
		Picture img = new Picture(300, 200);
		int w = img.width();
		int h = img.height();

		// set the pixels in the blue stripe
		Color blue = new Color(0, 48, 95);
		for (int col = 0; col < w / 3; col++) {
			for (int row = 0; row < h - 1; row++) {
				img.set(col, row, blue);
			}
		}

		// set the pixels in the yellow stripe
		Color yellow = new Color(255, 189, 17);
		for (int col = w / 3; col < 2 * w / 3; col++) {
			for (int row = 0; row < h - 1; row++) {
				img.set(col, row, yellow);
			}
		}

		// set the pixels in the red stripe
		Color red = new Color(185, 17, 55);
		for (int col = 2 * w / 3; col < w; col++) {
			for (int row = 0; row < h - 1; row++) {
				img.set(col, row, red);
			}
		}
		return img;
	}

	public static Picture copy(Picture p) {

		Picture result = new Picture(p.width(), p.height());

		for (int row = 0; row < p.height() - 1; row++) {
			for (int col = 0; col < p.width() - 1; col++) {
				result.set(col, row, p.get(col, row));
			}
		}

		return result;
	}

	public static Picture border(Picture p, int thickness) {

		Picture result = new Picture(p.width(), p.height());

		for (int row = 0; row < p.height() - 1; row++) {
			for (int col = 0; col < p.width() - 1; col++) {
				if (col <= thickness || col >= p.width() - 1 - thickness || row <= thickness
						|| row >= p.height() - 1 - thickness) {
					result.set(col, row, Color.BLUE);
				} else {
					result.set(col, row, p.get(col, row));
				}
			}
		}

		return result;
	}

	public static Picture gray(Picture p) {

		Picture result = new Picture(p.width(), p.height());
		int gray;
		Color c;

		for (int row = 0; row < p.height() - 1; row++) {
			for (int col = 0; col < p.width() - 1; col++) {
				c = p.get(col, row);
				gray = (int) Math.round(0.2989 * c.getRed() + 0.5870 * c.getGreen() + 0.1130 * c.getBlue());
				result.set(col, row, new Color(gray, gray, gray));
			}
		}

		return result;
	}

	public static Picture binary(Picture p, Color c1, Color c2) {

		Picture result = new Picture(p.width(), p.height()), grayVers = gray(p);

		for (int row = 0; row < p.height() - 1; row++) {
			for (int col = 0; col < p.width() - 1; col++) {
				if (grayVers.get(col, row).getRed() < 128)
					result.set(col, row, c1);
				else
					result.set(col, row, c2);
			}
		}

		return result;
	}

	public static Picture flip(Picture p) {

		Picture result = new Picture(p.width(), p.height());

		for (int row = p.height() - 1; row >= 0; row--) {
			for (int col = 0; col < p.width() - 1; col++) {
				result.set(col, row, p.get(col, p.height() - 1 - row));
			}
		}

		return result;
	}

	public static Picture rotate(Picture p) {

		Picture result = new Picture(p.height(), p.width());

		for (int row = p.height() - 1; row >= 0; row--) {
			for (int col = 0; col < p.width() - 1; col++) {
				result.set(row, col, p.get(col, p.height() - 1 - row));
			}
		}

		return result;
	}

	public static Picture redeye(Picture p) {

		Picture result = new Picture(p.width(), p.height());
		Color c; // get the colour of current pixel

		for (int row = 0; row < p.height() - 1; row++) {
			for (int col = 0; col < p.width() - 1; col++) {

				c = p.get(col, row);

				// checks if ratio of red to green and blue is greater than half
				if (c.getRed() / (c.getBlue() + c.getGreen()) > .5) {
					// sets those pixels to black. It does, however, set the pixels
					// that are not the eye black as well.
					result.set(col, row, Color.BLACK);
				}

				else
					result.set(col, row, p.get(col, row));
			}
		}

		return result;
	}

	public static Picture blur(Picture p, int radius) {

		// stores the valid pixels to average for the blur
		ArrayList<Color> colours = new ArrayList<Color>();
		// stores rgb values for the pixel to blur
		int red = 0, blue = 0, green = 0;

		Picture result = new Picture(p.width(), p.height());
		// complete the method

		for (int row = 0; row < p.height() - 1; row++) {
			for (int col = 0; col < p.width() - 1; col++) {

				// finds if neighbouring pixels are valid and if so, stores them in arrayList
				for (int i = 0; i <= radius; i++) {
					// bottom pixel(s)
					if (isValid(row + i, col, p))
						colours.add(p.get(col, row + i));
					// top pixel(s)
					if (isValid(row - i, col, p))
						colours.add(p.get(col, row - i));
					// right pixel(s)
					if (isValid(row, col + i, p))
						colours.add(p.get(col + i, row));
					// left pixel(s)
					if (isValid(row, col - i, p))
						colours.add(p.get(col - i, row));
					// top-right diagonal pixel(s)
					if (isValid(row - i, col + i, p))
						colours.add(p.get(col + i, row - i));
					// top-left diagonal pixel(s)
					if (isValid(row - i, col - i, p))
						colours.add(p.get(col - i, row - i));
					// bottom-right diagonal pixel(s)
					if (isValid(row + i, col + i, p))
						colours.add(p.get(col + i, row + i));
					// bottom-left diagonal pixel(s)
					if (isValid(row + i, col - i, p))
						colours.add(p.get(col - i, row + i));
				}

				// adds up rgb values for surrounding pixels
				for (Color c : colours) {
					red += c.getRed();
					green += c.getGreen();
					blue += c.getBlue();
				}

				// divide by size of arrayList to find average
				red /= colours.size();
				green /= colours.size();
				blue /= colours.size();

				result.set(col, row, new Color(red, green, blue));

				// reset variables
				red = 0;
				blue = 0;
				green = 0;
				colours.clear();
			}
		}

		return result;
	}

	// checks if neighbouring pixel is within the bounds of the picture
	public static boolean isValid(int row, int col, Picture p) {
		return row >= 0 && row < p.height() - 1 && col >= 0 && col < p.width() - 1;
	}

	/**
	 * A2Viewer class calls this method when a menu item is selected. This method
	 * computes a new image and then asks the viewer to display the computed image.
	 * 
	 * @param op the operation selected in the viewer
	 */
	public static void processImage(String op) {

		switch (op) {
		case A2Viewer.FLAG:
			// create a new image by copying the original image
			Picture p = A2.flag();
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.COPY:
			// create a new image by copying the original image
			p = A2.copy(A2.orig);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.BORDER_1:
			// create a new image by adding a border of width 1 to the original image
			p = A2.border(A2.orig, 1);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.BORDER_5:
			// create a new image by adding a border of width 5 the original image
			p = A2.border(A2.orig, 5);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.BORDER_10:
			// create a new image by adding a border of width 10 the original image
			p = A2.border(A2.orig, 10);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.TO_GRAY:
			// create a new image by converting the original image to grayscale
			p = A2.gray(A2.orig);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.TO_BINARY:
			// create a new image by converting the original image to black and white
			p = A2.binary(A2.orig, Color.BLACK, Color.WHITE);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.FLIP_VERTICAL:
			// create a new image by flipping the original image vertically
			p = A2.flip(A2.orig);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.ROTATE_RIGHT:
			// create a new image by rotating the original image to the right by 90 degrees
			p = A2.rotate(A2.orig);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.RED_EYE:
			// create a new image by removing the redeye effect in the original image
			p = A2.redeye(A2.orig);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.BLUR_1:
			// create a new image by blurring the original image with a box blur of radius 1
			p = A2.blur(A2.orig, 1);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.BLUR_3:
			// create a new image by blurring the original image with a box blur of radius 3
			p = A2.blur(A2.orig, 3);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.BLUR_5:
			// create a new image by blurring the original image with a box blur of radius 5
			p = A2.blur(A2.orig, 5);
			A2.viewer.setComputed(p);
			break;
		default:
			// do nothing
		}
	}

	/**
	 * Starting point of the program. Students can comment/uncomment which image to
	 * use when testing their program.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		A2.viewer = new A2Viewer();
		A2.viewer.setVisible(true);

		URL img;
		// uncomment one of the next two lines to choose which test image to use (person
		// or cat)
		img = A2.class.getResource("redeye-400x300.jpg");
		// img = A2.class.getResource("cat.jpg");

		A2.orig = new Picture(new File(img.getFile()));
		A2.viewer.setOriginal(A2.orig);
	}

}
