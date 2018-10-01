package imgMatch;
/*
 * 本文件是对文件类型进行检测
 * 符合的文件类型在 FileType.java 文件里面
 * copy的别人的代码
 * 输入： 文件的is流
 * 输出：true/false
 */
import java.io.IOException;
import java.io.InputStream;

public class TestFile
{

	public static boolean getFileType(InputStream is) throws IOException
	{
		byte[] src = new byte[28];
		is.read(src, 0, 28);
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0)
		{
			return false;
		}
		for (int i = 0; i < src.length; i++)
		{
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v).toUpperCase();
			if (hv.length() < 2)
			{
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		FileType[] fileTypes = FileType.values();
		for (FileType fileType : fileTypes)
		{
			if (stringBuilder.toString().startsWith(fileType.getValue()))
			{
				return true;
			}
		}
		return false;
	}

}
