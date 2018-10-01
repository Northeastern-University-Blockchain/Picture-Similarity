package imgMatch;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class test
{
	
	static
	{
		//System.loadLibrary("callOpencvInCpp");//����C++��Opencv
		System.load("C:/Users/Administrator/Desktop/Pic/SpringRestPicUpload-Similarity/imgcompare/imgMatch/JNI_Opencv.dll");
	}
	
	public native void scan(String path,String path1,int des_width,int des_hight);
	
	public  String start(String strFileSrc,String strFileDes)
	{
		//System.loadLibrary(Core.NATIVE_LIBRARY_NAME);//ʹ��opencv����������
		//ImageHistogram histogram = null;//���ڲ����ƶȵ�ֱ��ͼ
		InputStream isSrc = null;//ԴͼƬ��Stream��
		InputStream isDes = null;//Ҫ����ͼƬ��Stream��
		//String strFileSrc = "E:" + File.separator + "tmpFiles" + File.separator + "1";
		//String strFileDes = "E:" + File.separator + "tmpFiles" + File.separator + "2";
		final int des_width = 2400;//Ŀ���ļ��Ŀ�͸�
		final int des_hight = 3200;
		//System.out.println("asdsdaddsdads");
		try
		{
			//�������ж��ļ��Ƿ���ϱ�׼
			isSrc = new FileInputStream(strFileSrc);
			isDes = new FileInputStream(strFileDes);
			if (TestFile.getFileType(isSrc)&&TestFile.getFileType(isDes))
			{
				isSrc.close();
				isDes.close();
				//������֮�󣬵�֪�ļ����ͷ��ϣ�Ȼ������ж�
				test tes = new test();
				System.out.println(strFileSrc);
				System.out.println(strFileDes);
		
				//ɨ��Դ�ļ�������һ��
				tes.scan(strFileSrc,"f:/img/dst_src.jpg",des_width,des_hight);
				File fileSrc = new File("f:"+File.separator+"img"+File.separator+"dst_src.jpg");
				//ɨ��Ŀ���ļ�������һ��
				tes.scan(strFileDes,"f:/img/dst_des.jpg",des_width,des_hight);
				File fileDes = new File("f:"+File.separator+"img"+File.separator+"dst_des.jpg");
				
				//�������ж��ļ��Ƿ���ڣ��о��Ƕ��һ�٣����Գ���ɾ��
				if (fileSrc.isFile() && fileDes.isFile())
				{
					//����PHash�ж�
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
					System.out.println("����ͼƬ��PHash���ƶ�Ϊ��" + phash);
					
					ImageHistogram ih = new ImageHistogram();
					double score = 0;
					score = ih.match(fileSrc, fileDes);
					System.out.println("����ͼƬ��Histogram���ƶ�Ϊ��"+score);
					
					double result = 0;
					if(phash >=0.95)
						result = phash;
					else
						result = score*0.3 + phash*0.7;
					
					System.out.println("�������ƶ�Ϊ��" + result);
					return "similarity:" + result;
				} else
				{
					System.out.println("�ļ������ڣ���������");
					return "�ļ������ڣ���������";
				}
			}
			else
			{
				System.out.println("�ļ���ʽ���󣡣�����");
				return "�ļ���ʽ���󣡣�����";
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
