<template>
	<view>
		
		<view class="user_info_wrap">
		  <view v-if="userinfo.avatarUrl" class="user_img_wrap">
		    <image class="user_bg" mode="widthFix" :src="userinfo.avatarUrl" ></image>
		    <view class="user_info">
		      <image class="user_icon" :src="userinfo.avatarUrl"></image>
		      <view class="user_name">{{userinfo.nickName}}</view>
		    </view>
		  </view>
		  <view v-else class="user_btn" @click="handleGetUserInfo">
		    登录
		  </view>
		</view>
		<view class="user_content">
		  <view class="user_main">
		    <!-- 历史足迹 -->
		    <view class="history_wrap">
		      <navigator>
		        <view class="his_num">0</view>
		        <view class="his_name">收藏的店铺</view>
		      </navigator>
		      <navigator url="/pages/collect/collect">
		        <view class="his_num">{{collectNums}}</view>
		        <view class="his_name">收藏的商品</view>
		      </navigator>
		      <navigator>
		        <view class="his_num">0</view>
		        <view class="his_name">关注的商品</view>
		      </navigator>
		      <navigator>
		        <view class="his_num">0</view>
		        <view class="his_name">我的足迹</view>
		      </navigator>
		    </view>
		    <!-- 我的订单 -->
		    <view class="orders_wrap">
		      <view class="orders_title">我的订单</view>
		      <view class="order_content">
		        <navigator url="/pages/order/index?type=1">
		          <view class="iconfont icon-ding_dan"></view>
		          <view class="order_name">全部订单</view>
		        </navigator>
		        <navigator url="/pages/order/index?type=2">
		          <view class="iconfont icon-fukuantongzhi"></view>
		          <view class="order_name">待付款</view>
		        </navigator>
		        <navigator url="/pages/order/index?type=3">
		          <view class="iconfont icon-receipt-address"></view>
		          <view class="order_name">待收货</view>
		        </navigator>
		        <navigator>
		          <view class="iconfont icon-tuihuotuikuan_dianpu"></view>
		          <view class="order_name">退款/退货</view>
		        </navigator>
		      </view>
		    </view>
		    <!-- 收货地址管理 -->
		    <view class="address_wrap">
		      收货地址管理
		    </view>
		    <!-- 应用信息相关 -->
		    <view class="app_info_wrap">
		      <view class="app_info_item app_info_contact">
		        <text>联系客服</text>
		        <text>400-618-4000</text>
		      </view>
		      <navigator url="/pages/feedback/feedback" class="app_info_item">意见反馈</navigator>
		      <view class="app_info_item">关于我们</view>
		    </view>
		    <!-- 推荐 -->
		    <view class="recommend_wrap">
		      把应用推荐给其他人
		    </view>
		  </view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userinfo:{},
				// 被收藏的商品的数量
				collectNums:0
			}
		},
		methods: {
			async handleGetUserInfo(){
				const res=await uni.getUserProfile({desc: '获取用户详细信息'})
				console.log(res);
				this.userinfo=res[1].userInfo;
				uni.setStorageSync("userinfo",this.userinfo);
			}
		},
		onShow(){
			this.userinfo=uni.getStorageSync("userinfo");
			const collect=uni.getStorageSync("collect")||[];
			this.collectNums=collect.length;
				
		}
	}
</script>

<style>
@import url("./me.css");
</style>
