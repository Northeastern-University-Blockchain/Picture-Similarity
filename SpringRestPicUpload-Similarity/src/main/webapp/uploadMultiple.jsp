<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" session="false"%>
<html>
<head>
<title>Upload Multiple Files</title>

<!-- 
<script>

        var file1 = document.getElementById("file1"); 
        var file2 = document.getElementById("file2"); 
        
        var fileName1 = document.getElementById("fileName1");
        var fileName2 = document.getElementById("fileName2");   
        function handleFile()
        {           
         fileName1.value = file1.value; 
         fileName2.value = file2.value;      
        }

</script>
 -->
<script type="text/javascript">
var flag = 0;
/**
 * 校验表单
 */
function checkForm(){
	var idcard =document.getElementById("servertypename").value;
	var fileName = $('#file1').val();
	var check_flag = false;
	if(idcard==""){
		alert("类型名称不能为空！");
	}else if(fileName==""){
		alert("请上传图标！");
	}else {
		if(flag==1)
			alert("您只能输入GIF,BMP,PNG,JPG,JPEG格式的文件");
		else if(flag==2)
			alert("请上传尺寸为120*120的图片！");
		else
			check_flag = true;
	}
	return check_flag;
};
/**
 * 预览图片
 */
function previewImage(file, url) {
	var MAXWIDTH = 120;
    var MAXHEIGHT = 120;
    if (file.files && file.files[0]) {
        var img = document.getElementById(url);
        img.onload = function() {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width = rect.width;
            img.height = rect.height;
            img.style.marginTop = rect.top + 'px';
        };
        var reader = new FileReader();
        reader.onload = function(evt) {
            img.src = evt.target.result;
        };
        reader.readAsDataURL(file.files[0]);
    } else {
        file.select();
    }
    getImageSize(document.getElementById("file1"));
};

/**
 * 校验图片尺寸
 */
function getImageSize(obj) {
    var _file = document.getElementById("file1");
    var i = _file.value.lastIndexOf('.');
    var len = _file.value.length;
    var extEndName = _file.value.substring(i + 1, len);
    var extName = "GIF,BMP,PNG,JPG,JPEG";
    if (extName.indexOf(extEndName.toUpperCase()) == -1) {
        alert("您只能输入" + extName + "格式的文件");
        flag = 1;
    } else {
        var url, image;
        if (obj.files) {
            if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
                obj.select();
                url = document.selection.createRange().text;
            }
            url = window.URL.createObjectURL(obj.files[0]);
        } else {
            url = obj.value;
            url = "file:///" + url;
        }
        image = new Image();
        image.src = url;
        image.onload = function() {
            if (image.width != 0 || image.height != 0) {
                alert("图片选择成功！");
                flag = 2;
            }else{
            	flag = 0;
            }
        };
    }
};

/**
 * 显示图片名

var file1 = document.getElementById("file1"); 
var file2 = document.getElementById("file2"); 

var fileName1 = document.getElementById("fileName1");
var fileName2 = document.getElementById("fileName2");   
function handleFile()
{           
 fileName1.value = file1.value; 
 fileName2.value = file2.value;      
}
*/

</script>


</head>
<body>
	<form method="POST" action="uploadMultipleFile"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>Upload Multiple Pics</td>
			</tr>
			<tr>
				<td>Pic upload 1 :</td>
				<td><input type="file" id="file1" name="file"  accept="image/gif,image/png,image/bmp,image/jpeg" onchange="previewImage(this,'img1')"></td>
			</tr>

			<tr>
				<td>Pic Name 1 :</td>
				<td><input type="text" name="name"></td>
			</tr>

			<tr>
				<td>Pic upload 2 :</td>
				<td><input type="file" id="file2" name="file" accept="image/gif,image/png,image/bmp,image/jpeg" onchange="previewImage(this,'img2')"></td>

			</tr>

			<tr>
				<td>Pic Name 2 :</td>
				<td><input type="text" name="name"></td>
			</tr>

			<tr>
				<td>Upload</td>
				<td><input type="submit" value="Upload"></td>
			</tr>
		</table>
	</form>
	
	
	<img src="images/alum_def_img.jpg" name="img1" id="img1" width="100px;" height="100px;" align="center" style="margin-top: 5px; margin-bottom: 5px;"/>
	<img src="images/alum_def_img.jpg" name="img2" id="img2" width="100px;" height="100px;" align="center" style="margin-top: 5px; margin-bottom: 5px;"/>
	


</body>
</html>