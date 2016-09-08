var banner_index = 2,prv_banner_index=1;
var banner_num ;
var s ;
var delayTime ;
var obj ;
$(document).ready(function(){
	
	$(".banner_ctrl a").hover(function(){
		$(this).fadeTo(100,.5);
		},function(){
		$(this).fadeTo(100,.1);
	});
			
	var tNum=$(".m_banner .banner").length-1;
	var nNum=0;		
	$(".banner_ctrl .prev").click(function(){
		(nNum-1)<0?n2=tNum:n2=nNum-1;
		bSwitch(nNum,n2);
		nNum=n2;
	});	
	$(".banner_ctrl .next").click(function(){
		(nNum+1)>tNum?n2=0:n2=nNum+1;
		bSwitch(nNum,n2);
		nNum=n2;
	});
	function bSwitch(nNum,n2){
		$(".m_banner .banner:eq("+nNum+")").fadeOut();
		$(".m_banner .banner:eq("+n2+")").fadeIn();
	};
							
	$(function(){
		var switchTime;
	 	$(".m_banner").hover(function(){
			clearInterval(switchTime);
		},function(){
		switchTime = setInterval(function(){
			(nNum+1)>tNum?n2=0:n2=nNum+1;
			bSwitch(nNum,n2);
			nNum=n2;
		},8000);
		}).trigger("mouseleave");
	});
	
	$(".notice_box").animate({bottom:0});
	$(".notice_box_t .close").click(function(){
		$(".notice_box").fadeOut();
	});

});

$(document).ready(function(){
	
	$(".banner_ctrl1 a").hover(function(){
		$(this).fadeTo(100,.5);
		},function(){
		$(this).fadeTo(100,.1);
	});
			
	var tNum=$(".m_banner1 .banner").length-1;
	var nNum=0;		
	$(".banner_ctrl1 .prev").click(function(){
		(nNum-1)<0?n2=tNum:n2=nNum-1;
		bSwitch(nNum,n2);
		nNum=n2;
	});	
	$(".banner_ctrl1 .next").click(function(){
		(nNum+1)>tNum?n2=0:n2=nNum+1;
		bSwitch(nNum,n2);
		nNum=n2;
	});
	function bSwitch(nNum,n2){
		$(".m_banner1 .banner:eq("+nNum+")").fadeOut();
		$(".m_banner1 .banner:eq("+n2+")").fadeIn();
	};
							
	$(function(){
		var switchTime;
	 	$(".m_banner1").hover(function(){
			clearInterval(switchTime);
		},function(){
		switchTime = setInterval(function(){
			(nNum+1)>tNum?n2=0:n2=nNum+1;
			bSwitch(nNum,n2);
			nNum=n2;
		},8000);
		}).trigger("mouseleave");
	});
	
	$(".notice_box").animate({bottom:0});
	$(".notice_box_t .close").click(function(){
		$(".notice_box").fadeOut();
	});

});


function delayHide(){
	$(obj).children(".s_nav").removeClass("fade_in");
	if(delayTime)
		clearTimeout(delayTime);
	delayTime = null;
	obj = null;
}

function slide() {
	if (banner_index > banner_num) {banner_index = 1;} 
	$('#c'+banner_index).addClass("curr");
	if(prv_banner_index > 0){
		$('#c'+prv_banner_index).removeClass("curr");
	}
	$('#banner'+banner_index).fadeIn(500);
	if(prv_banner_index > 0){
		$('#banner'+prv_banner_index).hide(); 
	}
	prv_banner_index=banner_index;
	banner_index++;
}
function convert(index){
	if($('#c'+index).hasClass("curr"))
		return ;
	
	if(s != null){
		clearInterval(s);
		s = null ;
	}
	banner_index = index ;
	prv_banner_index = ( prv_banner_index == 0 ? 1 : prv_banner_index );
	$('#c'+banner_index).addClass("curr");
	$('#c'+prv_banner_index).removeClass("curr");
	$('#banner'+banner_index).fadeIn(500);
	$('#banner'+prv_banner_index).hide();
	prv_banner_index=banner_index;banner_index++;
	if(s == null )
		s=setInterval(slide, 8000); 
}
function go(url){
	$("#gform").attr("action",url);
	$("#gform").submit();
}
$(function(){	
		// 导航左侧栏js效果 start
		$(".pullDownList li").hover(function(){
			$(".yMenuListCon").fadeIn();
			var index=$(this).index(".pullDownList li");
			if (!($(this).hasClass("menulihover")||$(this).hasClass("menuliselected"))) {
				$($(".yBannerList")[index]).css("display","block").siblings().css("display","none");
				$($(".yBannerList")[index]).removeClass("ybannerExposure");
				setTimeout(function(){
				$($(".yBannerList")[index]).addClass("ybannerExposure");
				},60)
			}else{	
			}
			$(this).addClass("menulihover").siblings().removeClass("menulihover");
				$(this).addClass("menuliselected").siblings().removeClass("menuliselected");
			$($(".yMenuListConin")[index]).fadeIn().siblings().fadeOut();
		},function(){
			
		})
		$(".pullDown").mouseleave(function(){
			$(".yMenuListCon").fadeOut();
			$(".yMenuListConin").fadeOut();
			$(".pullDownList li").removeClass("menulihover");

		})
		// 导航左侧栏js效果  end
	
	/*
人品爆发页tab选项卡与产品轮播
	*/
	window.onload=function()
	{   
		$(".TTSliderPicList ul").eq(0).addClass("show");	
		$(".TTSliderPicList ul").eq(0).append(" <li></li>");
		var oTTSlider=document.getElementById('TTSlider');
		var oTTSliderTitle=getByClass('TTSliderTitle',oTTSlider,'div')[0];
		var aSubNav=oTTSliderTitle.getElementsByTagName('li');
		var oTTSliderPrevBtn=document.getElementById('TTSliderPrevBtn');
		var oTTSliderNextBtn=document.getElementById('TTSliderNextBtn');
		var oTTSliderPicList=getByClass('TTSliderPicList',oTTSlider,'div')[0];
		var aItem=oTTSliderPicList.getElementsByTagName('ul');
		var len=aItem.length;
		var showIndex=aItemImgWidth=iNow=0;
		var aEle=[];
		
		for(var i=0;i<len;i++)
		{  
			var aItemImgs=aItem[i].getElementsByTagName('li');
			
			if(!aItemImgWidth)
			{
				aItemImgWidth=aItemImgs[0].offsetWidth;
				
			}
			aEle.push(aItemImgs);	//存入数组,考虑到有多个轮播,且每个轮播里面的图片个数可能不一致.
            
			
			aSubNav[i].index=i;
			
			aSubNav[i].onmouseover=function()//切换
			{
				var index=showIndex=this.index;  
				
				for(var j=0;j<len;j++)
				{
					if(j!=index)
					{
							aItem[j].className='';
							aSubNav[j].className='';
					}
				}
				aSubNav[index-1] && (aSubNav[index-1].className='noneBorRight' );
				if(index>0)
				{
					(aSubNav[0].getElementsByTagName('div')[0].style.borderLeft='1px solid #ccc');
				}
				else
				{
					aSubNav[0].getElementsByTagName('div')[0].style.borderLeft='2px solid #ebebeb';
				}
				
				aSubNav[index].className='cur';
				aItem[index].className='show';
			}
			
		}


		for(var i=0;i<len;i++)
		{
			var num=aEle[i].length;
			if(aItem[i].className=='show')
			{
				showIndex=i;
			}
			aItem[i].style.width=num*(aItemImgWidth)+'px'
		}

		oTTSliderNextBtn.onclick=function()
		{
			var maxNum=aEle[showIndex].length-1;
			aItem[showIndex].insertBefore(aEle[showIndex][maxNum],aEle[showIndex][0]);
			aItem[showIndex].style.left=-aItemImgWidth+'px';
			doMove(aItem[showIndex],0);
		}

		oTTSliderPrevBtn.onclick=function()
		{
			doMove(aItem[showIndex],-(aItemImgWidth),function(){
				aItem[showIndex].style.left=0;
				aItem[showIndex].appendChild(aEle[showIndex][0])
			});
		}

		function doMove(o,t,fn)
		{
			clearInterval(o.timer);
			o.timer=setInterval(function(){
				var is= (t-getStyle(o,'left'))/8;
				is= is>0?Math.ceil(is):Math.floor(is);
				if(t==o.offsetLeft)
				{
					clearInterval(o.timer);
					(typeof fn==='function') && fn();
				}
				else
				{
					o.style.left=o.offsetLeft+is+'px';
				}

			},30)
		}

		function getStyle(o,a)
		{
			return o.currentStyle ? parseFloat(o.currentStyle[a]) : parseFloat(getComputedStyle(o,false)[a]);
		}

		function getByClass(s,p,e)
		{
			var reg=new RegExp('(\\b)'+s+'(\\b)');
			var aElement=(p||document).getElementsByTagName(e||'*');
			var aResult=[];
			for(var i=0;i<aElement.length;i++)
			{
				reg.test(aElement[i].className) && aResult.push(aElement[i]);
			}
			return aResult;
		}
		$(".TTSliderPicList ul:first-child li:last-child").remove();
	}
	
$(function(){
	$(".yScrollListTitle h1").eq(0).addClass("yth1click").siblings().addClass("ytitleh12");
	$(".yScrollListInList:first-child").css('display','block');
	$(".yScrollListTitle h1").hover(function  () {
		var index=$(this).index(".yScrollListTitle h1");
		$(this).addClass("yth1click").siblings().removeClass("yth1click");
		$($(".yScrollListInList")[index]).show().siblings().hide();
	})
	$(".yScrollListInList1 ul").css({width:$(".yScrollListInList1 ul li").length*(160+84)+"px"});
	$(".yScrollListInList2 ul").css({width:$(".yScrollListInList2 ul li").length*(160+84)+"px"});
	var numwidth=(160+84)*5;
	$(".yScrollListInList .yScrollListbtnl").click(function(){
		var obj=$(this).parent(".yScrollListInList").find("ul");
		if (!(obj.is(":animated"))) {
			var lefts=parseInt(obj.css("left").slice(0,-2));
			if(lefts<30){
				obj.animate({left:lefts+numwidth},1000);
			}
		}
	})
	$(".yScrollListInList .yScrollListbtnr").click(function(){
		var obj=$(this).parent(".yScrollListInList").find("ul");
		var objcds=-(30+(Math.ceil(obj.find("li").length/5)-2)*numwidth);
		if (!(obj.is(":animated"))) {
			var lefts=parseInt(obj.css("left").slice(0,-2));
			if(lefts>objcds){
				obj.animate({left:lefts-numwidth},1000);
			}
		}
	})
})

//商品分类
$(document).ready(function(){
	//$('.all-sort-list').hide();
	//$('.menu > .all-sort').hover(function(){
	//	 $('.all-sort-list').show();
	//	 },function(){
	//		$('.all-sort-list').hide();
	//});

	//$('.all-sort-list').hover(function(){  
	//        $(this).show();
	//	},function(){
	//		$(this).hide();
	//});
	
    $('.all-sort-list > .item').hover(function(){
			var eq = $('.all-sort-list > .item').index(this),				//获取当前滑过是第几个元素
				h = $('.all-sort-list').offset().top,						//获取当前下拉菜单距离窗口多少像素
				s = $(window).scrollTop(),									//获取游览器滚动了多少高度
				i = $(this).offset().top,									//当前元素滑过距离窗口多少像素
				item = $(this).children('.item-list').height(),				//下拉菜单子类内容容器的高度
				sort = $('.all-sort-list').height();						//父类分类列表容器的高度
			
			if ( item < sort ){												//如果子类的高度小于父类的高度
				if ( eq == 0 ){
					$(this).children('.item-list').css('top', (i-h));
				} else {
					$(this).children('.item-list').css('top', (i-h)+1);
				}
			} else {
				if ( s > h ) {												//判断子类的显示位置，如果滚动的高度大于所有分类列表容器的高度
					if ( i-s > 0 ){											//则 继续判断当前滑过容器的位置 是否有一半超出窗口一半在窗口内显示的Bug,
						$(this).children('.item-list').css('top', (s-h)+2 );
					} else {
						$(this).children('.item-list').css('top', (s-h)-(-(i-s))+2 );
					}
				} else {
					$(this).children('.item-list').css('top', 3 );
				}
			}	
      
			$(this).addClass('hover');
			$(this).children('.item-list').css('display','block');
		},function(){
			$(this).removeClass('hover');
			$(this).children('.item-list').css('display','none');
			//$('.wrap').css('display','none');
		});

		$('.item > .item-list > .close').click(function(){
			$(this).parent().parent().removeClass('hover');
			$(this).parent().hide();
		//	$('.wrap').hide();
		});
	  })
	})
	//城市切换
   $(function(){	
    //<![CDATA[
    ldh={
    get:function (el){
    return typeof el=="string"?document.getElementById(el):el;
    },
    on:function(el,type,fn){
    el=this.get(el);
    el.attachEvent?
    el.attachEvent('on'+type,function(){fn.call(el,event)}):
    el.addEventListener(type,fn,false);
    return this
    },
    align:function (el,el2,x,y){
    var rect=this.get(el2).getBoundingClientRect(),x=x||0,y=y||0;
    el.style.top=rect.top+this.get(el2).offsetHeight+y+'px';
    el.style.left=rect.left+x+'px'
    },
    stop:function (e){
    e=e||window.event;
    e.stopPropagation&&e.stopPropagation();
    e.cancelBubble=true
    },
    onblur:function(el,fn) {
    el=this.get(el);
    this.on(el,'mouseover',function (e){
    ldh.stop()
    }).on(document, 'click',function(e) {
    el.style.display='none';
    });
    return this
    }
    };
    ldh.onblur('csmenu').on('csmenuBtn','mouseover',function (){
    var css=ldh.get('csmenu').style;
    if(css.display!='none')return ;
    ldh.align(ldh.get('csmenu'),this,-10,10);
    css.display= 'block';
    ldh.stop();
    })
    $('#csmenu').mouseleave(function(){			
		$('#csmenu').css('display','none');
	});
   })
