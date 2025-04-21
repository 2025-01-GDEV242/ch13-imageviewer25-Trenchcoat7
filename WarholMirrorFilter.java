import java.awt.Color;

/**
 * Creates a 2x2 grid of filtered, mirrored versions of the original image.
 * 
 * @Author Katie Strong
 * @Version 4.21.25
 */
public class WarholMirrorFilter extends Filter
{
    public WarholMirrorFilter(String name)
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

        // Copy original image
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                original.setPixel(x, y, image.getPixel(x, y));

        // Clear the canvas
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                image.setPixel(x, y, Color.BLACK);

        // Iterate over the scaled-down coordinates
        for (int y = 0; y < halfHeight; y++) {
            for (int x = 0; x < halfWidth; x++) {
                // Get corresponding pixel from original
                int origX = x * 2;
                int origY = y * 2;
                Color pix = original.getPixel(origX, origY);

                // Top-left: Original quarter-size
                image.setPixel(x, y, pix);

                // Top-right: Red tint + mirrored horizontally
                int red = pix.getRed();
                image.setPixel(halfWidth + (halfWidth - x - 1), y, new Color(red, 0, 0));

                // Bottom-left: Green tint + mirrored vertically
                int green = pix.getGreen();
                image.setPixel(x, halfHeight + (halfHeight - y - 1), new Color(0, green, 0));

                // Bottom-right: Blue tint + mirrored horizontally & vertically
                int blue = pix.getBlue();
                image.setPixel(halfWidth + (halfWidth - x - 1), halfHeight + (halfHeight - y - 1), new Color(0, 0, blue));
            }
        }
    }
}
