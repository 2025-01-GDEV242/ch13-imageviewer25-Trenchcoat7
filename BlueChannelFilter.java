
/**
 * Write a description of class BlueChannelFilter here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BlueChannelFilter extends Filter
{
    // instance variables - replace the example below with your own
    private int x;

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
            // Use the blue value as intensity for all channels
            Color blueGray = new Color(blue, blue, blue);
            image.setPixel(x, y, blueGray);
        }
    }
}
}
