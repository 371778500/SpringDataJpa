$(function(){
	if(localStorage.getItem("user")!=null && JSON.parse(localStorage.getItem("user")).id != undefined ){
		$("#loginpage").css({
			"display":"none"
		})
		$("#loginout").css({
			"display":"block"
		})
		document.getElementById("loginlog").innerHTML="欢迎："+JSON.parse(localStorage.getItem("user")).username;
	}
	$("#loginout").click(function(){
		$("#loginpage").css({
			"display":"block"
		})
		$("#loginout").css({
			"display":"none"
		})
		localStorage.removeItem("user");
		document.getElementById("loginlog").innerHTML="";
	})
	
	product={
		"page":1,
		"data":[]
	};
	
		$.ajax({
			type:"get",
			url:"http://localhost:8082/TestSpringJpa/user/queryproductbyheart",
			async:false,
			dataType:'json',
			success:function(data){
				var vote="<ol>";
				for(var i=0;i<data.length;i++){
					vote+="<li><a>"+data[i].productName+"</a><span>("+data[i].heart+")</span></li>";
				}
				vote+="</ol>"
				$("#productvote").append(vote);
			},
			error:function(e){
				console.log(e);
			}
		});
		
	
		function getProduct(){
			$.ajax({
				type:"get",
				data:{"page":product.page},
				url:"http://localhost:8082/TestSpringJpa/user/queryproductbydate",
				async:false,
				dataType:'json',
				success:function(data){
					pullProductDate(data);
					product.page+=1;
					product.data=product.data.concat(data);
				},
				error:function(e){
					console.log(e);
				}
		});
	}
		
	function pullProductDate(data,flag){
		var productpage="";
		for(var i=0;i<data.length;i++){
			if(i%2==0){
				productpage+="<div class='row'>";
			}
			productpage+="<div id='"+data[i].id+"' class='clickpic col-md-6 col-sm-6'><article class='blog-teaser'><header><img src='img/"+data[i].productPic+"' >"+
									"<h3><a href='#' >"+data[i].productName+"</a></h3><hr></header></article></div>";
		
			if(i%2==1){
				productpage+="</div>";
			}
		}
		if(data.length%2==1){
			productpage+="</div>";
		}
		if(flag){
			$("#productdata").html("");
		}
		$("#productdata").append(productpage);
		for(var i=0;i<data.length;i++){
			document.getElementById(data[i].id).addEventListener("click", clickpic, true)
		}
	}
	
	window.currentDateId=null;
	window.currentDate=null;
	
	$("#selectpByName").click(function(){
		var productname=$('#searchbox').val();
		if(productname==""||productname==null){
			alert("请输入作品名");
			return;
		}
		$.ajax({
				type:"get",
				data:{"SelectPbyName":productname},
				url:"http://localhost:8082/TestSpringJpa/user/queryproductbyProductName",
				async:false,
				dataType:'json',
				success:function(data){
					pullProductDate(data,true);
				},
				error:function(e){
					console.log(e);
				}
		});
	})
	
	function clickpic(obj){
		currentDateId=$(this).attr("id");
		console.log(currentDateId);
		Obody.insertAdjacentHTML("afterBegin", insertText);
		var Omask = document.getElementById("mask")
		var OmainBody = document.getElementById("mainBody")
		var Oclose = document.getElementById("closeBtn");

		Omask.style.display = 'block';
		OmainBody.style.display = 'block';
		var page_l = '18%';
		var page_t = '5%';
		//alert(page_l)
		//alert(page_t)
		OmainBody.style.left = page_l
		OmainBody.style.top = page_t
		document.getElementById("closeBtn").addEventListener("click", closeMask, true)
		document.getElementById("startheart").addEventListener("click", clickheart, true)
		
		for(var i=0;product.data.length;i++){
			if(product.data[i].id==currentDateId){
				currentDate=product.data[i];
				$("#imgpath").attr("src","img/"+product.data[i].productPic);
				$("#heartnum").html(product.data[i].heart);
				$("#product_det").html(product.data[i].productDet);
				break;
			}
		}
		if(JSON.parse(localStorage.getItem("user"))){
			var userid=JSON.parse(localStorage.getItem("user")).id;
		}
		if(userid!="" && userid!=undefined){
			var userheart={
				"userid":userid,
				"productid":currentDate.id
			}
			$.ajax({
					type:"get",
					data:userheart,
					url:"http://localhost:8082/TestSpringJpa/user/getheart",
					async:false,
					dataType:'json',
					success:function(data){
						console.log(data);
						if(data.id != "null"){
							$("#isheart").addClass("color_red");
						}
					},
					error:function(e){
						console.log(e);
					}
			});
			
		}
		
		
	}
	
	
	
	$("#productdata").click(function(obj){
		$("#myModal").show();
	})
		
	getProduct();
	
	$("#moreproduct").click(function(){
		getProduct();
	})
	
	
	/*遮罩弹出框-垂直居中*/
	var win_w = document.documentElement.clientWidth || window.innerWidth;
	var win_h = document.documentElement.clientHeight || window.innerHeight;
	//alert("屏幕的宽度为:"+win_w)
	//alert("屏幕的宽度为:"+win_h)
	var Obody = document.getElementsByTagName("body")[0]

	var insertText = '<div class="mask" id="mask" style="display:none;"></div>' +
		'<div class="mainBody" id="mainBody" style="display:none;">'+
		'<div style="margin-top:27px"><img style="height: 340px;width: 700px;box-shadow: 0 0 8px #000;" id="imgpath"></img> <div  id="startheart"><span id="isheart" class="glyphicon glyphicon-heart"></span><span  id="heartnum"></span></div></div>' +
		'<div><span id="product_det"></span></div>'+
		'<a class="closeBtn" id="closeBtn">&times;</a>' +
		'</div> ';
	//Obody.innerHTML(insertText);	

	//关闭弹出框	
	function closeMask() {
		var Omask = document.getElementById("mask")
		var OmainBody = document.getElementById("mainBody")
		Omask.parentNode.removeChild(Omask);
		OmainBody.parentNode.removeChild(OmainBody);
		//window.location.reload();
		
	}

	
})


function clickheart(){
		//点击♥
		console.log(currentDateId+"heart");
		if(JSON.parse(localStorage.getItem("user"))){
			var userid=JSON.parse(localStorage.getItem("user")).id;
		}
		if(userid=="" || userid==undefined){
			alert("请先登录");
			var url=location.href.substring(0,location.href.lastIndexOf('/'))+'/contact';
			window.location.href=url;
		}
		if($("#isheart").hasClass("color_red")){
			alert("您对该作品已投过票！");
			return;
		}
		$("#heartnum").html(parseInt(currentDate.heart)+1);
		$("#isheart").addClass("color_red");
		var userheart={
			"userid":userid,
			"productid":currentDate.id,
			"heartnum":parseInt(currentDate.heart)+1
		}
		$.ajax({
				type:"get",
				data:userheart,
				url:"http://localhost:8082/TestSpringJpa/user/saveheart",
				async:false,
				dataType:'json',
				success:function(data){
					console.log(data);
					currentDate.heart=parseInt(currentDate.heart)+1;
				},
				error:function(e){
					console.log(e);
				}
		});
		
		
	}
