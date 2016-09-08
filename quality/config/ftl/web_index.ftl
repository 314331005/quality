<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "head.ftl"/>
 <script type="text/javascript" src="${r'${pageContext.request.contextPath}'}/javascript/script.js"></script>
<script type="text/javascript">
$(document).ready(function(){ 
	$('.list_lh li:even').addClass('lieven'); 
})
$(function(){
	$("div.list_lh").myScroll({
		speed:40, //数值越大，速度越慢
		rowHeight:68 //li的高度
	});
});
$(document).ready(function(){
	$('.list_lh_sy li:even').addClass('lieven');
})
$(function(){
	$("div.list_lh_sy").myScroll({
		speed:40, //数值越大，速度越慢
		rowHeight:68 //li的高度
	});
});
</script>
<title>网站首页</title>
</head>

<body>
<div id="top">
  <!-- bar   start  -->
     <div class="topNav">
  <div class="topNav-width clearfix">
    <dl class="tnLeft">
      <dd>
        <h3><a target="_blank" href="">长春</a></h3>
      </dd>
      <dd>
        <h3><span id="csmenuBtn">切换城市</span></h3>
          <div class="csnav"></div>  
      </dd>          
    </dl>
         
    <dl class="tnRight">
    <c:choose>
    <c:when test="${r'${empty sessionScope.accountId}'}">

      <dd class="welcome"> 欢迎您，请<a target="_self" href="${r'${pageContext.request.contextPath}'}/website/denglu" title="登录">登录</a></dd>
      <dd>
      <dd class="welcome"> <a target="_self" href="${r'${pageContext.request.contextPath}'}/zhuce/first" title="注册">注册</a></dd>
    </c:when>
    <c:otherwise>
      <dd class="welcome"> 嗨！${r'${sessionScope.name }'}</dd>
      <dd class="welcome"> 积分：<a target="_blank" href="" title="积分">${r'${sessionScope.integral }'}</a>
       <dd>
       <dd class="welcome"><a target="_self" href="${r'${pageContext.request.contextPath}'}/website/logout" title="退出">退出</a></dd>
    </c:otherwise>
    </c:choose>
       <dd>
       <a target="_blank" href="" title="签到" class="top_qd">签到</a></dd>
      <dd>
        <h3><a target="_blank" href="${r'${pageContext.request.contextPath}'}/logistics/order">我的订单<i></i></a></h3>
        <ul>
          <li><a href="">待付款</a></li>
          <li><a href="">待发货</a></li>
          <li><a href="">待收货</a></li>
           <li><a href="">待评价</a></li>
          <li><a href="">退款</a></li>
        </ul>
      </dd>
       <dd>
        <h3><a target="_blank" href="${r'${pageContext.request.contextPath}'}/logistics/wishes">我的收藏<i></i></a></h3>
        <ul>
          <li><a href="">收藏品牌</a></li>
          <li><a href="">收藏商品</a></li>
          
        </ul>
      </dd>
      <dd>
        <h3><a target="_blank" href="">客服<i></i></a></h3>
        <ul>
          <li><a href="">帮助中心</a></li>
          <li><a href="">在线客服</a></li>
          <li><a href="">客服邮箱</a></li>
          <li><a href="">售后服务</a></li>
          <li><a href="">意见建议</a></li>
        </ul>
      </dd>
      <dd>
        <h3><a target="_blank" href="gerenshouye.html">关注<i></i></a></h3>
        <ul>
          <li><a href="">品牌</a></li>
          <li><a href="">商品</a></li>
          <li><a href="">活动</a></li>
        </ul>
      </dd>
      <dd>
        <h3><a target="_blank" href="">手机端<i></i></a></h3>
         <ul>
          <li><a href="">andriod</a></li>
          <li><a href="">ios</a></li>
          <li><a href="">微信</a></li>
        </ul>
      </dd>
    </dl>
  </div>

</div>
 <div id="csmenu" style="display:none;">
    <ul class="csmenu_ul">
    <li><a href="#">北京</a></li>
    <li><a href="#">上海</a></li>
    <li><a href="#">武汉</a></li>
    <li><a href="#">广州</a></li>
    <li><a href="#">深圳</a></li>
    <li class="focus"><a href="#">杭州</a></li>
    <li><a href="#">南京</a></li>
    <li><a href="#">成都</a></li>
    <li><a href="#">天津</a></li>
    <li><a href="#">西安</a></li>
    <li><a href="#">福州</a></li>
    <li><a href="#">重庆</a></li>
    <li><a href="#">厦门</a></li>
    <li><a href="#">青岛</a></li>
    <li><a href="#">大连</a></li>
    </ul>
    <div class="csmenufoot"><a href="city.html">其它城市？</a></div>
    </div> 
<script type="text/javascript">jQuery(".topNav").slide({ type:"menu",  titCell:"dd", targetCell:"ul", delayTime:0,defaultPlay:false,returnDefault:true  });	</script>
     <!-- bar   end  -->
   <div class="logobar">
       <div class="logobar_con">
       <div class="logobar_one">
        <img src="${r'${pageContext.request.contextPath}'}/image/top_logo.jpg" />
       </div>
       <div class="logobar_two">
        <img src="${r'${pageContext.request.contextPath}'}/image/top_yd.jpg" />
       </div>
       <div class="logobar_three">
         
            <div class="search radius6">
                <form name="searchform" method="post" action="/e/search/index.php">
                    <input name='ecmsfrom' type='hidden' value='9'>
                    <input type="hidden" name="show" value="title,newstext">
                    <select name="classid" id="choose">
                        <option value="0">商品</option>
                        <option value="1">品牌</option>
                        <option value="4">活动</option>
                        
                    </select> 
                    <input class="inp_srh" name="keyboard"  placeholder="请输入您要搜索的活动" >
                    <input class="btn_srh" type="submit" name="submit" value="搜索">
                </form>
            </div>
       </div>
     </div>
     </div>
</div>
<!-- 导航   start  -->
		<div class="yNavIndex">
			<div class="menu">
		<div class="all-sort"><h2><a href="">全部商品分类</a></h2></div>		
		<div class="nav">
			<ul class="clearfix">
				<li><a href="${r'${pageContext.request.contextPath}'}" target="_self" class="yMenua">首页</a></li>
				<#list event as et>
				 <li><a href="${r'${pageContext.request.contextPath}'}/website/eventJump/${et.id}" target="_self">${et.name}<span></span></a></li>
				</#list>
				<li><a href="huodong.html" target="_self">活动汇聚</a></li>
				<li><a href="huojiangmingdan.html" target="_self">中奖名单</a></li>
			</ul>
		</div>
	</div>
	<!--导航 End-->

	<!--所有分类 Start-->
	<div class="wrap">
		<div class="all-sort-list">
		  <#list producttype?sort_by(["seqno"]) as pt_one>	
		    <#if pt_one.level=1>		
			  <div class="item">			   
				<h3><span class="item-icon${pt_one.seqno}"></span><a href="">${pt_one.name}</a></h3>
				<div class="item-list clearfix">
					<div class="close">x</div>
					<div class="subitem">
					  <#list producttype?sort_by(["seqno"]) as pt_two>
						    <#if pt_two.level=2 && pt_two.pid=pt_one.id>			  
								<dl class="fore2">
									<dt><a href="#">${pt_two.name}</a></dt>
									<dd>
									 <#list producttype?sort_by(["seqno"]) as pt_three>
						                 <#if pt_three.level=3 && pt_three.pid=pt_two.id>										
										    <em><a href="#">${pt_three.name}</a></em>
										 </#if>
									 </#list>	 
									</dd>
								</dl>
						   </#if>
					  </#list>											
					</div>				
				</div>
			
		     </div>
		   </#if>	
		 </#list>	
		</div>
	</div>
	<!--所有分类 End-->

            <!--中奖名单-->
            <div class="zjmd">              
              <div class="bcon_sy">
		<h1><b>中奖实时播报</b></h1>
		<!-- 代码开始 -->
		<div class="list_lh_sy">
			<ul>
				<li>
					<p><a href="#" target="_blank">1000000</a><em>获得</em></p>
					<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
				</li>
				<li>
					<p><a href="#" target="_blank">2000000</a><em>获得</em></p>
					<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
				</li>
				<li>
					<p><a href="#" target="_blank">3000000</a><em>获得</em></p>
					<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
				</li>
				<li>
					<p><a href="#" target="_blank">4000000</a><em>获得</em></p>
					<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
				</li>
				<li>
					<p><a href="#" target="_blank">5000000</a><em>获得</em></p>
					<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
				</li>
				<li>
					<p><a href="#" target="_blank">6000000</a><em>获得</em></p>
					<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
				</li>
	            <li>
					<p><a href="#" target="_blank">5000000</a><em>获得</em></p>
					<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
				</li>
				<li>
					<p><a href="#" target="_blank">6000000</a><em>获得</em></p>
					<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
				</li>
	            <li>
					<p><a href="#" target="_blank">5000000</a><em>获得</em></p>
					<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
				</li>
				<li>
					<p><a href="#" target="_blank">6000000</a><em>获得</em></p>
					<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
				</li>
	
			
			</ul>
		</div>
		<!-- 代码结束 -->
        </div>
            </div>
		</div>
		<!-- 导航   end  -->
<!--banner start-->
 <div class="m_banner">
    <div class="banner b1" style=" background-image:url(${r'${pageContext.request.contextPath}'}/image/hb_1.jpg);" id="banner1"> </div>
    <div class="banner b2" style=" background-image:url(${r'${pageContext.request.contextPath}'}/image/hb_2.jpg);display:none;" id="banner5"> </div>
    <div class="banner b3" style=" background-image:url(${r'${pageContext.request.contextPath}'}/image/hb_3.jpg);display:none;" id="banner2"></div>
    <div class="banner b4" style=" background-image:url(${r'${pageContext.request.contextPath}'}/image/hb_4.jpg);display:none;" id="banner2"></div>
    
    <div class="banner_ctrl"> <a href="#" class="prev" title=""></a> <a href="javascript:;" class="next" title=""></a> </div>
    
   </div>
 <!--banner end-->  
 <!--laba start-->
 <div class="laba">
   <img src="${r'${pageContext.request.contextPath}'}/image/3.gif"  style="float:left"/>
   <div class="scroll">
   <marquee direction=left style="float:left">零元抢购活动即将上线，参与多多，好礼多多！只有你想不到的，没有我们不敢送的！一切精彩尽在平台！零元抢购活动即将上线，参与多多，好礼多多！</marquee></div>
 </div>
 <!--laba end-->
 <!--huodong start-->
 <div id="ggb">
   <div id="dbRoWrap">
	<div id="dbRo1">
		<a href="moral.html"><img src="${r'${pageContext.request.contextPath}'}/image/bf_white.png" alt="${r'${pageContext.request.contextPath}'}/image/bf_orange.png" /></a>
	</div>
	<div id="dbRo2">
		<a href="#"><img src="${r'${pageContext.request.contextPath}'}/image/xd_white.png" alt="${r'${pageContext.request.contextPath}'}/image/xd_orange.png" /></a>
	</div>
	<div id="dbRo3">
		<a href="#"><img class="dOut" src="${r'${pageContext.request.contextPath}'}/image/sq_white.png" alt="${r'${pageContext.request.contextPath}'}/image/sq_orange.png" /></a>
	</div>
<script type="text/javascript">
	$('#dbRo1').dbRotate2D();
	$('#dbRo2').dbRotate2D();
	$('#dbRo3').dbRotate2D();
	//$('#dbRoWrap div').dbRotate2D({});
</script>
 <div class="ggb_gg">
   <div class="tit"></div>
   <div class="ggb_gg_content">
      <ul>
       <li><a href="#">2016年2月15日，IpadAir抢拍活动
即将开始</a></li>
       <li><a href="#">2016年2月15日，IpadAir抢拍活动
即将开始</a></li>
       <li><a href="#">2016年2月15日，IpadAir抢拍活动
即将开始</a></li>
       <li><a href="#">2016年2月15日，IpadAir抢拍活动
即将开始</a></li>
       <li><a href="#">2016年2月15日，IpadAir抢拍活动
即将开始</a></li>
       <li><a href="#">2016年2月15日，IpadAir抢拍活动
即将开始</a></li>
      </ul>
   </div>
 </div>

 </div>
</div>
 <!--huodong end-->
 <!--hot start-->
 <div class="hot">
   <div class="hot_title">
    <!--<img src="${r'${pageContext.request.contextPath}'}/image/title_left.jpg" /><span>热门活动  Hot Activity</span>-->
     <span>热门活动</span>
     <div class="hot_xh"><b>Hot Activity</b>
     <div class="more"><a href="#">more ></a></div></div>
    
   </div> 
   
   <div class="indexmaindiv" id="indexmaindiv">
	<div class="indexmaindiv1 clearfix" >
		<div class="stylesgoleft" id="goleft"></div>
		<div class="maindiv1 " id="maindiv1">
		<ul id="count1">
			<#list protypesssq as sssq_pro>
				<li>
					<div class="playerdetail">
						<div class="detailimg"><a href="${r'${pageContext.request.contextPath}'}/website/productdetail/findproduct/${sssq_pro.productId}" target="_self"><img src="${r'${pageContext.request.contextPath}'}/${sssq_pro.listPictureUrl}"/></a></div>
						<div class="teanames">${sssq_pro.productName}</div>
						<div class="teadetail">${r'${dics.get("timeQuantum").get("'}${sssq_pro.timeQuantum}${r'")}'}</div>
						<div class="price">
                    	<a class="checkdetail" href="${r'${pageContext.request.contextPath}'}/website/productdetail/findproduct/${sssq_pro.productId}">详情进入</a></div>
					</div>
				</li>
			</#list>
		</ul>
		</div>
		<div class="stylesgoright" id="goright"></div>
	</div>

   </div>
 </div>
 <!--hot end-->
 <!--人品爆发 start-->
 <div class="baofa">
   <div class="hot_title">
     <span>人品爆发</span>
     <div class="hot_xh"> <b> Good Luck</b>
     <div class="more"><a href="#">more ></a></div></div>
    
   </div>
   <div class="film_focus">
	<div class="film_focus_desc">
		<ul class="film_focus_nav">
			<li class="cur"><b class="zhanl"><p><img src="${r'${pageContext.request.contextPath}'}/image/show1_hover.png" />9:00</p></b><span class="zhanl"><p><img src="${r'${pageContext.request.contextPath}'}/image/show1.png" />9:00</p></span></li>
			<li><b class="huod"><p><img src="${r'${pageContext.request.contextPath}'}/image/show1_hover.png" />10:00</p></b><span class="huod"><p><img src="${r'${pageContext.request.contextPath}'}/image/show2.png" />10:00</p></span></li>
			<li><b><p><img src="${r'${pageContext.request.contextPath}'}/image/show1_hover.png" />11:00</p></b><span><p><img src="${r'${pageContext.request.contextPath}'}/image/show1.png" />11:00</p></span></li>
			<li><b><p><img src="${r'${pageContext.request.contextPath}'}/image/show1_hover.png" />12:00</p></b><span><p><img src="${r'${pageContext.request.contextPath}'}/image/show1.png" />12:00</p></span></li>
            <li><b><p><img src="${r'${pageContext.request.contextPath}'}/image/show1_hover.png" />13:00</p></b><span><p><img src="${r'${pageContext.request.contextPath}'}/image/show1.png" />13:00</p></span></li>
		</ul>
        <!--实时中奖名单 start-->
      
		<div class="bcon">
			<h1><b>中奖实时播报</b></h1>
			<!-- 代码开始 -->
			<div class="list_lh">
				<ul>
					<li>
						<p><a href="#" target="_blank">1000000</a><em>获得</em></p>
						<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
					</li>
					<li>
						<p><a href="#" target="_blank">2000000</a><em>获得</em></p>
						<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
					</li>
					<li>
						<p><a href="#" target="_blank">3000000</a><em>获得</em></p>
						<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
					</li>
					<li>
						<p><a href="#" target="_blank">4000000</a><em>获得</em></p>
						<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
					</li>
					<li>
						<p><a href="#" target="_blank">5000000</a><em>获得</em></p>
						<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
					</li>
					<li>
						<p><a href="#" target="_blank">6000000</a><em>获得</em></p>
						<p><a href="#" target="_blank" class="a_blue">战江湖公测豪华礼包</a><span>17:28:05</span></p>
					</li>
		
				
				</ul>
			</div>
			<!-- 代码结束 -->
		</div>
  
	</div>
    
	<div class="film_focus_imgs_wrap">
		<ul class="film_focus_imgs">	
		  <#list 0..4 as count>
		   <li>
             <dl> 
                <#list eventproduct as event_pro>
                  <#if event_pro.timeQuantum=count?string && event_pro.eventName="人品爆发">
	                <dd><a href="#"><div class="mouse_s"><img src="${r'${pageContext.request.contextPath}'}/image/cp.jpg" /><p>${event_pro.productName}</p></div><div class="hover_s"><img src="${r'${pageContext.request.contextPath}'}/image/chse.png" /><p>${event_pro.productName}</p></div></a></dd>
	              </#if>
                </#list>   
              </dl>
            </li> 
          </#list>  
        </ul>
	</div>
 </div>
</div>
<script type="text/javascript">
(function(A){
	A.fn.th_video_focus=function(E){
		
		var G={
			actClass:"cur",
			navContainerClass:".focus_pic_preview",
			focusContainerClass:".focus_pic",
			animTime:300,
			delayTime:5000
		};
		
		if(E){
			A.extend(G,E)
		}
		
		var C=G.actClass, D=G.navContainerClass, B=G.focusContainerClass, F=G.animTime, H=G.delayTime, I=null;
		
		return this.each(function(){
			
			var O=A(this), M=A(D+" li",O), P=A(B+" li",O), L=M.length, K=O.height();
			
			function N(R){
				var V=K*R*-1;
				var U=A(B+" li",O), W=null, T=null;
				for(var S=0;S<=R;S++){
					W=U.eq(S);
					T=W.find('script[type="text/templ"]');
					if(T.length>0){
						W.html(T.html())
					}
				}
				A(B,O).stop().animate({top:V},F,function(){
					var Y=O.find("h3"), X=Y.height();
					Y.height(0).html(A(B+" li").eq(R).find("img").attr("alt")).animate({height:X},0)
				});
				A(D+" li").eq(R).addClass(C).siblings().removeClass(C)
			}
			
			function Q(){
				if(I){
					clearInterval(I)
				}
				I=setInterval(function(){
					var R=A(D+" li."+C).index();
					N((R+1)%L)
				},H)
			}
			
			O.hover(function(){
				if(I){
					clearInterval(I)
				}
			},function(){
				Q()
			});
		
			var J=null;
			
			M.hover(function(){
				
				var R=A(this).index();
				
				if(I){
					clearInterval(I)
				}
				J=setTimeout(function(){
					N(R)
				},400)
			},function(){
				if(J){
					clearTimeout(J)
				}
				Q()
			}).click(function(T){
				var R=A(this).index(), S=P.eq(R).find("a");
				if(document.uniqueID||window.opera){
					S[0].click();
					T.stopPropagation();
					T.preventDefault()
				}
			});
			
			Q()
			
		})
	}
	
})(jQuery);

$(function(){
	
	
	$(".film_focus").th_video_focus({
		navContainerClass:".film_focus_nav",
		focusContainerClass:".film_focus_imgs",
		delayTime:5000
	});
	
});	

</script>
<!--先到先得  start-->
 <div class="baofa">
   <div class="hot_title">
     <span>先到先得 </span>
     <div class="hot_xh"> <b>First Served</b>
     <div class="more"><a href="#">more ></a></div></div>
    
   </div>
   <div class="film_focus1">
	<div class="film_focus_desc1">
		<ul class="film_focus_nav1">
			<li class="cur"><b class="zhanl"><p><img src="${r'${pageContext.request.contextPath}'}/image/show1_hover.png" />9:00</p></b><span class="zhanl"><p><img src="${r'${pageContext.request.contextPath}'}/image/show1.png" />9:00</p></span></li>
			<li><b class="huod"><p><img src="${r'${pageContext.request.contextPath}'}/image/show1_hover.png" /></p>10:00</b><span class="huod"><p><img src="${r'${pageContext.request.contextPath}'}/image/show2.png" />10:00</p></span></li>
			<li><b><p><img src="${r'${pageContext.request.contextPath}'}/image/show1_hover.png" /></p>11:00</b><span><p><img src="${r'${pageContext.request.contextPath}'}/image/show1.png" />11:00</p></span></li>
			<li><b><p><img src="${r'${pageContext.request.contextPath}'}/image/show1_hover.png" /></p>12:00</b><span><p><img src="${r'${pageContext.request.contextPath}'}/image/show1.png" />12:00</p></span></li>
            <li><b><p><img src="${r'${pageContext.request.contextPath}'}/image/show1_hover.png" /></p>13:00</b><span><p><img src="${r'${pageContext.request.contextPath}'}/image/show1.png" />13:00</p></span></li>
		</ul>
        <!--轮播广告 start-->
       <div class="baofa_md">
           
    <!--Luara图片切换骨架begin-->
    <div class="example">
        <ul>
            <li><img src="${r'${pageContext.request.contextPath}'}/image/jy_1.jpg" alt="1"/></li>
            <li><img src="${r'${pageContext.request.contextPath}'}/image/jy_2.jpg" alt="2"/></li>
            <li><img src="${r'${pageContext.request.contextPath}'}/image/jy_1.jpg" alt="3"/></li>
            <li><img src="${r'${pageContext.request.contextPath}'}/image/jy_2.jpg" alt="4"/></li>
        </ul>
        <ol>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ol>
    </div>
    <!--Luara图片切换骨架end-->
    <script>
    $(function(){
        <!--调用Luara示例-->
        $(".example").luara({width:"192",height:"222",interval:4000,selected:"seleted"});

    });
    </script>
       </div>
	</div>
    
	<div class="film_focus_imgs_wrap1">
		<ul class="film_focus_imgs1">
		  <#list 0..4 as count>		  
		    <li>
             <dl> 
                <#list eventproduct as event_pro>
                  <#if event_pro.timeQuantum=count?string && event_pro.eventName="先到先得">
	                <dd><a href="#"><div class="mouse_s"><img src="${r'${pageContext.request.contextPath}'}/image/cp.jpg" /><p>${event_pro.productName}</p></div><div class="hover_s"><img src="${r'${pageContext.request.contextPath}'}/image/chse.png" /><p>${event_pro.productName}</p></div></a></dd>
	              </#if>
                </#list>   
              </dl>
            </li> 
		  </#list> 	
		</ul>
	</div>    
</div>
<script type="text/javascript">
(function(A){
	A.fn.th_video_focus=function(E){
		
		var G={
			actClass:"cur",
			navContainerClass:".focus_pic_preview1",
			focusContainerClass:".focus_pic1",
			animTime:300,
			delayTime:5000
		};
		
		if(E){
			A.extend(G,E)
		}
		
		var C=G.actClass, D=G.navContainerClass, B=G.focusContainerClass, F=G.animTime, H=G.delayTime, I=null;
		
		return this.each(function(){
			
			var O=A(this), M=A(D+" li",O), P=A(B+" li",O), L=M.length, K=O.height();
			
			function N(R){
				var V=K*R*-1;
				var U=A(B+" li",O), W=null, T=null;
				for(var S=0;S<=R;S++){
					W=U.eq(S);
					T=W.find('script[type="text/templ"]');
					if(T.length>0){
						W.html(T.html())
					}
				}
				A(B,O).stop().animate({top:V},F,function(){
					var Y=O.find("h3"), X=Y.height();
					Y.height(0).html(A(B+" li").eq(R).find("img").attr("alt")).animate({height:X},0)
				});
				A(D+" li").eq(R).addClass(C).siblings().removeClass(C)
			}
			
			function Q(){
				if(I){
					clearInterval(I)
				}
				I=setInterval(function(){
					var R=A(D+" li."+C).index();
					N((R+1)%L)
				},H)
			}
			
			O.hover(function(){
				if(I){
					clearInterval(I)
				}
			},function(){
				Q()
			});
		
			var J=null;
			
			M.hover(function(){
				
				var R=A(this).index();
				
				if(I){
					clearInterval(I)
				}
				J=setTimeout(function(){
					N(R)
				},400)
			},function(){
				if(J){
					clearTimeout(J)
				}
				Q()
			}).click(function(T){
				var R=A(this).index(), S=P.eq(R).find("a");
				if(document.uniqueID||window.opera){
					S[0].click();
					T.stopPropagation();
					T.preventDefault()
				}
			});
			
			Q()
			
		})
	}
	
})(jQuery);

$(function(){
	
	
	$(".film_focus1").th_video_focus({
		navContainerClass:".film_focus_nav1",
		focusContainerClass:".film_focus_imgs1",
		delayTime:5000
	});
	
});	

</script>
  <!--先到先得 end-->
  </div>
  <!--试试手气 start-->
  <div class="sssq">
     <div class="hot_title">
       <span>试试手气 </span>
       <div class="hot_xh"><b> Try</b>
       <div class="more"><a href="#">more ></a></div></div>  
     </div>
     <div class="sssq_time">
           <div class="sssq_time_left">
                <div class="time-item">
                    <span id="day_show">0天</span>
                    <strong id="hour_show">0时</strong>
                    <strong id="minute_show">0分</strong>
                    <strong id="second_show">0秒</strong>
                </div><!--倒计时模块-->

           </div>
           <div class="sssq_time_right">
               <img src="${r'${pageContext.request.contextPath}'}/image/3.gif"  style="float:left"/>
   <div class="scroll">
   <marquee direction=left style="float:left">零元抢购活动即将上线，参与多多，好礼多多！只有你想不到的，没有我们不敢送的！一切精彩尽在平台！</marquee></div>
           </div>
     </div>
     <div class="sssq_cp">
       <ul>
        
          <#list protypesssq as sssq_pro>
       
           <#if sssq_pro_index<6 >
	          <li>
	            <div>
	              <a href="#"><img src="${r'${pageContext.request.contextPath}'}/image/sssq_cp.jpg" /></a>
	              <p class="sssq_kc">${sssq_pro.productName}</p>
	              <p class="sssq_btn"><a class="sssq_cp_btn" href="#">立即参与</a></p>
	            </div>
	          </li>
           </#if>
         </#list>
       </ul>
     </div>
  </div>
  <!--试试手气  end-->
  <!--小编推荐  start-->
  <div class="xbtj">     
       <div class="xbtj_left">
         <div class="hot_title">
           <span>小编推荐  </span>
            <div class="hot_xhx"><b>Recommend</b>
           <div class="more"><a href="#">more>></a></div></div>
         </div>
     <div class="Div1">

    <b class="Div1_prev Div1_prev1" ><img src="${r'${pageContext.request.contextPath}'}/image/lizi_img011.jpg" title="上一页" /></b>
    <b class="Div1_next Div1_next1" ><img src="${r'${pageContext.request.contextPath}'}/image/lizi_img012.jpg"  title="下一页"/></b>
    <div class="Div1_main">
    	<div>
        	<span class="Div1_main_span1">
            	<a href="#" class="Div1_main_a1"><img src="${r'${pageContext.request.contextPath}'}/image/lizi_img007.jpg" /></a>

     
                <a href="#" class="Div1_main_a2">+详情点击</a>
            </span>
            <span>
            	<a href="#" class="Div1_main_a1"><img src="${r'${pageContext.request.contextPath}'}/image/lizi_img007.jpg" /></a>
 
    
                <a href="#" class="Div1_main_a2">+详情点击</a>
            </span>
            <span>
            	<a href="#" class="Div1_main_a1"><img src="${r'${pageContext.request.contextPath}'}/image/lizi_img007.jpg" /></a>

          
                <a href="#" class="Div1_main_a2">+详情点击</a>
            </span>
            <span>
            	<a href="#" class="Div1_main_a1"><img src="${r'${pageContext.request.contextPath}'}/image/lizi_img007.jpg" /></a>
  
        
                <a href="#" class="Div1_main_a2">+详情点击</a>
            </span>
        </div>
        <div>
        	<span>
            	<a href="#" class="Div1_main_a1"><img src="${r'${pageContext.request.contextPath}'}/image/lizi_img007.jpg" /></a>

              
                <a href="#" class="Div1_main_a2">+详情点击</a>
            </span>
            <span>
            	<a href="#" class="Div1_main_a1"><img src="${r'${pageContext.request.contextPath}'}/image/lizi_img007.jpg" /></a>
      
  
                <a href="#" class="Div1_main_a2">+详情点击</a>
            </span>
            <span>
            	<a href="#" class="Div1_main_a1"><img src="${r'${pageContext.request.contextPath}'}/image/lizi_img007.jpg" /></a>

                <a href="#" class="Div1_main_a2">+详情点击</a>
            </span>
      
        </div>
    </div>
</div>
      </div>
      <div class="xbtj_right">
            <!--Luara图片切换骨架begin-->
    <div class="example2">
        <ul>
            <li><img src="${r'${pageContext.request.contextPath}'}/image/le_1.jpg" alt="1"/></li>
            <li><img src="${r'${pageContext.request.contextPath}'}/image/le_2.jpg" alt="2"/></li>
            <li><img src="${r'${pageContext.request.contextPath}'}/image/le_1.jpg" alt="3"/></li>
            <li><img src="${r'${pageContext.request.contextPath}'}/image/le_2.jpg" alt="4"/></li>
        </ul>
        <ol>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ol>
    </div>
    <!--Luara图片切换骨架end-->
    <script>
        $(function(){
            <!--调用Luara示例-->
            $(".example2").luara({width:"180",height:"260",interval:4500,selected:"seleted",deriction:"left"});

        });
    </script>
      </div>
  </div>  
  
  <!--小编推荐  end-->
  <!--footer  start-->
  <div class="footer">
    <div class="footer_1">
      <div class="footer_ts">
       <img src="${r'${pageContext.request.contextPath}'}/image/foot_ts.jpg" />
      </div>
    </div>
    <div class="footer_2">
      <div class="footer_2_fast">
        <ul>
          <li>
           <b>购物指南</b>
           <a href="#">购物流程</a>
           <a href="#">会员介绍</a>
           <a href="#">最新活动</a>
           <a href="#">常见问题</a>
           <a href="#">联系客服</a>
          </li>
          <li>
            <b>配送方式</b>
            <a href="#">上门自提</a>
               <a href="#">211限时达</a>
               <a href="#">配送服务查询</a>
               <a href="#">配送费收取标准</a>
               <a href="#">海外配送</a>
          </li>
          <li>
             <b>售后服务</b>
               <a href="#">售后政策</a>
               <a href="#">价格保护</a>
               <a href="#">退款说明</a>
               <a href="#">返修/退换货</a>
               <a href="#">取消订单</a>
          </li>
          <li>
            <b>特色服务</b>
             <a href="#">确保正品</a>
               <a href="#">DIY装机</a>
               <a href="#">延保服务</a>
            
          </li>
        </ul>      
      </div>
    </div>
    <div class="footer_3">
      <div class="footer_3_con">
      <a href="#">关于我们</a>|<a href="#">测试链接</a>|<a href="#">测试链接</a>|<a href="#">测试链接</a>|<a href="#">测试链接</a>|<a href="#">测试链接</a>|<a href="#">测试链接</a>|<a href="#">测试链接</a>
    </div>
   </div>
   <div class="footer_4">
     <p>Copyright © 2014-2017 吉林省佳硕科技有限责任公司版权所有，并保留所有权利</p>
<p>长春市高新区飞跃路与蔚山路交汇 东北亚文化创意科技园 D栋 Tel:0431-88888888 E-mail:js@js.com  </p>
<p>电信业务经营许可证：吉Bxx-20150020 吉ICP备14000666号-6</p>
   </div>
   <div class="footer_5">
    <a href="#"><img src="${r'${pageContext.request.contextPath}'}/image/bot_1.png" /></a>
    <a href="#"><img src="${r'${pageContext.request.contextPath}'}/image/bot_2.png" /></a>
    <a href="#"><img src="${r'${pageContext.request.contextPath}'}/image/bot_3.png" /></a>
    <a href="#"><img src="${r'${pageContext.request.contextPath}'}/image/bot_4.png" /></a>
    <a href="#"><img src="${r'${pageContext.request.contextPath}'}/image/bot_5.jpg" /></a>
    <a href="#"><img src="${r'${pageContext.request.contextPath}'}/image/bot_6.jpg" /></a>
   </div>
  </div>
   <!--footer  end-->
      <!--right_nav  start-->
<div class="tb-toolbar">
   <div class="tb-toolbar-space" style="height: 3%;"></div>
   <ul class="tb-toolbar-list tb-toolbar-list-with-worthbuying tb-toolbar-list-with-cart tb-toolbar-list-with-history tb-toolbar-list-with-coupon">
       <li class="tb-toolbar-item tb-toolbar-history">
         <div class="tb-toolbar-item-bd tb-toolbar-loading"> 
         </div>
	</li>
	<li class="tb-toolbar-item-split"></li>

    <li class="tb-toolbar-item tb-toolbar-item-worthbuying" data-item="worthbuying"> <a href="#" class="tb-toolbar-item-hd"> 
      <div class="tb-toolbar-item-icon">
       
      </div>
      <div class="tb-toolbar-item-tipa">
       <iframe style="border:solid 1px #7ec8ea" name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=111" width="977" height="100" frameborder="no" marginwidth="0" marginheight="0" scrolling="no"></iframe>
       <div class="tb-toolbar-item-arrowa">
        ◆
       </div>
      </div> </a> 
     <div class="tb-toolbar-item-bd"></div>
	</li>
    <li class="tb-toolbar-item-split"></li>
    <li class="tb-toolbar-item tb-toolbar-item-cart" data-item="cart"> <a href="#" class="tb-toolbar-item-hd tb-toolbar-item-hd-cart J_TBToolbarCart"> 
      <div class="tb-toolbar-item-icon tb-toolbar-item-icon-cart">
       
      </div> 
      <div class="tb-toolbar-item-label tb-toolbar-item-label-cart">
       积分
      </div> 
      <div class="J_ToolbarCartNum tb-toolbar-item-badge-cart">
       0
      </div> 
      <div class="tb-toolbar-item-tip">
       我的积分
       <div class="tb-toolbar-item-arrow">
        ◆
       </div>
      </div> </a> 
     <div class="tb-toolbar-item-bd tb-toolbar-mini-cart tb-toolbar-loading"> 
     </div>
	</li>
    <li class="tb-toolbar-item-split"></li>
    <li class="tb-toolbar-item tb-toolbar-history" data-item="history"> <a href="#" class="tb-toolbar-item-hd"> 
      <div class="tb-toolbar-item-icon">
       
      </div> 
      <div class="tb-toolbar-item-tip">
       我的足迹
       <div class="tb-toolbar-item-arrow">
        ◆
       </div>
      </div> </a> 
     <div class="tb-toolbar-item-bd tb-toolbar-loading"> 
     </div>
	</li>
    <li class="tb-toolbar-item-split"></li>
    <li class="tb-toolbar-item tb-toolbar-item-coupon" data-item="coupon"> <a href="#" class="tb-toolbar-item-hd tb-toolbar-item-hd-coupon"> 
      <div class="tb-toolbar-item-icon">
       
      </div> 
      <div class="tb-toolbar-item-tip tb-toolbar-item-tip-coupon">
       推荐活动
       <div class="tb-toolbar-item-arrow">
        ◆
       </div>
      </div> </a> 
     <div class="tb-toolbar-item-hd-extra"> 
      <div class="tb-toolbar-item-bubble tb-toolbar-item-bubble-coupon J_TBToolbarBubbleCoupon">
       <span class="tb-toolbar-item-arrow">◆</span>
       <span class="tb-toolbar-item-bubble-saw"></span>
      </div> 
     </div> 
     <div class="tb-toolbar-item-bd tb-toolbar-item-bd-coupon tb-toolbar-loading"></div>
	</li>
   </ul>
   <div class="tb-toolbar-space" style="height: 7%;"></div>
   <ul class="tb-toolbar-list tb-toolbar-list-with-feedback tb-toolbar-list-with-gotop">
    <li class="tb-toolbar-item" data-item="feedback"> 
	</li>
    <li class="tb-toolbar-item" data-item="gotop"> <a href="#" class="tb-toolbar-item-hd"> 
      <div class="tb-toolbar-item-icon">
       
      </div> 
      <div class="tb-toolbar-item-tip">
       <span class="tb-toolbar-item-tip-text">顶部</span>
       <div class="tb-toolbar-item-arrow">
        ◆
       </div>
      </div> </a>
	</li>
   </ul>
  </div>
</div>
<!--right_nav  end-->
 <script type="text/javascript">

</script>
 <!--倒计时-->
<script type="text/javascript">

var intDiff = parseInt(1000);//倒计时总秒数量

function timer(intDiff){
	window.setInterval(function(){
	var day=0,
		hour=0,
		minute=0,
		second=0;//时间默认值		
	if(intDiff > 0){
		day = Math.floor(intDiff / (60 * 60 * 24));
		hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
		minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
		second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
	}
	if (minute <= 9) minute = '0' + minute;
	if (second <= 9) second = '0' + second;
	$('#day_show').html(day+"天");
	$('#hour_show').html('<s id="h"></s>'+hour+'时');
	$('#minute_show').html('<s></s>'+minute+'分');
	$('#second_show').html('<s></s>'+second+'秒');
	intDiff--;
	}, 1000);
} 

$(function(){
	timer(intDiff);
});	
</script>
<script type="text/javascript" src="${r'${pageContext.request.contextPath}'}/javascript/jquery.select.js"></script>

</body>
</html>
