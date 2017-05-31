$(function() {
	var product={
		"productName":"",
		"productDet":"",
		"productPic":""
	}
	
	if(localStorage.getItem("user")!=null && JSON.parse(localStorage.getItem("user")).id != undefined ){
		$("#loginpage").css({
			"display":"none"
		})
		product.userid=JSON.parse(localStorage.getItem("user")).id;
		document.getElementById("loginlog").innerHTML="欢迎："+JSON.parse(localStorage.getItem("user")).username;
	}
	
	if(product.userid=="" || product.userid==undefined){
			alert("请先登录");
			var url=location.href.substring(0,location.href.lastIndexOf('/'))+'/contact';
			window.location.href=url;
		}
	
	$("#upload").click(function(){
		product.productName=$("#productName").val();
		product.productDet=$("#productDet").val();
		
		if(product.userid=="" || product.userid==undefined){
			alert("请先登录");
			var url=location.href.substring(0,location.href.lastIndexOf('/'))+'/contact';
			window.location.href=url;
			return;
		}
		if(product.productName==""){
			alert("请输入作品名");
			return;
		}
		if(product.productDet==""){
			alert("请输入作品描述");
			return;
		}
		if(product.productPic==""){
			alert("请上传作品");
			return;
		}
		
		
		$.ajax({
			type:"get",
			data:product,
			url:"http://localhost:8082/TestSpringJpa/user/saveProduct",
			async:false,
			dataType:'json',
			success:function(data){
				if(data.flag=="true"){
					var url=location.href.substring(0,location.href.lastIndexOf('/'))+'/index';
					window.location.href=url;
				}
			},
			error:function(e){
				console.log(e);
			}
		});
		
		
	})
	
	
	
	var uploader = new plupload.Uploader({

		//上传插件初始化选用那种方式的优先级顺序，如果第一个初始化失败就走第二个，依次类推
		runtimes: 'html5,flash,silverlight,html4',

		//触发浏览文件按钮标签的唯一id,,在flash、html5、和silverlight中能找到触发事件的源
		browse_button: 'uploadqualification', //多个的写法  ['xx','xxxx']

		//访问web服务controller的路径
		url:  '/TestSpringJpa/user/upload',

		//flash文件地址 (不需要改动)
		flash_swf_url: '/trd/plupload-2.1.2/js/Moxie.swf',
		silverlight_xap_url: '/trd/plupload-2.1.2/js/Moxie.xap',

		//配置上传相关参数
		filters: {
			max_file_size: '10mb',
			mime_types: [{
				title: "Image files",
				extensions: "jpg,jpeg,gif,png"
			}]

			//						mime_types: [{
			//							title: "word files",
			//							extensions: "doc,xlxs"
			//						}]
		},

		//多选文件上传
		multi_selection: false,

		//初始化
		init: {
			//上传前配置参数
			BeforeUpload: function(up, files) {
//				uploader.setOption("multipart_params", {
//					"uploadTempId": uploadTempId
//				});
			},
			//上传开始
			FilesAdded: function(up, files) {
				uploader.start();
			},
			//上传中
			UploadFile: function(up, files) {
				$("#uploadqualification").css({"display":"none"});
				$("#uploadover").css({"display":"block"});
				document.getElementById("uploadover").innerHTML = '' + files.percent + "%";
			},

			//上传出错
			Error: function(up, err) {
				alert(err.code + ": " + err.message);
				return;
			},
			//上传完成
			UploadComplete: function(up, file) {
				document.getElementById("uploadover").innerHTML = "上传完成！"
				product.productPic=file[file.length-1].name;
			}
		}
	});
	uploader.init();
})