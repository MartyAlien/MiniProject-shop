<template>
	<view>
		<view class="detail_swiper">
			<swiper autoplay circular indicator-dots>
				<swiper-item v-for="(item,index) in goodsObj.pics" :key="item.pics_id"
					@click="handlePrevewImage(item.pics_mid)">
					<image mode="widthFix" :src="item.pics_mid"></image>
				</swiper-item>
			</swiper>
		</view>

		<view class="goods_price">￥{{goodsObj.goods_price}}</view>
		<view class="goods_name_row">
			<view class="goods_name">{{goodsObj.goods_name}}{{goodsObj.goods_name}}</view>
			<view class="goods_collect" @click="handleCollect">
				<text :class="{'iconfont':true,'icon-shoucang1': isCollect,'icon-shoucang': !isCollect}"></text>
				<view class="collect_text">{{isCollect?'已收藏':'收藏'}}</view>
			</view>
		</view>

		<view class="goods_info">
			<view class="goods_info_title">图文详情</view>
			<view class="goods_info_content">
				<!-- 富文本 -->
				<rich-text :nodes="goodsObj.goods_introduce"></rich-text>
			</view>
		</view>


		<view class="btm_tool">
			<view class="tool_item">
				<view class="iconfont icon-kefu"></view>
				<view>客服</view>
				<button open-type="contact"></button>
			</view>
			<view class="tool_item">
				<view class="iconfont icon-yixianshi-"></view>
				<view>分享</view>
				<button open-type="share"></button>
			</view>
			<navigator open-type="switchTab" url="/pages/cart/cart" class="tool_item">
				<view class="iconfont icon-gouwuche"></view>
				<view>购物车</view>
			</navigator>
			<view class="tool_item btn_cart " @click="handleCartAdd">
				<uni-badge :text="num" type="error" size="small">2</uni-badge>加入购物车
			</view>
			<view class="tool_item btn_buy">
				立即购买
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				goodsObj: {
					goods_name: "",
					goods_price: "",
					goods_introduce: '',
					pics: [],
				},
				// 商品是否被收藏
				isCollect: false,
				// 商品对象
				GoodsInfo: {},
				num:0
			}
		},
		methods: {
			// 获取商品详情数据
			async getGoodsDetail(goods_id) {
				const res = await this.$api({
					url: "/goods/detail",
					data: {
						goods_id
					}
				});
				this.GoodsInfo = res.data.message;
				// 1 获取缓存中的商品收藏的数组
				let collect = uni.getStorageSync("collect") || [];
				// 2 判断当前商品是否被收藏
				this.isCollect = collect.some(v => v.goods_id === this.GoodsInfo.goods_id);
				this.goodsObj = res.data.message;
				this.goodsObj.goods_introduce = res.data.message.goods_introduce.replace(/\.webp/g, '.jpg');
				console.log(this.goodsObj);
			},
			// 点击轮播图 放大预览
			handlePrevewImage(current) {
				// 1 先构造要预览的图片数组 
				const urls = this.GoodsInfo.pics.map(v => v.pics_mid);
				// 2 接收传递过来的图片url
				uni.previewImage({
					current,
					urls
				});
			},
			// 点击 加入购物车
			handleCartAdd() {
				// 1 获取缓存中的购物车 数组
				let cart = uni.getStorageSync("cart") || [];
				// 2 判断 商品对象是否存在于购物车数组中
				let index = cart.findIndex(v => v.goods_id === this.GoodsInfo.goods_id);
				if (index === -1) {
					//3  不存在 第一次添加
					this.GoodsInfo.num = 1;
					this.GoodsInfo.checked = true;
					cart.push(this.GoodsInfo);
					this.num=this.GoodsInfo.num;
				} else {
					// 4 已经存在购物车数据 执行 num++
					cart[index].num++;
					this.num=cart[index].num;
				}
				// 5 把购物车重新添加回缓存中
				uni.setStorageSync("cart", cart);
				// 6 弹窗提示
				uni.showToast({
					title: '加入成功',
					icon: 'success',
					// true 防止用户 手抖 疯狂点击按钮 
					mask: true
				});
			},
			// 点击 商品收藏图标
			handleCollect() {
				let isCollect = false;
				// 1 获取缓存中的商品收藏数组
				let collect = uni.getStorageSync("collect") || [];
				// 2 判断该商品是否被收藏过
				let index = collect.findIndex(v => v.goods_id === this.GoodsInfo.goods_id);
				// 3 当index！=-1表示 已经收藏过 
				if (index !== -1) {
					// 能找到 已经收藏过了  在数组中删除该商品
					collect.splice(index, 1);
					isCollect = false;
					uni.showToast({
						title: '取消收藏',
						icon: 'success',
						mask: true
					});

				} else {
					// 没有收藏过
					collect.push(this.GoodsInfo);
					isCollect = true;
					uni.showToast({
						title: '收藏成功',
						icon: 'success',
						mask: true
					});
				}
				// 4 把数组存入到缓存中
				uni.setStorageSync("collect", collect);
				// 5 修改data中的属性  isCollect
				this.isCollect=isCollect;
			}

		},
		onLoad(options) {
			
		},
		onShow: function() {
			let pages = getCurrentPages();
			let currentPage = pages[pages.length - 1];
			let options = currentPage.options;
			const {
				goods_id
			} = options;
			//console.log(pages,currentPage,options,goods_id);
			this.getGoodsDetail(goods_id);
			// 1 获取缓存中的购物车 数组
			let cart = uni.getStorageSync("cart") || [];
			let index = cart.findIndex(v => v.goods_id == goods_id);
			this.num=index!==-1?cart[index].num:0;
		}
	}
</script>

<style>
	@import url("./goods_detail.css");
</style>
