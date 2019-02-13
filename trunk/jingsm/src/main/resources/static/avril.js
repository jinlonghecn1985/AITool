document.write("<script language='javascript' src='./jl/http.jl.js'></script>");
document.write("<script language='javascript' src='./jl/jquery.ajax.jl.js'></script>");
document.write("<script language='javascript' src='./jl/jquerysession.js'></script>");

//初始化代码
$(document).ready(function(){ 	
	console.log('load avril.js');	
	AVRIL.info();		
});

function showJLWarning(message){
	new $.zui.Messager(message, {
		type : 'warning',
		placement : 'top-right',
		close : false
	// 禁用关闭按钮
	}).show();
}

function showJLSuccess(message){
	new $.zui.Messager(message, {
		type : 'success',
		placement : 'top-right',
		close : false
	// 禁用关闭按钮
	}).show();
}

function showJLError(message){
	new $.zui.Messager(message, {
		type : 'danger',
		placement : 'top-right',
		close : false
	// 禁用关闭按钮
	}).show();
}

(function($) {
	//注册全局系统对象
	window['AVRIL'] = {};
	
	AVRIL.setProject=function(projectId, projectName){
		$.session.set("projectId", projectId);
		$.session.set("projectName", projectName);
	};
	
	AVRIL.clearProject=function(){
		$.session.remove("projectId");
		$.session.remove("projectName");
	};	
	
	AVRIL.projectId=function(){
		return $.session.get("projectId");
	}
	
	AVRIL.projectName=function(){
		return $.session.get("projectName");
	}	
	
	//定义方法-属性
	AVRIL.info = function(){
		console.log('AVRIL FUCK');
	};
	
	AVRIL.authorName = function(){
		return "<h4>技术支持：流程信息部</h4>";
	}
	
	//查询项目
	AVRIL.loadProjectList = function(fn, pn, ps, searchWord){
		var sou='';
		if(searchWord!=undefined && searchWord.length>0){
			sou = "&projectName="+searchWord;
		}
		myAjax("/projects?sort=%7B%22gmtCreated%22%3A%22desc%22%7D&pageNo="+pn+"&pageSize="+ps+sou, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.loadProjectInfo = function(fn){
		myAjax("/project/"+AVRIL.projectId(), "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.doAssAction = function(fn){
		myAjax("/ai/"+AVRIL.projectId(), "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.saveProjectInfo = function(fn, obj){
		if(obj.projectId==undefined || obj.projectId.length==0 || obj.projectId==0){
			myAjaxJson("/project", "post", obj, function(data){
				if(fn){fn(data);}
			}, false);
		}else if(obj.projectId!=undefined){
			myAjaxJson("/project/"+obj.projectId, "put", obj, function(data){
				if(fn){fn(data);}
			}, false);
		}
	}
	
	//加载联系人
	AVRIL.loadContactsList = function(fn, pn, ps, searchWord){
		var sou='';
		sou ="&projectId="+AVRIL.projectId();
		if(searchWord!=undefined && searchWord.length>0){
			sou = +"&contacts="+searchWord;
		}
		myAjax("/contactss?sort=%7B%22gmtModify%22%3A%22desc%22%7D&pageNo="+pn+"&pageSize="+ps+sou, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	//
	AVRIL.loadRegulationList = function(fn, pn, ps, searchWord){
		var sou='';
		sou ="&projectId="+AVRIL.projectId();
		if(searchWord!=undefined && searchWord.length>0){
			sou += "&content="+searchWord;
		}
		myAjax("/regulationss?sort=%7B%22gmtModify%22%3A%22desc%22%7D&pageNo="+pn+"&pageSize="+ps+sou, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	AVRIL.loadConnunicateList= function(fn, pn, ps, searchWord){
		var sou='';
		sou ="&projectId="+AVRIL.projectId();
		if(searchWord!=undefined && searchWord.length>0){
			sou += "&note="+searchWord;
		}
		myAjax("/communicates?sort=%7B%22useCode%22%3A%22desc%22%7D&pageNo="+pn+"&pageSize="+ps+sou, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	AVRIL.loadAssessmentList= function(fn, pn, ps, searchWord){
		var sou='';
		sou ="&projectId="+AVRIL.projectId();
		if(searchWord!=undefined && searchWord.length>0){
			sou += "&keyword="+searchWord;
		}
		myAjax("/assessments?sort=%7B%22cid%22%3A%22asc%22%2C%22flag%22%2C%22asc%22%7D&pageNo="+pn+"&pageSize="+ps+sou, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	AVRIL.loadAssessmentList2= function(fn, searchWord){
		myAjax("/assessment?cid="+searchWord, "get", {}, function(data){			
			if(fn){
				fn(data);
			}
		}, false);
	}
	
	7561
	
	/**
	 * 
	 * 初始化分页条
	 * @param pageBe
	 */
	AVRIL.initPageBar = function(pageBe){
		var maxPage;
		if(pageBe==undefined || pageBe.totalCount==0){
			maxPage=1;
			$(".pager").html("<li class=\"active\"><a href=\"javascript:void(0);\" onclick=\"goPage(1)\" class=\"on\">刷新</a></li>");
			//无数据
			return;
		}
		if(pageBe.totalPages==1){
			$(".pager").html("<li class=\"active\"><a href=\"javascript:void(0);\" onclick=\"goPage(1)\" class=\"on\">1</a></li>");
			return;
		}
		var khtml = "";
		if(pageBe.hasPrePage==true){
			khtml+="<li class=\"previous\"><a href=\"javascript:void(0);\" onclick=\"goPage("+(pageBe.page-1)+")\">« 上一页</a></li>";
		}
		if(pageBe.totalPages<10){
			var phtml=khtml;
			for(var j=0; j<pageBe.totalPages; j++){
				phtml += "<li "+(j+1==pageBe.page?"class=\"active\"":"")+"><a href=\"javascript:void(0);\" onclick=\"goPage("+(j+1)+")\" >"+(j+1)+"</a></li>";
			}
			if(pageBe.page!=pageBe.totalPages){
				phtml+="<li class=\"next\"><a href=\"javascript:void(0);\" onclick=\"goPage("+(pageBe.page+1)+")\">下一页  »</a></li>";
			}
			$(".pager").html(phtml);
			return;
		}
		khtml += "<li "+(1==pageBe.page?"class=\"active\"":"")+"><a href=\"javascript:void(0);\" onclick=\"goPage(1)\" >1</a></li><li "+(2==pageBe.page?"class=\"active\"":"")+"><a href=\"javascript:void(0);\" onclick=\"goPage(2)\" >2</a></li>";
		var cpage = pageBe.page; //准备中间页
		if(pageBe.page<6){
			//在头5页时
			cpage=3;
		}else if(pageBe.page>pageBe.totalPages-4){
			cpage=pageBe.totalPages-6;
			khtml+=" <li><a href=\"javascript:void(0);\">⋯</a></li>";
		}else{
			cpage=pageBe.page-2;
			khtml+=" <li><a href=\"javascript:void(0);\">⋯</a></li>";
		}
		
		for(var k=0; k<5; k++){
			khtml+=("<li "+((cpage+k)==pageBe.page?"class=\"active\"":"")+"><a href=\"javascript:void(0);\" onclick=\"goPage("+(cpage+k)+")\" >"+(cpage+k)+"</a></li>");
		}
		if(pageBe.page<pageBe.totalPages-4){
			khtml+=" <li><a href=\"javascript:void(0);\">⋯</a></li>";
		}
		khtml += ("<li "+((pageBe.totalPages-1)==pageBe.page?"class=\"active\"":"")+"><a href=\"javascript:void(0);\" onclick=\"goPage("+(pageBe.totalPages-1)+")\" >"+(pageBe.totalPages-1)+"</a></li><li "+(pageBe.totalPages==pageBe.page?"class=\"active\"":"")+"><a href=\"javascript:void(0);\" onclick=\"goPage("+(pageBe.totalPages)+")\" >"+(pageBe.totalPages)+"</a></li>");
		if(pageBe.hasNextPage==true){
			khtml+="<li class=\"next\"><a href=\"javascript:void(0);\" onclick=\"goPage("+(pageBe.page+1)+")\">下一页  »</a></li>";
		}
		$(".pager").html(khtml);
		return;
	};
	
})(jQuery);