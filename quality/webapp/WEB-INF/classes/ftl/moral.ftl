<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "head.ftl"/>
<link href="${r'${pageContext.request.contextPath}'}/css/moral.css" type="text/css" rel="stylesheet"/>

<title>人品爆发</title> 
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
    </c:otherwise>
    </c:choose>
       <dd class="welcome"> 积分：<a target="_blank" href="" title="积分">${r'${sessionScope.integral }'}</a>
       <a target="_blank" href="" title="签到" class="top_qd">签到</a></dd>
      <dd>
      <dd>
        <h3><a target="_blank" href="myorder.html">我的订单<i></i></a></h3>
        <ul>
          <li><a href="">待付款</a></li>
          <li><a href="">待发货</a></li>
          <li><a href="">待收货</a></li>
           <li><a href="">待评价</a></li>
          <li><a href="">退款</a></li>
        </ul>
      </dd>
       <dd>
        <h3><a target="_blank" href="gerenshouye.html">我的收藏<i></i></a></h3>
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
				<li><a href="${r'${pageContext.request.contextPath}'}/turnplate.jsp" target="_self">幸运转盘</a></li>
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
 </div>
  <!--banner start-->
<div class="m_banner1">
    <div class="banner b1" style=" background-image:url(${r'${pageContext.request.contextPath}'}/image/hb_22.jpg);" id="banner1"> </div>
  <div class="banner b2" style=" background-image:url(${r'${pageContext.request.contextPath}'}/image/hb_2.jpg);display:none;" id="banner5"> </div>    
   
  <div class="banner_ctrl1"> <a href="#" class="prev" title=""></a> <a href="javascript:;" class="next" title=""></a> </div>
  </div>
 <!--banner end--> 
 <div class="moral_con">
   <c:forEach var="event" items="${r'${eventList}'}">
    <c:if test="${r'${event.id eq evId}'}">
     <div class="moral_con_title">
         <div class="hot_title">
           <span>${r'${event.name}'}</span>
           <div class="hot_xh">            
           </div>      
        </div>
     </div>
     <div class='TTSlider' id='TTSlider'>
            <div  class='TTSliderTitle'>
                 <ul>
                    <li class='cur'><div><a href=''><img  src="${r'${pageContext.request.contextPath}'}/image/show1_hover_1.png"/></a><a href=''>9:00</a></div></li>
                    <li><div><a href=''><img  src="${r'${pageContext.request.contextPath}'}/image/show1_hover_1.png"/></a><a href=''>10:00</a></div></li>
                    <li><div><a href=''><img  src="${r'${pageContext.request.contextPath}'}/image/show1_hover_1.png"/></a><a href=''>11:00</a></div></li>
                    <li><div><a href=''><img  src="${r'${pageContext.request.contextPath}'}/image/show1_hover_1.png"/></a><a href=''>12:00</a></div></li>
                    <li><div><a href=''><img  src="${r'${pageContext.request.contextPath}'}/image/show1_hover_1.png"/></a><a href=''>13:00</a></div></li>
                 </ul>
             </div>
             <div class='TTSliderPic'>
                <div href='javascript:;' id='TTSliderPrevBtn' class='TTSliderPrevBtn'><a href='javascript:;'></a></div>
                <div href='javascript:;' id='TTSliderNextBtn' class='TTSliderNextBtn'><a href='javascript:;'></a></div>
                <div class='TTSliderPicList'>
                   <c:forEach var="eventTime" items="${r'${eventTimeList}'}">
                     <ul>
                        <c:forEach var="eventPro" items="${r'${eventProList}'}">
                         <c:if test="${r'${eventPro.eventId eq evId && eventPro.timeQuantum eq eventTime.no}'}">
	                        <li>
	                          <div class="bf_lb">
	                            <a href=''><img src='${r'${pageContext.request.contextPath}/${eventPro.listPictureUrl}'}'/></a><p>${r'${eventPro.productName}'}</p> <p class="rpbf_btn"><a class="rpbf_cp_btn" href="#">点击开抢</a></p>
	                          </div>	                                    
	                        </li>
	                      </c:if>
                        </c:forEach>
                     </ul>
                   </c:forEach>
                </div>
             </div>
        </div>
    </c:if>
   </c:forEach>
 </div>
   <div class="rp_ggt">
     <img src="${r'${pageContext.request.contextPath}'}/image/rp_ggt.jpg" />
   </div>
 <div> 
    <div class="moral_con_title">
         <div class="hot_title">
           <span>剩余精彩抢位</span>    
           <div class="hot_xh1"> 
            <b>Remaining Position</b>           
           </div>    
        </div>
     </div>
     
     <div class="yScrollList">
		<div class="yScrollListTitle">
			<c:forEach var="ptoType" items="${r'${proTypeList}'}">
				<h1>${r'${ptoType.name}'}</h1>
			</c:forEach>
		</div>
	<div class="yScrollListIn">
	  <c:forEach var="ptoType"  varStatus="status" items="${r'${proTypeList}'}">
		<div class="yScrollListInList yScrollListInList${r'${status.index+1}'}">
			<ul>
			  <c:forEach var="eventPro" items="${r'${eventProList}'}">
	  		  <c:if test="${r'${ptoType.code eq eventPro.firlevelTycode && eventPro.balance>0}'}">
				<li>
					<a href="">
						<img src="${r'${pageContext.request.contextPath}'}/image/inimg.jpg">
						<p>${r'${eventPro.productName}'}</p><p> ${r'${eventPro.balance}'}/${r'${eventPro.amount}'}</p>
						<a class="rpbf_btn1" href="">
                        点击开抢</a>
					</a>
				</li>
			  </c:if>
			  </c:forEach>
			</ul>
			<div class="yScrollListbtn yScrollListbtnl"></div>
			<div class="yScrollListbtn yScrollListbtnr"></div>
		  </div>
		</c:forEach>
		
	</div>
</div>
</div>

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
<script type="text/javascript" src="${r'${pageContext.request.contextPath}'}/javascript/jquery.select.js"></script>
</body>
</html>
