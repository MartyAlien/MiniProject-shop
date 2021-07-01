<template>
	<view>
		<!-- 收货地址 -->
		<view class="revice_address_row">
			<!-- 当收货地址 不存在 按钮显示  对象 空对象 bool类型也是true  -->
			<view class="address_btn" v-if="!address.userName">
				<button @click="handleChooseAddress" type="primary" plain>获取收货地址</button>
			</view>
			<!-- 当收货地址 存在 详细信息就显示 -->
			<view v-else class="user_info_row">
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
				<!-- 当cart数组 长度不为0 显示 商品信息 -->
				<block v-if="cart.length!==0">
					<view class="cart_item" v-for="(item,index) in cart" :key="item.goods_id">
						<!-- 复选框 -->
						<view class="cart_chk_wrap">
							<checkbox-group @click="handeItemChange(item.goods_id)">
								<checkbox :checked="item.checked"></checkbox>
							</checkbox-group>
						</view>
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
									<view @click="handleItemNumEdit({id:item.goods_id,operation:-1})" class="num_edit">-</view>
									<view class="goods_num">{{item.num}}</view>
									<view @click="handleItemNumEdit({id:item.goods_id,operation:1})" class="num_edit">+</view>
								</view>
							</view>
						</view>
					</view>
				</block>
				<block v-else>
					<image mode="widthFix"
						src="http://hbimg.b0.upaiyun.com/e1b1467beea0a9c7d6a56b32bac6d7e5dcd914f7c3e6-YTwUd6_fw658">
					</image>
				</block>
			</view>
		</view>

		<!-- 底部工具栏 -->
		<view class="footer_tool">
			<!-- 全选 -->
			<view class="all_chk_wrap">
				<checkbox-group @click="handleItemAllCheck">
					<checkbox :checked="allChecked">全选</checkbox>
				</checkbox-group>
			</view>
			<!-- 总价格 -->
			<view class="total_price_wrap">
				<view class="total_price">
					合计: <text class="total_price_text">￥{{totalPrice | priceFilter}}</text>
				</view>
				<view>包含运费</view>
			</view>
			<!-- 结算 -->
			<view class="order_pay_wrap" @click="handlePay">
				结算({{totalNum}})
			</view>
		</view>
	</view>
</template>

<script>
	import {
		getSetting,
		chooseAddress,
		openSetting,
		showModal,
		showToast
	} from "../../utils/asyncWx.js";
	export default {
		data() {
			return {
				address: {},
				cart: [],
				allChecked: false,
				totalPrice: 0,
				totalNum: 0
			}
		},
		methods: {
			async handleChooseAddress() {
				try {
					// 4 调用获取收货地址的 api
					let address = await uni.chooseAddress();
					this.address = address[1];
					this.address.all = address[1].provinceName + address[1].cityName + address[1].countyName + address[
						1].detailInfo;
					// 5 存入到缓存中
					uni.setStorageSync("address", this.address);
					console.log(this.address);
				} catch (error) {
					console.log(error);
				}
			},
			setCart(cart) {
				let allChecked = true;
				// 1 总价格 总数量
				let totalPrice = 0;
				let totalNum = 0;
				cart.forEach(v => {
					if (v.checked) {
						totalPrice += v.num * v.goods_price;
						totalNum += v.num;
					} else {
						allChecked = false;
					}
				})
				// 判断数组是否为空
				allChecked = cart.length != 0 ? allChecked : false;
				this.cart = cart;
				this.totalPrice = totalPrice;
				this.totalNum = totalNum;
				this.allChecked = allChecked;
				uni.setStorageSync("cart", cart);
			},
			 // 商品的选中
			handeItemChange(goods_id) {
				// 2 获取购物车数组 
				let { cart } = this.$data;
				// 3 找到被修改的商品对象
				let index = cart.findIndex(v => v.goods_id === goods_id);
				// 4 选中状态取反
				cart[index].checked = !cart[index].checked;
		
				this.setCart(cart);
		
			},
			// 商品全选功能
			handleItemAllCheck() {
				// 1 获取data中的数据
				let {
					cart,
					allChecked
				} = this.$data;
				// 2 修改值
				allChecked = !allChecked;
				// 3 循环修改cart数组 中的商品选中状态
				cart.forEach(v => v.checked = allChecked);
				// 4 把修改后的值 填充回data或者缓存中
				this.setCart(cart);
			},
			// 商品数量的编辑功能
			async handleItemNumEdit(e) {
				// 1 获取传递过来的参数 
				const { operation, id } = e;
				// 2 获取购物车数组
				let { cart } = this.$data;
				// 3 找到需要修改的商品的索引
				const index = cart.findIndex(v => v.goods_id === id);
				// 4 判断是否要执行删除
				if (cart[index].num === 1 && operation === -1) {
					// 4.1 弹窗提示
					const res = await showModal({
						content: "您是否要删除？"
					});
					if (res.confirm) {
						cart.splice(index, 1);
						this.setCart(cart);
					}
				} else {
					// 4  进行修改数量
					cart[index].num += operation;
					// 5 设置回缓存和data中
					this.setCart(cart);
				}
			},
			// 点击 结算 
			async handlePay(){
				// 1 判断收货地址
				const {address,totalNum}=this.$data;
				if(!address.userName){
					await showToast({title:"您还没有选择收货地址"});
					return;
				}
				// 2 判断用户有没有选购商品
				if(totalNum===0){
					await showToast({title:"您还没有选购商品"});
					return ;
				}
				// 3 跳转到 支付页面
				uni.navigateTo({
					url: '/pages/pay/pay'
				});
					
			}
		},
		onShow() {
			// 1 获取缓存中的收货地址信息
			this.address = uni.getStorageSync("address");
			// 1 获取缓存中的购物车数据
			let cart = uni.getStorageSync("cart") || [];
			this.setCart(cart);
		},
		filters:{
			priceFilter(e){
				return e.toFixed(2);
			}
		}
	}
</script>

<style>
	@import url("./cart.css");
</style>
