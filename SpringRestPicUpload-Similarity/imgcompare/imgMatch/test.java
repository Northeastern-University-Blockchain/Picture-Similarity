package imgMatch;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class test
{
	
	static
	{
		//System.loadLibrary("callOpencvInCpp");
		System.load("C:/Users/Administrator/Desktop/Pic/SpringRestPicUpload-Similarity/imgcompare/imgMatch/JNI_Opencv.dll");
	}
	
	public native void scan(String path,String path1,int des_width,int des_hight);
	
	public  String start(String strFileSrc,String strFileDes)
	{
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		//ImageHistogram histogram = null;
		InputStream isSrc = null;
		InputStream isDes = null;
		//String strFileSrc = "E:" + File.separator + "tmpFiles" + File.separator + "1";
		//String strFileDes = "E:" + File.separator + "tmpFiles" + File.separator + "2";
		final int des_width = 2400;
		final int des_hight = 3200;
		//System.out.println("asdsdaddsdads");
		try
		{
			
			isSrc = new FileInputStream(strFileSrc);
			isDes = new FileInputStream(strFileDes);
			if (TestFile.getFileType(isSrc)&&TestFile.getFileType(isDes))
			{
				isSrc.close();
				isDes.close();
				
				test tes = new test();
				System.out.println(strFileSrc);
				System.out.println(strFileDes);
		
				
				tes.scan(strFileSrc,"e:/tmpFiles/dst_src.jpg",des_width,des_hight);
				File fileSrc = new File("e:"+File.separator+"tmpFiles"+File.separator+"dst_src.jpg");
			
				//tes.scan(strFileDes,"f:/img/dst_des.jpg",des_width,des_hight);
				//File fileDes = new File("f:"+File.separator+"img"+File.separator+"dst_des.jpg");
				File fileDes = new File(strFileDes);


				if (fileSrc.isFile() && fileDes.isFile())
				{
					//PHash
					ImagePHash iph = new ImagePHash();
					int dis = 0;
					try
					{
						dis = iph.distance(fileSrc, fileDes);
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					double phash = (1-dis/(64.0));
					System.out.println("PHash" + phash);
					
					ImageHistogram ih = new ImageHistogram();
					double score = 0;
					score = ih.match(fileSrc, fileDes);
					System.out.println("Histogram"+score);
					
					double result = 0;
					if(phash >=0.95)
						result = phash;
					else
						result = score*0.5 + phash*0.5;
					
					System.out.println("Similarity：" + result);
					return "Similarity:" + result;
				} else
				{
					System.out.println("File not exist！");
					return "File not exist！";
				}
			}
			else
			{
				System.out.println("File format error！");
				return "File format error！";
			}
		} catch (IOException e)
		{
			e.printStackTrace();
			return "";
		}

	}
	//public static void main(String[] args)
	//{
	//	System.out.println("11111111");
	//	String a = start("e:"+File.separator + "tmpFiles"+File.separator+"1.jpg","e:"+File.separator + "tmpFiles"+File.separator+"2.jpg");
	//	System.out.println(a);
	//}
}
