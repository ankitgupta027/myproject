package testalavista;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

/**
 * Created by akshay.mendole on 20/08/16.
 */
public class VersionComparator {
    public static void main(String[] args) {

        DefaultArtifactVersion version1 = new DefaultArtifactVersion("2.14");

        DefaultArtifactVersion version2 = new DefaultArtifactVersion("1.2");

        System.out.println(version1.compareTo(version2));
    }
}
