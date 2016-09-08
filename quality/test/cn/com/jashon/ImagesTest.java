package cn.com.jashon;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.nutz.img.Images;
import org.nutz.lang.Files;

public class ImagesTest {
	
	private final String FILE_TARGET_PATH_PREFIX = "F:/demos/";
	
	@Test
	public void scaleTest() throws IOException {
		File[] imgs = Files.files(new File("F:/images"), ".jpg");
		for(File f : imgs) {
			System.out.println(f.getName());
			
//			Images.zoomScale(f.getAbsolutePath(), FILE_TARGET_PATH_PREFIX.concat(f.getName()), 200, -1, Color.WHITE);
			
			Images.clipScale(f.getAbsolutePath(), FILE_TARGET_PATH_PREFIX.concat(f.getName()), new int[]{10,10} , new int[]{150,150});
		}
	}

}
