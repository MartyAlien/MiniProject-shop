<template>
	<view>
		<!-- 收货地址 -->
		<view class="revice_address_row">
			<view class="user_info_row">
				<view class="user_info">
					<view>{{address.userName}}</view>
					<view>{{address.all}}</view>
				</view>
				<view class="user_phone">{{address.telNumber}}</view>
			</view>
		</view>

		<!-- 购物车内容 -->
		<view class="cart_content">
			<view class="cart_title">购物车</view>
			<view class="cart_main">
				<view class="cart_item" v-for="(item,index) in cart" :key="item.goods_id">
					<!-- 商品图片 -->
					<navigator class="cart_img_wrap">
						<image mode="widthFix" :src="item.goods_small_logo"></image>
					</navigator>
					<!-- 商品信息 -->
					<view class="cart_info_wrap">
						<view class="goods_name">{{item.goods_name}}</view>
						<view class="goods_price_wrap">
							<view class="goods_price">￥{{item.goods_price}}</view>
							<view class="cart_num_tool">
								<view class="goods_num">X {{item.num}}</view>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>

		<!-- 底部工具栏 -->
		<view class="footer_tool">
			<!-- 总价格 -->
			<view class="total_price_wrap">
				<view class="total_price">
					合计: <text class="total_price_text">￥{{totalPrice}}</text>
				</view>
				<view>包含运费</view>
			</view>
			<view class="order_pay_wrap" @click="handleOrderPay">
				支付({{totalNum}})
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				address: {},
				cart: [],
				totalPrice: 0,
				totalNum: 0
			}
		},
		methods: {
			// 点击 支付 
			async handleOrderPay() {
				try {
					// 1 判断缓存中有没有token 
					const token = uni.getStorageSync("token");
					// 2 判断
					if (!token) {
						uni.navigateTo({
							url: '/pages/auth/auth'
						});
						return;
					}
					// 3 创建订单
					// 3.1 准备 请求头参数
					// const header = { Authorization: token };
					// 3.2 准备 请求体参数
					const order_price = this.$data.totalPrice;
					const consignee_addr = this.$data.address.all;
					const cart = this.$data.cart;
					let goods = [];
					cart.forEach(v => goods.push({
						goods_id: v.goods_id,
						goods_number: v.num,
						goods_price: v.goods_price
					}))
					const orderParams = {
						order_price,
						consignee_addr,
						goods
					};
					console.log("订单信息",orderParams);
					// 4 准备发送请求 创建订单 获取订单编号
					// 5 发起 预支付接口
					/* const { pay } = await this.$api({
						url: "/my/orders/req_unifiedorder",
						method: "POST",
						data: { order_number }
					});
					// 6 发起微信支付 
					await uni.requestPayment(pay);
					// 7 查询后台 订单状态
					const res = await this.$api({
						url: "/my/orders/chkOrder",
						method: "POST",
						data: { order_number }
					});*/
					await uni.showToast({ title: "支付成功" });
					// 8 手动删除缓存中 已经支付了的商品
					let newCart = uni.getStorageSync("cart");
					newCart = newCart.filter(v => !v.checked);
					uni.setStorageSync("cart", newCart);

					// 8 支付成功了 跳转到订单页面
					uni.switchTab({
						url: '/pages/cart/cart',
					});	
				} catch (error) {
					await showToast({ title: "支付失败" })
					console.log(error);
				}
			}

		},
		onShow() {
			// 1 获取缓存中的收货地址信息
			this.address = uni.getStorageSync("address");
			// 1 获取缓存中的购物车数据
			let cart = uni.getStorageSync("cart") || [];
			// 过滤后的购物车数组
			cart = cart.filter(v => v.checked);

			// 1 总价格 总数量
			let totalPrice = 0;
			let totalNum = 0;
			cart.forEach(v => {
				totalPrice += v.num * v.goods_price;
				totalNum += v.num;
			})
			this.totalNum = totalNum;
			this.totalPrice = totalPrice;
			this.cart = cart;
		},
	}
</script>

<style>
	@import url("./pay.css");
</style>
