package imagefetchapp.imagefetchapp;

/**
 * Created by shivam on 2/6/18.
 */
import java.io.File;
import java.io.FilenameFilter;
/**
 *
 *  This class is used to filter file type as per our
 *  requirement. Now we need to get all the Audio files
 *  so we are providing file format as .mp3. We can change this as
 *  per our requirement.
 */
class MyImageFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return  (name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png"));
    }
}