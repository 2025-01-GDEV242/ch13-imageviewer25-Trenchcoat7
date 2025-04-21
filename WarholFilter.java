import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Creates a 2x2 grid of an image, each with a different color tint.
 * 
 * @Author Katie Strong
 * @Version 4.21.25
 */
public class WarholFilter extends Filter
{
    public WarholFilter(String name)
    {
        super(name);
    }

    @Override
    public void apply(OFImage image)
    {
        int width = image.getWidth();
        int height = image.getHeight();
        
        int halfWidth = width / 2;
        int halfHeight = height / 2;

        OFImage original = new OFImage(width, height);
        // Copy the original image so we don't overwrite while editing
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                original.setPixel(x, y, image.getPixel(x, y));
        
        // Clear the target image
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                image.setPixel(x, y, Color.BLACK);

        // Top-left: Quarter-size original
        for (int y = 0; y < height; y += 2) {
            for (int x = 0; x < width; x += 2) {
                Color c = original.getPixel(x, y);
                image.setPixel(x / 2, y / 2, c);
            }
        }

        // Top-right: Red tint
        for (int y = 0; y < halfHeight; y++) {
            for (int x = 0; x < halfWidth; x++) {
                Color c = original.getPixel(x * 2, y * 2);
                int red = c.getRed();
                Color redTint = new Color(red, 0, 0);
                image.setPixel(x + halfWidth, y, redTint);
            }
        }

        // Bottom-left: Green tint
        for (int y = 0; y < halfHeight; y++) {
            for (int x = 0; x < halfWidth; x++) {
                Color c = original.getPixel(x * 2, y * 2);
                int green = c.getGreen();
                Color greenTint = new Color(0, green, 0);
                image.setPixel(x, y + halfHeight, greenTint);
            }
        }

        // Bottom-right: Blue tint
        for (int y = 0; y < halfHeight; y++) {
            for (int x = 0; x < halfWidth; x++) {
                Color c = original.getPixel(x * 2, y * 2);
                int blue = c.getBlue();
                Color blueTint = new Color(0, 0, blue);
                image.setPixel(x + halfWidth, y + halfHeight, blueTint);
            }
        }
    }
}
