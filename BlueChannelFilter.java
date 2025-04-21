import java.awt.Color;

/**
 * An image filter to remove color from an image and put a blue channel filter on it.
 * 
 * @author Katie Strong
 * @version 4.21.25
 */
public class BlueChannelFilter extends Filter
{
    /**
     * Constructor for objects of class RedChannelFilter.
     * @param name The name of the filter.
     */
    public BlueChannelFilter(String name)
    {
        super(name);
    }

    /**
     * Apply this filter to an image.
     * 
     * @param  image  The image to be changed by this filter.
     */
    public void apply(OFImage image)
{
    int height = image.getHeight();
    int width = image.getWidth();
    for(int y = 0; y < height; y++) {
        for(int x = 0; x < width; x++) {
            Color pix = image.getPixel(x, y);
            int blue = pix.getBlue(); // range 0–255
            // Use the red value as intensity for all channels
            Color blueGray = new Color(blue, blue, blue);
            image.setPixel(x, y, blueGray);
        }
    }
}

}
