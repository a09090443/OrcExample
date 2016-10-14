import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ImageGrayFilterDemo extends JFrame {
	BufferedImage bi1;
	Image bi2;

	public ImageGrayFilterDemo() {
		super("javahome.idv.tw");
		this.setLayout(new GridLayout(2, 1));
		try {
			bi1 = ImageIO.read(new File("/home/zipe/tmp/test1.png"));
			bi2 = ImageIO.read(new File("/home/zipe/tmp/test1.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		bi2 = Toolkit.getDefaultToolkit().createImage(
				new FilteredImageSource(bi2.getSource(), new GrayFilter()));
		this.add(new JLabel(new ImageIcon(bi1)));
		this.add(new JLabel(new ImageIcon(bi2)));
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ImageGrayFilterDemo();
			}
		});
	}
}

class GrayFilter extends RGBImageFilter {
	public GrayFilter() {
	}

	public void setColorModel(ColorModel colorModel) {
		substituteColorModel(colorModel, new GrayModel(colorModel));
	}

	public int filterRGB(int x, int y, int pixel) {
		return pixel;
	}

	class GrayModel extends ColorModel {
		ColorModel model;

		public GrayModel(ColorModel sourceModel) {
			super(sourceModel.getPixelSize());
			this.model = sourceModel;
		}

		protected int getGrayLevel(int pixel) {
			return (int) (model.getRed(pixel) * 0.3 + model.getGreen(pixel)
					* 0.59 + model.getBlue(pixel) * 0.11);
		}

		public int getAlpha(int pixel) {
			return model.getAlpha(pixel);
		}

		public int getRed(int pixel) {
			return getGrayLevel(pixel);
		}

		public int getGreen(int pixel) {
			return getGrayLevel(pixel);
		}

		public int getBlue(int pixel) {
			return getGrayLevel(pixel);
		}

		public int getRGB(int pixel) {
			int gray = getGrayLevel(pixel);
			return ((getAlpha(pixel) << 24) + (gray << 16) + (gray << 8))
					| gray;
		}
	}
}
